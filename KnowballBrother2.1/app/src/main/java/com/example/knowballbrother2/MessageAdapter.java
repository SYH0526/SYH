package com.example.knowballbrother2;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class MessageAdapter extends ArrayAdapter<Message> {
    private int resourceID;

    public MessageAdapter(Context context, int tv_ResourceID, List<Message> objects) {
        super(context, tv_ResourceID, objects);
        resourceID = tv_ResourceID;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        Message message = getItem(position);
        View view;
        if(convertView == null){
            view = LayoutInflater.from(getContext()).inflate(resourceID,parent,false);
        }
        else{
            view = convertView;
        }
        ImageView picture = (ImageView)view.findViewById(R.id.iv_message_picture);
        TextView title = (TextView)view.findViewById(R.id.tv_message_title);
        picture.setBackgroundResource(message.getPicture());
        title.setText(message.getTitle());
        return view;
    }
}
