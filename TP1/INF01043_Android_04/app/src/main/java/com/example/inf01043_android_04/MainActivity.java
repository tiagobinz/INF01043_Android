package com.example.inf01043_android_04;

import androidx.appcompat.app.AppCompatActivity;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;

import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements SensorEventListener {

    SensorManager sensorManager;

    TextView luminosityText;
    Sensor luminositySensor;

    TextView gyroscopeX;
    TextView gyroscopeY;
    TextView gyroscopeZ;
    Sensor gyroscopeSensor;

    TextView positionLat;
    TextView positionLon;
    Sensor positionSensor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);

        luminosityText = findViewById(R.id.luminosity_value);
        luminositySensor = sensorManager.getDefaultSensor(Sensor.TYPE_LIGHT);

        gyroscopeX = findViewById(R.id.gyroscope_x);
        gyroscopeY = findViewById(R.id.gyroscope_y);
        gyroscopeZ = findViewById(R.id.gyroscope_z);
        gyroscopeSensor = sensorManager.getDefaultSensor(Sensor.TYPE_GYROSCOPE);
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
}