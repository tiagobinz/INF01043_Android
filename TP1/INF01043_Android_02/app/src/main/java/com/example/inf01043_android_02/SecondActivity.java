package com.example.inf01043_android_02;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class SecondActivity extends AppCompatActivity {

    public TextView messageText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        messageText = findViewById(R.id.message_text);
        messageText.setText(getIntent().getExtras().getString("message"));
    }
}
