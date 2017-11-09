package com.epicodus.ootw.ui;

import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.epicodus.ootw.Constants;
import com.epicodus.ootw.R;
import com.epicodus.ootw.model.Weather;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import butterknife.Bind;
import butterknife.ButterKnife;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class WeatherInfoActivity extends AppCompatActivity {

    public static final String TAG = MainActivity.class.getSimpleName();
    private final double latitude = Constants.LATITUDE;
    private final double longitude = Constants.LONGITUDE;

    private Weather weather;
    @Bind(R.id.temperatureLabel) TextView mTemperatureLabel;
    @Bind(R.id.timeLabel) TextView mTimeLabel;
    @Bind(R.id.humidityValue) TextView mHumidityValue;
    @Bind(R.id.precipValue) TextView mPrecipValue;
    @Bind(R.id.summaryLabel) TextView mSummaryLabel;
    @Bind(R.id.refreshImageView) ImageView mRefreshImageView;
    @Bind(R.id.progressBar) ProgressBar mProgressBar;
    @Bind(R.id.moreButton) Button mMoreButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather_info);

        ButterKnife.bind(this);

        Typeface amaticBold= Typeface.createFromAsset(getAssets(), "fonts/Amatic-Bold.ttf");
        mMoreButton.setTypeface(amaticBold);

        mRefreshImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getWeatherForecast(latitude, longitude);
            }
        });

        mMoreButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(WeatherInfoActivity.this, OutfitActivity.class);
                startActivity(intent);
            }
        });

        mProgressBar.setVisibility(View.INVISIBLE);
        getWeatherForecast(latitude, longitude);
    }

    private void getWeatherForecast(double latitude, double longitude) {

        String forecastURL = "https://api.darksky.net/forecast/" + Constants.APIKEY +
                "/" + latitude + "," + longitude;

        if (isNetworkAvailable()) {
            toggleRefresh();
            OkHttpClient client = new OkHttpClient();
            Request request = new Request.Builder()
                    .url(forecastURL)
                    .build();

            Call call = client.newCall(request);
            call.enqueue(new Callback() {
                @Override
                public void onFailure(Call call, IOException e) {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            toggleRefresh();
                        }
                    });
                    alertUserAboutError();
                }

                @Override
                public void onResponse(Call call, Response response) throws IOException {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            toggleRefresh();
                        }
                    });
                    try {
                        String jsonData = response.body().string();
                        Log.v(TAG, jsonData);
                        if (response.isSuccessful()) {
                            weather = getCurrentDetails(jsonData);

                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    updateDisplay();
                                }
                            });
                        } else {
                            alertUserAboutError();
                        }
                    }
                    catch (IOException e) {
                        Log.d(TAG, "Exception caught: ", e);
                    }
                    catch (JSONException e){
                        Log.d(TAG, "Exception caught: ", e);
                    }
                }
            });

        }else {
            Toast.makeText(this, "Network is unavailable!", Toast.LENGTH_LONG).show();
        }
    }

    private void toggleRefresh() {
        if (mProgressBar.getVisibility() == View.INVISIBLE){
            mProgressBar.setVisibility(View.VISIBLE);
            mRefreshImageView.setVisibility(View.INVISIBLE);
        }else {
            mProgressBar.setVisibility(View.INVISIBLE);
            mRefreshImageView.setVisibility(View.VISIBLE);
        }
    }

    private void updateDisplay() {
        mTemperatureLabel.setText(weather.getTemperature() + "");
        mTimeLabel.setText("At " + weather.getFormattedTime());
        mHumidityValue.setText(weather.getHumidity()+"");
        mPrecipValue.setText(weather.getPercipChance()+"%");
        mSummaryLabel.setText(weather.getSummary());

    }

    private Weather getCurrentDetails(String jsonData) throws JSONException {
        JSONObject forecast = new JSONObject(jsonData);
        String timezone = forecast.getString("timezone");
        Log.i(TAG,"From JSON :" + timezone);

        JSONObject currently = forecast.getJSONObject("currently");

        Weather weather = new Weather();
        weather.setHumidity(currently.getDouble("humidity"));
        weather.setTime(currently.getLong("time"));
        weather.setPercipChance(currently.getDouble("precipProbability"));
        weather.setSummary(currently.getString("summary"));
        weather.setTemperature(currently.getDouble("temperature"));
        weather.setTimezone(timezone);

        return weather;
    }

    private boolean isNetworkAvailable() {
        ConnectivityManager manager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = manager.getActiveNetworkInfo();
        boolean isAvailable = false;
        if (networkInfo !=null && networkInfo.isConnected()){
            isAvailable = true;
        }
        return isAvailable;
    }

    private void alertUserAboutError() {
        AlertFragment dialog = new AlertFragment();
        dialog.show(getFragmentManager(), "error_dialog");
    }
}
