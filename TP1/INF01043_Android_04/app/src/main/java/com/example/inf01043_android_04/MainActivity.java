package com.example.inf01043_android_04;

import androidx.appcompat.app.AppCompatActivity;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;

import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements SensorEventListener {

    TextView luminosityText;

    SensorManager sensorManager;
    Sensor sensor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        luminosityText = findViewById(R.id.luminosity_text);

        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        sensor = sensorManager.getDefaultSensor(Sensor.TYPE_LIGHT);
    }

    protected void onResume() {
        super.onResume();
        sensorManager.registerListener(this, sensor, SensorManager.SENSOR_DELAY_NORMAL);
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
    }

    @Override
    public void onAccuracyChanged(Sensor s, int accuracy) {

    }
}