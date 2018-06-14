package com.example.dimitrivc.final_project;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
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
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.util.ArrayList;

public class TableActivity extends AppCompatActivity {
    // for Firebase authentification
    private FirebaseAuth mAuth;

    // to read and write
    private DatabaseReference mDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_table);
        // for Firebase autenthification
        mAuth = FirebaseAuth.getInstance();

        // to read and write
        mDatabase = FirebaseDatabase.getInstance().getReference();

        // get all added charities
        ValueEventListener postListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                // get userId current user.
                FirebaseUser currentUser = mAuth.getCurrentUser();
                String userId = currentUser.getUid();
                // fix exception for getUid.

                // make arraylist for
                ArrayList<String> addedCharitiesArrayList = new ArrayList<>();

                // iterate over dataSnapshot to get all added charities
                for (DataSnapshot aDatasnapshot : dataSnapshot.child("users").child(userId).
                        child("listAddedCharities").getChildren()) {

                    // access children one at the time. add to arraylist?
                    Charity aCharity = aDatasnapshot.getValue(Charity.class);

                    // get from charity name, and expected utility.
                    String expectedUtility = String.valueOf(aCharity.expectedUtility);
                    String nameCharity = aCharity.charityName;

                    // add name charity + EU to arraylist
                    addedCharitiesArrayList.add(nameCharity);
                    addedCharitiesArrayList.add(expectedUtility);
                }

                // make adapter for listview and set it for ArrayList with charities
                ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>
                        (TableActivity.this, android.R.layout.simple_list_item_1, addedCharitiesArrayList);

                // get access to listview
                ListView listView = findViewById(R.id.listView);

                // set adapter to listview to show charities.
                listView.setAdapter(arrayAdapter);

            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
                // Getting Score failed, log a message
                Log.w("getting score failed", "loadPost:onCancelled", databaseError.toException());
            }
        };
        mDatabase.addValueEventListener(postListener);








        // TODO:
        // check intent. If intent, then add charity & EU to listview:

        // name charity: EU
        // remove when long click; go to MakeTable when short click. check To do.

        // check: https://developer.android.com/reference/android/widget/Adapter
        // check subclasses and choose best adapter.
        // see what is useful right now, but also later when I use Firebase to store data


        // TODO make sure that if user leaves this Activity & has added charities already, these are saved and added when user returns
        // TODO remove this:
        //////////////////////////////////////////////////////////
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
        ////////////////////////////////////////// end for API

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

        // TODO: add calculation.

        Intent intent = new Intent(this, CalculateActivity.class);
        startActivity(intent);
    }

    // changed: if user wants to Save a new charity, she is forwarded to SelectActivity
    public void goToSave(View view) {
        Intent intent = new Intent(this, SelectActivity.class);
        startActivity(intent);
    }

    public void goToSelect(View view) {
        startActivity(new Intent(this, SelectActivity.class));
    }
} // end class
