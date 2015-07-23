package com.changeandsuccess.nofapchallenge.battle_stuff;

import android.app.Activity;
import android.app.Dialog;
import android.os.AsyncTask;
import android.util.Log;
import android.view.View;
import android.widget.ListView;

import com.changeandsuccess.nofapchallenge.R;
import com.changeandsuccess.nofapchallenge.model.Battle_partner_item;
import com.changeandsuccess.nofapchallenge.utils.JsonReader;
import com.changeandsuccess.nofapchallenge.utils.UserDatabase;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by albert on 10/3/14.
 */
public class AcceptBattle extends AsyncTask<String, Integer, String> {

    JSONArray jsonArray;

    Activity activity;
    String friendID;
    JSONObject jsonOb;
    View rootView;
    // ProgressBar progressBar;
    String myindex;
    final static String MENTORSHIPURL = "http://mobile.tanggoal.com/friend/accept_battle/";

    public AcceptBattle(String userID, View rootView, Activity activity) {
        this.rootView = rootView;
        this.activity = activity;
        this.friendID = userID;

        UserDatabase info = new UserDatabase(activity);
        info.open();
        String[][] data = info.getData();
        info.close();

        myindex = data[0][1];


    }


    //interface to get result
    @Override
    protected String doInBackground(String... params) {
        try {


            jsonOb = JsonReader.readJsonFromUrl(MENTORSHIPURL + myindex + '/' + friendID);


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
    }//end do in background

    @Override
    protected void onPostExecute(String result) {
        super.onPostExecute(result);


        if (jsonOb != null) {

          /*  Dialog d = new Dialog(activity);
            d.setTitle(""+jsonOb.toString());
            d.show();*/
            rootView.setVisibility(View.INVISIBLE);
        } else {

            Log.d("emptyarray", "sptmey man");
        }


    }// end post ex





}