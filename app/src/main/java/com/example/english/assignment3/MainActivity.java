package com.example.english.assignment3;

import android.app.Activity;
import android.content.Context;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends Activity {

    boolean traced;
    LocationManager locationManager;
    TextView tx;
    String locationProvider;
    LocationListener locationListener;
    TextView averagespeed;
    TextView currentspeed;
    TextView overalltime;
    GraphView graph;
    int cout;
    Timer timer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final ArrayList<Location> list = new ArrayList<>();
        traced = false;

        graph = (GraphView) findViewById(R.id.graph);

        tx = (TextView) findViewById(R.id.tracer);
        averagespeed = (TextView)findViewById(R.id.averagespeed);
        currentspeed = (TextView)findViewById(R.id.currentspeed);
        overalltime = (TextView)findViewById(R.id.overalltime);

        locationManager = (LocationManager) this.getSystemService(Context.LOCATION_SERVICE);
        locationProvider = LocationManager.GPS_PROVIDER;
        locationListener = new LocationListener() {
            @Override
            public void onLocationChanged(Location location) {
                if (location != null && traced) {
                    if (list.size() < 101) {
                        list.add(location);
                    } else {
                        list.remove(0);
                        list.add(location);
                    }
                    graph.add(location);
                    if(traced) {
                        currentspeed.setText("Current Speed: " + location.getSpeed() * 3.6f + "km/h");
                        averagespeed.setText("Average Speed: " + graph.average() + "km/h");
                    }
                }
            }

            @Override
            public void onProviderDisabled(String provider) {
            }

            @Override
            public void onProviderEnabled(String provider) {
            }

            @Override
            public void onStatusChanged(String provider, int status, Bundle extras) {
            }
        };

        final Button start = (Button) findViewById(R.id.start);

        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (start.getText().toString() == "STOP") {
                    start.setText("START");
                    tx.setText("GPS inactive");
                } else {
                    start.setText("STOP");
                    tx.setText("GPS active");
                }
                traced = !traced;
                chrono();
                if (traced) {
                    locationManager.requestLocationUpdates(locationProvider, 1000, 0, locationListener);
                }
            }
        });
    }

    public void chrono(){
        if(traced) {
            cout = 0;
            timer = new Timer();
            timer.schedule(new TimerTask() {
                @Override
                public void run() {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            cout++;
                            overalltime.setText("Overall Time: " + cout + "s");
                        }
                    });
                }
            }, 0, 1000);
        }else{
            timer.cancel();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
