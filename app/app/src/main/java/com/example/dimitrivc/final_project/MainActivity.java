package com.example.dimitrivc.final_project;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Add (not necessarily in onCreate):
        //-Firebase check to see auth: is user logged in already?
        //-Save instance state

        //-info button: ActionBar Icon with corresponding method and Dialog Fragment
        //-add nice picture

    }

    public void GoToTable(View view) {
        // Add: Firebase stuff to make sure user is logged in: e-mail & password

        // Go to TableActivity
        Intent intent = new Intent(this, TableActivity.class);
        startActivity(intent);
    }

    public void GoToCreate(View view) {
        // Go to CreateActivity
        Intent intent = new Intent(this, CreateActivity.class);
        startActivity(intent);
    }
}
