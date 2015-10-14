package com.changeandsuccess.nofapchallenge.loginStuff;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.widget.TextView;

import com.changeandsuccess.nofapchallenge.LoginActivity;
import com.changeandsuccess.nofapchallenge.MainActivity;
import com.changeandsuccess.nofapchallenge.utils.UserDatabase;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by albert on 10/8/14.
 */
public class ReadFromServer extends AsyncTask<String, Integer, String> {

    Context context;
    String[] params;
    String jsonString;
    //Activity activity;


    public ReadFromServer(Context context, String[] params){
        this.context = context;
        this.params = params;
        //this.activity = activity;

    }

    //interface to get result
    @Override
    protected String doInBackground(String...params) {
        try {
            HttpURLConnection connection;
            OutputStreamWriter request = null;

            String parameters = "email="+params[0]+"&password="+ params[1];

            URL url = new URL("http://mobile.tanggoal.com/login/run");
            connection = (HttpURLConnection) url.openConnection();
            connection.setDoOutput(true);
            connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            connection.setRequestMethod("POST");

            request = new OutputStreamWriter(connection.getOutputStream());
            request.write(parameters);
            request.flush();
            request.close();
            String line = "";
            InputStreamReader isr = new InputStreamReader(connection.getInputStream());
            BufferedReader reader = new BufferedReader(isr);
            StringBuilder sb = new StringBuilder();
            while ((line = reader.readLine()) != null)
            {
                sb.append(line + "\n");
            }
            // Response from server after login process will be stored in response variable.
            String response = sb.toString();
            // onPostExecute(response);

            jsonString = response;

            return response;
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } /*catch (JSONException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
                Log.d("here", "noJSON");
            }*/
        return null;
    }

    @Override
    protected void onPostExecute(String result) {

        super.onPostExecute(result);
        try {
            JSONObject json = new JSONObject(result);
            String user_index = json.get("index").toString();
            String username = json.get("username").toString();
            String email = json.get("email").toString();
            String password =  json.get("password").toString();
            String profile_picture = json.get("profile_picture").toString();
            String FID =  json.get("FID").toString();
            String level =  json.get("level").toString();
            String text_profile = json.get("profile").toString();
            String exp_points = json.get("points").toString();

            //Log.d("jsonfuck", ""+ username + email+password+profile_picture+FID);

            UserDatabase entry = new UserDatabase(context);
            entry.open();
            entry.createEntry(user_index, username, email, password, profile_picture, FID,level, text_profile, exp_points);
            entry.close();

            new GetLastDayFromServer(context, user_index).execute();

            if(user_index!=null){



                //LoginActivity
                LoginActivity loginActivity = new LoginActivity();
                loginActivity.loginSuccess(context);


                //start activity
                Intent i = new Intent(context,
                        MainActivity.class);

                context.startActivity(i);


            }else{


                Dialog d = new Dialog(context);
                d.setTitle("Email or Passord is wrong");
                TextView tv = new TextView(context);
                tv.setText("");
                d.setContentView(tv);
                d.show();
            }

        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }





}// end read