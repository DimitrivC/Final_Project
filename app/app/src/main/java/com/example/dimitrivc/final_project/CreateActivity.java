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


    public void goToTableCreate(View view) {

        // get access to textViews for email and password
        EditText E_mail = findViewById(R.id.editText3);
        EditText Password = findViewById(R.id.editText4);

        // get email and password chosen by user
        String email = E_mail.getText().toString();
        String password = Password.getText().toString();

        // add: method to check if password is at least seven/more than six characters.

        // pass email and password on to createAccount
        createAccount(email, password);

    }

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

                            //FirebaseUser user = mAuth.getCurrentUser();

                            // if user needs a specific object, create one here I guess.

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
    }


}
