package com.changeandsuccess.nofapchallenge.message_activity;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.view.View;
import android.widget.TextView;

import com.changeandsuccess.nofapchallenge.R;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.mime.HttpMultipartMode;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.CoreProtocolPNames;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by albert on 10/21/15.
 */
public class Check_unread_messages extends AsyncTask<String, Integer, String> {

    String[] messageArray= new String[3];
    String jsonString;

    HttpResponse httpResponse;
    Context context;
    public static final String MY_PREFS_NAME = "MyPrefsFile";

    String my_index, message_index;

    public Check_unread_messages( String my_index, Context context){

        this.my_index = my_index;
        this.message_index = message_index;
        this.context = context;
    }//end seen public

    //interface to get result
    @Override
    protected String doInBackground(String...params) {

        // Log.e("dickhole", ""+messageArray[2]+" :"+context);


        try {

            //return response;

            MultipartEntityBuilder builder = MultipartEntityBuilder.create();
            builder.setMode(HttpMultipartMode.BROWSER_COMPATIBLE);


            HttpClient httpClient = new DefaultHttpClient();

            httpClient.getParams().setParameter(CoreProtocolPNames.USER_AGENT,
                    System.getProperty("http.agent"));

            HttpPost httpPost = new HttpPost("http://mobile.tanggoal.com/message/count_list_message_summary/"+my_index);

            httpPost.setEntity( builder.build());


            httpResponse = httpClient.execute(httpPost);
            HttpEntity httpEntity = httpResponse.getEntity();
            String is;

            try {
                BufferedReader rd = new BufferedReader(new InputStreamReader(httpResponse.getEntity().getContent()));
                String body = "";
                String content = "";

                while ((body = rd.readLine()) != null) {
                    content += body + "\n";
                }

                return content;
            } catch (IOException e) {

                // TODO Auto-generated catch block
                e.printStackTrace();

            }

        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void onPostExecute(String result) {

        super.onPostExecute(result);
        try {

            /*Dialog d = new Dialog(context);
            d.setTitle("" + result);
            d.show();*/

            //int intresult = Integer.parseInt(result);

            SharedPreferences.Editor editor = context.getSharedPreferences(MY_PREFS_NAME, 0).edit();
           // editor.putInt("unread_message", intresult);
            editor.putString("unread_message",result);
            editor.commit();

            View rootView = ((Activity) context).getWindow().getDecorView().findViewById(android.R.id.content);
           TextView inbox_notification_badge = (TextView) rootView.findViewById(R.id.inbox_notification_badge);

            inbox_notification_badge.setText("" + result);


        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }



}// end read