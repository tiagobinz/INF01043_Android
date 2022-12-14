package com.example.inf01043_android_04;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.pm.PackageManager;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements SensorEventListener, LocationListener {

    SensorManager sensorManager;

    TextView luminosityText;
    Sensor luminositySensor;

    TextView gyroscopeX;
    TextView gyroscopeY;
    TextView gyroscopeZ;
    Sensor gyroscopeSensor;

    double latitude;
    double longitude;
    TextView positionLat;
    TextView positionLon;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);

        // setup luminosity
        luminosityText = findViewById(R.id.luminosity_value);
        luminositySensor = sensorManager.getDefaultSensor(Sensor.TYPE_LIGHT);

        // setup gyroscope
        gyroscopeX = findViewById(R.id.gyroscope_x);
        gyroscopeY = findViewById(R.id.gyroscope_y);
        gyroscopeZ = findViewById(R.id.gyroscope_z);
        gyroscopeSensor = sensorManager.getDefaultSensor(Sensor.TYPE_GYROSCOPE);

        // setup location
        Button getGPSBtn = (Button) findViewById(R.id.getGPSBtn);
        ActivityCompat.requestPermissions(MainActivity.this, new
                String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 123);
        getGPSBtn.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 GPSTracker g = new GPSTracker(getApplicationContext());
                 Location l = g.getLocation();
                 if (l != null) {
                     latitude = l.getLatitude();
                     longitude = l.getLongitude();
                     positionLat = findViewById(R.id.position_lat);
                     positionLat.setText(Double.toString(latitude));
                     positionLon = findViewById(R.id.position_lon);
                     positionLon.setText(Double.toString(longitude));
                 }
             }
         });
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions,
                                           int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    protected void onResume() {
        super.onResume();
        sensorManager.registerListener(
                this,
                luminositySensor,
                SensorManager.SENSOR_DELAY_NORMAL
        );
        sensorManager.registerListener(
                this,
                gyroscopeSensor,
                SensorManager.SENSOR_DELAY_NORMAL
        );
    }

    protected void onPause() {
        super.onPause();
        sensorManager.unregisterListener(this);
    }

    @Override
    public void onSensorChanged(SensorEvent e) {
        Sensor s = e.sensor;
        if (s.getType() == Sensor.TYPE_LIGHT) {
            luminosityText.setText(Float.toString(e.values[0]));
        }
        if (s.getType() == Sensor.TYPE_GYROSCOPE) {
            gyroscopeX.setText(Float.toString(e.values[0]));
            gyroscopeY.setText(Float.toString(e.values[1]));
            gyroscopeZ.setText(Float.toString(e.values[2]));
        }
    }

    @Override
    public void onAccuracyChanged(Sensor s, int accuracy) {

    }

    @Override
    public void onLocationChanged(Location var1) {

    }

    @Override
    public void onStatusChanged(String var1, int var2, Bundle var3) {

    }

    @Override
    public  void onProviderEnabled(String var1) {

    }

    @Override
    public void onProviderDisabled(String var1) {

    }
}