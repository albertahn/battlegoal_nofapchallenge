package com.changeandsuccess.nofapchallenge.coach_profile;

import android.app.Activity;
import android.app.Dialog;
import android.os.AsyncTask;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.changeandsuccess.nofapchallenge.R;
import com.changeandsuccess.nofapchallenge.utils.UserDatabase;
import com.changeandsuccess.nofapchallenge.utils.JsonReader;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

/**
 * Created by albert on 8/10/14.
 */
public class FollowButton extends AsyncTask<String, Integer, String> {

    final static String MENTORSHIPURL = "http://mobile.tanggoal.com/mentorship/add_mentor/";
    View profileView;
    Activity activity;
    String coachIndex;
    JSONObject jsonOb;

    String resulting;
    String myindex;


    public FollowButton(String coachID, View profileView, Activity activity){
        this.profileView = profileView;

        this.activity = activity;
        this.coachIndex = coachID;


        UserDatabase info = new UserDatabase(activity);
        info.open();
        String[][] data = info.getData();
        info.close();

         myindex = data[0][1];

    }

    //interface to get result
    @Override
    protected String doInBackground(String...params) {
        try {

            jsonOb = JsonReader.readJsonFromUrl(MENTORSHIPURL+myindex+'/' + coachIndex);

            resulting = jsonOb.toString();

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

        Button follow_btn =( Button) profileView.findViewById(R.id.follow_btn);

        if(resulting==null){
            Dialog d = new Dialog(activity);
            d.setTitle("Something Wrong...Check Internet Connection ");
            d.show();

        }else{


            follow_btn.setText("FOLLOWING");

            follow_btn.setBackgroundResource(R.drawable.main_header_selector);

            follow_btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    new UnFollowButton(coachIndex, profileView, activity).execute();
                }
            });

        }


    }// end post ex

}

