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
 * Created by bearg on 6/6/2016.
 */
public class ForecastAdapter extends RecyclerView.Adapter<ForecastAdapter.ViewHolder> {

    private List<Forecastday> forecastDays = new ArrayList<>();

    public ForecastAdapter(List<Forecastday> forecastDays) {
        this.forecastDays = forecastDays;
    }

    public void setForecastDays(List<Forecastday> forecastDays) {
        this.forecastDays.clear();
        this.forecastDays.addAll(forecastDays);
        notifyDataSetChanged();
    }

    public void addForecastDay(Forecastday day) {
        forecastDays.add(day);
        notifyItemInserted(forecastDays.size()- 1);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public final TextView dayOrNightTv;
        public final TextView forecastTv;
        public final ImageView iconView;

        public ViewHolder(View itemView) {
            super(itemView);
            dayOrNightTv = (TextView) itemView.findViewById(R.id.day_or_night);
            forecastTv = (TextView) itemView.findViewById(R.id.forecast_text);
            iconView = (ImageView) itemView.findViewById(R.id.weather_icon);

        }
    }

    @Override
    public ViewHolder onCreateViewHolder(final ViewGroup parent, final int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.forecast_row, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        holder.dayOrNightTv.setText(forecastDays.get(position).title);
        holder.forecastTv.setText(forecastDays.get(position).fcttext);

        Context ctx = holder.iconView.getContext();
        // icon url is http://icons.wxug.com/i/c/?/ICON.gif where ? is a-k. changing the
        // letter changes the icon set that displays
        String iconUrl = forecastDays.get(position).iconUrl.replaceFirst("k", "j");

        Picasso.with(ctx).load(iconUrl).resize(150, 150).error(android.R.drawable.ic_dialog_alert).into(holder.iconView);
    }

    @Override
    public int getItemCount() {
        return forecastDays.size();
    }
}
