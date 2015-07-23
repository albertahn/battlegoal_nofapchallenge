package com.changeandsuccess.nofapchallenge.battle_stuff;

import android.app.Activity;
import android.app.Dialog;
import android.os.AsyncTask;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.changeandsuccess.nofapchallenge.R;
import com.changeandsuccess.nofapchallenge.coach_profile.BattleSend;
import com.changeandsuccess.nofapchallenge.utils.JsonReader;
import com.changeandsuccess.nofapchallenge.utils.UserDatabase;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

/**
 * Created by albert on 11/14/14.
 */
public class DeleteBattle_from_tab extends AsyncTask<String, Integer, String> {

    final static String MENTORSHIPURL = "http://mobile.tanggoal.com/friend/delete_battle_friend/";
    View profileView;
    Activity activity;
    String coachIndex;
    JSONObject jsonOb;
    String resulting;
    String myindex;


    public DeleteBattle_from_tab(String coachID, View profileView, Activity activity){
        this.profileView = profileView;
        this.activity = activity;
        this.coachIndex = coachID;


        UserDatabase info = new UserDatabase(activity);
        info.open();
        String[][] data = info.getData();
        info.close();

        myindex = data[0][1];


       /* Dialog d = new Dialog(activity);
        d.setTitle(myindex +"battle"+coachIndex);

        d.show();*/

    }

    //interface to get result
    @Override
    protected String doInBackground(String...params) {


        try {

            jsonOb = JsonReader.readJsonFromUrl(MENTORSHIPURL + myindex + '/' + coachIndex);

            //   resulting = jsonOb.toString();



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

        Dialog d = new Dialog(activity);
        d.setTitle("sending battle"+jsonOb.toString());
        d.show();
        profileView.setVisibility(View.INVISIBLE);

    }// end post ex

} //end
