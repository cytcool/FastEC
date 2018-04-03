package com.example.cyt.fastec;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.latte.app.Latte;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Latte.init(this)
                .withApiHost("http:/.127.0.0.1/")
                .configure();
    }
}
