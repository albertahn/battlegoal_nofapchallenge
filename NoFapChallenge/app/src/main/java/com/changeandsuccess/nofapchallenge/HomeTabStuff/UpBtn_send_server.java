package com.changeandsuccess.nofapchallenge.HomeTabStuff;

import android.app.Activity;
import android.app.Dialog;
import android.os.AsyncTask;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.changeandsuccess.nofapchallenge.R;
import com.changeandsuccess.nofapchallenge.utils.JsonReader;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

/**
 * Created by albert on 11/9/14.
 * all rights reserved
 *  update my day number and parter number
 */
public class UpBtn_send_server extends AsyncTask<String, Integer, String>{

    Activity activity;
    String userIndex;
    JSONArray jsonObj;
    String getDayDif;

    public UpBtn_send_server(Activity activity, String userIndex, String getDayDif){
        this.activity = activity;
        this.userIndex =userIndex;
        this.getDayDif =getDayDif;
    }

    //interface to get result
    @Override
    protected String doInBackground(String...params) {

        // Log.e("dickhole", ""+messageArray[2]+" :"+context);

        try {

            final String STREAMURL = "http://mobile.tanggoal.com/battle/add_btn_press/"+userIndex+"/"+getDayDif;

            jsonObj = JsonReader.readJsonArrayFromUrl(STREAMURL);

        } catch (IOException e) {

            // TODO Auto-generated catch block
            e.printStackTrace();
        }catch (JSONException e) {
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

           /* Dialog das = new Dialog(activity);
            das.setTitle(""+ jsonObj);
            TextView tvsa = new TextView(activity);
            tvsa.setText(""+jsonObj.toString());
            das.setContentView(tvsa);
            das.show();*/


            final View rootView = ((Activity) activity).getWindow().getDecorView().findViewById(android.R.id.content);


        }catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();

        }
    }





}// end read