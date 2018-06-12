package com.example.dimitrivc.final_project;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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
        seekBar1.setProgress(25);
        seekBar2.setProgress(25);
        seekBar3.setProgress(25);
        seekBar4.setProgress(25);

        // initialize EditTexts to set value to probability of 25%
        editText1.setText(String.valueOf(25));
        editText2.setText(String.valueOf(25));
        editText3.setText(String.valueOf(25));
        editText4.setText(String.valueOf(25));

        final TextView textView = findViewById(R.id.textView8);
        final TextView textView2 = findViewById(R.id.textView7);

        // then method to alter them to get a total of a 100
        // - if progress changed, let others know: if it went x higher, then the others have to decrease x/3 each. If it went y lower, the others have to go y/3 higher.
        // - do this via setProgress().


        // after method to make things equal:
        // method for edittext:
        // and if the edittext is altered, to change the seekbar with them.
        // make sure that other changes, such that the probability of the others, is changed as well.

        // to get probability of first SeekBar, and to set it to EditText.
        seekBar1.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

//                // to make a total a 100:
//
//                // get "old" value of progress
//                int valueCurrent = Integer.parseInt(editText1.getText().toString());
//                // get new value of progress
//                int valueNew = seekBar.getProgress();
//                // get difference.
//                int difference = valueCurrent-valueNew;
//
//                if (difference > 0){
//                    // difference is negative. So, the probability of seekBar1 is increased.
//                    // so, the difference of the other seekbars has to be decreased.
//
//                    // take the positive of difference.
//                    difference = -difference;
//
//                    // divide by three (set to float; make max 1000. Perhaps add a box to check total of percentages; just add the progress.)
//                    int divided = difference/3;
//
//                    // make it a float somehow.
//                    // or, just do it later.
//
//                    textView.setText(String.valueOf(difference));
//                    textView2.setText(String.valueOf(divided));
//                    // get the progress from others
//                    // subtract from progress the result of the division
//                    // set as new progress
//                }
//                else {
//                    // difference is positive. So, the probability of seekBar1 has decreased.
//                    // so, the difference of the others has to be increased.
//
//                    // divide the difference by three
//                    // get the progress from others
//                    // add the difference divided to the progress
//                    // set as new progress
//                }

                // set probability to correct amount
                editText1.setText(String.valueOf(seekBar.getProgress()));

            }
            // stuff I don't use but have to implement.
            @Override
            public void onStartTrackingTouch(SeekBar seekBar1) {
            }
            @Override
            public void onStopTrackingTouch(SeekBar seekBar1) {
            }
        });

        seekBar2.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

                editText2.setText(String.valueOf(seekBar.getProgress())+ "%");
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar1) {
            }
            @Override
            public void onStopTrackingTouch(SeekBar seekBar1) {
            }
        });

        seekBar3.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

                editText3.setText(String.valueOf(seekBar.getProgress())+ "%");
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar1) {
            }
            @Override
            public void onStopTrackingTouch(SeekBar seekBar1) {
            }
        });

        seekBar4.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

                editText4.setText(String.valueOf(seekBar.getProgress())+ "%");
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar1) {
            }
            @Override
            public void onStopTrackingTouch(SeekBar seekBar1) {
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
            float ExpectedUtility = 1*probability1+value2*probability2+value3*probability3+value4*probability4;


            // TEST: to see if EU is correct, which it is.
            TextView textTest = findViewById(R.id.textView3);
            textTest.setText(String.valueOf(ExpectedUtility));

            // put in intent to go to TableActivity.
            // put it in textView there, along with name charity.
            // make dynamic listview:
            // with name charity, and expected utility, and then they should be able to click on it, and have the data retrieved.
            // so, it should be saved in Firebase.

        }
    }
} // end Activity
