package com.changeandsuccess.nofapchallenge.comment_stuff.comment_sqlite;

import android.app.Activity;
import android.os.AsyncTask;
import android.util.Log;
import android.view.View;
import android.widget.ListView;

import com.changeandsuccess.nofapchallenge.R;
import com.changeandsuccess.nofapchallenge.comment_stuff.Com_in_Adapter;
import com.changeandsuccess.nofapchallenge.comment_stuff.CommentItem;
import com.changeandsuccess.nofapchallenge.utils.JsonReader;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by albertan on 11/4/15.
 */
public class Check_if_updated extends AsyncTask<String, Integer, String> {




    JSONArray jsonArray;

    Activity activity;
    String userID;
    JSONObject jsonOb;
    View rootView;
    // ProgressBar progressBar;

    private ArrayList<CommentItem> itemsArrayList,ItemsArrayList_reply;


    public Check_if_updated(String userID, View rootView,  Activity activity) {
        this.rootView = rootView;
        this.activity = activity;
        this.userID = userID;


        // progressBar = (ProgressBar) rootView.findViewById(R.id.input_progress_bar);
        // progressBar.setVisibility(View.VISIBLE);

    }//check if updated


    //interface to get result
    @Override
    protected String doInBackground(String... params) {

        try {

            final String STREAMURL = activity.getResources().getString(R.string.default_comments_url);
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
    }//string

    @Override
    protected void onPostExecute(String result) {
        super.onPostExecute(result);

        if (jsonArray != null) {
            //homeListView.getContext()

            //insert into sqlite(jsonArray);
            insertToSqlite(jsonArray);

            itemsArrayList = generateData(jsonArray);
            ItemsArrayList_reply = generateReply(jsonArray);
            Com_in_Adapter proAdapter = new Com_in_Adapter(activity,itemsArrayList,ItemsArrayList_reply, userID);
            final ListView listView = (ListView) rootView.findViewById(R.id.message_frag_list);
            if(listView !=null){
                listView.setAdapter(proAdapter);

            }//if

        } else {
            Log.d("emptyarray", "sptmey man");
        }

    }// end post ex

    public void insertToSqlite(JSONArray jsondata){
        LanguageAll_db languageAll_db = new LanguageAll_db(activity);

        //database

        /*
        *
        *  UserDatabase entry = new UserDatabase(context);
            entry.open();
            entry.createEntry(user_index, username, email, password, profile_picture, FID,level, text_profile, exp_points);
            entry.close();
        * */

        for (int i = 0; i < jsondata.length(); i++) {


            try {


                languageAll_db.open();
                languageAll_db.createEntry(
                        jsondata.getJSONObject(i).getString("members_index"),
                        jsondata.getJSONObject(i).getString("profile_picture"),
                        jsondata.getJSONObject(i).getString("username"),
                        jsondata.getJSONObject(i).getString("comment_index"),
                        jsondata.getJSONObject(i).getString("comment_text"),

                        jsondata.getJSONObject(i).getString("courses_index"),
                        jsondata.getJSONObject(i).getString("comment_picture"),
                        jsondata.getJSONObject(i).getString("comment_text"),
                        jsondata.getJSONObject(i).getString("reply_num"),
                        jsondata.getJSONObject(i).getString("likes"),

                        jsondata.getJSONObject(i).getString("new_type"),
                        jsondata.getJSONObject(i).getString("course_name"),
                        jsondata.getJSONObject(i).getString("course_privacy"),

                        jsondata.getJSONObject(i).getString("timestamp"),

                        jsondata.getJSONObject(i).getString("reply_to")
                );
                languageAll_db.close();
                // }//end if
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }


    }//


    ArrayList<CommentItem> generateData(JSONArray jsondata) {
        ArrayList<CommentItem> items = new ArrayList<CommentItem >();


        for (int i = 0; i < jsondata.length(); i++) {
            try {
                //check if course comment
                //if(jsondata.getJSONObject(i).getString("courses_index")!="") {
                if (jsondata.getJSONObject(i).getInt("reply_to")==0) {
                    items.add(new CommentItem(
                            jsondata.getJSONObject(i).getString("members_index"),
                            jsondata.getJSONObject(i).getString("profile_picture"),
                            jsondata.getJSONObject(i).getString("username"),
                            jsondata.getJSONObject(i).getString("comment_index"),
                            jsondata.getJSONObject(i).getString("comment_text"),
                            jsondata.getJSONObject(i).getInt("reply_to"),
                            jsondata.getJSONObject(i).getString("reply_num"),
                            jsondata.getJSONObject(i).getString("likes"),
                            jsondata.getJSONObject(i).getString("timestamp")
                    ));
                }
                // }//end if
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return items;
    }// end generate


    ArrayList<CommentItem> generateReply(JSONArray jsondata) {
        ArrayList<CommentItem> items = new ArrayList<CommentItem >();


        for (int i = 0; i < jsondata.length(); i++) {
            try {
                //check if course comment
                //if(jsondata.getJSONObject(i).getString("courses_index")!="") {
                if (jsondata.getJSONObject(i).getInt("reply_to")!=0) {
                    items.add(new CommentItem(
                            jsondata.getJSONObject(i).getString("members_index"),
                            jsondata.getJSONObject(i).getString("profile_picture"),
                            jsondata.getJSONObject(i).getString("username"),
                            jsondata.getJSONObject(i).getString("comment_index"),
                            jsondata.getJSONObject(i).getString("comment_text"),
                            jsondata.getJSONObject(i).getInt("reply_to"),
                            jsondata.getJSONObject(i).getString("reply_num"),
                            jsondata.getJSONObject(i).getString("likes"),
                            jsondata.getJSONObject(i).getString("timestamp")
                    ));
                }
                // }//end if
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return items;
    }// end generate

} //end class