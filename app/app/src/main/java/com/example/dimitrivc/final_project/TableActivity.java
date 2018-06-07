package com.example.dimitrivc.final_project;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

public class TableActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_table);

        //jsonObject: {
        //jsonArray: [

        // https://www.programmableweb.com/api/social-actions
        // dahdfha
    // API: http://developer.everydayhero.com/charities/
        // to get a list of all charities:  GET https://everydayhero.com/api/v2/charities
        // to get question and answers from API
        RequestQueue requestQueue = Volley.newRequestQueue(this);

        String url = "https://everydayhero.com/api/v2/charities";

        jsonObjectRequest jsonObjectRequest = new jsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<jsonObject>()){

        };

        // get TextView to put content API
        TextView textView = findViewById(R.id.textView);
        // set content API in textView
        textView.setText("bla");


    } // end onCreate








    public void goToCalculate(View view) {
        Intent intent = new Intent(this, CalculateActivity.class);
        startActivity(intent);
    }

    public void goToAdd(View view) {
        Intent intent = new Intent (this, CalculateActivity.class);
        startActivity(intent);
    }
}
