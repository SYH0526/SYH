package com.example.knowballbrother3;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;


public class RankAdapter extends ArrayAdapter<Rank> {
    private int resourceID;

    public RankAdapter(Context context, int tv_ResourceID, List<Rank> objects) {
        super(context, tv_ResourceID, objects);
        resourceID = tv_ResourceID;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        Rank list = getItem(position);
        View view;
        if(convertView == null){
            view = LayoutInflater.from(getContext()).inflate(resourceID,parent,false);
        }
        else{
            view = convertView;
        }
        TextView rank = (TextView)view.findViewById(R.id.tv_list_rank);
        TextView college = (TextView)view.findViewById(R.id.tv_list_college);
        TextView name = (TextView)view.findViewById(R.id.tv_list_name);
        TextView number = (TextView)view.findViewById(R.id.tv_list_number);
        rank.setText(list.getRank());
        college.setText(list.getCollege());
        name.setText(list.getName());
        number.setText(list.getNumber());
        return view;
    }
}
