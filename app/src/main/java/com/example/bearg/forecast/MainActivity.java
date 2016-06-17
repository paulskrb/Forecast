package com.example.bearg.forecast;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.bearg.forecast.adapters.ForecastAdapter;
import com.example.bearg.forecast.interfaces.WeatherService;
import com.example.bearg.forecast.model.threedayforecast.Forecast;
import com.example.bearg.forecast.model.threedayforecast.Forecastday;

import com.example.bearg.forecast.model.threedayforecast.ThreeDayForecast;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {

    private static final String WEATHER_BASE_URL =
            "http://api.wunderground.com/api/3c39584cb3cf6c8f/";

    private static final String ICON_BASE_URL =
            "";

    private RecyclerView conditionsAndForecastRecycler;
    private SwipeRefreshLayout swipeRefreshLayout;
    private List<Forecastday> forecastdays = new ArrayList<>();
    private ForecastAdapter forecastAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        swipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.refresh_layout);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                // currently, we just cancel the loading animation when the user refreshes
                swipeRefreshLayout.setRefreshing(false);
            }
        });
        initRecycler();
        loadJSON();

    }

    @NonNull
    private OkHttpClient getClient() {
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor(new HttpLoggingInterceptor.Logger() {
            @Override
            public void log(String message) {
                Log.d("OkHttp: ", message);
            }
        });
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
        httpClient.addInterceptor(logging);
        return httpClient.build();
    }

    private void initRecycler() {
        conditionsAndForecastRecycler = (RecyclerView) findViewById(R.id.recycler);
        conditionsAndForecastRecycler.setLayoutManager(new LinearLayoutManager(this));
        conditionsAndForecastRecycler.setHasFixedSize(true);
        forecastAdapter = new ForecastAdapter(forecastdays);
        conditionsAndForecastRecycler.setAdapter(forecastAdapter);

    }

    private void loadJSON() {

        Type listType = new TypeToken<List<Forecastday>>(){}.getType();

        Gson gson = new GsonBuilder()
                .create();

        Retrofit retrofit = new Retrofit.Builder()
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .baseUrl(WEATHER_BASE_URL)
                .client(getClient())
                .build();

        WeatherService weatherService = retrofit.create(WeatherService.class);
        Observable<ThreeDayForecast> forecast = weatherService.getForecast("27909");

        forecast.flatMap(new Func1<ThreeDayForecast, Observable<List<Forecastday>>>() {
            @Override
            public Observable<List<Forecastday>> call(final ThreeDayForecast threeDayForecast) {
                return Observable.just(threeDayForecast.forecast.txtForecast.forecastday);
            }
        })
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<List<Forecastday>>() {
                    @Override
                    public void onCompleted() {
                        Toast.makeText(MainActivity.this, "Weather data updated", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onError(final Throwable e) {
                        e.printStackTrace();
                        Toast.makeText
                                (MainActivity.this, "Couldn't retrieve weather data", Toast.LENGTH_LONG)
                                .show();
                    }

                    @Override
                    public void onNext(final List<Forecastday> forecastDays) {
                        forecastAdapter.setForecastDays(forecastDays);
                    }
                });



    }




    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
