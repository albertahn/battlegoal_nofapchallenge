package com.changeandsuccess.nofapchallenge.searchActivity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.changeandsuccess.nofapchallenge.CoachProfile;
import com.changeandsuccess.nofapchallenge.R;
import com.changeandsuccess.nofapchallenge.adapter.Profile_MyMentors_Adapter;
import com.changeandsuccess.nofapchallenge.model.MyMentorsTabItem;
import com.changeandsuccess.nofapchallenge.utils.JsonReader;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by albert on 8/10/14.
 *
 * search/search_user/
 */
public class LoadSearch extends AsyncTask<String, Integer, String> {

    JSONObject jsonOb;
    String propicname;
    JSONArray jsonArray;
    Context contextman;

    Activity activity;
    View rootView;
    ListView myMentorsListView;

    final static String STREAMURL = "http://mobile.tanggoal.com/search/search_user/";


    public LoadSearch(Activity activity){

        this.activity = activity;
       // this.rootView = rootView;
        rootView = ((Activity) activity).getWindow().getDecorView().findViewById(android.R.id.content);
        myMentorsListView = (ListView) rootView.findViewById(R.id.search_results_listview);

    }

    @Override
    protected String doInBackground(String...params) {
        try {
            jsonArray = JsonReader.readJsonArrayFromUrl(STREAMURL+params[0]);


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
                    activity.startActivity(i);

                }
            });


        }else{

            Log.d("emptyarray", "sptmey man");
        }

        //now array

    }



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

}// end read