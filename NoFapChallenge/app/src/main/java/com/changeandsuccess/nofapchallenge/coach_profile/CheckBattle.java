package com.changeandsuccess.nofapchallenge.coach_profile;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.changeandsuccess.nofapchallenge.LoginActivity;
import com.changeandsuccess.nofapchallenge.LoginHelper;
import com.changeandsuccess.nofapchallenge.R;
import com.changeandsuccess.nofapchallenge.model.LoginItem;
import com.changeandsuccess.nofapchallenge.utils.JsonReader;
import com.changeandsuccess.nofapchallenge.utils.UserDatabase;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by albert on 10/10/14.
 */
public class CheckBattle extends AsyncTask<String, Integer, String> {

    final static String STREAMURL = "http://mobile.tanggoal.com/friend/check_if_battle_sent/";
    View profileView;
    Activity activity;
    String coachIndex;
    //JSONObject jsonOb;
    String status;
    JSONArray jsonArray;
    ArrayList<LoginItem> generatedLoginItem;
    String myindex;
    String coachStatus;

    Button battleBTN;



    public CheckBattle(String coachID, View profileView, Activity activity){
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

            jsonArray = JsonReader.readJsonArrayFromUrl(STREAMURL + myindex + '/' + coachIndex);

                    status =  jsonArray.getJSONObject(0).getString("status");
//                    /jsonArray.getJSONObject(0).getString("my_index")
           // coachStatus = jsonOb.getString("status");

            return status;
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


        battleBTN = (Button) profileView.findViewById(R.id.battle_btn);


        if(status.equals("not_sent_yet")){

            battleBTN.setText("Send Battle Request");
//click listeder for battle
            setClickSendBattle(battleBTN);

        }else if(status.equals("pending")){

            battleBTN.setText("pending");



        }else if(status.equals("accepted")){

            battleBTN.setText("Delete Battle");

//click listeder for delete battle
            setDeleteListener(battleBTN);

        }


    }// end post ex

    public static ArrayList<LoginItem> generateData(String[][] data){
        ArrayList<LoginItem> items = new ArrayList<LoginItem>();

        for (int i =0; i<data.length ; i++){

            items.add(new LoginItem( data[i][1], data[i][2], data[i][3],data[i][4],data[i][5],data[i][6],data[i][7]));

        }
        return items;
    } //end generate


    public void setClickSendBattle(View button){

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Dialog d = new Dialog(activity);
                d.setTitle("sent battle");
                d.show();

               new BattleSend(coachIndex,profileView, activity).execute();

            }
        });

    }//end oclick listener

    public void setDeleteListener(View button){

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                new DeleteBattle(coachIndex,profileView, activity).execute();


            }
        });
    }

}