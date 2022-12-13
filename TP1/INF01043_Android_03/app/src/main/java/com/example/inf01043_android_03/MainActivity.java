package com.example.inf01043_android_03;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;

import android.widget.EditText;

public class MainActivity extends AppCompatActivity implements SensorEventListener {

    EditText acc_x, acc_y, acc_z;

    SensorManager sensorManager;
    Sensor sensor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        acc_x = findViewById(R.id.acc_x);
        acc_y = findViewById(R.id.acc_y);
        acc_z = findViewById(R.id.acc_z);

        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        sensor = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
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
        if (s.getType() == Sensor.TYPE_ACCELEROMETER) {
            float x = e.values[0];
            float y = e.values[1];
            float z = e.values[2];
			
			acc_x.setText(Float.toString(x));
            acc_y.setText(Float.toString(y));
            acc_z.setText(Float.toString(z));
			
			if (Math.abs(x) >= 10 || Math.abs(y) >= 10) {
				Intent i = new Intent(MainActivity.this, SecondActivity.class);
				startActivity(i);
				finish();
			}
        }
    }

    @Override
    public void onAccuracyChanged(Sensor s, int accuracy) {

    }
}