package com.changeandsuccess.nofapchallenge.adapter;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.changeandsuccess.nofapchallenge.R;
import com.changeandsuccess.nofapchallenge.battle_stuff.AcceptBattle;
import com.changeandsuccess.nofapchallenge.battle_stuff.DeleteBattle_from_tab;
import com.changeandsuccess.nofapchallenge.model.MyMentorsTabItem;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.display.RoundedBitmapDisplayer;

import java.util.ArrayList;

/**
 * Created by albert on 10/3/14.
 */
public class BattleRequests_Adapter extends ArrayAdapter<MyMentorsTabItem> {

    Bitmap profilePhotoImage;

    static int rowList = R.layout.battlerequests_____adapter_battle_row_in_list;
    View rowView;

    TextView score_text;

    private final Activity context;
    private final ArrayList<MyMentorsTabItem> itemsArrayList;
    ImageView profile_photo;

    int positionj;

    public BattleRequests_Adapter (Activity context, ArrayList<MyMentorsTabItem> itemsArrayList, String userID) {

        super(context, rowList, itemsArrayList);
        this.context = context;
        this.itemsArrayList = itemsArrayList;

    }//


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        this.positionj = position;

        // 1. Create inflater
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        // 2. Get rowView from inflater
        rowView = inflater.inflate(rowList, parent, false);

        // 3. Get the two text view from the rowView
        TextView usernameView = (TextView) rowView.findViewById(R.id.battle_username);

        // 4. Set the text for textView
        usernameView.setText(itemsArrayList.get(position).getusername());

        //5. images for the profile pictures
        profile_photo = (ImageView) rowView.findViewById(R.id.profile_picture);
        String imageurl = "http://tanggoal.com/public/uploads/members_pic/" + itemsArrayList.get(position).getProfilePic();
        ImageLoader imageloader = ImageLoader.getInstance();

        if (!imageloader.isInited()) {

            imageloader.init(ImageLoaderConfiguration.createDefault(context));

        }
        //imageloader.displayImage(url, view);
        DisplayImageOptions options = new DisplayImageOptions.Builder()
                .showImageForEmptyUri(R.drawable.ic_launcher)
                .displayer(new RoundedBitmapDisplayer(50))
                .cacheOnDisc(true)
                .cacheInMemory(true)
                .considerExifParams(true)
                .build();
        imageloader.displayImage(imageurl, profile_photo, options);

        //1)see score
        final Button  seeScore_btn = (Button) rowView.findViewById(R.id.see_score_btn);

        //2) accept battle
        final Button  acceptBattle_btn =(Button) rowView.findViewById(R.id.accept_battle_btn);

        //3) duel
        final Button  duel_btn= (Button) rowView.findViewById(R.id.request_battle_btn);

//set button to delete request
        duel_btn.setText("Not Now");
        setClickListener(duel_btn,itemsArrayList.get(positionj).getuserIndex().toString(),rowView ,context);
//set button to accept request

        setAcceptClickListener(acceptBattle_btn,itemsArrayList.get(positionj).getuserIndex().toString(),rowView ,context);


        seeScore_btn.setVisibility(View.INVISIBLE);

        return rowView;


    }//get view

//for deleteing
    public void setClickListener(View btn, String userindex, View rowview , Activity activity){

final String auserindex = userindex;
        final View arowview= rowview;
        final Activity activity1= activity;
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                //CharSequence userid = itemsArrayList.get(positionj).getuserIndex();

                new DeleteBattle_from_tab(auserindex, arowview, activity1 ).execute();

                Dialog d =new Dialog(context);
                d.setTitle("Request Deleted");
                d.show();
            }
        });

    }


    //accept

    public void setAcceptClickListener(View btn, String userindex, View rowview , Activity activity){


        final String auserindex = userindex;
        final View arowview = rowview;
        final Activity activity1= activity;

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                new AcceptBattle(auserindex, arowview, activity1 ).execute();
                Dialog d =new Dialog(context);
                d.setTitle("Accepted");
                d.show();
            }
        });

    }

} //end adapter


