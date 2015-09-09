package com.changeandsuccess.nofapchallenge.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.changeandsuccess.nofapchallenge.R;
import com.changeandsuccess.nofapchallenge.model.MyMentorsTabItem;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.display.RoundedBitmapDisplayer;

import java.util.ArrayList;

/**
 * Created by albert on 7/11/14.
 */
public class Profile_MyMentors_Adapter extends ArrayAdapter<MyMentorsTabItem>{


    Bitmap profilePhotoImage;
    static int rowList = R.layout.profile_mymentors_adapter_____list_item_in_row_my_mentors;

    private final Context context;

    private final ArrayList<MyMentorsTabItem> itemsArrayList;

    public Profile_MyMentors_Adapter(Context context, ArrayList<MyMentorsTabItem> itemsArrayList) {


//ArrayList<HomeItem> itemsArrayList


        super(context, rowList, itemsArrayList);

        this.context = context;
        this.itemsArrayList = itemsArrayList;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        //init

        DisplayImageOptions displayimageOptions = new DisplayImageOptions.Builder().build();//.cacheInMemory().cacheOnDisc().build();

        // Create global configuration and initialize ImageLoader with this configuration
        ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(getContext()).
                defaultDisplayImageOptions(displayimageOptions).build();

        Boolean isInit= ImageLoader.getInstance().isInited();

        if(!isInit) {

            ImageLoader.getInstance().init(config);

        }// init image loader
        // 1. Create inflater
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        // 2. Get rowView from inflater
        View rowView = inflater.inflate(rowList, parent, false);

        // 3. Get the two text view from the rowView
        TextView username_view = (TextView) rowView.findViewById(R.id.username);


        //4. set the text

        username_view.setText(itemsArrayList.get(position).getusername());

        // 5. retrn rowView
        Integer memindex = Integer.parseInt(itemsArrayList.get(position).getuserIndex().toString());
        rowView.setId(memindex);

        //set the pro pic

        ImageView profile_photo = (ImageView) rowView.findViewById(R.id.profile_pic);

        //new LoadProfileImage(profile_photo).execute(itemsArrayList.get(position).getProfilePic().toString());

        String imageurl ="http://tanggoal.com/public/uploads/members_pic/"+ itemsArrayList.get(position).getProfilePic().toString();


       ImageLoader imageloader = ImageLoader.getInstance();

        //imageloader.displayImage(url, view);

        DisplayImageOptions options = new DisplayImageOptions.Builder()
                .showImageForEmptyUri(R.drawable.ic_launcher)
                .displayer(new RoundedBitmapDisplayer(50))

                .cacheInMemory(true)
                .considerExifParams(true)
                .build();

        imageloader.displayImage(imageurl, profile_photo, options);



        return rowView;
    }



}
