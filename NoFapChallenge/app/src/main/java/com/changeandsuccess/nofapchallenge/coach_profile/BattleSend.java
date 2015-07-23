package com.changeandsuccess.nofapchallenge.coach_profile;

import android.app.Activity;
import android.app.Dialog;
import android.os.AsyncTask;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.changeandsuccess.nofapchallenge.R;
import com.changeandsuccess.nofapchallenge.utils.JsonReader;
import com.changeandsuccess.nofapchallenge.utils.UserDatabase;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

/**
 * Created by albert on 10/2/14.
 */

public class BattleSend extends AsyncTask<String, Integer, String> {

    final static String MENTORSHIPURL = "http://mobile.tanggoal.com/friend/battle_friend/";
    View profileView;
    Activity activity;
    String coachIndex;
    JSONObject jsonOb;
    String resulting;
    String myindex;


    public BattleSend(String coachID, View profileView, Activity activity){
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


       Button battleBTN = (Button) profileView.findViewById(R.id.battle_btn);

battleBTN.setText("Pending");



        battleBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Toast toast = Toast.makeText(activity,
                        "Waiting Response. ", Toast.LENGTH_LONG);
                toast.setGravity(Gravity.CENTER, 0, 0);
                LinearLayout toastView = (LinearLayout) toast.getView();
                ImageView imageCodeProject = new ImageView(activity);
                imageCodeProject.setImageResource(R.drawable.ic_launcher);
                toastView.addView(imageCodeProject, 0);
                toast.show();

            }
        });


       /* Dialog d = new Dialog(activity);
        d.setTitle("sending battle"+jsonOb.toString());
        d.show();*/




    }// end post ex

} //end
