package com.changeandsuccess.nofapchallenge.store_puchase_stuff;

import android.app.Activity;
import android.app.Dialog;
import android.os.AsyncTask;
import android.view.View;
import android.widget.TextView;

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

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by albert on 9/25/15.
 */
public class SendPuchase_to_db extends AsyncTask<String, Integer, String> {


    Activity activity;
    View rootView;
    String user_index;
    int product_index;

    public SendPuchase_to_db(String email, int product_index, Activity activity) {


        this.user_index = email;
        this.activity = activity;
        this.product_index = product_index;
        //rootView = ((Activity) activity).getWindow().getDecorView().findViewById(android.R.id.content);

        //prodListView = (ListView) rootView.findViewById(R.id.product_page_listview);

    }//send purchase

    @Override
    protected String doInBackground(String... params) {



        try {

            MultipartEntityBuilder builder = MultipartEntityBuilder.create();
            builder.setMode(HttpMultipartMode.BROWSER_COMPATIBLE);
            builder.addTextBody("user_index", user_index, ContentType.create("text/plain", MIME.UTF8_CHARSET));
            builder.addTextBody("product_index", ""+product_index, ContentType.create("text/plain", MIME.UTF8_CHARSET));

            HttpClient httpClient = new DefaultHttpClient();

            httpClient.getParams().setParameter(CoreProtocolPNames.USER_AGENT,
                    System.getProperty("http.agent"));

            HttpPost httpPost = new HttpPost("http://mobile.tanggoal.com/buy/buy_android_product/");

            httpPost.setEntity( builder.build());


            HttpResponse httpResponse = httpClient.execute(httpPost);
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

        }//catch

        return null;
    }//end

    @Override
    protected void onPostExecute(String result) {

        Dialog da = new Dialog(activity);
        da.setTitle("Purchase Success :"+result);
        TextView tv = new TextView(activity);
        tv.setText(""+result);
        da.setContentView(tv);
        da.show();

        super.onPostExecute(result);



        //now login person

/*
        try {
            JSONObject json = new JSONObject(result);
            String user_index = json.get("index").toString();
            String username = json.get("username").toString();
            String email = json.get("email").toString();
            String password =  json.get("password").toString();
            String profile_picture = json.get("profile_picture").toString();
            String FID =  json.get("FID").toString();
            String level = json.get("level").toString();

            //Log.d("jsonfuck", ""+ username + email+password+profile_picture+FID);

            UserDatabase entry = new UserDatabase(activity);
            entry.open();
            entry.createEntry(user_index, username, email, password, profile_picture, FID, level);
            entry.close();

            if(user_index!=""){

                //getlastday

                new GetLastDayFromServer(activity, user_index).execute();

                //LoginActivity
                LoginActivity loginActivity = new LoginActivity();

                loginActivity.loginSuccess(activity);


                //start activity
                Intent i = new Intent(activity,
                        MainActivity.class);

                activity.startActivity(i);


            }else{


                Dialog da = new Dialog(activity);
                da.setTitle("Email or Passord is wrong");
                TextView tv = new TextView(activity);
                tv.setText("");
                da.setContentView(tv);
                da.show();
            }

        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

*/

    }//end post
}//end class