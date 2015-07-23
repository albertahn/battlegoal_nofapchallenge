package com.changeandsuccess.nofapchallenge.battle_stuff;

import android.app.Activity;
import android.app.Dialog;
import android.os.AsyncTask;
import android.util.Log;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import com.changeandsuccess.nofapchallenge.R;
import com.changeandsuccess.nofapchallenge.adapter.BattleRequests_Adapter;
import com.changeandsuccess.nofapchallenge.model.MyMentorsTabItem;
import com.changeandsuccess.nofapchallenge.utils.JsonReader;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by albert on 11/10/14.
 */
public class LoadRequests extends AsyncTask<String, Integer, String> {



    JSONArray jsonArray;

    Activity activity;
    String userID;
    JSONObject jsonOb;
    View rootView;
    // ProgressBar progressBar;


    public LoadRequests(String userID, View rootView, Activity activity) {
        this.rootView = rootView;
        this.activity = activity;
        this.userID = userID;

    }


    //interface to get result
    @Override
    protected String doInBackground(String... params) {
        try {

            final String STREAMURL = "http://mobile.tanggoal.com/friend/get_battle_request_list/"+userID;

            jsonArray = JsonReader.readJsonArrayFromUrl(STREAMURL);

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


        if (jsonArray != null) {

            //homeListView.getContext()

            BattleRequests_Adapter proAdapter = new BattleRequests_Adapter(activity, generateData(jsonArray), userID);

            ListView listView = (ListView) rootView.findViewById(R.id.message_frag_list);

            listView.setAdapter(proAdapter);

            //  progressBar.setVisibility(View.GONE);



            Dialog d = new Dialog(activity);
            TextView t = new TextView(activity);
            t.setText("Generated"+generateData(jsonArray));
            d.setContentView(t);
           // d.show();

        } else {

            Log.d("emptyarray", "sptmey man");
        }


    }// end post ex


    ArrayList<MyMentorsTabItem> generateData(JSONArray jsondata) {




        ArrayList<MyMentorsTabItem> items = new ArrayList<MyMentorsTabItem>();

        for (int i = 0; i < jsondata.length(); i++){

            try {

                //check if course comment
                //if(jsondata.getJSONObject(i).getString("courses_index")!="") {

                items.add(new MyMentorsTabItem(
                        jsondata.getJSONObject(i).getString("username"),
                        jsondata.getJSONObject(i).getString("index"),
                        jsondata.getJSONObject(i).getString("profile_picture")

                ));

                // }//end if
            } catch (JSONException e) {

                e.printStackTrace();

            }

        }//end

        return items;

    }// end generate



}//end load battle request