package com.example.newsapp;

import android.content.Context;
import android.util.Log;
import android.widget.ListView;
import android.widget.Toast;

import androidx.core.content.ContextCompat;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class EarthquakeDataService {
    public static final String GET_EARTHQUAKE_DATA = "https://api.orhanaydogdu.com.tr/deprem/live.php?limit=30";
    Context context;


    public interface VolleyResponseListener {

        void onError(String message);

        void onResponse(ArrayList<Earthquake> response);
    }

    public EarthquakeDataService(Context context) {
        this.context = context;
    }

    public void getArrayData(final VolleyResponseListener volleyResponseListener) {


        String url = GET_EARTHQUAKE_DATA;

        final ArrayList<Earthquake> arrayList = new ArrayList<>();
        
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    JSONArray featuresArray = response.getJSONArray("result");

                    for (int i = 0; i < featuresArray.length(); i++) {

                        JSONObject currentEarthquake = featuresArray.getJSONObject(i);
                        double magnitude = currentEarthquake.getDouble("mag");
                        String location = currentEarthquake.getString("lokasyon");
                        String date = currentEarthquake.getString("date");

                        arrayList.add(new Earthquake(location,magnitude,date));
                        Log.i("MainActivity", "Array Check");

                        volleyResponseListener.onResponse(arrayList);

                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.v("MainActivity", "ERROR");
                volleyResponseListener.onError("error message from volleyresponse");
            }
        });
        MySingleton.getInstance(context).addToRequestQueue(jsonObjectRequest);

        }

    public interface SecondFragmentVolleyResponseListener {

        void onError(String message);

        void onResponse(Earthquake  response);
    }
        public void getStringURL(final SecondFragmentVolleyResponseListener SecondFragmentVolleyResponseListener){

        String url = GET_EARTHQUAKE_DATA;

            JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject response) {
                    try {
                        JSONArray featuresArray = response.getJSONArray("result");
                        JSONObject currentEarthquake = featuresArray.getJSONObject(0);
                        double magnitude = currentEarthquake.getDouble("mag");
                        String location = currentEarthquake.getString("lokasyon");
                        double longitude = currentEarthquake.getDouble("lng");
                        double latitude = currentEarthquake.getDouble("lat");
                        double depth = currentEarthquake.getDouble("depth");
                        Earthquake last = new Earthquake(location,magnitude,longitude,latitude,depth);

                        SecondFragmentVolleyResponseListener.onResponse(last);

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Log.v("MainActivity", "ERROR");
                    SecondFragmentVolleyResponseListener.onError("error message from volleyresponse");
                }
            });
            MySingleton.getInstance(context).addToRequestQueue(jsonObjectRequest);


        }



}
