package com.changeandsuccess.nofapchallenge.notify_stuff;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.changeandsuccess.nofapchallenge.R;
import com.changeandsuccess.nofapchallenge.comment_stuff.LoadComments;
import com.changeandsuccess.nofapchallenge.utils.JsonReader;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

/**
 * Created by albert on 9/26/14.
 */
public class CheckNotify extends AsyncTask<String, Integer, String> {

    Activity activity;
    String userIndex;
    JSONObject jsonObj;


    public CheckNotify(Activity activity, String userIndex){
        this.activity = activity;

        this.userIndex =userIndex;
    }

    //interface to get result
    @Override
    protected String doInBackground(String...params) {

        // Log.e("dickhole", ""+messageArray[2]+" :"+context);


        try {

            final String STREAMURL = "http://mobile.tanggoal.com/notify/check_mobile_notify/"+userIndex;

            jsonObj = JsonReader.readJsonFromUrl(STREAMURL);

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




            final View rootView = ((Activity) activity).getWindow().getDecorView().findViewById(android.R.id.content);

            ImageButton bellButton = (ImageButton) rootView.findViewById(R.id.notify_button);

            if(jsonObj.get("result").equals("no") ) {


                bellButton.setImageResource(R.drawable.no_bell);

            }else{ //

               bellButton.setImageResource(R.drawable.bell);


               /* Dialog das = new Dialog(activity);
                das.setTitle(""+ jsonObj);
                TextView tvsa = new TextView(activity);
                tvsa.setText(""+jsonObj.toString());
                das.setContentView(tvsa);
                das.show();*/
            }//else

        }catch (Exception e) {

            // TODO Auto-generated catch block
            e.printStackTrace();

        }
    }





}// end read