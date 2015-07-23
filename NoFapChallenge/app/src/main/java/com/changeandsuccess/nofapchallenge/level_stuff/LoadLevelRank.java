package com.changeandsuccess.nofapchallenge.level_stuff;

import android.app.Activity;
import android.os.AsyncTask;
import android.util.Log;
import android.view.View;
import android.widget.ListView;

import com.changeandsuccess.nofapchallenge.R;
import com.changeandsuccess.nofapchallenge.adapter.LevelRankAdapter;
import com.changeandsuccess.nofapchallenge.model.RankItem;
import com.changeandsuccess.nofapchallenge.utils.JsonReader;

import org.json.JSONArray;
import org.json.JSONException;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by albert on 10/15/14.
 */
public class LoadLevelRank  extends AsyncTask<String, Integer, String> {

    final static String MENTORSHIPURL = "http://mobile.tanggoal.com/level/level_ranking/";
    View levelView;
    Activity activity;
    JSONArray jsonarr;


    public LoadLevelRank(View levelView, Activity activity){
        this.levelView = levelView;

        this.activity = activity;



    }

    //interface to get result
    @Override
    protected String doInBackground(String...params) {


        try {

            jsonarr = JsonReader.readJsonArrayFromUrl(MENTORSHIPURL);




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



        LevelRankAdapter proAdapter = new LevelRankAdapter(activity, generateData(jsonarr));

        final ListView listView = (ListView) levelView.findViewById(R.id.rank_list);

        if(listView !=null){

            listView.setAdapter(proAdapter);
        }


    }// end post ex



    ArrayList<RankItem> generateData(JSONArray jsondata) {

        ArrayList<RankItem> items = new ArrayList<RankItem>();

        for (int i = 0; i < jsondata.length(); i++) {


            try {
                //check if course comment
                //if(jsondata.getJSONObject(i).getString("courses_index")!="") {

                items.add(new RankItem(
                        jsondata.getJSONObject(i).getString("level"),
                        jsondata.getJSONObject(i).getString("COUNT(*)")

                ));

                // }//end if
            } catch (JSONException e) {

                e.printStackTrace();

            }

        }//end for

        items.add(new RankItem(
                "Use the App More to Unlock NEW LEVEL!",
                ""

        ));

        items.add(new RankItem(
                "Use the App More to Unlock NEW LEVEL!",
                ""

        ));
        items.add(new RankItem(
                "Use the App More to Unlock NEW LEVEL!",
                ""

        ));
        items.add(new RankItem(
                "Use the App More to Unlock NEW LEVEL!",
                ""

        ));


        return items;

    }// end generate
}
