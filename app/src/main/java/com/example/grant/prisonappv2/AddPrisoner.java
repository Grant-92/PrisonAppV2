package com.example.grant.prisonappv2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class AddPrisoner extends AppCompatActivity {

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

        }else if(Integer.parseInt(PAge) < 0 || Integer.parseInt(PSentence) < 0){
            showToast("Enter Valid Ages and or Sentences, Integers only");
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
}
