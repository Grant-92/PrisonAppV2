package com.example.grant.prisonappv2;

import android.content.SharedPreferences;
import android.content.res.Resources;
import android.graphics.Color;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Switch;

import java.util.Map;
import java.util.Set;

public class Settings extends AppCompatActivity {


    Switch darkMode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);


        darkMode = findViewById(R.id.darkmode);
    }


}

