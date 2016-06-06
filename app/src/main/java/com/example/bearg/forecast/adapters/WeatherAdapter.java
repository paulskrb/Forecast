package com.example.bearg.forecast.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.bearg.forecast.R;
import com.example.bearg.forecast.model.currentconditions.CurrentObservation;
import com.example.bearg.forecast.model.threedayforecast.Forecastday;

import org.w3c.dom.Text;

import java.util.ArrayList;

/**
 * Created by bearg on 6/6/2016.
 */
public class WeatherAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private static final int TYPE_CONDITIONS = 1;
    private static final int TYPE_FORECAST = 2;

    private ArrayList<Forecastday> forecastDays;
    private CurrentObservation observation;

    public WeatherAdapter(ArrayList<Forecastday> forecastDays) {
        this.forecastDays = forecastDays;
    }

    public WeatherAdapter(CurrentObservation observation) {
        this.observation = observation;
    }



    public class ConditionsHolder extends RecyclerView.ViewHolder {

        public final TextView weatherTv;
        public final TextView tempTv;

        public ConditionsHolder(View itemView) {
            super(itemView);
            weatherTv = (TextView) itemView.findViewById(R.id.weather);
            tempTv = (TextView) itemView.findViewById(R.id.temp);

        }
    }

    public class ForecastHolder extends RecyclerView.ViewHolder {

        public final TextView dayOrNightTv;
        public final TextView forecastTextTv;

        public ForecastHolder(View itemView) {
            super(itemView);
            dayOrNightTv = (TextView) itemView.findViewById(R.id.day_or_night);
            forecastTextTv = (TextView) itemView.findViewById(R.id.forecast_text);
        }
    }

    @Override
    public int getItemViewType(int position) {
        return position == 0? TYPE_CONDITIONS : TYPE_FORECAST;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        RecyclerView.ViewHolder vh;
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());

        switch (viewType) {
            case TYPE_CONDITIONS:
                View v1 = inflater.inflate(R.layout.current_conditions_row, parent, false);
                vh = new ConditionsHolder(v1);
                break;

            case TYPE_FORECAST:
                View v2 = inflater.inflate(R.layout.forecast_row, parent, false);
                vh = new ForecastHolder(v2);
                break;

            default:
                throw new IllegalStateException("Neither conditions nor forecast view chosen");
        }

        return vh;
    }

    // configures the ViewHolder with the actual data that needs to be displayed
    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (position == 0) {



        }

    }

    @Override
    public int getItemCount() {
        return 0;
    }
}
