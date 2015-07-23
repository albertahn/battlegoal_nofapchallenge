package com.changeandsuccess.nofapchallenge.battle_stuff;

import android.app.Activity;
import android.os.AsyncTask;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.changeandsuccess.nofapchallenge.R;
import com.changeandsuccess.nofapchallenge.utils.JsonReader;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.display.RoundedBitmapDisplayer;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

/**
 * Created by albert on 10/5/14.
 */
public class LoadScoreActivity  extends AsyncTask<String, Integer, String> {



    JSONObject friendScoreInfo;

    Activity activity;
    String userID, friendID;

    View rootView;


    //stuff on the root

    String myname, friendname, myscore, friendscore, myprofile_pic, friendprofile_pic;


    //check if image loader is init

    ImageLoader imageLoader = ImageLoader.getInstance();






    public LoadScoreActivity(String userID, String friendID, Activity activity) {

        this.activity = activity;
        this.userID = userID;
        this.friendID=friendID;

        this.rootView = activity.getWindow().getDecorView().findViewById(android.R.id.content);


    }


    //interface to get result
    @Override
    protected String doInBackground(String... params) {
        try {

            final String FRIENDURL = "http://mobile.tanggoal.com/friend/new_see_battle_score/"+userID+'/'+friendID;

            friendScoreInfo = JsonReader.readJsonFromUrl(FRIENDURL);

            //set the strings

            myname =  friendScoreInfo.getString("my_name");
            friendname = friendScoreInfo.getString("friend_name");

            myscore = friendScoreInfo.getString("my_score");
            friendscore = friendScoreInfo.getString("friend_score");

            myprofile_pic = friendScoreInfo.getString("my_profile_picture");
            friendprofile_pic= friendScoreInfo.getString("friend_profile_picture");



            // return jsonArray;
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (JSONException e) {

            // TODO Auto-generated catch block
            e.printStackTrace();
            Log.d("here", "noJSON");
        }
        return null;
    }

    @Override
    protected void onPostExecute(String result) {
        super.onPostExecute(result);


        if ( !myname.equals(null)) {
            //homeListView.getContext() jsondata.getJSONObject(i).getString("friends_key_index"),
            //change the view
            if (imageLoader.isInited() == false) {

                imageLoader.init(ImageLoaderConfiguration.createDefault(activity));

            }//end if

            ImageView myprofile_picture = (ImageView) rootView.findViewById(R.id.my_image);
            ImageView friendprofile_picture = (ImageView) rootView.findViewById(R.id.friend_image);
//my image
            String myimageurl = "http://tanggoal.com/public/uploads/members_pic/" + myprofile_pic;
            String friendimageurl = "http://tanggoal.com/public/uploads/members_pic/" +friendprofile_pic;

            DisplayImageOptions options = new DisplayImageOptions.Builder()
                    .showImageForEmptyUri(R.drawable.ic_launcher)
                    .displayer(new RoundedBitmapDisplayer(150))
                    .cacheInMemory(true)
                    .considerExifParams(true)
                    .build();
            //first my img
            imageLoader.displayImage(myimageurl, myprofile_picture, options);
            imageLoader.displayImage(friendimageurl, friendprofile_picture, options);
            //now set the name and score

            TextView my_name = (TextView) rootView.findViewById(R.id.my_name_text);
            TextView friend_name = (TextView) rootView.findViewById(R.id.friend_name_text);
             my_name.setText(myname);
             friend_name.setText(friendname);

            //now score

            TextView myscore_text = (TextView) rootView.findViewById(R.id.my_score);
            TextView friendscore_text = (TextView) rootView.findViewById(R.id.friend_score);

            myscore_text.setText(myscore);
            friendscore_text.setText(friendscore);




        } else {

            Log.d("emptyarray", "sptmey man");
        }


    }// end post ex



}