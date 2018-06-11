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

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

public class SelectActivity extends AppCompatActivity {
    // for Firebase authentification
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select);
        // for Firebase autenthification
        mAuth = FirebaseAuth.getInstance();


        // make button visible
        // besides .INVISIBLE, there's also .GONE!!!!


        final TextView textView = findViewById(R.id.textView2);
        // get access to listView
        final ListView listView = findViewById(R.id.listView);
        final List<String> listdata = new ArrayList<>();
        final ArrayAdapter<String> adapter =
                new ArrayAdapter<>(
                        this,
                        android.R.layout.simple_list_item_1,
                        listdata);
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        String url = "https://charitybase.uk/api/v0.2.0/charities";
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest
                (Request.Method.GET, url, null, new Response.Listener<JSONObject>(){
                    @Override
                    public void onResponse(JSONObject response) {
                        try {

                            JSONArray jsonArray = response.getJSONArray("charities");
                            if (jsonArray != null){

                                // iterate over data to get names of all charities
                                for (int i = 0; i < 10; i++) {
                                    listdata.add(jsonArray.getJSONObject(i).getString("name"));
                                }

                                // set adapter with names charities to listView
                                listView.setAdapter(adapter);
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

        // check if item listView was clicked, go to TableActivity with name charity
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position,
                                    long id) {
                // get name of charity which has been clicked on
                String selectedFromList = (listView.getItemAtPosition(position).toString());
                // add name charity to intent to go to TableActivity
                startActivity(new Intent(SelectActivity.this, MakeTableActivity.class).
                        putExtra("name charity", selectedFromList));
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
