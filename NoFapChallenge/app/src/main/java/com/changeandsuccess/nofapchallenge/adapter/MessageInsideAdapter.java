package com.changeandsuccess.nofapchallenge.adapter;

import android.content.Context;
import android.support.v7.app.ActionBar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.changeandsuccess.nofapchallenge.R;
import com.changeandsuccess.nofapchallenge.model.MessageTabItem;
import com.changeandsuccess.nofapchallenge.utils.UserDatabase;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.display.RoundedBitmapDisplayer;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by albert on 7/22/14.
 */
public class MessageInsideAdapter extends ArrayAdapter<MessageTabItem>{

    View rowView;
    ImageView profile_photo;

    static int rowList = R.layout.messageinsideadapter_____message_bubble;

    private final Context context;
    private final ArrayList<MessageTabItem> itemsArrayList;

    ImageLoader imageLoader = ImageLoader.getInstance();
    //imageLoader.destroy();

    ActionBar actionbar;


    public MessageInsideAdapter(Context context, ArrayList<MessageTabItem> itemsArrayList, ActionBar actionbar) {

         super(context, rowList, itemsArrayList);
        this.context = context;
        this.itemsArrayList = itemsArrayList;
        this.actionbar =actionbar;

        if(imageLoader.isInited()==false) {

            imageLoader.init(ImageLoaderConfiguration.createDefault(context));

        }//end if

    } //end


    @Override
    public View getView(int position, View convertView, ViewGroup parent){

        // 1. Create inflater
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

//check if i sent the message

        UserDatabase info = new UserDatabase(context);
        info.open();
        String[][] data = info.getData();
        info.close();

        String userIndex = data[0][1];


        String messagePersonID = itemsArrayList.get(position).getmembers_index();

        if(userIndex.equals(messagePersonID)){


            rowView = inflater.inflate(R.layout.messageinsideadapter_____message_bubble_my, parent, false);

            TextView text_body_sample = (TextView) rowView.findViewById(R.id.text_body);
            TextView username =(TextView) rowView.findViewById(R.id.user_name);
            TextView timestamp = (TextView) rowView.findViewById(R.id.time_stamp);

            String dateString = itemsArrayList.get(position).get_timestamp();
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

            try {

                Date  convertedDate = dateFormat.parse(dateString);
                Calendar lastcal = Calendar.getInstance();
                lastcal.setTime(convertedDate);

                int thismonth = lastcal.get(lastcal.MONTH);
                int thisday = lastcal.get(lastcal.DAY_OF_MONTH);
                int thishour = lastcal.get(lastcal.HOUR_OF_DAY);
                int thismin = lastcal.get(lastcal.MINUTE);

                timestamp.setText(thismonth +"/"+thisday+" "+ thishour +":"+thismin);

            } catch (ParseException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }// end catch

            // 4. Set the text for textView
            text_body_sample.setText(itemsArrayList.get(position).getmessages_body());
           // username.setText(itemsArrayList.get(position).getuser_name());
            //timestamp.setText(itemsArrayList.get(position).get_timestamp());
            return rowView;

        }else{

        // 2. Get rowView from inflater
    rowView = inflater.inflate(rowList, parent, false);

        // 3. Get the two text view from the rowView
        TextView text_body_sample = (TextView) rowView.findViewById(R.id.text_body);
        TextView username =(TextView) rowView.findViewById(R.id.user_name);
        TextView timestamp = (TextView) rowView.findViewById(R.id.time_stamp);

        // 4. Set the text for textView
        text_body_sample.setText(itemsArrayList.get(position).getmessages_body());
        //username.setText(itemsArrayList.get(position).getuser_name());


            actionbar.setTitle(itemsArrayList.get(position).getuser_name());

            //set timeline


            String dateString = itemsArrayList.get(position).get_timestamp();
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            try {

                Date  convertedDate = dateFormat.parse(dateString);
                Calendar lastcal = Calendar.getInstance();
                lastcal.setTime(convertedDate);

                int thismonth = lastcal.get(lastcal.MONTH);
                int thisday = lastcal.get(lastcal.DAY_OF_MONTH);
                int thishour = lastcal.get(lastcal.HOUR_OF_DAY);
                int thismin = lastcal.get(lastcal.MINUTE);

                timestamp.setText(thismonth +"/"+thisday+" "+ thishour +":"+thismin);
            } catch (ParseException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }// end catch




        profile_photo = (ImageView) rowView.findViewById(R.id.user_profile);


        String imageurl ="http://tanggoal.com/public/uploads/members_pic/"+ itemsArrayList.get(position).getprofile_picture();


         //   ImageLoader imageLoader = ImageLoader.getInstance();
            //imageLoader.destroy();

           // imageLoader.init(ImageLoaderConfiguration.createDefault(context));

        DisplayImageOptions options = new DisplayImageOptions.Builder()
                .showImageForEmptyUri(R.drawable.ic_launcher)
                .displayer(new RoundedBitmapDisplayer(50))

                .cacheInMemory(true)
                .considerExifParams(true)
                .build();

            imageLoader.displayImage(imageurl, profile_photo, options);

        // 5. retrn rowView
        Integer memindex = Integer.parseInt(itemsArrayList.get(position).gethas_message_index().toString());
        rowView.setId(memindex);
            return rowView;
        }//end else

    }


}
