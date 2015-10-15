package com.changeandsuccess.nofapchallenge.comment_stuff;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.changeandsuccess.nofapchallenge.R;
import com.changeandsuccess.nofapchallenge.comment_stuff.LikeButton;
import com.changeandsuccess.nofapchallenge.comment_stuff.LoadReplies;
import com.changeandsuccess.nofapchallenge.model.CommentItem;
import com.changeandsuccess.nofapchallenge.utils.UserDatabase;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.display.RoundedBitmapDisplayer;

import java.util.ArrayList;

/**
 * Created by albert on 8/25/14.
 */
public class ReplyAdapter extends ArrayAdapter<CommentItem> {

    View rowView;
    ImageView profile_photo;


    static int rowList = R.layout.com_in_adapter_____comment_bubble_list_row;

    private final Activity activity;
    private final Context context;
    private final ArrayList<CommentItem> itemsArrayList;

    ImageLoader imageLoader = ImageLoader.getInstance();
    //imageLoader.destroy();


    public ReplyAdapter(Activity context, ArrayList<CommentItem> itemsArrayList) {

        super(context, rowList, itemsArrayList);
this.activity = context;

        this.context = context;
        this.itemsArrayList = itemsArrayList;

        if (imageLoader.isInited() == false) {

            imageLoader.init(ImageLoaderConfiguration.createDefault(context));

        }//end if

    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        // 1. Create inflater
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

//check if i sent the message

       int rep_to= itemsArrayList.get(position).getreply_to();


            // 2. Get rowView from inflater
            rowView = inflater.inflate(rowList, parent, false);
        rowView.findViewById(R.id.moreCommentsBtn).setVisibility(View.GONE);

            // 3. Get the two text view from the rowView
            TextView text_body_sample = (TextView) rowView.findViewById(R.id.text_body);
            TextView username = (TextView) rowView.findViewById(R.id.user_name);
        TextView replynum = (TextView) rowView.findViewById(R.id.num_reply);
        final TextView likesnum = (TextView) rowView.findViewById(R.id.num_likes);
            // 4. Set the text for textView
            text_body_sample.setText(itemsArrayList.get(position).getcomment_text());
            username.setText(itemsArrayList.get(position).getusername());
        replynum.setText(itemsArrayList.get(position).getreply_num());
        likesnum.setText(itemsArrayList.get(position).getLikes());


        profile_photo = (ImageView) rowView.findViewById(R.id.user_profile);


            String imageurl = "http://tanggoal.com/public/uploads/members_pic/" + itemsArrayList.get(position).getprofile_picture();

            DisplayImageOptions options = new DisplayImageOptions.Builder()
                    .showImageForEmptyUri(R.drawable.ic_launcher)
                    .displayer(new RoundedBitmapDisplayer(50))
                    .cacheInMemory(true)
                    .considerExifParams(true)
                    .build();


            if(! itemsArrayList.get(position).getprofile_picture().isEmpty()){

                imageLoader.displayImage(imageurl, profile_photo, options);
                Log.e("image_for_com",""+itemsArrayList.get(position).getprofile_picture());
            }


            // 5. retrn rowView
           final Integer memindex = Integer.parseInt(itemsArrayList.get(position).getcomment_index().toString());
            rowView.setId(memindex);



            ImageButton replyButton = (ImageButton) rowView.findViewById(R.id.reply_button);


        replyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                UserDatabase info = new UserDatabase(context);
                info.open();
                String[][] data = info.getData();
                info.close();

                String userIndex = data[0][1];


                new  LoadReplies(userIndex, activity, memindex).execute(""+memindex);



            }
        });//endclick


        final ImageButton likeButton = (ImageButton) rowView.findViewById(R.id.like_button);
        likeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UserDatabase info = new UserDatabase(context);
                info.open();
                String[][] data = info.getData();
                info.close();

                String userIndex = data[0][1];

                LikeButton likebtn = new LikeButton(userIndex, activity);

                try {
                    String newlikes = likebtn.execute(memindex + "").get();
                    likesnum.setText(""+newlikes);

                    likeButton.setBackgroundResource(R.drawable.btn_blue);

                    likeButton.setEnabled(false);

                }catch (Exception e){


                }

                //String newlikes = likebtn.getNewNumLikes();


            }
        });

            //reply_rel.setId(position);

            return rowView;


        }//end else


}