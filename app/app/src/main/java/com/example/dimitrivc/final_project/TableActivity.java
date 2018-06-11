package com.example.dimitrivc.final_project;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

public class TableActivity extends AppCompatActivity {
    // for Firebase authentification
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_table);
        // for Firebase autenthification
        mAuth = FirebaseAuth.getInstance();


        ////////////////// for API:

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
        /////////////////// end for API
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

    // sign out user, go to MainActivity
    public void goToLoginTable(View view) {
        // sign out user
        FirebaseAuth.getInstance().signOut();
        // go to MainActivity
        startActivity(new Intent(this, MainActivity.class));
    }

    public void goToCalculate(View view) {
        Intent intent = new Intent(this, CalculateActivity.class);
        startActivity(intent);
    }

    public void goToAdd(View view) {
        Intent intent = new Intent (this, CalculateActivity.class);
        startActivity(intent);
    }


    public void goToSave(View view) {
        Intent intent = new Intent(this, SaveActivity.class);
        startActivity(intent);
    }

    public void goToSelect(View view) {
        startActivity(new Intent(this, SelectActivity.class));
    }
} // end class
