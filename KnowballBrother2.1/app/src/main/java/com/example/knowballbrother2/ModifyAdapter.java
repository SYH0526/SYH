package com.example.knowballbrother2;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class ModifyAdapter extends ArrayAdapter<Modify> {

    private int resourceID;
    private DBHelper dbHelper;

    public ModifyAdapter(Context context,int tv_ResourceID, List<Modify> objects) {
        super(context, tv_ResourceID, objects);
        resourceID = tv_ResourceID;

    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        Modify modify = getItem(position);
        View view;
        if(convertView == null){
            view = LayoutInflater.from(getContext()).inflate(resourceID,parent,false);
        }
        else{
            view = convertView;
        }
        ImageView picture = (ImageView)view.findViewById(R.id.iv_modify_picture);
        TextView title = (TextView)view.findViewById(R.id.tv_modify_title);
        picture.setBackgroundResource(modify.getPicture());
        title.setText(modify.getTitle());
        return view;
    }
}
