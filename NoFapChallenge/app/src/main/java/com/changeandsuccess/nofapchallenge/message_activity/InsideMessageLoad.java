package com.changeandsuccess.nofapchallenge.message_activity;

import android.app.Activity;
import android.app.Dialog;
import android.os.AsyncTask;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.ActionBar;
import android.util.Log;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import com.changeandsuccess.nofapchallenge.R;
import com.changeandsuccess.nofapchallenge.adapter.MessageInsideAdapter;
import com.changeandsuccess.nofapchallenge.model.MessageTabItem;
import com.changeandsuccess.nofapchallenge.utils.JsonReader;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by albert on 7/21/14.
 */
public class InsideMessageLoad extends AsyncTask<String, Integer, String> {


    final static String STREAMURL = "http://mobile.tanggoal.com/message/show_this_message/";
    JSONArray jsonArray;

    Activity activity;
   String other_guy_index, my_index;
    JSONObject jsonOb;
    View rootView;

    TextView coachName;
    String coachNameString;

    ActionBar actionbar;


    public InsideMessageLoad(String my_index, String other_guy_index, View rootView, Activity activity, ActionBar actionbar) {

        this.rootView = rootView;
        this.activity = activity;
        this.other_guy_index = other_guy_index;
        this.my_index = my_index;
        this.actionbar = actionbar;


       /* Dialog d = new Dialog(activity);
        TextView t = new TextView(activity);
        t.setText(""+rootView);
        d.setContentView(t);
        d.show();*/

    }


    //interface to get result
    @Override
    protected String doInBackground(String... params) {

        try {

            jsonArray = JsonReader.readJsonArrayFromUrl(STREAMURL + other_guy_index+'/'+my_index);

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

        if (jsonArray != null) {

            MessageInsideAdapter proAdapter = new MessageInsideAdapter(activity, generateData(jsonArray), actionbar);
            ListView listView = (ListView) rootView.findViewById(R.id.message_frag_list);
            listView.setAdapter(proAdapter);
//scroll to bottom
            listView.setSelection(listView.getCount() - 1);

        } else {

            Log.d("emptyarray", "sptmey man");
        }



        //actionbar.setTitle();

    }// end post ex



    ArrayList<MessageTabItem> generateData(JSONArray jsondata) {

        ArrayList<MessageTabItem> items = new ArrayList<MessageTabItem>();

        for (int i = 0; i < jsondata.length(); i++) {


            try {
                //check if course comment
                items.add(new MessageTabItem(
                        jsondata.getJSONObject(i).getString("messages_index"),
                        jsondata.getJSONObject(i).getString("messages_body"),
                        jsondata.getJSONObject(i).getString("has_message_index"),
                        jsondata.getJSONObject(i).getString("members_index"),
                        jsondata.getJSONObject(i).getString("profile_picture"),
                        jsondata.getJSONObject(i).getString("username"),
                        jsondata.getJSONObject(i).getString("timestamp"),
                        " ",
                        " ",
                        ""
                ));

            } catch (JSONException e) {

                e.printStackTrace();

            }


        }

        return items;

    }// end generate





}