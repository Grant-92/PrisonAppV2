package com.example.grant.prisonappv2;

import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class detailedActivity extends AppCompatActivity {

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

        LayoutInflater inflate = getLayoutInflater();

        View view = inflate.inflate(R.layout.activity_update_prisoner, null);

        TextView upID = view.findViewById(R.id.updateIDTextView);
        TextView upName = view.findViewById(R.id.updateName);
        TextView upAge = view.findViewById(R.id.updateAge);
        TextView upcharge = view.findViewById(R.id.updateCharge);
        TextView upChargeDes = view.findViewById(R.id.updateChargeDes);
        TextView upSentence = view.findViewById(R.id.updateSentence);
        Spinner upSecSpinner = view.findViewById(R.id.updatesecspinneer);

        upID.setText(id);
        upName.setText(name);
        upAge.setText(String.valueOf(age));
        upcharge.setText(charge);
        upChargeDes.setText(chargeDescription);
        upSentence.setText(String.valueOf(sentence));


        Toast.makeText(this, "Please alter the feild to be edited and reset the sec level", Toast.LENGTH_LONG).show();
        build.setView(view).show();

    }

    public void delete (View v){

        DatabaseReference inmateInDB = FirebaseDatabase.getInstance().getReference("prisoner").child(id);
        inmateInDB.removeValue();
        finish();

    }
}
