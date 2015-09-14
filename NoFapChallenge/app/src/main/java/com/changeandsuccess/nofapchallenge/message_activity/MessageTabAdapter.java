package com.changeandsuccess.nofapchallenge.message_activity;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.changeandsuccess.nofapchallenge.R;
import com.changeandsuccess.nofapchallenge.model.MessageTabItem;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.display.RoundedBitmapDisplayer;

import java.util.ArrayList;

/**
 * Created by albert on 7/21/14.
 */
public class MessageTabAdapter extends ArrayAdapter<MessageTabItem> {

    Bitmap profilePhotoImage;

    ImageView profile_photo;

    static int rowList = R.layout.messagetabadapter_____message_frag_list_row;

    private final Context context;
    private final ArrayList<MessageTabItem> itemsArrayList;

    public MessageTabAdapter(Context context, ArrayList<MessageTabItem> itemsArrayList) {


//ArrayList<HomeItem> itemsArrayList


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
        View rowView = inflater.inflate(rowList, parent, false);

        // 3. Get the two text view from the rowView
        TextView text_body_sample = (TextView) rowView.findViewById(R.id.text_body_sample);
        TextView username =(TextView) rowView.findViewById(R.id.username);


        // 4. Set the text for textView
        text_body_sample.setText(itemsArrayList.get(position).getmessages_body());
        username.setText(itemsArrayList.get(position).getother_person_name());


//image



        profile_photo = (ImageView) rowView.findViewById(R.id.product_picture);


        String imageurl ="http://tanggoal.com/public/uploads/members_pic/"+ itemsArrayList.get(position).getother_person_pic().toString();

        ImageLoader imageloader = ImageLoader.getInstance();

        //imageloader.displayImage(url, view);


        if(imageloader.isInited() ==false) {

            imageloader.init(ImageLoaderConfiguration.createDefault(context));

        }//end if

        DisplayImageOptions options = new DisplayImageOptions.Builder()
                .showImageForEmptyUri(R.drawable.ic_launcher)
                .displayer(new RoundedBitmapDisplayer(0))

                .cacheInMemory(true)
                .considerExifParams(true)
                .build();

        imageloader.displayImage(imageurl, profile_photo, options);



        // 5. retrn rowView

        if(itemsArrayList.get(position).getother_person_index() ==null){

        }else{

           // Log.e("coc", ""+itemsArrayList.get(position).getother_person_index().toString());

            int memindex = Integer.parseInt(itemsArrayList.get(position).getother_person_index().toString());

            rowView.setId(memindex);
        }


        //rowView.setTag(R.id., itemsArrayList.get(position).getother_person_name());




        return rowView;
    }


}
