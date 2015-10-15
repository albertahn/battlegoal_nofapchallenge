package com.changeandsuccess.nofapchallenge.comment_stuff;

import android.app.Activity;
import android.app.Dialog;
import android.os.AsyncTask;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.ProgressBar;

import com.changeandsuccess.nofapchallenge.R;
import com.changeandsuccess.nofapchallenge.model.CommentItem;
import com.changeandsuccess.nofapchallenge.utils.JsonReader;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by albert on 9/5/14.
 */
public class LoadReplies  extends AsyncTask<String, Integer, String> {


    final static String STREAMURL = "http://mobile.tanggoal.com/comment/get_course_replies/";
    final static String ONECOMMENT = "http://mobile.tanggoal.com/comment/get_one_comment/";
    JSONArray jsonArray;
    JSONObject jsonObject;
    Activity activity;
   final String userID;

    //origin comment stuff
    String origin_text;
    String origin_username;

    int commentindex;




    public LoadReplies(String userID, Activity activity, int commentindex) {
       // this.rootView = rootView;
        this.activity = activity;
       this.userID = userID;
        this.commentindex = commentindex;

    }


    //interface to get result
    @Override
    protected String doInBackground(String... params) {
        try {

            jsonArray = JsonReader.readJsonArrayFromUrl(STREAMURL+commentindex);

            jsonObject = JsonReader.readJsonFromUrl(ONECOMMENT+commentindex);


            //get the origion comment stuff

            origin_text =(String) jsonObject.getString("comment_text").toString();
            origin_username = (String) jsonObject.getString("username").toString();

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


        if (jsonArray != null) {
            //homeListView.getContext()

            // View rootView = ((Activity) activity).getWindow().getDecorView().findViewById(android.R.id.content);

            final View repliesInputpart = LayoutInflater.from(activity).inflate(R.layout.commentsfrag_____message_input_part, null);

            ReplyAdapter proAdapter = new ReplyAdapter(activity, generateData(jsonArray));

            final ListView listView = (ListView) repliesInputpart.findViewById(R.id.message_frag_list);

            listView.setAdapter(proAdapter);


            //input onclick part
           final Dialog d = new Dialog(activity);


          final Button sendButton = (Button) repliesInputpart.findViewById(R.id.send_message_btn);

            sendButton.setOnClickListener( new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                   EditText inputpart = (EditText) repliesInputpart.findViewById(R.id.input_edit_text);

                    String inputText = inputpart.getText().toString();

                  /*  Dialog d = new Dialog(activity);
                    d.setTitle(commentindex+""+inputText);
                    d.show();*/

                    new InputReply(activity, userID, inputText, commentindex, repliesInputpart).execute();

                    inputpart.setText("");
                    sendButton.setEnabled(false);

                    ProgressBar progressBar = (ProgressBar) repliesInputpart.findViewById(R.id.input_progress_bar);
                    progressBar.setVisibility(View.VISIBLE);

                    d.dismiss();

                }
            });


            d.setTitle(origin_username+": "+origin_text);
            d.setContentView(repliesInputpart);
            d.show();
            //now the input of replies

        } else {

            Log.d("emptyarray", "sptmey man");
        }


    }// end post ex


    ArrayList<CommentItem> generateData(JSONArray jsondata) {

        ArrayList<CommentItem> items = new ArrayList<CommentItem >();

        for (int i = 0; i < jsondata.length(); i++) {


            try {
                //check if course comment
                //if(jsondata.getJSONObject(i).getString("courses_index")!="") {
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
                // }//end if
            } catch (JSONException e) {

                e.printStackTrace();

            }


        }

        return items;

    }// end generate



}
