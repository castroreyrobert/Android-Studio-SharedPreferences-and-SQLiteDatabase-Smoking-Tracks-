package com.example.castroreyrobert.sharedpreferencesdemo;


import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class SmokeAdapter extends ArrayAdapter<SmokerModel>{
    public SmokeAdapter(Context context, ArrayList<SmokerModel> object) {
        super(context, 0, object);
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder viewHolder;

        SmokerModel smokerModel = getItem(position);

        if (convertView ==  null){
            LayoutInflater layoutInflater = (LayoutInflater) this.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = layoutInflater.inflate(R.layout.overview_custom_row,parent,false);

            viewHolder = new ViewHolder();
            viewHolder.tvDate2 = (TextView)convertView.findViewById(R.id.tvDate2);
            viewHolder.tvSticks2 = (TextView)convertView.findViewById(R.id.tvSticks2);

            convertView.setTag(viewHolder);
        }else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        viewHolder.tvDate2.setText(smokerModel.getDate());
        viewHolder.tvSticks2.setText(smokerModel.getSticks());

        return convertView;
    }

    static class ViewHolder{
        TextView tvDate2, tvSticks2;
    }
}
