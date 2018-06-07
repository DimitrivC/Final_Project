package com.example.dimitrivc.final_project;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class CalculateActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculate);
    }

    // to do another calculation
    public void goToTableReset(View view) {
        Intent intent = new Intent(this, TableActivity.class);
        startActivity(intent);
    }

    // to go back to the calculation
    public void goToTableRestore(View view) {
        Intent intent = new Intent (this, TableActivity.class);
        startActivity(intent);
    }

    public void goToLoginCalculate(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}
