package com.changeandsuccess.nofapchallenge.comment_stuff;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.changeandsuccess.nofapchallenge.CoachProfile;
import com.changeandsuccess.nofapchallenge.R;
import com.changeandsuccess.nofapchallenge.model.LoginItem;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.display.RoundedBitmapDisplayer;

import java.util.ArrayList;

/**
 * Created by tanggames on 2015-10-07.
 */
public class Reply_SingleItem extends RelativeLayout {
    View rowView;
    ImageView profile_photo;
    TextView text_body_sample;
    TextView username;
    TextView replynum;
    Context m_context;

    public Reply_SingleItem(Context context) {
        super(context);
       // init(context,parent);
    }

    public Reply_SingleItem(Context context, AttributeSet attrs) {
        super(context, attrs);
      // init(context,parent);
    }
    public void init(final Context context,ViewGroup parent){
        m_context = context;
        LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        rowView = inflater.inflate(R.layout.replyadapter_____singleitem,this,true);

        ImageView profile_photo = (ImageView) rowView.findViewById(R.id.user_profile);;
        TextView text_body_sample = (TextView) rowView.findViewById(R.id.text_body);
        TextView username = (TextView) rowView.findViewById(R.id.user_name);
        TextView replynum = (TextView) rowView.findViewById(R.id.num_reply);

        //onclick listener

        rowView.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {

                Dialog d = new Dialog(context);
                d.setTitle("hi");
                d.show();

            }
        });

    }// init

    public void setPhoto(String name,final String members_index){
        String imageurl = "http://tanggoal.com/public/uploads/members_pic/" + name;
        ImageLoader imageLoader = ImageLoader.getInstance();

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

                    Intent i = new Intent(m_context,
                            CoachProfile.class);

                    i.putExtra("coachID", ""+cock);
                    // i.putExtra("coachName", ""+)
                    m_context.startActivity(i);
                }
            });
        }
    }
    public void setBodyText(String str){
        text_body_sample.setText(str);

    }
    public void setUsername(String str){
        username.setText(str);
    }
    public void setReplynum(String str){
        replynum.setText(str);
    }
}
