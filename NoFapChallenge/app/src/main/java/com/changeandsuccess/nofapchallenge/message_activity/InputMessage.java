package com.changeandsuccess.nofapchallenge.message_activity;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.os.AsyncTask;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import android.support.v7.app.ActionBar;

import com.changeandsuccess.nofapchallenge.R;

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
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by albert on 7/25/14.
 */
public class InputMessage extends AsyncTask<String, Integer, String> {

    Activity context;
    String[] messageArray= new String[3];
    String jsonString;

    HttpResponse httpResponse;

    ActionBar actionBar;

    public InputMessage(Activity context, String[] messageArray, ActionBar actionBar){
        this.context = context;
        this.messageArray = messageArray;
        this.actionBar = actionBar;
        //this.activity = activity;

    }

    //interface to get result
    @Override
    protected String doInBackground(String...params) {

       // Log.e("dickhole", ""+messageArray[2]+" :"+context);


        try {

            //return response;

            MultipartEntityBuilder builder = MultipartEntityBuilder.create();
            builder.setMode(HttpMultipartMode.BROWSER_COMPATIBLE);
            builder.addTextBody("sending_to", messageArray[2], ContentType.create("text/plain", MIME.UTF8_CHARSET));
            builder.addTextBody("input_val", messageArray[1], ContentType.create("text/plain", MIME.UTF8_CHARSET));
            builder.addTextBody("my_id", messageArray[0], ContentType.create("text/plain", MIME.UTF8_CHARSET));


            HttpClient httpClient = new DefaultHttpClient();

            httpClient.getParams().setParameter(CoreProtocolPNames.USER_AGENT,
                    System.getProperty("http.agent"));

            HttpPost httpPost = new HttpPost("http://mobile.tanggoal.com/message/input_message/");

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

           /* Dialog das = new Dialog(context);
            das.setTitle(""+ result);
            TextView tvsa = new TextView(context);
            tvsa.setText(""+result);
            das.setContentView(tvsa);
            das.show();*/


            JSONObject json = new JSONObject(result);

            String[] dataArray = new String[7];

            String user_index = json.get("members_index").toString();
            String username = json.get("username").toString();
            String timestamp = json.get("timestamp").toString();
            String messages_body =  json.get("messages_body").toString();
            String profile_picture = json.get("profile_picture").toString();
            String messages_index =  json.get("messages_index").toString();
            String has_message_index =  json.get("has_message_index").toString();

            dataArray[0] = user_index;
            dataArray[1]=username;
            dataArray[2]=timestamp;
            dataArray[3]=messages_body;
            dataArray[4]=profile_picture;
            dataArray[5]= messages_index;
            dataArray[6]=has_message_index;



            if(user_index!=""){

              /*  messageSuccess(context, dataArray);

                // 1. Create inflater
                LayoutInflater inflater = (LayoutInflater) context
                        .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
*/
                // View rootView = inflater.inflate(R.layout.message_input_part, null, false);

                View rootView = ((Activity) context).getWindow().getDecorView().findViewById(android.R.id.content);

               // ListView insideMessageListView = (ListView) rootView.findViewById(R.id.message_frag_list);


                // int rowList = R.layout.message_bubble_my;
                // 2. Get rowView from inflater
               // View rowView = inflater.inflate(R.layout.message_bubble_my, null, false); //inflater.inflate(rowList, null, false);


                //Log.e("fuckers",""+messageArray[0]+messageArray[2]+rootView+context);

                new InsideMessageLoad(messageArray[0],messageArray[2],rootView, context, actionBar).execute();

                ListView insideMessageListView = (ListView) rootView.findViewById(R.id.message_frag_list);

                insideMessageListView.setSelection(insideMessageListView.getCount() - 1);

                ProgressBar progressBar = (ProgressBar) rootView.findViewById(R.id.input_progress_bar);
                progressBar.setVisibility(View.GONE);

                Button sendMessageBtn = (Button) rootView.findViewById(R.id.send_message_btn);
                sendMessageBtn.setEnabled(true);

            }else{

                Dialog ds = new Dialog(context);
                ds.setTitle("Comment did not go through");
                TextView tvs = new TextView(context);
                tvs.setText("");
                ds.setContentView(tvs);
                ds.show();
            }

        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }



    public void messageSuccess(Context context, String[] dataArray){


        // 1. Create inflater
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

       // View rootView = inflater.inflate(R.layout.message_input_part, null, false);

        View rootView = ((Activity) context).getWindow().getDecorView().findViewById(android.R.id.content);

        ListView insideMessageListView = (ListView) rootView.findViewById(R.id.message_frag_list);

        // int rowList = R.layout.message_bubble_my;
        // 2. Get rowView from inflater
        View rowView = inflater.inflate(R.layout.messageinsideadapter_____message_bubble_my, null, false); //inflater.inflate(rowList, null, false);


        //add the rowview

        insideMessageListView.addFooterView(rowView);

        // 3. Get the two text view from the rowView
        TextView text_body_sent = (TextView) rowView.findViewById(R.id.text_body);
        TextView userName =(TextView) rowView.findViewById(R.id.user_name);


        // 4. Set the text for textView
        text_body_sent.setText(dataArray[3]);
        userName.setText(dataArray[1]);

        insideMessageListView.setSelection(insideMessageListView.getCount() - 1);

        EditText inputTextpart = ( EditText) rootView.findViewById(R.id.input_edit_text);
        inputTextpart.setText("");


    }//end send


}// end read