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
import com.changeandsuccess.nofapchallenge.model.FeaturedItem;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.display.RoundedBitmapDisplayer;

import java.util.ArrayList;

/**
 * Created by albert on 6/22/14.
 */
public class FeaturedListAdapter extends ArrayAdapter<FeaturedItem> {

    Bitmap profilePhotoImage;

    static int rowList = R.layout.featuredlistadapter_____featured_row_in_list;
    View rowView;

    private final Context context;
    private final ArrayList<FeaturedItem> itemsArrayList;
    ImageView profile_photo;

    public FeaturedListAdapter(Context context, ArrayList<FeaturedItem> itemsArrayList) {



        super(context, rowList, itemsArrayList);

        this.context = context;
        this.itemsArrayList = itemsArrayList;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        // 1. Create inflater
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        // 2. Get rowView from inflater
        rowView = inflater.inflate(rowList, parent, false);

        // 3. Get the two text view from the rowView
        TextView usernameView = (TextView) rowView.findViewById(R.id.featured_username);
        TextView blurbView = (TextView) rowView.findViewById(R.id.featured_blurb);



        // 4. Set the text for textView
        usernameView.setText(itemsArrayList.get(position).getusername());

        blurbView.setText(itemsArrayList.get(position).getblurb());


        //5. images for the profile pictures

        profile_photo = (ImageView) rowView.findViewById(R.id.featured_profile_picture);


        String imageurl ="http://tanggoal.com/public/uploads/members_pic/"+ itemsArrayList.get(position).getprofilePicture().toString();


        ImageLoader imageloader = ImageLoader.getInstance();


       if(!imageloader.isInited()) {

           imageloader.init(ImageLoaderConfiguration.createDefault(context));
       }

        //imageloader.displayImage(url, view);

        DisplayImageOptions options = new DisplayImageOptions.Builder()
                .showImageForEmptyUri(R.drawable.ic_launcher)
                .displayer(new RoundedBitmapDisplayer(250))
                .cacheOnDisc(true)
                .cacheInMemory(true)
                .considerExifParams(true)
                .build();
        imageloader.displayImage(imageurl, profile_photo, options);



        // 5. retrn rowView
        Integer memindex = Integer.parseInt(itemsArrayList.get(position).getmembersIndex().toString());
        rowView.setId(memindex);
        // 5. retrn rowView
        return rowView;
    }





} //end adapter
