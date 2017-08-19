package com.example.android.quickinventoryapp.Custom;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;

import com.example.android.quickinventoryapp.R;



public class MyAlert extends DialogFragment {

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
       View view  =inflater.inflate(R.layout.custom_alert_dialog,null);
        builder.setView(view);
        Dialog dialog = builder.create();


        return dialog;
    }
}
