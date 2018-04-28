package com.arifinfrds.ppp_kelompok.ui;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.arifinfrds.ppp_kelompok.R;
import com.arifinfrds.ppp_kelompok.model.pojo.WeatherCondition;
import com.arifinfrds.ppp_kelompok.model.pojo.WeatherList;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by arifinfrds on 4/28/18.
 */

// Menurut https://www.raywenderlich.com/109843/common-design-patterns-for-android
// - Adapter masuk di Structural Pattern
public class WeatherAdapter extends RecyclerView.Adapter<WeatherAdapter.ViewHolder> {

    private ArrayList<WeatherList> items;

    public WeatherAdapter(ArrayList<WeatherList> items) {
        this.items = items;

    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_weather, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        WeatherList item = items.get(position);

        String iconId = item.getWeather().get(0).getIcon();
        String iconURL = "http://openweathermap.org/img/w/" + iconId + ".png";

        Picasso.get().load(iconURL).into(holder.thumbnailImageView);
        Picasso.get().load(WeatherCondition.getWeatherIconUrl(iconId)).into(holder.contentImageView);

        holder.titleTextView.setText(item.getWeather().get(0).getMain());
        holder.subtitleTextView.setText(item.getWeather().get(0).getDescription());

        holder.descriptionTextView.setText("it's " + item.getMain().getTemp() + " degree Farenheit");
        holder.timeTextView.setText(item.getDtTxt());
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        // MARK: - Public Properties
        public CardView cardView;
        public ImageView contentImageView;
        public TextView titleTextView;
        public TextView subtitleTextView;
        public TextView descriptionTextView;
        public TextView timeTextView;
        public ImageView thumbnailImageView;


        // MARK: Init
        public ViewHolder(View itemView) {
            super(itemView);
            cardView = itemView.findViewById(R.id.cardView);
            contentImageView = itemView.findViewById(R.id.contentImageView);
            titleTextView = itemView.findViewById(R.id.titleTextView);
            subtitleTextView = itemView.findViewById(R.id.subtitleTextView);
            descriptionTextView = itemView.findViewById(R.id.descriptionTextView);
            timeTextView = itemView.findViewById(R.id.timeTextView);
            thumbnailImageView = itemView.findViewById(R.id.thumbnailImageView);
        }
    }
}
