package com.changeandsuccess.nofapchallenge.message_activity;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.changeandsuccess.nofapchallenge.MessageInside;
import com.changeandsuccess.nofapchallenge.R;
import com.changeandsuccess.nofapchallenge.utils.JsonReader;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by albert on 7/20/14.
 * list of list_message_summary
 */
public class LoadMessageFrag extends AsyncTask<String, Integer, String> {

    public static final String MY_PREFS_NAME = "MyPrefsFile";

    final static String STREAMURL = "http://mobile.tanggoal.com/message/list_message_summary/";
    JSONArray jsonArray;

    Activity activity;
    String userID;
    JSONObject jsonOb;
    View rootView;
    int unread_message;



    public LoadMessageFrag(String userID, View rootView, Activity activity){
        this.rootView = rootView;
        this.activity = activity;
        this.userID = userID;

    }// load message


    //interface to get result
    @Override
    protected String doInBackground(String...params) {
        try {

            jsonArray = JsonReader.readJsonArrayFromUrl(STREAMURL + userID);

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



        if ( jsonArray != null) {
            //homeListView.getContext()

            MessageTabAdapter proAdapter = new MessageTabAdapter(activity, generateData(jsonArray));
            ListView listView = (ListView) rootView.findViewById(R.id.message_frag_list);

            proAdapter.notifyDataSetChanged();
            listView.setAdapter(proAdapter);

            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                    int cock = view.getId();
                    //String  otherguyname = view.getTag(position).toString();

                    Intent i = new Intent(activity,
                            MessageInside.class);

                    i.putExtra("other_guy_index", "" + cock);
                    //i.putExtra("otherguyname", otherguyname);

                     i.putExtra("prevActivity", "MainActivity");
                    activity.startActivity(i);

                }
            });



        }else{

            Log.d("emptyarray", "sptmey man");
        }




    }// end post ex


    ArrayList<MessageTabItem> generateData(JSONArray jsondata){

        ArrayList<MessageTabItem> items = new ArrayList<MessageTabItem>();


        for (int i =0; i<jsondata.length() ; i++){

            try{

                //check if course comment
                //if(jsondata.getJSONObject(i).getString("courses_index")!="") {

                    items.add(new MessageTabItem(
                            jsondata.getJSONObject(i).getString("messages_index"),
                            jsondata.getJSONObject(i).getString("messages_body"),
                            jsondata.getJSONObject(i).getString("has_message_index"),
                            jsondata.getJSONObject(i).getString("members_index"),
                            jsondata.getJSONObject(i).getString("profile_picture"),
                            jsondata.getJSONObject(i).getString("username"),
                            jsondata.getJSONObject(i).getString("timestamp"),
                            jsondata.getJSONObject(i).getString("other_person_pic"),
                            jsondata.getJSONObject(i).getString("other_person_name"),
                            jsondata.getJSONObject(i).getString("other_person_index"),
                            jsondata.getJSONObject(i).getString("seen")

                    ));


                if (jsondata.getJSONObject(i).getString("seen").toString().equalsIgnoreCase("0") && !jsondata.getJSONObject(i).getString("members_index").equalsIgnoreCase(userID)){

                    unread_message++;

                   /* Dialog d = new Dialog(activity);
                    d.setTitle(""+unread_message);
                    d.show();*/

                    SharedPreferences.Editor editor = activity.getSharedPreferences(MY_PREFS_NAME, 0).edit();

                    editor.putString("unread_message", ""+unread_message);
                    editor.commit();




                }

               // }//end if
            }catch(JSONException e){

                e.printStackTrace();

            }

        }//endfor


       /* SharedPreferences prefs = activity.getSharedPreferences(MY_PREFS_NAME, 0);

        int unread_int = prefs.getInt("unread_message", 0);

        Dialog d = new Dialog(activity);
        d.setTitle(""+ unread_int);
        d.show();*/

        return items;

    }// end generate
}
