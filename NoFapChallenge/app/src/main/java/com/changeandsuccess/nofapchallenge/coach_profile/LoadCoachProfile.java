package com.changeandsuccess.nofapchallenge.coach_profile;

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
 * Created by albert on 7/15/14.
 */
public class LoadCoachProfile extends AsyncTask<String, Integer, String> {

    final static String STREAMURL = "http://mobile.tanggoal.com/user/user_profile/";

    View profileView;

    Activity activity;
    String coachIndex;
    JSONObject jsonOb;

    TextView coachName;
    String coachNameString;



    public LoadCoachProfile(String coachID, View profileView, Activity activity){
        this.profileView = profileView;
        this.activity = activity;
        this.coachIndex = coachID;

    }

    //interface to get result
    @Override
    protected String doInBackground(String...params) {

        try {

           jsonOb = JsonReader.readJsonFromUrl(STREAMURL+coachIndex);

            coachNameString = jsonOb.getString("username");

            //return jsonArray;
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

        try {

            ImageView profile_photo = (ImageView) profileView.findViewById(R.id.myprofile_picture);

            String picname = jsonOb.getString("profile_picture");

            Log.e("dicksuck", "cock"+jsonOb);

            String imageurl = "http://tanggoal.com/public/uploads/members_pic/" + picname;



            //imageloader.displayImage(url, view);

            DisplayImageOptions options = new DisplayImageOptions.Builder()
                    .showImageForEmptyUri(R.drawable.ic_launcher)
                    .displayer(new RoundedBitmapDisplayer(250))
                    .cacheOnDisc(true)
                    .cacheInMemory(true)
                    .considerExifParams(true)
                    .build();


            ImageLoader imageloader = ImageLoader.getInstance();

            if(!imageloader.isInited()){

            imageloader.init(ImageLoaderConfiguration.createDefault(activity));
            }
            imageloader.displayImage(imageurl, profile_photo, options);



            //set the name of person

            coachName = (TextView) profileView.findViewById(R.id.user_name_profile);

            coachName.setText(coachNameString);

        } catch (JSONException e) {

            e.printStackTrace();

        }// end catch

    }// end post ex

}
