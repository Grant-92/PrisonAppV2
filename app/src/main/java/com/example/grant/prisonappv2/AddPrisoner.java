package com.example.grant.prisonappv2;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class AddPrisoner extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{

    TextView id;
    EditText name;
    EditText age;
    EditText charge;
    EditText chargeDes;
    EditText sentence;
    Spinner levels;

    Button submit;
    Button reset;

    DatabaseReference dbPrisoners;

    String uniqueKey;


    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle toggle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_prisoner);


        dbPrisoners = FirebaseDatabase.getInstance().getReference("prisoner");
        uniqueKey = dbPrisoners.push().getKey();



        id = findViewById(R.id.updateIDTextView);
        id.setText(uniqueKey);
        name = findViewById(R.id.updateName);
        age = findViewById(R.id.updateAge);
        charge = findViewById(R.id.updateCharge);
        chargeDes = findViewById(R.id.updateChargeDes);
        sentence = findViewById(R.id.updateSentence);
        levels = findViewById(R.id.updatesecspinneer);

        submit = findViewById(R.id.submitBtn);
        reset = findViewById(R.id.resetBtn);




        // nav drawer related code
        drawerLayout = findViewById(R.id.nav_drawer);
        toggle = new ActionBarDrawerToggle(this, drawerLayout, R.string.open, R.string.close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        NavigationView navigationView = findViewById(R.id.navigation_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    public void addNewPrisoner(View v) {

        String PName = name.getText().toString().trim();
        String PAge = age.getText().toString().trim();
        String PCharge = charge.getText().toString().trim();
        String PChargeDes = chargeDes.getText().toString().trim();
        String PSentence = sentence.getText().toString().trim();
        String pSecLevel = levels.getSelectedItem().toString();

        if(TextUtils.isEmpty(PName) || TextUtils.isEmpty(PAge) || TextUtils.isEmpty(PCharge)
                || TextUtils.isEmpty(PChargeDes) ||TextUtils.isEmpty(PSentence) || TextUtils.isEmpty(pSecLevel)){
            showToast("All fields are mandatory");

        }else if(Integer.parseInt(PAge) <= 18 || Integer.parseInt(PSentence) < 0){
            showToast("Enter Valid Ages(Minimum 18) and or Sentences(Minimum 1), Integers only");
        }else{

            Prisoner p = pickType(pSecLevel);
            p.setId(uniqueKey);
            p.setName(PName);
            p.setAge(Integer.parseInt(PAge));
            p.setCharge(PCharge);
            p.setChargeDescription(PChargeDes);
            p.setSentence(Integer.parseInt(PSentence));
            //TODO may include the pic loc later

            dbPrisoners.child(uniqueKey).setValue(p);
            showToast("Prisoner Added");
            finish();

        }


    }

    public Prisoner pickType(String PSecLevel){
        if(PSecLevel.equals("Max")){
            Prisoner p = new MaxSecPrisoner(" ", " ", -1, " ", " ",-1,PSecLevel, " ");
            return p;
        }else if(PSecLevel.equals("Med")){
            Prisoner p = new MaxSecPrisoner(" ", " ", -1, " ", " ",-1,PSecLevel, " ");
            return p;
        }else if (PSecLevel.equals("Min")){
            Prisoner p = new MaxSecPrisoner(" ", " ", -1, " ", " ",-1,PSecLevel, " ");
            return p;
        }else{
            showToast("A Error occurred");
            return null;
        }
    }
    public void showToast(String message) {
        Toast toast = Toast.makeText(this, message, Toast.LENGTH_SHORT);
        toast.show();

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
            Toast.makeText(this, "Your already at the Add Prisoner screen", Toast.LENGTH_SHORT).show();

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
