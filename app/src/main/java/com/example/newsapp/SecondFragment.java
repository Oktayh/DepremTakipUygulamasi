package com.example.newsapp;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.os.SystemClock;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

public class SecondFragment extends Fragment {
    TextView placeButton;
    TextView mMagnitude;
    TextView mDepth;
    String lag;
    String lng;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View secondView =  inflater.inflate(R.layout.fragment_second, container, false);

        placeButton = (TextView) secondView.findViewById(R.id.place);
        mMagnitude = (TextView) secondView.findViewById(R.id.perceived_magnitude);
        mDepth = (TextView) secondView.findViewById(R.id.depth);

        EarthquakeDataService earthquakeDataService = new EarthquakeDataService(getContext());
        earthquakeDataService.getStringURL(new EarthquakeDataService.SecondFragmentVolleyResponseListener() {
            @Override
            public void onError(String message) {
                Toast.makeText(getContext(),"ERROR MESSAGE",Toast.LENGTH_LONG).show();
            }

            @Override
            public void onResponse(Earthquake response) {
                    placeButton.setText(response.getLocation());
                    mMagnitude.setText(String.valueOf(response.getMagnitude()));
                    mDepth.setText(String.valueOf(response.getDepth()));
                    lag = String.valueOf(response.getLatitude());
                    lng = String.valueOf(response.getLongitude());

            }
        });


        mMagnitude.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String geo = "geo:"+lag+","+lng;
                Uri gmmIntentUri = Uri.parse(geo);
                Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
                mapIntent.setPackage("com.google.android.apps.maps");
                startActivity(mapIntent);
            }
        });

        return secondView;
    }
}