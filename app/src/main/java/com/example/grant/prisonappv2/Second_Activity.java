package com.example.grant.prisonappv2;

import android.content.Intent;
import android.content.res.Resources;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.Adapter;
import android.widget.ListView;
import android.widget.Toast;

public class Second_Activity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{

    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle toggle;
    private ListView datalist;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);


        //TODO continue work for Custom listview ItemAdaptor needs to be wrote replace IA constructor args with whatevers handy that comes from firebase
        datalist = findViewById(R.id.dataList);
        ItemAdapter itemAdapter = new ItemAdapter(this, null);




        drawerLayout = findViewById(R.id.nav_drawer);
        toggle = new ActionBarDrawerToggle(this, drawerLayout, R.string.open, R.string.close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        NavigationView navigationView = findViewById(R.id.navigation_view);
        navigationView.setNavigationItemSelectedListener(this);
    }


    /**
     * called via getSupportActionBar() determines if an item was clicked
     * @param item
     * @return
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(toggle.onOptionsItemSelected(item)){
            return true;
        }
        return super.onOptionsItemSelected(item);
    }



    //TODO create classes and layouts for all screens

    /**
     * called onClick of an item and determines what should be done with the click action
     * @param item
     * @return
     */
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

        Log.d("TAG", "Made it to on navitemselcted problem is elsewhere");
        int ids = item.getItemId();

        if(ids == R.id.home){
            Toast.makeText(this,"HOME", Toast.LENGTH_SHORT).show();
        }
        if (ids == R.id.search){
            Toast.makeText(this,"SEARCH", Toast.LENGTH_SHORT).show();
        }
        if (ids == R.id.add){
            Intent showAddPrisoner = new Intent(getApplicationContext(), AddPrisoner.class);
            startActivity(showAddPrisoner);

        }
        if (ids == R.id.adduser){
            Toast.makeText(this,"ADDU", Toast.LENGTH_SHORT).show();
        }
        if (ids == R.id.settings){
            Toast.makeText(this,"SETTINGS", Toast.LENGTH_SHORT).show();
        }
        if (ids == R.id.log_out){
            Toast.makeText(this,"LOGOUT", Toast.LENGTH_SHORT).show();
        }

        return true;
    }
}
