package com.changeandsuccess.nofapchallenge.comment_stuff;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.changeandsuccess.nofapchallenge.CoachProfile;
import com.changeandsuccess.nofapchallenge.R;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.display.RoundedBitmapDisplayer;

import java.util.ArrayList;

/**
 * Created by albertan on 10/26/15.
 */
public class UnderReplyAdapter{
/*

    static int rowList = R.layout.com_in_adapter_____comment_bubble_list_row;

    Activity activity;


    public UnderReplyAdapter(Activity context, View rootview, ArrayList<CommentItem> itemsArrayList_reply){
        super(context, rowList, itemsArrayList_reply);

        activity = context;


    }//end public


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {


        // 1. Create inflater
        LayoutInflater inflater = (LayoutInflater) activity
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

//check if i sent the message


        rowView.setId(commentindex);



        //get the rep buttons


        replyButton = (ImageButton) rowView.findViewById(R.id.reply_button);
        final ImageButton likeButton = (ImageButton) rowView.findViewById(R.id.like_button);
        //check login


//button for replies


        replyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



                new  LoadReplies(userIndex, activity, commentindex).execute();

            }
        });


        likeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                LikeButton likebtn = new LikeButton(userIndex, activity);

                try {
                    String newlikes = likebtn.execute(commentindex + "").get();
                    likesnum.setText(""+newlikes);

                    likeButton.setBackgroundResource(R.drawable.btn_blue);

                    likeButton.setEnabled(false);

                }catch (Exception e){


                }

                //String newlikes = likebtn.getNewNumLikes();


            }
        });

        //set the delete if user

        final String comment_person_index = itemsArrayList.get(position).getmembers_index();

        // Log.e("comment_person_index",""+comment_person_index);


//get deleteComment
        ImageButton deleteComment = (ImageButton) rowView.findViewById(R.id.delete_comment_btn);

        if(comment_person_index.equals(userIndex)){ //if userindex
            deleteComment.setVisibility(View.VISIBLE);

            deleteComment.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {




                    setDeleteDialog(commentindex);

                }
            });

        }else{
            deleteComment.setVisibility(View.GONE);


        }


        //reply_rel.setId(position);

        return rowView;


    }//end else
*/
}
