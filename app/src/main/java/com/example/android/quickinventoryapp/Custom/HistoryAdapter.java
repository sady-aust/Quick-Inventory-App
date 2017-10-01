package com.example.android.quickinventoryapp.Custom;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.android.quickinventoryapp.R;

import java.util.List;

/**
 * Created by rafiqul islam on 9/29/2017.
 */

public class HistoryAdapter extends ArrayAdapter<String> {

    private List<String> dates;
    private List<String> customersName;
    private List<String> productsName;
    private List<String> totalPrice;
    Context context;

    public HistoryAdapter(@NonNull Context context, List<String> dates,List<String> customer,List<String> product,List<String> price) {
        super(context, R.layout.single_list_item,R.id.dateTVHistory,dates);
        this.dates = dates;
        this.customersName = customer;
        this.productsName = product;
        this.totalPrice = price;
        this.context = context;


    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater)context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        View rootView = inflater.inflate(R.layout.history_single_list_item,parent,false);

        TextView date = (TextView)rootView.findViewById(R.id.dateTVHistory);
        TextView customerName = (TextView)rootView.findViewById(R.id.namesTVHistory);
        TextView productName = (TextView)rootView.findViewById(R.id.productTVHistory);
        TextView price = (TextView)rootView.findViewById(R.id.totalPriceTVHistory);

        date.setText(dates.get(position));
        customerName.setText(customersName.get(position));
        productName.setText(productsName.get(position));
        price.setText(totalPrice.get(position));

        return rootView;

    }
}
