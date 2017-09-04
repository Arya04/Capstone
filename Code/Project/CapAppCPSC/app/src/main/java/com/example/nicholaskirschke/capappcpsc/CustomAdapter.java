package com.example.nicholaskirschke.capappcpsc;

import android.content.Context;
import android.support.annotation.MainThread;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Arya on 10/18/16.
 */

public class CustomAdapter extends BaseAdapter {
    private static final String TAG = "custom adapter";

    LayoutInflater myInflater;
    private AppCompatActivity myActivity;
    private List<ForumData> forumList;

    public CustomAdapter(AppCompatActivity activity, List<ForumData> list){

        myActivity = activity;
        forumList = list;
        this.myInflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

    }
    public static class ViewHolder{
        TextView tv1;
        TextView tv2;

    }
    @Override
    public int getCount() {
        return forumList.size();
    }

    @Override
    public Object getItem(int position) {
        return forumList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if(convertView == null){
            convertView = myInflater.inflate(R.layout.row_layout,null);

            holder = new ViewHolder();
            holder.tv1 = (TextView)convertView.findViewById(R.id.ForumName);
            holder.tv2 = (TextView)convertView.findViewById(R.id.ForumDescription);


            convertView.setTag(holder);
        }else{
            holder = (ViewHolder) convertView.getTag();
        }
        holder.tv1.setText(forumList.get(position).TITLE);
        holder.tv2.setText(forumList.get(position).DESCRIPTION);


        return convertView;



    }
}
