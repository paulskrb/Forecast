package com.example.bearg.forecast.fragments;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.bearg.forecast.R;
import com.example.bearg.forecast.interfaces.WeatherApiManager;
import com.example.bearg.forecast.model.currentconditions.CurrentConditions;
import com.example.bearg.forecast.model.currentconditions.CurrentObservation;
import com.example.bearg.forecast.model.currentconditions.DisplayLocation;
import com.squareup.picasso.Picasso;

import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * A simple {@link Fragment} subclass.
 */
public class ConditionsFragment extends Fragment {

    private SwipeRefreshLayout swipeRefreshLayout;

    private ImageView currentWeatherIcon;
    private TextView updatedTimeTv;
    private TextView currentWeatherTv;
    private TextView tempTv;

    public ConditionsFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_conditions, container, false);
    }

    @Override
    public void onViewCreated(final View view, @Nullable final Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        currentWeatherIcon = (ImageView) view.findViewById(R.id.current_weather_icon);
        updatedTimeTv = (TextView) view.findViewById(R.id.updated);
        currentWeatherTv = (TextView) view.findViewById(R.id.current_weather);
        tempTv = (TextView) view.findViewById(R.id.temp);

        swipeRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.conditions_refresh);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                // currently, we just cancel the loading animation when the user refreshes
                swipeRefreshLayout.setRefreshing(false);
            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();
        loadForecast();
    }

    private void loadForecast() {

        Observable<CurrentConditions> observation =
                WeatherApiManager.getWeatherService().getObservation("27909");

        observation.subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<CurrentConditions>() {
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
                    public void onNext(final CurrentConditions currentConditions) {

                        String iconUrl = currentConditions.currentObservation.iconUrl;
                        Picasso.with(getContext()).load(iconUrl).resize(150, 150)
                                .error(android.R.drawable.ic_dialog_alert).into(currentWeatherIcon);

                        updatedTimeTv.setText(currentConditions.currentObservation.observationTime);
                        currentWeatherTv.setText(currentConditions.currentObservation.weather);
                        tempTv.setText(String.valueOf(Math.round(currentConditions.currentObservation.tempF)));
                    }
                });

    }
}
