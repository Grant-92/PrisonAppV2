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
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.ui.database.FirebaseListAdapter;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class Second_Activity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener, AdapterView.OnItemClickListener {

    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle toggle;
    private DatabaseReference databaseReference;
    private ListView datalist;

    List<Prisoner> inmates;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);


        datalist = findViewById(R.id.dataList);
        databaseReference = FirebaseDatabase.getInstance().getReference("prisoner");
        inmates = new ArrayList<>();
        datalist.setOnItemClickListener(this);





        // nav drawer related code
        drawerLayout = findViewById(R.id.nav_drawer);
        toggle = new ActionBarDrawerToggle(this, drawerLayout, R.string.open, R.string.close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        NavigationView navigationView = findViewById(R.id.navigation_view);
        navigationView.setNavigationItemSelectedListener(this);
    }




    @Override
    protected void onStart() {
        super.onStart();

        databaseReference.addValueEventListener(new ValueEventListener() {

            //Executes every time something in DB changes
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                //clear here cause i was getting duplicates in the listview sometimes
                inmates.clear();

                for(DataSnapshot prisonershot: dataSnapshot.getChildren()){

                    Prisoner p = prisonershot.getValue(Prisoner.class);

                    inmates.add(p);
                }

                ItemAdapter ia = new ItemAdapter(Second_Activity.this, inmates);
                datalist.setAdapter(ia);

            }

            //Executes on failure to read or write too or from DB
            @Override
            public void onCancelled(DatabaseError databaseError) {

                Toast.makeText(getApplicationContext(),"A database Error occurred: Data couldn't be received", Toast.LENGTH_SHORT).show();

            }
        });
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
            Intent showSearchPrisoner = new Intent(getApplicationContext(), SearchActivity.class);
            startActivity(showSearchPrisoner);
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

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        //TODO start detailed intent passing the view or postition to determine what is in next view
        Intent showDetailedActivity = new Intent(getApplicationContext(), detailedActivity.class);

        showDetailedActivity.putExtra("id", inmates.get(position).getId());
        showDetailedActivity.putExtra("name",inmates.get(position).getName());
        showDetailedActivity.putExtra("age",inmates.get(position).getAge());
        showDetailedActivity.putExtra("charge",inmates.get(position).getCharge());
        showDetailedActivity.putExtra("chargeDes",inmates.get(position).getChargeDescription());
        showDetailedActivity.putExtra("sentence",inmates.get(position).getSentence());
        showDetailedActivity.putExtra("seclevel",inmates.get(position).getseclevel());
        showDetailedActivity.putExtra("picLoc",inmates.get(position).getPic_location());

        startActivity(showDetailedActivity);
    }
}


