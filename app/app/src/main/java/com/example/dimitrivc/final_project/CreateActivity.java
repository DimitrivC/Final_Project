package com.example.dimitrivc.final_project;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

/**
 * CreateActivity
 *
 * Dimitri van Capelleveen - 29/6/2018
 *
 */

public class CreateActivity extends AppCompatActivity {
    // to check current auth state, to create new user
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create);
        // to check auth state, to create new user
        mAuth = FirebaseAuth.getInstance();
    }

    // check if user is signed in, act accordingly
    @Override
    public void onStart() {
        super.onStart();
        // check if user is signed in
        FirebaseUser currentUser = mAuth.getCurrentUser();
        // if signed in, go to TableActivity
        if (currentUser != null){
            startActivity(new Intent(this, TableActivity.class));
        }
    }

    // if user clicks button to create new account
    public void goToTableCreate(View view) {

        // get access to textViews for email and password
        EditText E_mail = findViewById(R.id.editText3);
        EditText Password = findViewById(R.id.editText4);

        // get email and password chosen by user
        String email = E_mail.getText().toString();
        String password = Password.getText().toString();

        // check if user inserted password with correct lengt
        if (password.length()>6) {
            // pass email and password on to createAccount
            createAccount(email, password);
        } // if password is too short
        else {
            Toast.makeText(CreateActivity.this, "Password to short.",
                    Toast.LENGTH_SHORT).show();
        }
    } // end goToTableCreate

    // create new Firebase account, go to TableActivity if successful
    public void createAccount(String email, String password) {
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // sign in success
                            Log.d("signed in", "createUserWithEmail:success");
                            Toast.makeText(CreateActivity.this, "Authentication succeeded.",
                                    Toast.LENGTH_SHORT).show();

                            // forward user to TableActivity
                            startActivity(new Intent(getApplicationContext(), TableActivity.class));
                        } else {
                            // If sign in fails, display message
                            Log.w("sign in failed", "createUserWithEmail:failure", task.getException());
                            Toast.makeText(CreateActivity.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    } // end createAccount

    // show DialogFragment for Information button
    public void showInformation(View view) {
        // initialize DialogFragment
        InformationFragment fragment = new InformationFragment();
        // set text for fragment
        fragment.setText("Information about another page");
        // show fragment, with text
        fragment.show(getFragmentManager(), "dialog");
    }
}
