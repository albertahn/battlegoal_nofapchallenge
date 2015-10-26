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
import com.changeandsuccess.nofapchallenge.utils.UserDatabase;
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
    String userindex;

    public Reply_SingleItem(Context context) {
        super(context);
       // init(context,parent);
    }

    public Reply_SingleItem(Context context, AttributeSet attrs) {
        super(context, attrs);
      // init(context,parent);


    } //reply single item

    public void init(final Context context,ViewGroup parent){
        m_context = context;
        LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        rowView = inflater.inflate(R.layout.replyadapter_____singleitem,this,true);



        rowView.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {



            }
        });

    }// init


    public void setUsername(String str){
        username.setText(str);
    }
}
