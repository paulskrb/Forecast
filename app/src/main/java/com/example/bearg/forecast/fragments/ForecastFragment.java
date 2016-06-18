package com.example.bearg.forecast.fragments;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.bearg.forecast.R;
import com.example.bearg.forecast.adapters.ForecastAdapter;
import com.example.bearg.forecast.interfaces.WeatherApiManager;
import com.example.bearg.forecast.model.threedayforecast.Forecastday;
import com.example.bearg.forecast.model.threedayforecast.ThreeDayForecast;

import java.util.ArrayList;
import java.util.List;

import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * A simple {@link Fragment} subclass.
 */
public class ForecastFragment extends Fragment {

    private RecyclerView conditionsAndForecastRecycler;
    private SwipeRefreshLayout swipeRefreshLayout;
    private List<Forecastday> forecastdays = new ArrayList<>();
    private ForecastAdapter forecastAdapter;

    public ForecastFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_forecast, container, false);
    }

    @Override
    public void onViewCreated(final View view, @Nullable final Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        // Get the SwipeRefreshLayout View from the inflated layout file
        swipeRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.refresh_layout);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                // currently, we just cancel the loading animation when the user refreshes
                swipeRefreshLayout.setRefreshing(false);
            }
        });

        conditionsAndForecastRecycler = (RecyclerView) view.findViewById(R.id.recycler);
        conditionsAndForecastRecycler.setLayoutManager(new LinearLayoutManager(getContext()));
        conditionsAndForecastRecycler.setHasFixedSize(true);
        forecastAdapter = new ForecastAdapter(forecastdays);
        conditionsAndForecastRecycler.setAdapter(forecastAdapter);
    }

    @Override
    public void onResume() {
        super.onResume();
        loadForecast();
    }

    private void loadForecast() {

        Observable<ThreeDayForecast> forecast = WeatherApiManager.getWeatherService().getForecast("27909");

        forecast.flatMap(threeDayForecast -> Observable.just(threeDayForecast.forecast.txtForecast.forecastday))
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<List<Forecastday>>() {
                    @Override
                    public void onCompleted() {
                        Toast.makeText(getContext(), "Weather data updated", Toast.LENGTH_SHORT).show();
                    }
                    @Override
                    public void onError(final Throwable e) {
                        e.printStackTrace();
                        Toast.makeText
                                (getContext(), "Couldn't retrieve weather data", Toast.LENGTH_LONG)
                                .show();
                    }

                    @Override
                    public void onNext(final List<Forecastday> forecastDays) {
                        forecastAdapter.setForecastDays(forecastDays);
                    }
                });
    }
}
