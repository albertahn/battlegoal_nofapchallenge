package com.changeandsuccess.nofapchallenge.coaches_tab_stuff;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.changeandsuccess.nofapchallenge.CoachProfile;
import com.changeandsuccess.nofapchallenge.R;
import com.changeandsuccess.nofapchallenge.adapter.FeaturedListAdapter;
import com.changeandsuccess.nofapchallenge.model.FeaturedItem;
import com.changeandsuccess.nofapchallenge.utils.JsonReader;

import org.json.JSONArray;
import org.json.JSONException;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by albert on 11/26/14.
 */
public class LoadCoachesTab  extends AsyncTask<String, Integer, String> {

ListView featuredListView;
    Activity activity;
    JSONArray jsonArray;
    FeaturedListAdapter proAdapter;

    final static String STREAMURL = "http://mobile.tanggoal.com/feature/show_featured/";


    public LoadCoachesTab( View rootView, Activity activity){


        featuredListView = (ListView) rootView.findViewById(R.id.featuredListView);


        this.activity = activity;

    }

    @Override
    protected String doInBackground(String...params) {
        try {


            jsonArray = JsonReader.readJsonArrayFromUrl(STREAMURL);

            //userName= jsonArray.getJSONObject(0).getString("username");

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


        if ( jsonArray != null && proAdapter == null) {

            proAdapter = new FeaturedListAdapter(featuredListView.getContext(), generateData(jsonArray));


            featuredListView.setAdapter(proAdapter);

            featuredListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
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

    }//end

    //getnerate
    ArrayList<FeaturedItem> generateData(JSONArray jsondata){

        ArrayList<FeaturedItem> items = new ArrayList<FeaturedItem>();

        for (int i =0; i<jsondata.length() ; i++){

            try{

                items.add(new FeaturedItem(jsondata.getJSONObject(i).getString("members_index") ,
                        jsondata.getJSONObject(i).getString("profile_picture"),
                        jsondata.getJSONObject(i).getString("publish"),
                        jsondata.getJSONObject(i).getString("username"),
                        jsondata.getJSONObject(i).getString("blurb"),
                        jsondata.getJSONObject(i).getString("category"),
                        jsondata.getJSONObject(i).getString("follow_num")));

            }catch(JSONException e){

                e.printStackTrace();

            }



        }

        return items;

    }




}// end read