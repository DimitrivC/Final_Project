package com.example.dimitrivc.final_project;


import android.app.DialogFragment;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.w3c.dom.Text;


/**
 * A simple {@link Fragment} subclass.
 */
public class InformationFragment extends DialogFragment {

    String text = "test text";

    public InformationFragment() {
        // Required empty public constructo
    }


    public boolean setText(String textInput){

        text = textInput;
        return true;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View inflate = inflater.inflate(R.layout.fragment_information, container, false);

        TextView textView = inflate.findViewById(R.id.fragmentTextView);

        textView.setText(text);

        // Inflate the layout for this fragment
        return inflate;
    }

}
