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
 * Created by rafiqul islam on 10/3/2017.
 */

public class NotificationAdapter extends ArrayAdapter<String> {

    List<String> idList;
    List<String> messageList;
    Context context;

    public NotificationAdapter(Context context, List<String> message, List<String> id) {
        super(context, R.layout.single_notification_list_item,R.id.messageTV,message);

        this.idList = id;
        this.messageList =message;
        this.context = context;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        View rootView = inflater.inflate(R.layout.single_notification_list_item,parent,false);
        TextView idTV = (TextView) rootView.findViewById(R.id.notificationNumberTV);
        TextView messageTv = (TextView) rootView.findViewById(R.id.messageTV);

        idTV.setText("ID # "+idList.get(position));
        messageTv.setText(messageList.get(position));
        return rootView;

    }
}
