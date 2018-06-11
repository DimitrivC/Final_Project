package com.example.dimitrivc.final_project;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class AddActivity extends AppCompatActivity {
    // for Firebase authentification
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);
        // for Firebase autenthification
        mAuth = FirebaseAuth.getInstance();

    } // end onCreate

    // check if user is signed in, act accordingly
    @Override
    public void onStart() {
        super.onStart();
        // to check if user is signed in
        FirebaseUser currentUser = mAuth.getCurrentUser();
        // if not signed in: go to MainActivity
        if (currentUser == null){
            startActivity(new Intent(this, MainActivity.class));
        }
    }

    // after charities have been selected to be added to the calculation, the user can press this
    // to go back to the calculation, with the charities selected here
    public void goToTableAdd(View view) {
        Intent intent = new Intent(this, TableActivity.class);
        startActivity(intent);
    }

    // sign out user, go to MainActivity
    public void goToLoginAdd(View view) {
        // sign out user
        FirebaseAuth.getInstance().signOut();
        // go to MainActivity
        startActivity(new Intent(this, MainActivity.class));
    }
}
