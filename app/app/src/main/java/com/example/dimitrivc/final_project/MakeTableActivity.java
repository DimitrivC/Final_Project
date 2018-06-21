package com.example.dimitrivc.final_project;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import static java.lang.Math.round;

public class MakeTableActivity extends AppCompatActivity {
    // for Firebase authentification
    private FirebaseAuth mAuth;

    // to write to Database
    private DatabaseReference mDatabase;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_make_table);
        // for Firebase autenthification
        mAuth = FirebaseAuth.getInstance();

        // to write to Database
        mDatabase = FirebaseDatabase.getInstance().getReference();

        // set name selected charity in TextView
        Intent intent = getIntent();
        Bundle name = intent.getExtras();
        if (name != null) {
            String nameCharity = name.get("name charity").toString();
            // add exception
            TextView textView = findViewById(R.id.textView3);
            textView.setText(nameCharity);
            String purpose = name.get("purpose").toString();

            Log.i("tagTest", purpose);

            // call different methods depending on what purpose is equal to.
            if (purpose.equals("save new charity")){
                saveNewCharity();
            }
            else if (purpose.equals("edit saved charity")){
                editSavedCharity();
            }
            else if (purpose.equals("add charity to calculation")){
                addCharityToCalculation();
            }
            else if (purpose.equals("edit added charity")){
                editAddedCharity();
            }
        }

        // initializing SeekBars
        SeekBar seekBar1 = findViewById(R.id.seekBar1);
        SeekBar seekBar2 = findViewById(R.id.seekBar2);
        SeekBar seekBar3 = findViewById(R.id.seekBar3);
        SeekBar seekBar4 = findViewById(R.id.seekBar4);
        // initializing EditTexts to put probabilities
        EditText editText1 = findViewById(R.id.editTextProbability1);
        EditText editText2 = findViewById(R.id.editTextProbability2);
        EditText editText3 = findViewById(R.id.editTextProbability3);
        EditText editText4 = findViewById(R.id.editTextProbability4);
        // set probability SeekBars to 25%
