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

/**
 * Created by rafiqul islam on 9/30/2017.
 */

public class AnalysisCategoryAdapter extends ArrayAdapter<String> {
    List<String> catgories;

    Context context;
    public AnalysisCategoryAdapter(Context context,List<String> strings) {
        super(context, R.layout.analysis_category_single_list_item,R.id.categoryTV,strings);

        catgories = strings;
        this.context = context;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        View rootView = inflater.inflate(R.layout.analysis_category_single_list_item,parent,false);
        TextView category = (TextView) rootView.findViewById(R.id.categoryTV);


        category.setText(catgories.get(position));

        return rootView;

    }

}
