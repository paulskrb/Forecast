package com.example.bearg.forecast;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

import com.example.bearg.forecast.adapters.WeatherAdapter;
import com.example.bearg.forecast.interfaces.WeatherService;
import com.example.bearg.forecast.model.currentconditions.CurrentObservation;
import com.example.bearg.forecast.model.threedayforecast.Forecastday;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;
import java.util.Arrays;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private static final String WEATHER_BASE_URL =
            "http://api.wunderground.com/api/3c39584cb3cf6c8f/";

    private RecyclerView conditionsAndForecastRecycler;
    private ArrayList<Forecastday> forecastDays;
    private CurrentObservation observation;
    private WeatherAdapter weatherAdapter;

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

    }

    private void loadJSON() {

        Gson gson = new GsonBuilder()
                .registerTypeAdapter(CurrentObservation.class, new CurrentObservationDeserializer())
                .create();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(WEATHER_BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .client(getClient())
                .build();

        WeatherService service = retrofit.create(WeatherService.class);
        Call<CurrentObservation> call = service.getObservation("27909");

        call.enqueue(new Callback<CurrentObservation>() {
            @Override
            public void onResponse(Call<CurrentObservation> call, Response<CurrentObservation> response) {

                observation = response.body();
                WeatherAdapter adapter = new WeatherAdapter(observation);
                conditionsAndForecastRecycler.setAdapter(adapter);

            }

            @Override
            public void onFailure(Call<CurrentObservation> call, Throwable t) {
                Log.d("Error: ", t.getMessage());
            }
        });

        Call<JSONResponse> forecastCall = service.getForecast("27909");

        forecastCall.enqueue(new Callback<JSONResponse>() {
            @Override
            public void onResponse(Call<JSONResponse> call, Response<JSONResponse> response) {

                JSONResponse jsr = response.body();
                forecastDays = new ArrayList<>(Arrays.asList(jsr.getTextForecasts()));
                weatherAdapter = new WeatherAdapter(forecastDays);
                conditionsAndForecastRecycler.setAdapter(weatherAdapter);
            }

            @Override
            public void onFailure(Call<JSONResponse> call, Throwable t) {
                Log.d("Error: ", t.getMessage());
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
