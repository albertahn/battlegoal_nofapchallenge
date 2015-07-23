package com.changeandsuccess.nofapchallenge.coaches_tab_stuff;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.os.AsyncTask;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.changeandsuccess.nofapchallenge.R;
import com.changeandsuccess.nofapchallenge.comment_stuff.LoadComments;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.mime.HttpMultipartMode;
import org.apache.http.entity.mime.MIME;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.CoreProtocolPNames;
import org.apache.http.protocol.HTTP;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by albert on 12/22/14.
 */
public class SendApplyCoach extends AsyncTask<String, Integer, String> {

    Activity context;
    //String[] messageArray= new String[3];
    String jsonString;

    HttpResponse httpResponse;
    String blurb, aboutMe, myIndex;

    public SendApplyCoach(Activity context, String blurb, String aboutme, String myIndex){
        this.context = context;
        this.blurb = blurb;
        this.aboutMe =aboutme;
        this.myIndex = myIndex;

    }

    //interface to get result
    @Override
    protected String doInBackground(String...params) {

        try {
            MultipartEntityBuilder builder = MultipartEntityBuilder.create();

            builder.setMode(HttpMultipartMode.BROWSER_COMPATIBLE);
            builder.addTextBody("blurb", blurb, ContentType.create("text/plain", MIME.UTF8_CHARSET));
            builder.addTextBody("about_me", aboutMe, ContentType.create("text/plain", MIME.UTF8_CHARSET));

            builder.addTextBody("my_index", myIndex, ContentType.create("text/plain", MIME.UTF8_CHARSET));

            HttpClient httpClient = new DefaultHttpClient();

            httpClient.getParams().setParameter(CoreProtocolPNames.USER_AGENT,
                    System.getProperty("http.agent"));

            HttpPost httpPost = new HttpPost("http://mobile.tanggoal.com/feature/insert_expert/");

            httpPost.setHeader("Connection", "Keep-Alive");
            httpPost.setHeader("Accept-Charset", HTTP.UTF_8);
            httpPost.setHeader("ENCTYPE", "multipart/form-data");

            httpPost.setEntity( builder.build());

            //httpPost.setEntity( new UrlEncodedFormEntry( nameValuePairs, HTTP.UTF_8 ) );
            httpResponse = httpClient.execute(httpPost);
            HttpEntity httpEntity = httpResponse.getEntity();
            String is;

            try {

                BufferedReader rd = new BufferedReader(new InputStreamReader(httpResponse.getEntity().getContent(), "MS949"));
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

          /*  Dialog das = new Dialog(context);
            das.setTitle(""+ result);
            TextView tvsa = new TextView(context);
            tvsa.setText(""+result);
            das.setContentView(tvsa);
            das.show();*/

            JSONObject json = new JSONObject(result);

            String user_index = json.get("my_index").toString();
            String blurb = json.get("blurb").toString();
            String about_me = json.get("about_me").toString();

            View rootView = ((Activity) context).getWindow().getDecorView().findViewById(android.R.id.content);
            ProgressBar  progressBar = (ProgressBar) rootView.findViewById(R.id.progress_bar_apply);
            progressBar.setVisibility(View.INVISIBLE);

            if(user_index!=""){

                new GetMyApply(context, user_index, rootView).execute();

                Toast toast = Toast.makeText(context,
                        "Application Sent!", Toast.LENGTH_SHORT);
                toast.setGravity(Gravity.CENTER, 0, 0);
                toast.show();

            }else{

                Dialog ds = new Dialog(context);
                ds.setTitle("Check internet. Did not go through");
                TextView tvs = new TextView(context);
                tvs.setText("");
                ds.setContentView(tvs);
                ds.show();
            }//else end

        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    } //onpost




}// end read