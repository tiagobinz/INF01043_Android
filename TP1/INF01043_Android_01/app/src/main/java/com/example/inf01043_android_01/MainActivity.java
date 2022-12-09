package com.example.inf01043_android_01;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    EditText n1;
    EditText n2;
    Button b;
    TextView res;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        n1 = findViewById(R.id.n1);
        n2 = findViewById(R.id.n2);
        b = findViewById(R.id.button_add);
        res = findViewById(R.id.add_result);

        b.setOnClickListener((View view)-> {
            int a = 0, b = 0, c = 0;
            try {
                a = Integer.parseInt(n1.getText().toString());
                b = Integer.parseInt(n2.getText().toString());
                c = a + b;
            } catch (NumberFormatException e) {
                System.out.println("The inputs should be numbers!");
            }
            res.setText(Integer.toString(c));
        });
    }
}