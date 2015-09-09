package com.changeandsuccess.nofapchallenge.comment_stuff;


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
import org.apache.http.protocol.HTTP;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by albert on 8/25/14.
 */
public class InputComment extends AsyncTask<String, Integer, String> {

    Activity context;
    //String[] messageArray= new String[3];
    String jsonString;

    HttpResponse httpResponse;
    String userindex, inputText, courseIndex;


    public InputComment(Activity context, String userindex, String inputText){
        this.context = context;
        this.userindex = userindex;
        this.courseIndex = ""+context.getResources().getInteger(R.integer.course_int);
        this.inputText =inputText;
        /*
        try{

            this.inputText = java.net.URLEncoder.encode(inputText, "UTF-8");
        }catch (IOException e){

            Log.e("inputText comment", ""+e);
        }*/


       /* Dialog d = new Dialog(context);
        d.setTitle(inputText);
        d.show();
*/
        //String jj =URLEncoder.encode()
    }

    //interface to get result
    @Override
    protected String doInBackground(String...params) {

        // Log.e("dickhole", ""+messageArray[2]+" :"+context);

        try {

            MultipartEntityBuilder builder = MultipartEntityBuilder.create();




            builder.setMode(HttpMultipartMode.BROWSER_COMPATIBLE);

            builder.addTextBody("comment", inputText, ContentType.create("text/plain", MIME.UTF8_CHARSET));
            builder.addTextBody("user_index", userindex, ContentType.create("text/plain", MIME.UTF8_CHARSET));
            builder.addTextBody("course_index", courseIndex , ContentType.create("text/plain", MIME.UTF8_CHARSET));


            //Charset chars = Charset.forName("UTF-8");
            //builder.setCharset(chars);

            HttpClient httpClient = new DefaultHttpClient();

            httpClient.getParams().setParameter(CoreProtocolPNames.USER_AGENT,
                    System.getProperty("http.agent"));

            HttpPost httpPost = new HttpPost("http://mobile.tanggoal.com/comment/insert_course_comment/");

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

           /* Dialog das = new Dialog(context);
            das.setTitle(""+ result);
            TextView tvsa = new TextView(context);
            tvsa.setText(""+result);
            das.setContentView(tvsa);
            das.show();*/


            JSONObject json = new JSONObject(result);

            String[] dataArray = new String[5];

            String user_index = json.get("members_index").toString();
            String username = json.get("username").toString();
            String profile_picture = json.get("profile_picture").toString();

            String comment_index =  json.get("comment_index").toString();
            String comment_text =  json.get("comment_text").toString();

            dataArray[0] = user_index;
            dataArray[1] = username;
            dataArray[2] = comment_index;
            dataArray[3] = comment_text;
            dataArray[4]= profile_picture;




            if(user_index!=""){

                messageSuccess(context, dataArray);

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



    public void messageSuccess(Activity context, String[] dataArray){


        // 1. Create inflater
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        // View rootView = inflater.inflate(R.layout.message_input_part, null, false);

        View rootView = ((Activity) context).getWindow().getDecorView().findViewById(android.R.id.content);

        ListView insideMessageListView = (ListView) rootView.findViewById(R.id.message_frag_list);


        // 2. Get rowView from inflater
        View rowView = inflater.inflate(R.layout.messageinsideadapter_____message_bubble_my, null, false); //inflater.inflate(rowList, null, false);

        //refresh

        //insideMessageListView.addHeaderView(rowView);

        new LoadComments(userindex,rootView, context).execute();


        // 3. Get the two text view from the rowView
        TextView text_body_sent = (TextView) rowView.findViewById(R.id.text_body);
        TextView username =(TextView) rowView.findViewById(R.id.user_name);


        // 4. Set the text for textView
        text_body_sent.setText(dataArray[3]);
        username.setText(dataArray[1]);

       // insideMessageListView.setSelection(insideMessageListView.getCount() - 1);

        EditText inputTextpart = ( EditText) rootView.findViewById(R.id.input_edit_text);
        inputTextpart.setText("");

        ProgressBar progressBar = (ProgressBar) rootView.findViewById(R.id.input_progress_bar);
        progressBar.setVisibility(View.GONE);

        Button   sendMessageBtn = (Button) rootView.findViewById(R.id.send_message_btn);
        sendMessageBtn.setEnabled(true);


    }


}// end read