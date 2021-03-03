package com.example.newsapp;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class FirstFragment extends Fragment {


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View initialView = inflater.inflate(R.layout.fragment_first, container, false);


        final ListView listView = (ListView) initialView.findViewById(R.id.FragmentOneList);
        EarthquakeDataService earthquakeDataService = new EarthquakeDataService(getContext());
        earthquakeDataService.getArrayData(new EarthquakeDataService.VolleyResponseListener() {
            @Override
            public void onError(String message) {
                Toast.makeText(getContext(),"ERROR MESSAGE",Toast.LENGTH_LONG).show();
            }

            @Override
            public void onResponse(ArrayList<Earthquake> response) {
                CustomAdapter adapter = new CustomAdapter(getContext(),response);
                listView.setAdapter(adapter);

            }
        });


        return initialView;
    }
}