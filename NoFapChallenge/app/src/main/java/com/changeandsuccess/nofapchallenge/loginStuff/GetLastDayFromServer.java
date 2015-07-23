package com.changeandsuccess.nofapchallenge.loginStuff;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.TextView;

import com.changeandsuccess.nofapchallenge.LoginActivity;
import com.changeandsuccess.nofapchallenge.MainActivity;
import com.changeandsuccess.nofapchallenge.utils.DatabaseStuff;
import com.changeandsuccess.nofapchallenge.utils.JsonReader;
import com.changeandsuccess.nofapchallenge.utils.UserDatabase;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by albert on 1/20/15.
 */
public class GetLastDayFromServer extends AsyncTask<String, Integer, String> {


    String jsonString;

    String userID;

    //Activity activity;

    JSONObject jsonObj;

    String success, username;

    Context context;

    public GetLastDayFromServer(Context context, String userID){

        this.userID = userID;
        //this.activity = activity;
        this.context =context;

    }

    //interface to get result
    @Override
    protected String doInBackground(String...params) {
        try {

            final String STREAMURL = "http://mobile.tanggoal.com/level/get_last_success_day/"+userID;

            jsonObj = JsonReader.readJsonFromUrl(STREAMURL);


            success =  jsonObj.get("success").toString();
            username =  jsonObj.get("members_username").toString();


        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }catch (JSONException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
                Log.d("jsonhere", "noJSON");
            }
        return null;
    }

    @Override
    protected void onPostExecute(String result) {

        super.onPostExecute(result);
        try {

            //Log.d("jsonfuck", ""+ username + email+password+profile_picture+FID);

            DatabaseStuff entry = new DatabaseStuff(context);
            entry.open();
            entry.createEntry(success, "Your Success", "2014-02-12 03:22:11");
            entry.close();

            if(username!=null){

                //start activity
                Intent i = new Intent(context,
                        MainActivity.class);

                context.startActivity(i);


            }else{


                Dialog d = new Dialog(context);
                d.setTitle("Email or Passord is wrong");
                TextView tv = new TextView(context);
                tv.setText("");
                d.setContentView(tv);
                d.show();
            }

        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }





}// end read