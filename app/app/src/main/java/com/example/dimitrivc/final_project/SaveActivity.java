package com.example.dimitrivc.final_project;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class SaveActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_save);
    }

    public void goToTableSave(View view) {
        Intent intent = new Intent(this, TableActivity.class);
        startActivity(intent);
    }

    public void goToLoginSave(View view) {
        Intent intent = new Intent (this, TableActivity.class);
        startActivity(intent);
    }
}
