package com.changeandsuccess.nofapchallenge.comment_stuff;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.changeandsuccess.nofapchallenge.R;
import com.changeandsuccess.nofapchallenge.utils.JsonReader;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.mime.HttpMultipartMode;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.CoreProtocolPNames;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by albert on 9/13/14.
 */
public class DeleteComment extends AsyncTask<String, Integer, String> {

    Activity activity;
    String commentIndex,userIndex;


    public DeleteComment(Activity activity, String commentIndex, String userIndex){
        this.activity = activity;
        this.commentIndex = commentIndex;
        this.userIndex =userIndex;
    }

    //interface to get result
    @Override
    protected String doInBackground(String...params) {

        // Log.e("dickhole", ""+messageArray[2]+" :"+context);


        try {

            final String STREAMURL = "http://mobile.tanggoal.com/comment/delete_course_comment/"+commentIndex;

            JSONObject jsonObj = JsonReader.readJsonFromUrl(STREAMURL);

        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }catch (JSONException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            Log.d("here", "noJSON");
        }
        return null;
    }

    @Override
    protected void onPostExecute(String result) {

        super.onPostExecute(result);
        try {

           /* Dialog das = new Dialog(activity);
            das.setTitle(""+ result);
            TextView tvsa = new TextView(activity);
            tvsa.setText(""+result);
            das.setContentView(tvsa);
            das.show();*/


            messageSuccess(activity);

        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }



    public void messageSuccess(Activity context){


        // 1. Create inflater
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        // View rootView = inflater.inflate(R.layout.message_input_part, null, false);

        View rootView = ((Activity) context).getWindow().getDecorView().findViewById(android.R.id.content);

        new LoadComments(userIndex, rootView, context).execute();


        ProgressBar progressBar = (ProgressBar) rootView.findViewById(R.id.input_progress_bar);
        progressBar.setVisibility(View.GONE);

        Button sendMessageBtn = (Button) rootView.findViewById(R.id.send_message_btn);
        sendMessageBtn.setEnabled(true);


    }


}// end read