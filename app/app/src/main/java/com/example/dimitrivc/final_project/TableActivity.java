package com.example.dimitrivc.final_project;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class TableActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_table);

        //jsonObject: {
        //jsonArray: [

        // get TextView to put content API
        final TextView textView = findViewById(R.id.textView);

        RequestQueue requestQueue = Volley.newRequestQueue(this);

        String url = "https://charitybase.uk/api/v0.2.0/charities";

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest
                (Request.Method.GET, url, null, new Response.Listener<JSONObject>(){
                    @Override
                    public void onResponse(JSONObject response) {
                        try {

                            JSONArray jsonArray = response.getJSONArray("charities");
                            if (jsonArray != null){

                                textView.setText(jsonArray.getJSONObject(0).getString("name"));

                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.d("error", String.valueOf(error));
                        textView.setText("@string/error2");
                    }
                });
        requestQueue.add(jsonObjectRequest);

    } // end onCreate


    public void goToCalculate(View view) {
        Intent intent = new Intent(this, CalculateActivity.class);
        startActivity(intent);
    }

    public void goToAdd(View view) {
        Intent intent = new Intent (this, CalculateActivity.class);
        startActivity(intent);
    }

    public void goToLoginTable(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

} // end class
