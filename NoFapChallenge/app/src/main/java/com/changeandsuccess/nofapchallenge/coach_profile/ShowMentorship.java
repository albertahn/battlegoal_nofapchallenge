package com.changeandsuccess.nofapchallenge.coach_profile;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.changeandsuccess.nofapchallenge.LoginActivity;
import com.changeandsuccess.nofapchallenge.LoginHelper;
import com.changeandsuccess.nofapchallenge.R;
import com.changeandsuccess.nofapchallenge.model.LoginItem;
import com.changeandsuccess.nofapchallenge.utils.JsonReader;
import com.changeandsuccess.nofapchallenge.utils.UserDatabase;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by albert on 8/12/14.
 */
public class ShowMentorship extends AsyncTask<String, Integer, String> {

    final static String STREAMURL = "http://mobile.tanggoal.com/mentorship/show_mentorship/";
    View profileView;
    Activity activity;
    String coachIndex;
    JSONObject jsonOb;
    ArrayList<LoginItem> generatedLoginItem;
    String myindex;
    String coachStatus;



    public ShowMentorship(String coachID, View profileView, Activity activity){
        this.profileView = profileView;

        this.activity = activity;
        this.coachIndex = coachID;


        UserDatabase info = new UserDatabase(activity);
        info.open();
        String[][] data = info.getData();
        info.close();
//check login
        String[][] loginData = new LoginHelper().checkLogin(activity);
        generatedLoginItem = generateData(loginData);

        if(generatedLoginItem.toString() !="[]") {

            myindex = data[0][1];

        }else{

            Intent i = new Intent(activity,
                    LoginActivity.class);
            activity.startActivity(i);
        }


    }

    //interface to get result
    @Override
    protected String doInBackground(String...params) {
        try {

            jsonOb = JsonReader.readJsonFromUrl(STREAMURL+myindex+'/'+ coachIndex);

            coachStatus = jsonOb.getString("status");

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



        if(coachStatus==null){//is empty

            follow_btn.setText("follow");

        }else{

            follow_btn.setText("FOLLOWING");

            follow_btn.setBackgroundResource(R.drawable.tab_bg_selected);


            follow_btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {


                    new UnFollowButton(coachIndex, profileView, activity).execute();

                }
            });



        }


    }// end post ex

        public static ArrayList<LoginItem> generateData(String[][] data){
            ArrayList<LoginItem> items = new ArrayList<LoginItem>();

            for (int i =0; i<data.length ; i++){

                items.add(new LoginItem( data[i][1], data[i][2], data[i][3],data[i][4],data[i][5],data[i][6],data[i][7]));

            }
            return items;
        } //end generate

}