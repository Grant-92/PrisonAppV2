package com.example.grant.prisonappv2;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity {

    private EditText loginEmail;
    private EditText loginPassword;
    private Button loginbtn;
    private ProgressBar  progress;
    private FirebaseAuth firebase;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        firebase = FirebaseAuth.getInstance();

        loginEmail = findViewById(R.id.login_email);
        loginPassword = findViewById(R.id.login_password);
        loginbtn = findViewById(R.id.login_btn);
        progress = findViewById(R.id.login_progress);


    }





    //login fully validate somehow still takes blank strings
    public void login(View v){
        progress.setVisibility(View.VISIBLE);
        String email = loginEmail.getText().toString().trim();
        String password = loginPassword.getText().toString().trim();

        if(TextUtils.isEmpty(loginEmail.getText()) || TextUtils.isEmpty(loginPassword.getText())){
            progress.setVisibility(View.INVISIBLE);
            showToast("Enter a valid Email and Password");
        }else {
            firebase.signInWithEmailAndPassword(email, password)
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                finish();
                                Intent loggedIn = new Intent(getApplicationContext(), Second_Activity.class);
                                startActivity(loggedIn);
                            } else {
                                progress.setVisibility(View.INVISIBLE);
                                showToast("Invalid Login Email or Password try Again");
                            }
                        }

                    });
        }
    }

    public void showToast(String message) {
        Toast toast = Toast.makeText(this, message, Toast.LENGTH_SHORT);
        toast.show();
    }


}
