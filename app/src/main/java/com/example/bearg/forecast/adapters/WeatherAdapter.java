package com.example.bearg.forecast.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.bearg.forecast.R;
import com.example.bearg.forecast.model.threedayforecast.Forecastday;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by bearg on 6/23/2016.
 */
public class WeatherAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private static final int TYPE_CONDITIONS = 0;
    private static final int TYPE_FORECAST = 1;

    private List<Forecastday> forecastDays = new ArrayList<>();

    public void setForecastDays(final List<Forecastday> forecastDays) {
        this.forecastDays = forecastDays;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(final ViewGroup parent, final int viewType) {
        if (viewType == TYPE_CONDITIONS) {
            return null;
        }

        else if (viewType == TYPE_FORECAST) {
            View forecast = LayoutInflater.from(parent.getContext()).inflate(R.layout.forecast_row, parent, false);
            return new ForecastViewHolder(forecast);
        }

        else throw new RuntimeException("Check program logic. View type was neither conditions nor forecast");
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, final int position) {

        if (holder instanceof ConditionsViewHolder) {
            bindConditions();
        }

        else if (holder instanceof ForecastViewHolder) {
            bindForecast((ForecastViewHolder) holder, position);
        }

        else throw new RuntimeException("Check program logic. Type of ViewHolder was neither conditions nor forecast");
    }

    private void bindConditions() {

    }

    @Override
    public int getItemCount() {
        return forecastDays.size() + 1;
    }

    @Override
    public int getItemViewType(final int position) {
        return position == 0? TYPE_CONDITIONS : TYPE_FORECAST;
    }

    public class ForecastViewHolder extends RecyclerView.ViewHolder {

        public final TextView dayOrNightTv;
        public final TextView forecastTv;
        public final ImageView iconView;

        public ForecastViewHolder(final View itemView) {
            super(itemView);
            dayOrNightTv = (TextView) itemView.findViewById(R.id.day_or_night);
            forecastTv = (TextView) itemView.findViewById(R.id.forecast_text);
            iconView = (ImageView) itemView.findViewById(R.id.weather_icon);
        }
    }

    private void bindForecast(ForecastViewHolder holder, int position) {

        holder.dayOrNightTv.setText(forecastDays.get(position).title);
        holder.forecastTv.setText(forecastDays.get(position).fcttext);

        Context ctx = holder.iconView.getContext();
        // icon url is http://icons.wxug.com/i/c/?/ICON.gif where ? is a-k. changing the
        // letter changes the icon set that displays
        String iconUrl = forecastDays.get(position).iconUrl.replaceFirst("k", "j");

        Picasso.with(ctx).load(iconUrl).resize(150, 150).error(android.R.drawable.ic_dialog_alert).into(holder.iconView);
    }

    public class ConditionsViewHolder extends RecyclerView.ViewHolder {
        public ConditionsViewHolder(final View itemView) {
            super(itemView);
        }
    }


}
