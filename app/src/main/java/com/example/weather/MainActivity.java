package com.example.weather;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


public class MainActivity extends AppCompatActivity {
    EditText city;
    Button click;
    WeatherForecast weatherForecast;

    String baseURL ="https://api.openweathermap.org/data/2.5/weather?q=";
    String API = "&appid=1fd22c8ec23c42e87f66281137f0ab92";

    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        city = findViewById(R.id.cityName);
        click = findViewById(R.id.button);


        click.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String myURL = baseURL + city.getText().toString() + API ;

                JsonObjectRequest jsonObjectRequest = new
                        JsonObjectRequest(Request.Method.GET, myURL, null,
                         new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject jsonObjectresponse) {
                        Log.i("JSON", "JSON" + jsonObjectresponse);

                        try {
                            String info = jsonObjectresponse.getString("weather");
                            JSONArray array = new JSONArray(info);

                            for (int i = 0; i < array.length(); i++) {
                                JSONObject object = array.getJSONObject(i);
                                String myWeather = object.getString("main");
                                Intent intent = new Intent(MainActivity.this,ResultCity.class);
                                intent.putExtra("main",myWeather);
                                startActivity(intent);
                                Log.i("ID", "ID" + object.getString("id"));
                                Log.i("MAIN", "MAIN" + object.getString("main"));
                            }
                        } catch (JSONException exception) {
                            exception.printStackTrace();
                        }
                    }
                },
                        new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                Log.i("Error", "Something Went Wrong" + error);
                            }
                        }
                );
                WeatherForecast.getInstance(MainActivity.this).addToRequest(jsonObjectRequest);

            }
        });


    }
}
