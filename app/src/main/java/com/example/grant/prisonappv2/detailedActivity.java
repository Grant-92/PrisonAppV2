package com.example.grant.prisonappv2;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class detailedActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle toggle;

    private Prisoner p;

    String id;
    String name;
    int age;
    String charge;
    String chargeDescription;
    int sentence;
    String seclevel;
    String pic_location;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detailed_activity);

        // nav drawer related code
        drawerLayout = findViewById(R.id.nav_drawer);
        toggle = new ActionBarDrawerToggle(this, drawerLayout, R.string.open, R.string.close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        NavigationView navigationView = findViewById(R.id.navigation_view);
        navigationView.setNavigationItemSelectedListener(this);

        id = getIntent().getStringExtra("id");
        name = getIntent().getStringExtra("name");
        age = getIntent().getIntExtra("age", -1);
        charge = getIntent().getStringExtra("charge");;
        chargeDescription  = getIntent().getStringExtra("chargeDes");;
        sentence  = getIntent().getIntExtra("sentence", -1);;
        seclevel  = getIntent().getStringExtra("seclevel");;
        pic_location = getIntent().getStringExtra("picLoc");;





        TextView idTv = findViewById(R.id.id);
        TextView nameTv = findViewById(R.id.name);
        TextView ageTv = findViewById(R.id.age);
        TextView chargeTv = findViewById(R.id.charge);
        TextView chargeDesTv = findViewById(R.id.chargedes);
        TextView sentenceTv = findViewById(R.id.sentence);
        TextView seclevelTv = findViewById(R.id.seclevel);




        idTv.setText(id);
        nameTv.setText(name);
        ageTv.setText(String.valueOf(age));
        chargeTv.setText(charge);
        chargeDesTv.setText(chargeDescription);
        sentenceTv.setText(String.valueOf(sentence));
        seclevelTv.setText(seclevel);



    }



    public void update (View v){
        //TODO create a update fragment or recycle the add veiw?

        AlertDialog.Builder build = new AlertDialog.Builder(this);
        build.setTitle("Update Prisoner");



        LayoutInflater inflate = getLayoutInflater();



        View view = inflate.inflate(R.layout.activity_update_prisoner, null);
        Toast.makeText(this, "Please alter the field to be edited and reset the sec level", Toast.LENGTH_LONG).show();
        build.setView(view).show();

        TextView upID = view.findViewById(R.id.updateIDTextView);
        final TextView upName = view.findViewById(R.id.updateName);
        final TextView upAge = view.findViewById(R.id.updateAge);
        final TextView upcharge = view.findViewById(R.id.updateCharge);
        final TextView upChargeDes = view.findViewById(R.id.updateChargeDes);
        final TextView upSentence = view.findViewById(R.id.updateSentence);
        final Spinner upSecSpinner = view.findViewById(R.id.updatesecspinneer);
        final Button updatebtn = view.findViewById(R.id.updatebtn);

        upID.setText(id);
        upName.setText(name);
        upAge.setText(String.valueOf(age));
        upcharge.setText(charge);
        upChargeDes.setText(chargeDescription);
        upSentence.setText(String.valueOf(sentence));

        updatebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatabaseReference ref = FirebaseDatabase.getInstance().getReference("prisoner").child(id);
                Prisoner p = new Prisoner(id, upName.getText().toString(), Integer.parseInt(upAge.getText().toString()),
                        upcharge.getText().toString(), upChargeDes.getText().toString(), Integer.parseInt(upSentence.getText().toString()),
                        upSecSpinner.getSelectedItem().toString(),pic_location);

                ref.setValue(p);
                finish();
            }
        });



    }

    public void delete (View v){

        DatabaseReference inmateInDB = FirebaseDatabase.getInstance().getReference("prisoner").child(id);
        inmateInDB.removeValue();
        Toast.makeText(this, "Prisoner with ID: " + id + "   deleted successfully", Toast.LENGTH_LONG).show();
        finish();

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
            finish();
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
}
