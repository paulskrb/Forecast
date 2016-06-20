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
import com.squareup.picasso.Picasso;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
    private TextView windSpeedTv;
    private TextView relativeHumidityTv;
    private TextView dewPointTv;
    private TextView uvTv;
    private TextView pressureTv;
    private TextView feelsLikeTv;

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

        // initialize views
        currentWeatherIcon = (ImageView) view.findViewById(R.id.current_weather_icon);
        updatedTimeTv = (TextView) view.findViewById(R.id.updated);
        currentWeatherTv = (TextView) view.findViewById(R.id.current_weather);
        tempTv = (TextView) view.findViewById(R.id.temp);
        windSpeedTv = (TextView) view.findViewById(R.id.wind_speed);
        relativeHumidityTv = (TextView) view.findViewById(R.id.rel_humidity);
        dewPointTv = (TextView) view.findViewById(R.id.dewpoint);
        uvTv = (TextView) view.findViewById(R.id.uv_index);
        pressureTv = (TextView) view.findViewById(R.id.pressure);
        feelsLikeTv = (TextView) view.findViewById(R.id.feelsLikeF);

        swipeRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.conditions_refresh);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                loadConditions();
                swipeRefreshLayout.setRefreshing(false);
            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();
        loadConditions();
    }

    private void loadConditions() {

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
                        loadConditions(currentConditions);
                    }
                });

    }

    private void loadConditions(CurrentConditions currentConditions) {
        final CurrentObservation currentObservation = currentConditions.currentObservation;
        String iconUrl = currentObservation.iconUrl;
        Picasso.with(getContext()).load(iconUrl).resize(150, 150)
                .error(android.R.drawable.ic_dialog_alert).into(currentWeatherIcon);

        String observationTime = currentObservation.observationTime;

        // will match ?X:XX PM/AM, where ? means an optional first digit
        String regex = "\\d?\\d:\\d{2}\\s[AP]M";

        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(observationTime);
        if (matcher.find()) {
            // matcher.group(0) gets the matched portion, ?X:XX PM/AM in this case
            updatedTimeTv.setText(
                    String.format("Updated %s", matcher.group(0)));
        }

        else {
            updatedTimeTv.setText("Update Time Unknown");
        }

        currentWeatherTv.setText(currentObservation.weather);

        // U+00B0 is the Unicode code point for the degree symbol
        String tempF = String.valueOf(Math.round(currentObservation.tempF)) + "\u00b0";
        tempTv.setText(tempF);

        String windSpeed = String.valueOf(Math.round(currentObservation.windMph)) + " mph";
        windSpeedTv.setText(windSpeed);

        relativeHumidityTv.setText(currentObservation.relativeHumidity);

        String dewpointF = String.valueOf(Math.round(currentObservation.dewpointF)) + "\u00b0";
        dewPointTv.setText(dewpointF);

        // convert uv from a string like "2.0" to just "2". double backslash is needed to escape
        // the period, because period has special meaning in a regex
        String uv = currentObservation.uV.split("\\.")[0];
        uvTv.setText(uv);

        pressureTv.setText(currentObservation.pressureIn);

        String feelsLikeF = currentObservation.feelslikeF + "\u00b0";
        feelsLikeTv.setText(feelsLikeF);

    }
}
