package com.example.singh.pizzachallenge.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.singh.pizzachallenge.R;
import com.example.singh.pizzachallenge.activities.pizzalist.PizzaListActivity;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Intent intent = new Intent(this, PizzaListActivity.class);
        startActivity(intent);
        finish();
    }
}
