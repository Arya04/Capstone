package com.example.nicholaskirschke.capappcpsc;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Arya on 11/28/16.
 */

public class PostCustomAdapter extends BaseAdapter {
    private static final String TAG = "post adapter";

    LayoutInflater myInflater;
    private AppCompatActivity myActivity;
    private List<PostData> postDataList;


    public PostCustomAdapter(AppCompatActivity activity, List<PostData> list) {
        myActivity = activity;
        postDataList = list;
        this.myInflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    public static class ViewHolder {
        TextView tv1;
        TextView tv2;
        TextView tv3;
    }

    @Override

    public int getCount() {
        return postDataList.size();
    }

    @Override

    public Object getItem(int position) {
        return postDataList.get(position);
    }

    @Override

    public long getItemId(int position) {
        return position;
    }

    @Override

    public View getView(int position, View convertView, ViewGroup parent) {
        PostCustomAdapter.ViewHolder holder;
        if (convertView == null) {
            convertView = myInflater.inflate(R.layout.post_layout, null);

            holder = new PostCustomAdapter.ViewHolder();
            holder.tv1 = (TextView) convertView.findViewById(R.id.userNamePost);
            holder.tv2 = (TextView) convertView.findViewById(R.id.createdAt);
            holder.tv3 = (TextView) convertView.findViewById(R.id.postDescription);


            convertView.setTag(holder);
        } else {
            holder = (PostCustomAdapter.ViewHolder) convertView.getTag();
        }
        //set the text
        holder.tv1.setText(postDataList.get(position).NAME);
        holder.tv2.setText(postDataList.get(position).CREATEDAT);
        holder.tv3.setText(postDataList.get(position).POST);




        return convertView;

    }

}

