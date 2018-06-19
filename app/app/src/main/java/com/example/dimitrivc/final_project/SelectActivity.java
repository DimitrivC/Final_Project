package com.example.dimitrivc.final_project;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
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

import java.util.ArrayList;
import java.util.List;

public class SelectActivity extends AppCompatActivity {
    // for Firebase authentification
    private FirebaseAuth mAuth;

    // to read and write
    private DatabaseReference mDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select);
        // for Firebase autenthification
        mAuth = FirebaseAuth.getInstance();

        // to read and write
        mDatabase = FirebaseDatabase.getInstance().getReference();

        // to check if user comes from (1) "Save a charity" or (2) "Add charity to calculation"
        Intent intent = getIntent();
        final String buttonName = intent.getStringExtra("button");

        // initialize textViews
        final TextView textViewTop = findViewById(R.id.textViewTop);
        final TextView textViewBottom = findViewById(R.id.textViewBottom);

        // get access to listViews
        final ListView listViewTop = findViewById(R.id.listViewTop);
        final ListView listViewBottom = findViewById(R.id.listViewBottom);

        // initialize array list for API content
        final List<String> APIContentArrayList = new ArrayList<>();

        // to get API content
        RequestQueue requestQueue = Volley.newRequestQueue(this);

        // to get API content
        String url = "https://charitybase.uk/api/v0.2.0/charities";

        // to get API content
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest
                (Request.Method.GET, url, null, new Response.Listener<JSONObject>(){
                    @Override
                    public void onResponse(JSONObject response) {
                        try {

                            JSONArray jsonArray = response.getJSONArray("charities");
                            if (jsonArray != null){

                                // iterate over data to get names of all charities
                                for (int i = 0; i < 10; i++) {

                                    // put API content in Arraylist
                                    APIContentArrayList.add(jsonArray.getJSONObject(i).getString("name"));
                                }

                                // Begin FB stuff:
                                // so, if successful, we have now an ArrayList listdata, with the API content.
                                ValueEventListener postListener = new ValueEventListener() {
                                    @Override
                                    public void onDataChange(DataSnapshot dataSnapshot) {

                                        // if user clicked button to save a new charity
                                        if (buttonName.equals("buttonSaveCharity")){

                                            textViewTop.setText("Save new charity");
                                            textViewBottom.setText("Edit a saved charity");

                                            // -get FB saved list (doesn't exist yet: determine children and stuff)
                                            // -compare content FB saved list & APIContentArrayList
                                            // make ArrayList for listViewTop: content API not in FB saved list
                                            // set to adapter
                                            // make ArrayList for listViewBottom: content FB saved list
                                            // set to adapter

                                        }
                                        // if user clicked button to add a charity to the calculation
                                        else if (buttonName.equals("buttonAddCharity")){

                                            textViewTop.setText("Add a charity to the calculation");
                                            textViewBottom.setText("Edit an added charity");

                                            // get userId current user.
                                            FirebaseUser currentUser = mAuth.getCurrentUser();
                                            String userId = currentUser.getUid();
                                            // fix exception for getUid.

                                            // make arraylist for
                                            ArrayList<String> addedCharitiesArrayList = new ArrayList<>();

                                            // iterate over dataSnapshot to get all added charities
                                            for (DataSnapshot aDatasnapshot : dataSnapshot.child("users").child(userId).
                                                    child("listAddedCharities").getChildren()) {

                                                // access children one at the time. add to arraylist
                                                Charity aCharity = aDatasnapshot.getValue(Charity.class);

                                                // get from charity name, (and expected utility).
                                                //String expectedUtility = String.valueOf(aCharity.expectedUtility);
                                                String nameCharity = aCharity.charityName;
                                                // fix exception

                                                // add name charity + EU to arraylist
                                                addedCharitiesArrayList.add(nameCharity);
                                                //addedCharitiesArrayList.add(expectedUtility);
                                            }

                                            // ArrayList for not added charities
                                            ArrayList<String> notAddedArrayList = new ArrayList<>();
                                            // iterate over content API
                                            for (String temp : APIContentArrayList){
                                                // if content API isn't in added charities list on Firebase
                                                if (!addedCharitiesArrayList.contains(temp)){
                                                    // add charity to ArrayList
                                                    notAddedArrayList.add(temp);
                                                }
                                            }
                                            // initialize adapter for listViewTop
                                            ArrayAdapter<String> adapter1 = new ArrayAdapter<>
                                                    (SelectActivity.this,
                                                            android.R.layout.simple_list_item_1,
                                                            notAddedArrayList);
                                            // set adapter to listViewTop
                                            listViewTop.setAdapter(adapter1);

                                            ArrayAdapter<String> adapter2 = new ArrayAdapter<>
                                                    (SelectActivity.this,
                                                            android.R.layout.simple_list_item_1,
                                                            addedCharitiesArrayList);
                                            listViewBottom.setAdapter(adapter2);

                                        }

                                    } // end onDataChange
                                    @Override
                                    public void onCancelled(DatabaseError databaseError) {
                                        // Getting Score failed, log a message
                                        Log.w("getting data failed", "loadPost:onCancelled", databaseError.toException());
                                    }
                                };
                                mDatabase.addValueEventListener(postListener); // End FB DataSnapshot; below more API json stuff:

                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.d("error", String.valueOf(error));
                        textViewTop.setText("@string/error2");
                    }
                });
        requestQueue.add(jsonObjectRequest); // end JSON object request for API




        // check if item listViewTop was clicked, go to TableActivity with appropriate information
        listViewTop.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position,
                                    long id) {

                if (buttonName.equals("buttonSaveCharity")){

                    // check what to do

                } else if (buttonName.equals("buttonAddCharity")){

                    // check what to do
                }

                // get name of charity which has been clicked on
                String selectedFromList = (listViewTop.getItemAtPosition(position).toString());
                // add name charity to intent to go to TableActivity
                startActivity(new Intent(SelectActivity.this, MakeTableActivity.class).
                        putExtra("name charity", selectedFromList));
            }
        });

        // if listViewBottom was clicked, go to TableActivity with appropriate information
        listViewBottom.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                if (buttonName.equals("buttonSaveCharity")){

                    // check what to do

                } else if (buttonName.equals("buttonAddCharity")){

                    // check what to d

                }
            }
        });



    } // end onCreate

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
    public void goToLoginSelect(View view) {
        // sign out user
        FirebaseAuth.getInstance().signOut();
        // go to MainActivity
        startActivity(new Intent(this, MainActivity.class));
    }
}
