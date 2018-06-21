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
 * LoginActivity
 *
 * Dimitri van Capelleveen - 29/6/2018
 *
 * In this activity, which is the launcher activity of this app, the user can login on Firebase,
 * using an e-mail address and a password of at least seven characters. In case logging in fails,
 * the user will be notified. If the user opens the app and is logged in already, she will be
 * forwarded to TableActivity. If the user doesn't have an account yet, she can click a button
 * with the text "Create new account" and in doing so, she will be directed to CreateActivity
 * where she can create an account). An image button, with the image of an  Information icon is
 * shown. If the user clicks this, a DialogFragment with information about the app is shown.
 */


public class LoginActivity extends AppCompatActivity {
    // for Firebase authentification
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        // for Firebase authentification
        mAuth = FirebaseAuth.getInstance();
    } // end onCreate

    // check if user is signed in, act accordingly
    @Override
    public void onStart() {
        super.onStart();
        // check if user is signed in
        FirebaseUser currentUser = mAuth.getCurrentUser();
        // if signed in: go to TableActivity
        if (currentUser != null){
            startActivity(new Intent(this, TableActivity.class));
        }
    }

    // get email and password, go to signIn
    public void GoToTable(View view) {
        // get access to textView
        EditText E_mail = findViewById(R.id.editText);
        EditText Password = findViewById(R.id.editText2);
        // get email and password as given by user
        String email = E_mail.getText().toString();
        String password = Password.getText().toString();
        // pass email and password on to signIn
        signIn(email, password);
    }

    public void signIn (String email, String password) {
        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d("signed in", "signInWithEmail:success");
                            Toast.makeText(LoginActivity.this, "Authentication succeeded.",
                                    Toast.LENGTH_SHORT).show();

                            //FirebaseUser user = mAuth.getCurrentUser();

                            // forward user to TableActivity
                            startActivity(new Intent (LoginActivity.this, TableActivity.class));

                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w("sign in failed", "signInWithEmail:failure", task.getException());
                            Toast.makeText(LoginActivity.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    } // end signIn

    // go to CreateActivity, to create new account
    public void GoToCreate(View view) {
        startActivity(new Intent(this, CreateActivity.class));
    }

    // if information button clicked, show info in dialogfragment
    public void showInformation(View view) {
        // initialize DialogFragment
        InformationFragment fragment = new InformationFragment();
        // set text for fragment
        fragment.setText("Information about app");
        // show fragment, with text
        fragment.show(getFragmentManager(), "dialog");
    } // end showInformation

} // end class
