package com.changeandsuccess.nofapchallenge.comment_stuff;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

/**
 * Created by tanggames on 2015-10-06.
 */
public class ReplyAdapter extends BaseAdapter {
    String[] names = {"소녀시대","걸스데이","씨스타","포미닛"};
    private Activity activity;

    public ReplyAdapter(Activity context) {
        this.activity = context;
    }//end interface

    @Override
    public int getCount() {
        return names.length;
    }

    @Override
    public Object getItem(int position) {
        return names[position];
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        TextView view = new TextView(activity);
        view.setText(names[position]);
        view.setTextSize(50.0f);
        view.setTextColor(Color.BLACK);

        return view;
    }
}
