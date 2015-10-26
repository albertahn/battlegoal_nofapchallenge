package com.changeandsuccess.nofapchallenge.level_stuff;

import android.app.Activity;
import android.graphics.Color;
import android.os.AsyncTask;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.changeandsuccess.nofapchallenge.R;
import com.changeandsuccess.nofapchallenge.adapter.LevelRankAdapter;
import com.changeandsuccess.nofapchallenge.model.RankItem;
import com.changeandsuccess.nofapchallenge.utils.JsonReader;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by albertan on 10/26/15.
 */
public class PointOneUp extends AsyncTask<String, Integer, String> {

    final static String MENTORSHIPURL = "http://mobile.tanggoal.com/level/up_one_point/";
    View levelView;
    Activity activity;
    JSONObject jsonobj;

    String user_index, exp_points, new_points;


    public PointOneUp(String user_index,String exp_points,  Activity activity){
        this.user_index = user_index;
        this.exp_points = exp_points;

        this.activity = activity;

    }//

    //interface to get result
    @Override
    protected String doInBackground(String...params) {


        try {

            jsonobj = JsonReader.readJsonFromUrl(MENTORSHIPURL + user_index + "/" + exp_points);

new_points = jsonobj.getString("points").toString();
            //return jsonArray;
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

        View rootView = ((Activity) activity).getWindow().getDecorView().findViewById(android.R.id.content);
        TextView exp_point_textview = (TextView) rootView.findViewById(R.id.points_menu);
        exp_point_textview.setText("Exp Points: "+ new_points);

        Toast toast = Toast.makeText(activity,
                "", Toast.LENGTH_SHORT);

        toast.setGravity(Gravity.CENTER, 0, 0);
        LinearLayout toastView = (LinearLayout) toast.getView();

        ImageView imageCodeProject = new ImageView(activity);
        imageCodeProject.setMaxWidth(340);
        imageCodeProject.setMaxHeight(340);
        imageCodeProject.setMinimumHeight(100);
        imageCodeProject.setMinimumWidth(100);

        TextView newPointsToast = new TextView(activity);

        newPointsToast.setText("+1 Point!");
        newPointsToast.setTextColor(Color.parseColor("#0000ff"));

        imageCodeProject.setImageResource(R.drawable.ic_launcher);//.setImageBitmap(mainbitmap);//.setImageResource(bitmap);

        toastView.setBackgroundColor(Color.parseColor("#00000000"));
        toastView.setMinimumWidth(50);
        toastView.setMinimumHeight(50);
        LinearLayout.LayoutParams para = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.MATCH_PARENT, Gravity.CENTER);

        toastView.addView(newPointsToast);
        toastView.addView(imageCodeProject, para);


        // toastView.addView(imageCodeProject);

        toast.show();

    }// end post ex


}
