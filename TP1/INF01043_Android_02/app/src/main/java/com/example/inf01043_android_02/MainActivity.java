package com.example.inf01043_android_02;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    EditText messageTextEdit;
    Button sendButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        messageTextEdit = findViewById(R.id.message_text_edit);
        sendButton = findViewById(R.id.send_button);

        sendButton.setOnClickListener((View v)->
        {
            Intent i = new Intent(MainActivity.this, SecondActivity.class);
            Bundle b = new Bundle();
            b.putString("message", messageTextEdit.getText().toString());
            i.putExtras(b);
            startActivity(i);
            finish();
        });
    }
}