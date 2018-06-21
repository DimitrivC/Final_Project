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

        // for Firebase authentification
        mAuth = FirebaseAuth.getInstance();

        // to read and write
        mDatabase = FirebaseDatabase.getInstance().getReference();

        // to check if user comes from (1) "Save a charity" or (2) "Add charity to calculation"
        Intent intent = getIntent();
        final String buttonName = intent.getStringExtra("button");

        // get content of API (so, charities) (& forward users to getFireBaseContent)
        getAPIContent(buttonName);

        // initialize listViews
        final ListView listViewTop = findViewById(R.id.listViewTop);
        final ListView listViewBottom = findViewById(R.id.listViewBottom);

        // set listeners on listViews for if user clicks on item
        listViewTop.setOnItemClickListener(listenerListView);
        listViewBottom.setOnItemClickListener(listenerListView);

    } // end onCreate

    @Override
    public void onStart() {
        super.onStart();
        // to check if user is signed in
        FirebaseUser currentUser = mAuth.getCurrentUser();
        // if not signed in: go to LoginActivity
        if (currentUser == null){
            startActivity(new Intent(this, LoginActivity.class));
        }
    } // end onStart


    // listener for listView items
    AdapterView.OnItemClickListener listenerListView = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

            // get intent to get button clicked to get access to this activity
            Intent intent = getIntent();
            String buttonName = intent.getStringExtra("button");

            // get name clicked charity
            String selectedFromList = (adapterView.getItemAtPosition(i).toString());
            // get intent for MakeTableActivity & add name clicked charity
            Intent intent2 = new Intent(SelectActivity.this, MakeTableActivity.class).
                    putExtra("name charity", selectedFromList);


            // if user clicked "Save a charity": to save a charity, not necessarilly for calculation
            if (buttonName.equals("buttonSavedCharity")){

                // if listViewTop was clicked:
                switch (view.getId()){

                    // if user wanted to save a new charity
                    case R.id.listViewTop:

                        intent2.putExtra("purpose", "save new charity");
                        startActivity(intent2);

                        break;
                    // if user wanted to edit a saved charity
                    case R.id.listViewBottom:

                        intent2.putExtra("purpose", "edit saved charity");
                        startActivity(intent2);

                        break;
                    default:
                        break;

                }
            } // if user clicked "Add a charity to calculation"
            else if (buttonName.equals("buttonAddCharity")){

                switch (adapterView.getId()){

                    // if user wants to add a new charity to the calculation
                    case R.id.listViewTop:

                        intent2.putExtra("purpose", "add charity to calculation");
                        startActivity(intent2);

                        break;
                    case R.id.listViewBottom:

                        intent2.putExtra("purpose", "edit added charity");
                        startActivity(intent2);

                        break;
                    default:
                        break;

                }
            }
        }
    };


    // (called in onCreate) to get content API (charities) & call method to get Firebase data
    public void getAPIContent(final String buttonName){
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
                                // initialize list for content API
                                final List<String> APIContentArrayList = new ArrayList<>();
                                // iterate over data to get names of all charities
                                for (int i = 0; i < 10; i++) {
                                    // put API content in Arraylist
                                    APIContentArrayList.add(jsonArray.getJSONObject(i).getString("name"));
                                }
                                // call method to get data Firebase
                                getFireBaseContent(buttonName, APIContentArrayList);
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.d("error", String.valueOf(error));
                        TextView textViewTop = findViewById(R.id.textViewTop);
                        textViewTop.setText("@string/error2");
                    }
                });
        requestQueue.add(jsonObjectRequest); // end JSON object request for API
    } // end getAPIContent

    // (called in getAPIContent) to get FireBase content
    public void getFireBaseContent (final String buttonName, final List<String> APIContentArrayList){

        // to get data FireBase
        ValueEventListener postListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                // if user clicked button to save a new charity
                if (buttonName.equals("buttonSaveCharity")){

                    savedCharities();

                }
                // if user clicked button to add a charity to the calculation
                else if (buttonName.equals("buttonAddCharity")){

                    addedCharities(dataSnapshot, APIContentArrayList);

                }
            } // end onDataChange
            @Override
            public void onCancelled(DatabaseError databaseError) {
                // Getting Score failed, log a message
                Log.w("getting data failed", "loadPost:onCancelled", databaseError.toException());
            }
        };
        mDatabase.addValueEventListener(postListener); // End FB DataSnapshot; below more API json stuff:

    } // end getFireBaseContent


    // method for save charities
    public void savedCharities (){

        TextView textViewTop = findViewById(R.id.textViewTop);
        TextView textViewBottom = findViewById(R.id.textViewBottom);

        textViewTop.setText("Save new charity");
        textViewBottom.setText("Edit a saved charity");

        // TODO:
        // -get FB saved list (doesn't exist yet: determine children and stuff)
        // -compare content FB saved list & APIContentArrayList
        // make ArrayList for listViewTop: content API not in FB saved list
        // set to adapter
        // make ArrayList for listViewBottom: content FB saved list
        // set to adapter

    } // end savedCharities

    // method for added charities
    public void addedCharities(DataSnapshot dataSnapshot, List<String> APIContentArrayList){

        // initialize textViews
        TextView textViewTop = findViewById(R.id.textViewTop);
        TextView textViewBottom = findViewById(R.id.textViewBottom);
        // set text for textView
        textViewTop.setText("Add a charity to the calculation");
        textViewBottom.setText("Edit an added charity");
        // initialize listViews
        ListView listViewTop = findViewById(R.id.listViewTop);
        ListView listViewBottom = findViewById(R.id.listViewBottom);


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


    } // end addedCharities


    // sign out user, go to LoginActivity
    public void goToLoginSelect(View view) {
        // sign out user
        FirebaseAuth.getInstance().signOut();
        // go to LoginActivity
        startActivity(new Intent(this, LoginActivity.class));
    }

    // if information button clicked, show DialogFragment
    public void showInformation(View view) {
        // initialize DialogFragment
        InformationFragment fragment = new InformationFragment();
        // set text for fragment
        fragment.setText("Information about this particular page");
        // show fragment, with text
        fragment.show(getFragmentManager(), "dialog");
    }
} // end Activity
