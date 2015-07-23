package com.changeandsuccess.nofapchallenge.battle_stuff;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.changeandsuccess.nofapchallenge.utils.JsonReader;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

/**
 * Created by albert on 10/3/14.
 */
public class RequestBattle extends AsyncTask<String, Integer, String> {



    JSONObject jsonobj;

    Context context;
    String userID, friendID;
    JSONObject jsonOb;
    View rootView;
String jresult;
    Button see_score_btn, accept_btn, request_battle_btn;


    public RequestBattle(String userID, String friendID, View rootView, Context context) {
        this.rootView = rootView;
        this.context = context;
        this.userID = userID;
        this.friendID = friendID;



    }


    //interface to get result
    @Override
    protected String doInBackground(String... params) {
        try {

            final String STREAMURL = "http://mobile.tanggoal.com/friend/battle_friend/"+userID+'/'+friendID;

            jsonobj = JsonReader.readJsonFromUrl(STREAMURL);

            jresult= jsonobj.getString("result").toString();


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



        }// end post ex


    }// end post ex





