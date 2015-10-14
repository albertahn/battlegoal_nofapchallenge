package com.changeandsuccess.nofapchallenge;


import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.changeandsuccess.nofapchallenge.loginStuff.ReadFromServer;
import com.changeandsuccess.nofapchallenge.model.LoginItem;
import com.changeandsuccess.nofapchallenge.utils.UserDatabase;
import com.changeandsuccess.nofapchallenge.utils.JsonReader;
import com.facebook.Session;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.display.RoundedBitmapDisplayer;

import org.apache.http.client.HttpClient;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

/**
 * Created by albert on 5/1/14.
 */


public class LoginHelper extends MainActivity{

    FragmentActivity mActivity;
    JsonReader myJSONreader = new JsonReader();
    final static String PROFILEURL = "http://mobile.tanggoal.com/user/user_profile/";

    TextView usernameText;
    HttpClient client;
    JSONObject json;
    JSONObject straightJson;
    Bitmap profilePhotoImage;


    UserDatabase myUserDatabase;

    Context context;


    public String[][] checkLogin(Context context) {

        context = context;

        UserDatabase info = new UserDatabase(context);
        info.open();
        String[][] data = info.getData();
        info.close();

        return data;

    } //end checklogin



    public void logoutUser(final Context context){

        this.context = context;

        //delete data
        UserDatabase info = new UserDatabase(context);
        info.open();
        info.deleteAllData();
        info.close();

       /* LayoutInflater inflater = (LayoutInflater) context.getSystemService
                (Context.LAYOUT_INFLATER_SERVICE);
*/
        //root view from activity
        View rootView = ((Activity) context).getWindow().getDecorView().findViewById(android.R.id.content);
      //  ViewGroup tabView = (ViewGroup)  rootView.findViewById(R.id.profile_tab);
      //  ViewGroup messageView = (ViewGroup)  rootView.findViewById(R.id.message_tab);
       // View layout = (View)  inflater.inflate(R.layout.click_to_login, null);

        //remove everything
      //  tabView.removeAllViewsInLayout();
//add the view
        //tabView.addView(layout);

        //rid of all stuff in message
     //   messageView.removeAllViewsInLayout();

//set so clickable


        //facebook session out
       logoutFB_session(context);

    } //end logout

    public void loginUser (String email, String password, Context context){

       String[] paramy = new String[2];

        paramy[0] = email;
        paramy[1] = password;

        ReadFromServer reading = new  ReadFromServer(context, paramy);

        reading.execute(paramy);

       // String[][] logindata= checkLogin(context);

    }




//logout facebook
public void logoutFB_session(Context context){
    this.context = context;

    Session session = Session.getActiveSession();
/*
    Dialog d = new Dialog(context);
    d.setTitle("c: ses: "+session.toString());
    TextView t = new TextView(context);
    t.setText(""+session.toString()
    );
    d.setContentView(t);
    d.show();
*/
    if(session!=null) {


        Intent i = new Intent(context,
                LoginActivity.class);

        context.startActivity(i);


       /* Dialog da = new Dialog(context);
        da.setTitle(" not null close");
        TextView ta = new TextView(context);
        ta.setText(""+session.toString()
        );
        da.setContentView(ta);
        da.show();*/

        session = new Session(context);
        //session.requestNewReadPermissions(new Session.NewPermissionsRequest(activity, permissionList));
        Session.setActiveSession(session);

        //session.close();
        session.closeAndClearTokenInformation();
        Session.getActiveSession().close();
        Session.setActiveSession(null);

    }else{

        Dialog da = new Dialog(context);
        da.setTitle("logged out");
        TextView ta = new TextView(context);
        ta.setText("");
        da.setContentView(ta);
        da.show();

    }

/*
    if (session != null) {

        if (!session.isClosed()) {
            session.closeAndClearTokenInformation();
            //clear your preferences if saved
        }
    } else {

        session = new Session(context);
        Session.setActiveSession(session);

        session.closeAndClearTokenInformation();
        //clear your preferences if saved

    }
*/

}// logout fb

}
