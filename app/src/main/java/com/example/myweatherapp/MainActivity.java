package com.example.myweatherapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {

    private static final String API = "1686d0519d95e56f8d0006812990bc78";
    TextView txtView;

    EditText search;

    Button btn_Search;

    Button btn_CurrentLocation;

    TextView txtChuck;

    Button btn_Chuck;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtView = (TextView) findViewById(R.id.weather_txt);

        search = (EditText) findViewById(R.id.search_txt);
        btn_Search = (Button) findViewById(R.id.btnSearch);

        btn_CurrentLocation = (Button) findViewById(R.id.btnCureent);

        txtChuck = (TextView) findViewById(R.id.chuckText);

        btn_Chuck = (Button) findViewById(R.id.btn_chuck);


        btn_Search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String search_s = search.getText().toString();
                onAPI(search_s);

            }


        });
        btn_CurrentLocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String search_s = search.getText().toString();
                onLocation(search_s);

            }


        });

        }
    private void onAPI(String city) {
        // Instantiate the RequestQueue
        RequestQueue queue = Volley.newRequestQueue(this);
        String url = String.format("https://api.openweathermap.org/data/2.5/weather?q=" + city + ",uk&units=metric&APPID=" + API);
        //String url = String.format("https://api.openweathermap.org/data/2.5/weather?q=" + "Belfast" + ",uk&units=metric&APPID=" + API);

        // Request a string response from the provided URL.
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {

                    @Override
                    public void onResponse(String response) {
                        // Display the first 500 characters of the response string.
                        txtView.setText("Temp is: " + response.substring(0));
                    }
                }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                txtView.setText("That didn't work!");
            }
        });

        //Add the request to the RequestQueue.
        queue.add(stringRequest);

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    JSONObject main = response.getJSONObject("main");
                    String temp = String.valueOf(main.getDouble("temp"));
                    String temp_max = String.valueOf(main.getDouble("temp_max"));
                    String temp_min = String.valueOf(main.getDouble("temp_min"));
                    String pressure = String.valueOf(main.getInt("pressure"));
                    String humidity = String.valueOf(main.getInt("humidity"));

                    JSONArray weather = response.getJSONArray("weather");
                    JSONObject weather2 = weather.getJSONObject(0);
                    String description = String.valueOf(weather2.getString("description"));

                    JSONObject wind = response.getJSONObject("wind");
                    String speed = String.valueOf(wind.getDouble("speed"));


                    txtView.setText(" Current Temperature: " + temp + "°C" + ",\n Maximum temperature: " + temp_max + "°C"
                            + ",\n Minimum temperature: " + temp_min + "°C" + ",\n Pressure: " + pressure + "Pa" + ",\n Humidity: "
                            + humidity + "%" + ",\n Conditions: " + description + ",\n Wind Speed: " + speed + "m/s");


                    Toast.makeText(MainActivity.this, main.toString(), Toast.LENGTH_SHORT).show();
                } catch (JSONException e) {
                    throw new RuntimeException(e);
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(MainActivity.this, "Error", Toast.LENGTH_SHORT).show();
            }
        });
        queue.add(request);


    }

    private void onLocation (String location){
        RequestQueue queue = Volley.newRequestQueue(this);

        String url = String.format("https://api.openweathermap.org/data/2.5/weather?q=" + "Londonderry" + ",uk&units=metric&APPID=" + API);


        // Request a string response from the provided URL.
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {

                    @Override
                    public void onResponse(String response) {
                        // Display the first 500 characters of the response string.
                        txtView.setText("Temp is: " + response.substring(0));
                    }
                }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                txtView.setText("That didn't work!");
            }
        });

        //Add the request to the RequestQueue.
        queue.add(stringRequest);

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    JSONObject main = response.getJSONObject("main");
                    String temp = String.valueOf(main.getDouble("temp"));
                    String temp_max = String.valueOf(main.getDouble("temp_max"));
                    String temp_min = String.valueOf(main.getDouble("temp_min"));
                    String pressure = String.valueOf(main.getInt("pressure"));
                    String humidity = String.valueOf(main.getInt("humidity"));

                    JSONArray weather = response.getJSONArray("weather");
                    JSONObject weather2 = weather.getJSONObject(0);
                    String description = String.valueOf(weather2.getString("description"));

                    JSONObject wind = response.getJSONObject("wind");
                    String speed = String.valueOf(wind.getDouble("speed"));


                    txtView.setText(" Current Temperature: " + temp + "°C" + ",\n Maximum temperature: " + temp_max + "°C"
                            + ",\n Minimum temperature: " + temp_min + "°C" + ",\n Pressure: " + pressure + "Pa" + ",\n Humidity: "
                            + humidity + "%" + ",\n Conditions: " + description + ",\n Wind Speed: " + speed + "m/s");


                    Toast.makeText(MainActivity.this, main.toString(), Toast.LENGTH_SHORT).show();
                } catch (JSONException e) {
                    throw new RuntimeException(e);
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(MainActivity.this, "Error", Toast.LENGTH_SHORT).show();
            }
        });
        queue.add(request);


    }



    }

