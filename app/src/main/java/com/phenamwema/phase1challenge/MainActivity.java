package com.phenamwema.phase1challenge;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    CardView about, profile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        about = (CardView) findViewById(R.id.btn_about);
        profile = (CardView) findViewById(R.id.btn_profile);

        about.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //navigate to about ALC webview activity
                startActivity(new Intent(MainActivity.this,AboutALC.class));
            }
        });

        profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //navigate to my profile activity
                startActivity(new Intent(MainActivity.this,MyProfile.class));
            }
        });

    }

}
