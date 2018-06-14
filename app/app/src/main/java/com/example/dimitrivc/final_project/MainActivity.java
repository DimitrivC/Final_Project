package com.example.dimitrivc.final_project;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {
    // for Firebase authentification
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
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
                            Toast.makeText(MainActivity.this, "Authentication succeeded.",
                                    Toast.LENGTH_SHORT).show();

                            //FirebaseUser user = mAuth.getCurrentUser();

                            // forward user to TableActivity
                            startActivity(new Intent (MainActivity.this, TableActivity.class));

                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w("sign in failed", "signInWithEmail:failure", task.getException());
                            Toast.makeText(MainActivity.this, "Authentication failed.",
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

//        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
//        InformationFragment fragment = new InformationFragment();
//
//        TextView textView = findViewById(R.id.fragmentTextView);
//        textView.setText("test test test");
//
//        fragment.show(ft, "dialog");
    }

} // end class
