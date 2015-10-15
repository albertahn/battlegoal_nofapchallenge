package com.changeandsuccess.nofapchallenge.comment_stuff;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.changeandsuccess.nofapchallenge.CoachProfile;
import com.changeandsuccess.nofapchallenge.R;
import com.changeandsuccess.nofapchallenge.model.CommentItem;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.display.RoundedBitmapDisplayer;

import java.util.ArrayList;

/**
 * Created by tanggames on 2015-10-06.
 */
public class ReplyAdapterOutside extends BaseAdapter {

    private Activity activity;
    Reply_SingleItem view;

    ArrayList<ReplyItem> items = new ArrayList<ReplyItem>();

    public ReplyAdapterOutside(Activity context) {
        this.activity = context;
    }//end interface

    @Override
    public int getCount() {
        return items.size();
    }

    @Override
    public Object getItem(int position) {
        return items.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        view = new Reply_SingleItem(activity);
        view.init(activity, parent);

        ReplyItem curItem = items.get(position);

        TextView username = (TextView) view.findViewById(R.id.user_name);
        username.setText(curItem.getUserName());

        TextView text_body_sample = (TextView) view.findViewById(R.id.text_body);
        text_body_sample.setText(curItem.getBodyText());

        setPhoto(curItem.getPortraitName(), curItem.getMembersIndex());

       // Button  moreCommentsBtn = (Button)view.findViewById(R.id.moreCommentsBtn);

      //  view.setBodyText(bodies[position]);
        return view;
    }

    public void setPhoto(String name,final String members_index){
        String imageurl = "http://tanggoal.com/public/uploads/members_pic/" + name;
        ImageLoader imageLoader = ImageLoader.getInstance();
        ImageView profile_photo = (ImageView) view.findViewById(R.id.user_profile);

        DisplayImageOptions options = new DisplayImageOptions.Builder()
                .showImageForEmptyUri(R.drawable.ic_launcher)
                .displayer(new RoundedBitmapDisplayer(50))
                .cacheInMemory(true)
                .considerExifParams(true)
                .build();
        if(!name.isEmpty()){
            imageLoader.displayImage(imageurl, profile_photo, options);

            profile_photo.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String cock = members_index;

                    Intent i = new Intent(activity, CoachProfile.class);

                    i.putExtra("coachID", ""+cock);
                    // i.putExtra("coachName", ""+)
                    activity.startActivity(i);
                }
            });
        }
    }

    public void addItem(ReplyItem item){
        items.add(item);
    }
}
