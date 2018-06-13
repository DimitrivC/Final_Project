package com.example.dimitrivc.final_project;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MakeTableActivity extends AppCompatActivity {
    // for Firebase authentification
    private FirebaseAuth mAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_make_table);
        // for Firebase autenthification
        mAuth = FirebaseAuth.getInstance();

        // set name selected charity in TextView
        Intent intent = getIntent();
        Bundle name = intent.getExtras();
        if (name != null) {
            String nameCharity = name.get("name charity").toString();
            // add exception
            TextView textView = findViewById(R.id.textView3);
            textView.setText(nameCharity);
        }

        // initializing SeekBars
        final SeekBar seekBar1 = findViewById(R.id.seekBar5);
        final SeekBar seekBar2 = findViewById(R.id.seekBar);
        final SeekBar seekBar3 = findViewById(R.id.seekBar3);
        final SeekBar seekBar4 = findViewById(R.id.seekBar4);
        // initializing EditTexts to put probabilities
        final EditText editText1 = findViewById(R.id.editText11);
        final EditText editText2 = findViewById(R.id.editText10);
        final EditText editText3 = findViewById(R.id.editText9);
        final EditText editText4 = findViewById(R.id.editText8);
        // set probability SeekBars to 25%
        seekBar1.setProgress(2500);
        seekBar2.setProgress(2500);
        seekBar3.setProgress(2500);
        seekBar4.setProgress(2500);
        // initialize EditTexts to set value to probability of 25%
        editText1.setText(String.valueOf(25));
        editText2.setText(String.valueOf(25));
        editText3.setText(String.valueOf(25));
        editText4.setText(String.valueOf(25));

        // to get probability of first SeekBar, and to set it to EditText.
        seekBar1.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            // to determine the change in progress of the SeekBar
            int valueStart = 0;
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                // set probability to correct amount while user is moving SeekBar
                editText1.setText(String.valueOf(seekBar.getProgress()/100));
            }
            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                // get the starting value
                valueStart = seekBar.getProgress();
            }
            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                // get the final value
                int valueEnd = seekBar.getProgress();
                // set probabilities other Seekbars so that total is 100
                setSeekBar(valueStart, valueEnd, seekBar2, seekBar3, seekBar4);
            }
        });

        seekBar2.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            // to determine the change in progress of the SeekBar
            int valueStart = 0;
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                // set probability to correct amount while user is moving SeekBar
                editText2.setText(String.valueOf(seekBar.getProgress()/100));
            }
            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                // get the starting value
                valueStart = seekBar.getProgress();
            }
            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                // get the final value
                int valueEnd = seekBar.getProgress();
                // set probabilities other Seekbars so that total is 100
                setSeekBar(valueStart, valueEnd, seekBar1, seekBar3, seekBar4);
            }
        });

        seekBar3.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            // to determine the change in progress of the SeekBar
            int valueStart = 0;
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                // set probability to correct amount while user is moving SeekBar
                editText3.setText(String.valueOf(seekBar.getProgress()/100));
            }
            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                // get the starting value
                valueStart = seekBar.getProgress();
            }
            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                // get the final value
                int valueEnd = seekBar.getProgress();
                // set probabilities other Seekbars so that total is 100
                setSeekBar(valueStart, valueEnd, seekBar1, seekBar2, seekBar4);
            }
        });

        seekBar4.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            // to determine the change in progress of the SeekBar
            int valueStart = 0;
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                // set probability to correct amount while user is moving SeekBar
                editText4.setText(String.valueOf(seekBar.getProgress()/100));
            }
            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                // get the starting value
                valueStart = seekBar.getProgress();
            }
            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                // get the final value
                int valueEnd = seekBar.getProgress();
                // set probabilities other Seekbars so that total is 100
                setSeekBar(valueStart, valueEnd, seekBar1, seekBar2, seekBar3);
            }
        });

        // if the user inserted values in the editText, these will be changed in the progressbar
        editText1.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }
            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }
            @Override
            public void afterTextChanged(Editable editable) {
                // if no probability is inserted // but, doesn't work yet. Also, fix something for
                // if more than 100 is inserted.
                int insertedValue = 0;
                // is some probability is inserted, so not empty:
                if (!editText1.toString().isEmpty()){
                    // get probability
                    insertedValue = Integer.parseInt(editText1.getText().toString())*100;
                }
                // set SeekBar to correct probability
                seekBar1.setProgress(insertedValue);
            }
        });

        // if the user inserted values in the editText, these will be changed in the progressbar
        editText2.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }
            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }
            @Override
            public void afterTextChanged(Editable editable) {
                // if no probability is inserted // but, doesn't work yet. Also, fix something for
                // if more than 100 is inserted.
                int insertedValue = 0;
                // is some probability is inserted, so not empty:
                if (!editText2.toString().isEmpty()){
                    // get probability
                    insertedValue = Integer.parseInt(editText2.getText().toString())*100;
                }
                // set SeekBar to correct probability
                seekBar2.setProgress(insertedValue);
            }
        });

        // if the user inserted values in the editText, these will be changed in the progressbar
        editText3.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }
            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }
            @Override
            public void afterTextChanged(Editable editable) {
                // if no probability is inserted // but, doesn't work yet. Also, fix something for
                // if more than 100 is inserted.
                int insertedValue = 0;
                // is some probability is inserted, so not empty:
                if (!editText3.toString().isEmpty()){
                    // get probability
                    insertedValue = Integer.parseInt(editText3.getText().toString())*100;
                }
                // set SeekBar to correct probability
                seekBar3.setProgress(insertedValue);
            }
        });

        // if the user inserted values in the editText, these will be changed in the progressbar
        editText4.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }
            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }
            @Override
            public void afterTextChanged(Editable editable) {
                // if no probability is inserted // but, doesn't work yet. Also, fix something for
                // if more than 100 is inserted.
                int insertedValue = 0;
                // is some probability is inserted, so not empty:
                if (!editText4.toString().isEmpty()){
                    // get probability
                    insertedValue = Integer.parseInt(editText4.getText().toString())*100;
                }
                // set SeekBar to correct probability
                seekBar4.setProgress(insertedValue);
            }
        });

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

    // to change probabilities of SeekBars if one of them is altered so that the total is 100
    public void setSeekBar(Integer valueStart, Integer valueEnd,
                           SeekBar seekBar1, SeekBar seekBar2, SeekBar seekBar3) {
        // determine progress
        int difference = valueStart-valueEnd;
        // get positive or negative value of difference
        difference = -difference;
        // divide by three
        int divided = difference/3; // it goes wrong here: what happens if you divide? Some amount is lost?

        // perhaps: check how much the total deviates from 10000 (since that is the progress max).
        // whatever it deviates, subtract or add equal values to all until the total is (close enough)
        // to a 100?

        // set the progress from others to correct point
        seekBar1.setProgress(seekBar1.getProgress()-divided);
        seekBar2.setProgress(seekBar2.getProgress()-divided);
        seekBar3.setProgress(seekBar3.getProgress()-divided);
    }

    public void calculate(View view) {

        // get access to editTexts to check if values are inserted, and if so to use these
        EditText editText5 = findViewById(R.id.editText5);
        EditText editText6 = findViewById(R.id.editText6);
        EditText editText7 = findViewById(R.id.editText7);

        // check if user inserted values in editTexts, act accordingly
        if (editText5.getText().toString().isEmpty()){
            // notify user that values have to be inserted
            Toast.makeText(MakeTableActivity.this, "Please insert all values",
                    Toast.LENGTH_LONG).show();
        }
        else if (editText6.getText().toString().isEmpty()) {
            Toast.makeText(MakeTableActivity.this, "Please insert all values",
                    Toast.LENGTH_LONG).show();
        }
        else if ((editText7.getText().toString().isEmpty())) {
            Toast.makeText(MakeTableActivity.this, "Please insert all values",
                    Toast.LENGTH_LONG).show();
        }
        // if all values have been inserted
        else {

            // get the values input: hier gaat iets mis, het zijn er maar 3!
            int value2 = Integer.parseInt(editText5.getText().toString());
            int value3 = Integer.parseInt(editText6.getText().toString());
            int value4 = Integer.parseInt(editText7.getText().toString());

            // initializing SeekBars to get probability
            SeekBar seekBar1 = findViewById(R.id.seekBar5);
            SeekBar seekBar2 = findViewById(R.id.seekBar);
            SeekBar seekBar3 = findViewById(R.id.seekBar3);
            SeekBar seekBar4 = findViewById(R.id.seekBar4);

            // get the probabilities assigned to the outcomes
            float probability1 = (float) seekBar1.getProgress()/100;
            float probability2 = (float) seekBar2.getProgress()/100;
            float probability3 = (float) seekBar3.getProgress()/100;
            float probability4 = (float) seekBar4.getProgress()/100;

            // calculate the expected utility of the charity
            float ExpectedUtility = (1*probability1+value2*probability2+value3*probability3+value4*probability4)/100;

            // TEST: to see if EU is correct, which it is.
            //TextView textTest = findViewById(R.id.textView3);
            //textTest.setText(String.valueOf(ExpectedUtility));

            // to go to TableActivity to add charity & expected utility
            Intent intent = new Intent(this, TableActivity.class);

            // to get name charity
            TextView textView = findViewById(R.id.textView3);
            // add name charity to intent for TableActivity
            intent.putExtra("name charity", textView.getText().toString());

            // add expected utility calculation to intent for TableActivity
            intent.putExtra("expected utility", ExpectedUtility);

            // go to TableActivity with expected utility calculation, and name charity
            startActivity(intent);


            // put it in textView there, along with name charity.
            // make dynamic listview:
            // with name charity, and expected utility, and then they should be able to click on it, and have the data retrieved.
            // so, it should be saved in Firebase.

        }
    }
} // end Activity
