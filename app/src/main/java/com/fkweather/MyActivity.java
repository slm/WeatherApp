package com.fkweather;


import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.text.Spannable;
import android.text.style.ForegroundColorSpan;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

import com.fkweather.models.LocateWeather;
import com.github.lzyzsd.randomcolor.RandomColor;
import com.pwittchen.weathericonview.library.WeatherIconView;

import butterknife.ButterKnife;
import butterknife.InjectView;
import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;


public class MyActivity extends FragmentActivity implements LocationListener{
    LocationManager locationManager;
    WeatherClient service;
    String provider;
    WeatherIconView tv1 ;
    LocationListener locationListener = this;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        setContentView(R.layout.activity_my);
        tv1 = (WeatherIconView) findViewById(R.id.sun);
        tv1.startAnimation(
                AnimationUtils.loadAnimation(this, R.anim.rotateinf) );
          RestAdapter restAdapter = new RestAdapter.Builder()
                .setEndpoint("http://api.openweathermap.org")
                .setLogLevel(RestAdapter.LogLevel.FULL)
                .build();

        service = restAdapter.create(WeatherClient.class);

        Criteria criteria = new Criteria();
        criteria.setPowerRequirement(Criteria.POWER_LOW);
        criteria.setSpeedRequired(true);
        criteria.setSpeedAccuracy(Criteria.ACCURACY_LOW);
        provider = locationManager.getBestProvider(criteria,true);
        if ( !locationManager.isProviderEnabled( LocationManager.GPS_PROVIDER ) ) {
            buildAlertMessageNoGps();
        }else{
            locationManager.requestLocationUpdates(provider, 0, 0, locationListener);
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.my, menu);
        return true;
    }

    private void buildAlertMessageNoGps() {
        final AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Your GPS seems to be disabled, do you want to enable it?")
                .setCancelable(false)
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    public void onClick(@SuppressWarnings("unused") final DialogInterface dialog, @SuppressWarnings("unused") final int id) {
                        Intent i =new Intent(android.provider.Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                        startActivityForResult(i, 0);
                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    public void onClick(final DialogInterface dialog, @SuppressWarnings("unused") final int id) {
                        dialog.cancel();
                        finish();
                    }
                });
        final AlertDialog alert = builder.create();
        alert.show();
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==0){
            if ( !locationManager.isProviderEnabled( LocationManager.GPS_PROVIDER ) ) {
                buildAlertMessageNoGps();
            }else{
                locationManager.requestLocationUpdates(provider, 0, 0, locationListener);
            }
        }
    }

    public void getWeather(Location location) {
        locationManager.removeUpdates(locationListener);
        service.getWeather(location.getLatitude(),location.getLongitude(),"f9024481848eeb908686e6befcc5eb8d",new Callback<LocateWeather>() {
            @Override
            public void success(LocateWeather locateWeather, Response response) {

                PlaceholderFragment fragment =new PlaceholderFragment();
                Bundle bundle = new Bundle();
                bundle.putDouble("tempkelvin",Math.round(locateWeather.getMain().getTemp()));
                bundle.putDouble("tempcelcius",Math.round(locateWeather.getMain().getTemp()- 273.15));
                bundle.putString("city",locateWeather.getName());
                bundle.putString("country",locateWeather.getSys().getCountry());
                fragment.setArguments(bundle);
                tv1.clearAnimation();
                tv1.setVisibility(View.GONE);
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.container, fragment).setCustomAnimations(android.R.animator.fade_in, android.R.animator.fade_out).commit();

            }

            @Override
            public void failure(RetrofitError error) {
                Log.i("error",error.getUrl());
                Log.i("error",error.getMessage());
            }
        });
    }

    @Override
    public void onLocationChanged(Location location) {
        getWeather(location);
        locationManager.removeUpdates(this);

    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

    @Override
    public void onProviderEnabled(String provider) {

    }

    @Override
    public void onProviderDisabled(String provider) {

    }




    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment {

        @InjectView(R.id.image)
        WeatherIconView icon;

        @InjectView(R.id.derece)
        TextView temp;

        @InjectView(R.id.cityname)
        TextView cityname;

        @InjectView(R.id.country)
        TextView country;

        @InjectView(R.id.message)
        TextView message;
        public PlaceholderFragment() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_my, container, false);
            ButterKnife.inject(this,rootView);
            Bundle bundle = getArguments();
            int tempkelvin = (int) bundle.getDouble("tempkelvin");
            int tempcelcius =(int) bundle.getDouble("tempcelcius");
            String city = bundle.getString("city");
            String country = bundle.getString("country");
            Log.i("log",tempkelvin+"");
            Log.i("log",tempcelcius+"");
            Log.i("log",city+"");
            Log.i("log",country+"");

            this.message.setLineSpacing(-30f, 1f);
            String msg = "Götün <c>dondu</c> dimi ? :))";
            int start =msg.indexOf("<c>");
            int end =  msg.indexOf("</c>")-3;

            this.message.setText(msg.replaceAll("<c>","").replace("</c>",""), TextView.BufferType.SPANNABLE);
            Spannable s = (Spannable) this.message.getText();


            RandomColor randomColor = new RandomColor();
            int color = Color.BLACK;
            if(tempcelcius<0){
                color =randomColor.random(RandomColor.Color.BLUE, 10)[0];
            }else if(tempcelcius>20){
                color =randomColor.random(RandomColor.Color.GREEN, 10)[0];
            }else if(tempcelcius>28){
                color =randomColor.random(RandomColor.Color.RED, 10)[0];
            }else{

            }
            s.setSpan(new ForegroundColorSpan(color), start, end, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
            this.message.setText(s);



            icon.setIconResource(getString(R.string.wi_snow_wind));


            temp.setText(""+tempcelcius);
            cityname.setText(""+city);
            this.country.setText("" + country);


            return rootView;
        }
    }



}
