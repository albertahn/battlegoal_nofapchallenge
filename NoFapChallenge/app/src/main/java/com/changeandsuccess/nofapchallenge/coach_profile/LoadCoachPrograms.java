package com.changeandsuccess.nofapchallenge.coach_profile;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

//import com.changeandsuccess.nofapchallenge.ProductActivity;
//import com.changeandsuccess.nofapchallenge.adapter.ProfileMyCoursesAdapter;
//import com.changeandsuccess.nofapchallenge.model.MyCoursesItem;
import com.changeandsuccess.nofapchallenge.utils.JsonReader;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by albert on 8/10/14.
 */
public class LoadCoachPrograms extends AsyncTask<String, Integer, String> {

    JSONObject jsonOb;

    JSONArray jsonArray;
Activity activity;
    ListView coachProListView;

    public LoadCoachPrograms(Activity activity, ListView listview){

        this.coachProListView = listview;

        this.activity = activity;

    }

    @Override
    protected String doInBackground(String... params) {
        try {

            String STREAMURL = "http://mobile.tanggoal.com/user/user_courses/" + params[0];
            jsonArray = JsonReader.readJsonArrayFromUrl(STREAMURL);

            String userprofileURL = "http://mobile.tanggoal.com/user/user_profile/";
            jsonOb = JsonReader.readJsonFromUrl(userprofileURL+params[0]);


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



        //now array
    }//end


}// end read