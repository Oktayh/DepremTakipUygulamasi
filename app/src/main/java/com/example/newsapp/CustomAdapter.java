package com.example.newsapp;

import android.content.Context;
import android.graphics.drawable.GradientDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class CustomAdapter extends ArrayAdapter<Earthquake> {
    private final LayoutInflater inflater;
    private final Context context;
    private   TextView location;
    private  TextView EarthquakeDate;
    private  TextView magnitude;
    private  ArrayList<Earthquake> earthquakes;


    public CustomAdapter(@NonNull Context context , @NonNull ArrayList<Earthquake> objects) {
        super(context, 0, objects);
        earthquakes = objects;
        this.context = context;
        inflater = LayoutInflater.from(context);
    }
    @Override
    public int getCount() {
        return earthquakes.size();
    }

    @Override
    public Earthquake getItem(int position) {
        return earthquakes.get(position);
    }

    @Override
    public long getItemId(int position) {
        return earthquakes.get(position).hashCode();
    }


    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        if (convertView == null){  convertView = inflater.inflate(R.layout.list_item, null);

            location = (TextView)  convertView.findViewById(R.id.location);
            magnitude = (TextView) convertView.findViewById(R.id.magnitude);
            EarthquakeDate = (TextView) convertView.findViewById(R.id.date);
        }
        Earthquake currentEarthquake = getItem(position) ;
        if (currentEarthquake != null){
        location.setText(currentEarthquake.getLocation());
        magnitude.setText(String.valueOf(currentEarthquake.getMagnitude()));

        GradientDrawable magnitudeCircle = (GradientDrawable) magnitude.getBackground();
        int magnitudeColor = getMagnitudeColor(currentEarthquake.getMagnitude());
        magnitudeCircle.setColor(magnitudeColor);

        System.out.println(currentEarthquake.getmDate());

        TextView dateView = (TextView) convertView.findViewById(R.id.date);

        dateView.setText(currentEarthquake.getmDate());

        }


        return convertView;
    }

    private int getMagnitudeColor(double magnitude) {
        int magnitudeColorResourceId;
        int magnitudeFloor = (int) Math.floor(magnitude);
        switch (magnitudeFloor) {
            case 0:
            case 1:
                magnitudeColorResourceId = R.color.magnitude1;
                break;
            case 2:
                magnitudeColorResourceId = R.color.magnitude2;
                break;
            case 3:
                magnitudeColorResourceId = R.color.magnitude3;
                break;
            case 4:
                magnitudeColorResourceId = R.color.magnitude4;
                break;
            case 5:
                magnitudeColorResourceId = R.color.magnitude5;
                break;
            case 6:
                magnitudeColorResourceId = R.color.magnitude6;
                break;
            case 7:
                magnitudeColorResourceId = R.color.magnitude7;
                break;
            case 8:
                magnitudeColorResourceId = R.color.magnitude8;
                break;
            case 9:
                magnitudeColorResourceId = R.color.magnitude9;
                break;
            default:
                magnitudeColorResourceId = R.color.magnitude10plus;
                break;
        }

        return ContextCompat.getColor(getContext(), magnitudeColorResourceId);
    }
    
}
