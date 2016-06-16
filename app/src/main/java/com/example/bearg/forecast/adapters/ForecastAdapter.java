package com.example.bearg.forecast.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.bearg.forecast.R;
import com.example.bearg.forecast.model.threedayforecast.Forecastday;

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

        public ViewHolder(View itemView) {
            super(itemView);
            dayOrNightTv = (TextView) itemView.findViewById(R.id.day_or_night);
            forecastTv = (TextView) itemView.findViewById(R.id.forecast_text);

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
    }

    @Override
    public int getItemCount() {
        return forecastDays.size();
    }
}
