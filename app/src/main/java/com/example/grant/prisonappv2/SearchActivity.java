package com.example.grant.prisonappv2;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Layout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class SearchActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{


    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle toggle;
    RadioButton name;
    RadioButton charge;
    RadioButton chargeDes;
    RadioButton level;

    ListView datalist;

    EditText seachTerm;
    Button stringSearchBtn;
    Button spinnerSearchBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        datalist = findViewById(R.id.result_list);




        // nav drawer related code
        drawerLayout = findViewById(R.id.nav_drawer);
        toggle = new ActionBarDrawerToggle(this, drawerLayout, R.string.open, R.string.close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        NavigationView navigationView = findViewById(R.id.navigation_view);
        navigationView.setNavigationItemSelectedListener(this);


         name = findViewById(R.id.radioName);
         charge = findViewById(R.id.radioCharge);
         chargeDes = findViewById(R.id.radioChargeDes);
         level = findViewById(R.id.radioLevel);

         seachTerm = findViewById(R.id.SearchTerm);
         stringSearchBtn = findViewById(R.id.stringSeachbtn);
         spinnerSearchBtn = findViewById(R.id.spinnerSeachbtn);


    }

    public void showSearchBox(View v){

        int dialogLayout = R.layout.activity_second;

        //TODO create layouts for the different outcomes here

        if(name.isChecked()){
            dialogLayout = R.layout.string_search_queries;

        }else if(charge.isChecked()){
            dialogLayout = R.layout.string_search_queries;

        }else if(chargeDes.isChecked()){
            dialogLayout = R.layout.string_search_queries;

        }else if (level.isChecked()){
            dialogLayout = R.layout.spinner_queries;

        }else{
            Toast.makeText(this, "An error occurred", Toast.LENGTH_SHORT);
        }







        AlertDialog.Builder build = new AlertDialog.Builder(this);
        build.setTitle("Search Term");
        LayoutInflater inflate = getLayoutInflater();
        View view = inflate.inflate(dialogLayout, null);
        build.setView(view).show();



    }






    /**
     * Maakes toggle functional
     *
     * @param item
     * @return
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (toggle.onOptionsItemSelected(item)) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }


    //TODO create classes and layouts for all screens

    /**
     * called onClick of an item and determines what should be done with the click action
     *
     * @param item
     * @return
     */
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

        //TODO add actions here for the rest of the nav functions
        Log.d("TAG", "Made it to on navitemselcted problem is elsewhere");
        int ids = item.getItemId();

        if (ids == R.id.home) {
            Toast.makeText(this, "Your already at the Home screen", Toast.LENGTH_SHORT).show();
        }
        if (ids == R.id.search) {
            //TODO Intent showSearchPrisoner = new Intent(getApplicationContext(), SearchPrisoners.class);
            //TODO startActivity(showSearchPrisoner);
        }
        if (ids == R.id.add) {
            Intent showAddPrisoner = new Intent(getApplicationContext(), AddPrisoner.class);
            startActivity(showAddPrisoner);
        }
        if (ids == R.id.adduser) {
            //TODO Intent showAddUser = new Intent(getApplicationContext(), AddUser.class);
            //TODO startActivity(showAddUser);
        }
        if (ids == R.id.settings) {
            Intent showSettings = new Intent(getApplicationContext(), Settings.class);
            startActivity(showSettings);
        }
        if (ids == R.id.log_out) {
            Toast.makeText(this, "LOGOUT", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
            this.finish();
        }

        return true;
    }
}
