package com.changeandsuccess.nofapchallenge;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.changeandsuccess.nofapchallenge.adapter.Profile_MyMentors_Adapter;
import com.changeandsuccess.nofapchallenge.model.MyMentorsTabItem;
import com.changeandsuccess.nofapchallenge.utils.DatabaseStuff;
import com.changeandsuccess.nofapchallenge.utils.JsonReader;

import org.json.JSONArray;
import org.json.JSONException;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by albert on 7/9/14.
 */
public class MySubscribersList extends ActionBarActivity{

    String userIndex;
    Activity activity;

    ListView myMentorsListView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.mycoachesactivity_____my_subscribers_list);

        myMentorsListView = (ListView) findViewById(R.id.my_sub_listview);

        // get the action bar
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);


        DatabaseStuff info = new DatabaseStuff(this);
        info.open();
        String[][] data = info.getData();
        info.close();

        userIndex = data[0][1];

        activity = this;

       new LoadMySubscribers().execute(userIndex);


    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:

                finish();
                //NavUtils.navigateUpFromSameTask(this);
                return true;
            default:

                finish();
                return super.onOptionsItemSelected(item);
        }
    }

    //load ppl


    public class LoadMySubscribers extends AsyncTask<String, Integer, String> {

        final static String STREAMURL = "http://mobile.tanggoal.com/follow/show_user_followers/";

        JSONArray jsonArray;

        @Override
        protected String doInBackground(String...params) {
            try {
                jsonArray = JsonReader.readJsonArrayFromUrl(STREAMURL + params[0]);


                //return params[0];
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

            if ( jsonArray != null) {

                Profile_MyMentors_Adapter mentAdapter = new Profile_MyMentors_Adapter(myMentorsListView.getContext(), generateData(jsonArray));

                myMentorsListView.setAdapter(mentAdapter);

                //click action

                myMentorsListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                        int cock = view.getId();

                        Intent i = new Intent(activity,
                                CoachProfile.class);

                        i.putExtra("coachID", ""+cock);
                        // i.putExtra("coachName", ""+)
                        startActivity(i);


                    }
                });


            }else{

                Log.d("emptyarray", "sptmey man");
            }

            //now array

        }

    }// end read


    ArrayList<MyMentorsTabItem> generateData(JSONArray jsondata){

        ArrayList<MyMentorsTabItem> items = new ArrayList<MyMentorsTabItem>();

        for (int i =0; i<jsondata.length() ; i++){


            try{

                items.add(new MyMentorsTabItem(jsondata.getJSONObject(i).getString("username"),
                        jsondata.getJSONObject(i).getString("index"),
                        jsondata.getJSONObject(i).getString("profile_picture")
                ));

            }catch(JSONException e){

                e.printStackTrace();

            }



        }

        return items;

    }// end getnerate item
}
