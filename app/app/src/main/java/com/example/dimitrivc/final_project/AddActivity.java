package com.example.dimitrivc.final_project;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class AddActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);
    }

    // after charities have been selected to be added to the calculation, the user can press this
    // to go back to the calculation, with the charities selected here
    public void goToTableAdd(View view) {
        Intent intent = new Intent(this, TableActivity.class);
        startActivity(intent);
    }
}
