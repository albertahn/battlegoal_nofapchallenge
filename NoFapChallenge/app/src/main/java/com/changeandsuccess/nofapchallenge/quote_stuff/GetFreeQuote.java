package com.changeandsuccess.nofapchallenge.quote_stuff;

import android.app.Activity;
import android.os.AsyncTask;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.changeandsuccess.nofapchallenge.R;
import com.changeandsuccess.nofapchallenge.model.CommentItem;
import com.changeandsuccess.nofapchallenge.utils.JsonReader;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by albert on 1/2/15.
 */
public class GetFreeQuote extends AsyncTask<String, Integer, String> {



    JSONArray jsonArray;

    Activity activity;
    String userID;
    JSONObject jsonOb;
    View rootView;
    // ProgressBar progressBar;


    public GetFreeQuote( Activity activity) {
        this.activity = activity;


        // progressBar = (ProgressBar) rootView.findViewById(R.id.input_progress_bar);
        // progressBar.setVisibility(View.VISIBLE);

    }


    //interface to get result
    @Override
    protected String doInBackground(String... params) {
        try {

            final String STREAMURL = "http://mobile.tanggoal.com/quote/get_free_quotes";//activity.getResources().getString(R.string.default_comments_url);

            jsonArray = JsonReader.readJsonArrayFromUrl(STREAMURL);

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

           /*Dialog d = new Dialog(activity);
            TextView t = new TextView(activity);
            t.setText(""+jsonArray.toString());
            d.setContentView(t);
            d.show();*/

       if (jsonArray != null) {

          /* Random rn = new Random();
           int range = 9 - 0 + 1;
           int randomNum =  rn.nextInt(range) + 0;
*/

           try {

               View rootview = ((Activity) activity).getWindow().getDecorView().findViewById(android.R.id.content);

               TextView quoteMain = (TextView) rootview.findViewById(R.id.quote_text_part);
               quoteMain.setText("" + jsonArray.getJSONObject(0).getString("quote"));

               TextView author = (TextView) rootview.findViewById(R.id.author_text);
               author.setText("- "+ jsonArray.getJSONObject(0).getString("author"));


           }catch (JSONException e) {

               e.printStackTrace();

           }

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