//        seekBar1.setProgress(2500);
//        seekBar2.setProgress(2500);
//        seekBar3.setProgress(2500);
//        seekBar4.setProgress(2500);
        // initialize EditTexts to set value to probability of 25%
        //editText1.setText(String.valueOf(25));
        //editText2.setText(String.valueOf(25));
        //editText3.setText(String.valueOf(25));
        //editText4.setText(String.valueOf(25));

        // set SeekBars to listener for if SeekBar dragged
        seekBar1.setOnSeekBarChangeListener(listenerTest);
        seekBar2.setOnSeekBarChangeListener(listenerTest);
        seekBar3.setOnSeekBarChangeListener(listenerTest);
        seekBar4.setOnSeekBarChangeListener(listenerTest);

        // set EditTexts to listener for user input
        editText1.addTextChangedListener(listenerEditText);
        editText2.addTextChangedListener(listenerEditText);
        editText3.addTextChangedListener(listenerEditText);
        editText4.addTextChangedListener(listenerEditText);

    } // end onCreate

    // check if user is signed in, act accordingly
    @Override
    public void onStart() {
        super.onStart();
        // to check if user is signed in
        FirebaseUser currentUser = mAuth.getCurrentUser();
        // if not signed in: go to LoginActivity
        if (currentUser == null){
            startActivity(new Intent(this, LoginActivity.class));
        }
    }

    // if the user wants to save a new charity: get "shared probs" from shared probs list from FB ; show buttons
    public void saveNewCharity(){

        //          -get "shared probs" for particular charity from Firebase
        //          -show two buttons: (1) "Save": add to FB Saved list
        //                             (2) "Save & Share probs": add to FB Saved list & FB Share Probs list; if user saved it on FB Share Probs list, rewrite it.

    }

    public void editSavedCharity(){

        //          -get saved content charity of FB Saved list
        //          -put content (values, probabilities) in correct place
        //          -show button: (1) "Save changes": save on FB Saved List; rewrite what has been written before ;
        //                        (2) "Save & Share probs": Save on FB Saved List, overwrite & save on FB Shared Probs list; if shared already, rewrite it.
        //                         (3) "remove": removes it from Firebase Saved List.


    }

    // if user wants to add a charity to the calculation
    public void addCharityToCalculation(){
        // get access to buttons
        View buttonAddCalc = findViewById(R.id.buttonAddToCalculation);
        View buttonAddAndSave = findViewById(R.id.buttonAddAndSave);
        // show buttons:
        buttonAddCalc.setVisibility(View.VISIBLE);
        //      attached to this is the method addToCalculation: calls calculate() to get values and probabilities and add to FB Added list; then goes to TableActivity
        buttonAddAndSave.setVisibility(View.VISIBLE);
        //      attached to this is the method addAndSave: calls calculate() to add to FB added list, then adds it to FB saved list

        // get content FB saved list to check if charity was saved
        ValueEventListener postListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                // get userId current user.
                FirebaseUser currentUser = mAuth.getCurrentUser();
                String userId = currentUser.getUid();
                // fix exception for getUid.

                TextView textView = findViewById(R.id.textView3);

                // if loop, with break if charity found.
                for (DataSnapshot aDatasnapshot : dataSnapshot.child("users").child(userId).
                        child("listSavedCharities").getChildren()) {

                    // get charity to get access to name
                    Charity aCharity = aDatasnapshot.getValue(Charity.class);

                    // get access to name to check if correct charity
                    String nameCharity = aCharity.charityName;

                    // check if correct added charity
                    if (nameCharity.equals(textView.getText())){


                        // if equal: charity is saved already, so we have to get and insert the values and probabilities
                        // if never equal: charity isn't saved.
                            // get shared probs, if they exist.

                    }
                }
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.w("getting data failed", "loadPost:onCancelled", databaseError.toException());
            }
        };
        mDatabase.addValueEventListener(postListener);




        //              if not saved:
        //                  get shared probs from Firebase if they exist, put in correct places

        //                  -if not: set to 25%:
        // initializing SeekBars
        SeekBar seekBar1 = findViewById(R.id.seekBar1);
        SeekBar seekBar2 = findViewById(R.id.seekBar2);
        SeekBar seekBar3 = findViewById(R.id.seekBar3);
        SeekBar seekBar4 = findViewById(R.id.seekBar4);

        // set probability SeekBars to 25%
        seekBar1.setProgress(2500);
        seekBar2.setProgress(2500);
        seekBar3.setProgress(2500);
        seekBar4.setProgress(2500);
    }

    // if the user wants to edit a charity already in the FB Added list, and save the changes to the Added list
    public void editAddedCharity(){

        //          -show button (1) "Save changes for calculation": replace values in FB added list
        //                       (2) "Save changes for calculation & later": replace values in FB added list &

        // to get data FireBase
        ValueEventListener postListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                // get userId current user.
                FirebaseUser currentUser = mAuth.getCurrentUser();
                String userId = currentUser.getUid();
                // fix exception for getUid.

                TextView textView = findViewById(R.id.textView3);

                // if loop, with break if charity found.
                for (DataSnapshot aDatasnapshot : dataSnapshot.child("users").child(userId).
                        child("listAddedCharities").getChildren()) {

                    // get charity to get access to name
                    Charity aCharity = aDatasnapshot.getValue(Charity.class);

                    // get access to name to check if correct charity
                    String nameCharity = aCharity.charityName;

                    // check if correct added charity
                    if (nameCharity.equals(textView.getText())){

                        // get values
                        Integer value2 = aCharity.value2;
                        Integer value3 = aCharity.value3;
                        Integer value4 = aCharity.value4;

                        // initialize edittexts
                        EditText editText2 = findViewById(R.id.editTextValueAlmost);
                        EditText editText3 = findViewById(R.id.editTextValueSlightly);
                        EditText editText4 = findViewById(R.id.editTextValueNotAtAll);

                        // set values in edittext
                        editText2.setText(value2.toString());
                        editText3.setText(value3.toString());
                        editText4.setText(value4.toString());

                        // get probabilities as integers (instead of floats)
                        Integer probability1 = round(aCharity.probability1);
                        Integer probability2 = round(aCharity.probability2);
                        Integer probability3 = round(aCharity.probability3);
                        Integer probability4 = round(aCharity.probability4);

                        // if probability is 0, the editText says %, if it is not zero (say 56), it says 0


                        // initializing EditTexts to put probabilities
                        EditText editTextProbability1 = findViewById(R.id.editTextProbability1);
                        EditText editTextProbability2 = findViewById(R.id.editTextProbability2);
                        EditText editTextProbability3 = findViewById(R.id.editTextProbability3);
                        EditText editTextProbability4 = findViewById(R.id.editTextProbability4);

                        //editTextProbability1.setText(1);

                        // initialize seekBars
                        SeekBar seekBar1 = findViewById(R.id.seekBar1);
                        SeekBar seekBar2 = findViewById(R.id.seekBar2);
                        SeekBar seekBar3 = findViewById(R.id.seekBar3);
                        SeekBar seekBar4 = findViewById(R.id.seekBar4);

                        // set progress seekBars to probability
                        seekBar1.setProgress(probability1);
                        seekBar2.setProgress(probability2);
                        seekBar3.setProgress(probability3);
                        seekBar4.setProgress(probability4);
                    }
                }
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.w("getting data failed", "loadPost:onCancelled", databaseError.toException());
            }
        };
        mDatabase.addValueEventListener(postListener);
    } // end editAddedCharity


    // if user inserts probability in editText, change progress SeekBar
    TextWatcher listenerEditText = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
        }
        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
        }
        @Override
        public void afterTextChanged(Editable editable) {
            EditText editText1 = findViewById(R.id.editTextProbability1);
            EditText editText2 = findViewById(R.id.editTextProbability2);
            EditText editText3 = findViewById(R.id.editTextProbability3);
            EditText editText4 = findViewById(R.id.editTextProbability4);
            int insertedValue = 0;
            // check which editText the user has used
            if (editable==editText1.getText()){
                // if no probability is inserted // but, doesn't work yet. Also, fix something for
                // if more than 100 is inserted.
                // is some probability is inserted, so not empty:
                if (!editText1.toString().isEmpty()){
                    // get probability
                    insertedValue = Integer.parseInt(editText1.getText().toString())*100;
                }
                // set SeekBar to correct probability
                SeekBar seekBar1 = findViewById(R.id.seekBar1);
                seekBar1.setProgress(insertedValue);
            } else if (editable==editText2.getText()){
                // is some probability is inserted, so not empty:
                if (!editText2.toString().isEmpty()){
                    // get probability
                    insertedValue = Integer.parseInt(editText2.getText().toString())*100;
                }
                // set SeekBar to correct probability
                SeekBar seekBar2 = findViewById(R.id.seekBar2);
                seekBar2.setProgress(insertedValue);
            } else if (editable==editText3.getText()){
                // is some probability is inserted, so not empty:
                if (!editText3.toString().isEmpty()){
                    // get probability
                    insertedValue = Integer.parseInt(editText3.getText().toString())*100;
                }
                // set SeekBar to correct probability
                SeekBar seekBar3 = findViewById(R.id.seekBar3);
                seekBar3.setProgress(insertedValue);
            } else if (editable==editText4.getText()){
                // is some probability is inserted, so not empty:
                if (!editText4.toString().isEmpty()){
                    // get probability
                    insertedValue = Integer.parseInt(editText4.getText().toString())*100;
                }
                // set SeekBar to correct probability
                SeekBar seekBar4 = findViewById(R.id.seekBar4);
                seekBar4.setProgress(insertedValue);
            }
        }
    };

    // to get probability of first SeekBar, and to set it to EditText.
    SeekBar.OnSeekBarChangeListener listenerTest = new SeekBar.OnSeekBarChangeListener() {
        // to determine the change in progress of the SeekBar
        int valueStart = 0;
        // valueStart is accessed in two methods below: perhaps make different valueStarts to ensure
        // that there are not failures, that is: to ensure that valueStart for one seekBar doesn't get used for the others.
        @Override
        public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
            // to determine which seekBar, to change the progress of that seekBar in EditText
            switch (seekBar.getId()) {
                case R.id.seekBar1:
                    EditText editText1 = findViewById(R.id.editTextProbability1);
                    editText1.setText(String.valueOf(seekBar.getProgress() / 100));
                    break;
                case R.id.seekBar2:
                    EditText editText2 = findViewById(R.id.editTextProbability2);
                    editText2.setText(String.valueOf(seekBar.getProgress() / 100));
                    break;
                case R.id.seekBar3:
                    EditText editText3 = findViewById(R.id.editTextProbability3);
                    editText3.setText(String.valueOf(seekBar.getProgress() / 100));
                    break;
                case R.id.seekBar4:
                    EditText editText4 = findViewById(R.id.editTextProbability4);
                    editText4.setText(String.valueOf(seekBar.getProgress() / 100));
                    break;
                default:
                    break;
            }
        }
        @Override
        public void onStartTrackingTouch(SeekBar seekBar) {
            // get the starting value
            switch (seekBar.getId()) {
                case R.id.seekBar1:
                    valueStart = seekBar.getProgress();
                    break;
                case R.id.seekBar2:
                    valueStart = seekBar.getProgress();
                    break;
                case R.id.seekBar3:
                    valueStart = seekBar.getProgress();
                    break;
                case R.id.seekBar4:
                    valueStart = seekBar.getProgress();
                    break;
                default:
                    break;
            }
        }
        @Override
        public void onStopTrackingTouch(SeekBar seekBar) {
            // get the final value
            int valueEnd = seekBar.getProgress();
            SeekBar seekBar1 = findViewById(R.id.seekBar1);
            SeekBar seekBar2 = findViewById(R.id.seekBar2);
            SeekBar seekBar3 = findViewById(R.id.seekBar3);
            SeekBar seekBar4 = findViewById(R.id.seekBar4);
            switch (seekBar.getId()) {
                case R.id.seekBar1:
                    // set probabilities other Seekbars so that total is 100
                    setSeekBar(valueStart, valueEnd, seekBar2, seekBar3, seekBar4);
                    break;
                case R.id.seekBar2:
                    // set probabilities other Seekbars so that total is 100
                    setSeekBar(valueStart, valueEnd, seekBar1, seekBar3, seekBar4);
                    break;
                case R.id.seekBar3:
                    // set probabilities other Seekbars so that total is 100
                    setSeekBar(valueStart, valueEnd, seekBar1, seekBar2, seekBar4);
                    break;
                case R.id.seekBar4:
                    // set probabilities other Seekbars so that total is 100
                    setSeekBar(valueStart, valueEnd, seekBar1, seekBar2, seekBar3);
                    break;
                default:
                    break;
            }
        }
    };

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

    // if the user wants to add the charity to the calculation, but does not want to save it for later
    public boolean calculate() {
        // check if all values are inserted
        boolean valuesInserted = valuesInserted();
        // if so, get values and probabilities
        if (valuesInserted) {
            // get charity object with all info
            Charity aCharity = getCharity();
            // get UserId to store charity in correct place
            FirebaseUser currentUser = mAuth.getCurrentUser();
            String userId = currentUser.getUid();
            // fix exception
            // store Charity object in FB Added list
            mDatabase.child("users").child(userId).child("listAddedCharities").push().setValue(aCharity);
            // return true if successful: charity info retrieved and stored in FB added list
            return true;
        }
        // return false if no charity was saved
        return false;
    } // end calculate

    // get charity object with all inserted values and probabilities.
    public Charity getCharity(){
        // to get the values assigned to the outcomes
        EditText editText5 = findViewById(R.id.editTextValueAlmost);
        EditText editText6 = findViewById(R.id.editTextValueSlightly);
        EditText editText7 = findViewById(R.id.editTextValueNotAtAll);
        // get the values from the editText
        int value2 = Integer.parseInt(editText5.getText().toString());
        int value3 = Integer.parseInt(editText6.getText().toString());
        int value4 = Integer.parseInt(editText7.getText().toString());
        // initializing SeekBars to get probability
        SeekBar seekBar1 = findViewById(R.id.seekBar1);
        SeekBar seekBar2 = findViewById(R.id.seekBar2);
        SeekBar seekBar3 = findViewById(R.id.seekBar3);
        SeekBar seekBar4 = findViewById(R.id.seekBar4);
        // get the probabilities assigned to the outcomes
        float probability1 = (float) seekBar1.getProgress()/100;
        float probability2 = (float) seekBar2.getProgress()/100;
        float probability3 = (float) seekBar3.getProgress()/100;
        float probability4 = (float) seekBar4.getProgress()/100;
        // calculate the expected utility of the charity
        float ExpectedUtility = (1*probability1+value2*probability2+value3*probability3+value4*probability4)/100;
        // to get name charity
        TextView textView = findViewById(R.id.textView3);
        // get name charity
        String nameCharity = textView.getText().toString();
        // get UserId to store charity in correct place
        FirebaseUser currentUser = mAuth.getCurrentUser();
        String userId = currentUser.getUid();
        // fix exception
        // make object to store Charity info in Firebase
        Charity aCharity = new Charity(nameCharity, ExpectedUtility,
                1, value2, value3, value4,
                probability1, probability2, probability3, probability4);
        // returns Charity object with values, probablities, name, expected utility
        return aCharity;
    } // end getCharity


    // if user wants (1) to add a new charity to the calculation & (2) add it to FB Saved list
    public void addAndSave(View view) {
        // (1) add charity to the calculation
        boolean calculate = calculate();
        // if getting and storing charity in FB added list successful
        if (calculate){
            // (2) Add it to FB saved list:
            // get charity object with name, values, probabilities, expected utility
            Charity aCharity = getCharity();
            // get UserId to store charity in correct place
            FirebaseUser currentUser = mAuth.getCurrentUser();
            String userId = currentUser.getUid();
            // store Charity object in FB Saved List
            mDatabase.child("users").child(userId).child("listSavedCharities").push().setValue(aCharity);
            // go to TableActivity
            startActivity(new Intent(this, TableActivity.class));
        }
    } // end addAndSave


    // to check if user inserted all values
    public boolean valuesInserted(){
        // get access to editTexts for values
        EditText editText5 = findViewById(R.id.editTextValueAlmost);
        EditText editText6 = findViewById(R.id.editTextValueSlightly);
        EditText editText7 = findViewById(R.id.editTextValueNotAtAll);
        // check if user inserted values in editTexts, act accordingly
        if (editText5.getText().toString().isEmpty()){
            // notify user that values have to be inserted
            Toast.makeText(MakeTableActivity.this, "Please insert all values",
                    Toast.LENGTH_LONG).show();
            return false;
        }
        else if (editText6.getText().toString().isEmpty()) {
            Toast.makeText(MakeTableActivity.this, "Please insert all values",
                    Toast.LENGTH_LONG).show();
            return false;
        }
        else if ((editText7.getText().toString().isEmpty())) {
            Toast.makeText(MakeTableActivity.this, "Please insert all values",
                    Toast.LENGTH_LONG).show();
            return false;
        }
        // if the user inserted all values
        return true;
    } // end valuesInserted

    // if user wants to add the charity to the calculation, but doesn't want to save it for later
    public void addToCalculation(View view) {
        // to get values and probabilities, and add this to FB Added list
        calculate();
        // go to TableActivity (where the new charity should be added to the calculation)
        startActivity(new Intent(this, TableActivity.class));
    } // end addToCalculation
} // end Activity
