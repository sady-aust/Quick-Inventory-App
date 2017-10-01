package com.example.android.quickinventoryapp.Custom;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.android.quickinventoryapp.R;

import java.util.List;



public class CustomerAdapter extends ArrayAdapter<String> {
    List<String> nameList;
    List<String> companyList;
    Context context;
    public CustomerAdapter(Context context, List<String> names, List<String> companyNames) {
        super(context, R.layout.single_list_item,R.id.customersTV,names);

        this.nameList = names;
        this.companyList =companyNames;
        this.context = context;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView,ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
      View rootView = inflater.inflate(R.layout.single_list_item,parent,false);
      TextView customerName = (TextView) rootView.findViewById(R.id.nameTV);
        TextView companyName = (TextView) rootView.findViewById(R.id.companyTV);

        customerName.setText(nameList.get(position));
        companyName.setText(companyList.get(position));
        return rootView;

    }
}
