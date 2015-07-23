package com.changeandsuccess.nofapchallenge.FB_stuff;

import android.app.Activity;
import android.app.Dialog;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.changeandsuccess.nofapchallenge.R;
import com.facebook.Request;
import com.facebook.Response;
import com.facebook.Session;
import com.facebook.SessionState;
import com.facebook.model.GraphUser;

import java.util.Arrays;
import java.util.List;

/**
 * Created by albert on 8/14/14.
 */
public class FB_loginClass{

    Activity activity;

    Session session;

    View rootView;
    ViewGroup viewGroup;

    //  final Session.StatusCallback statusCallback =
    //        new SessionStatusCallback();

    public FB_loginClass(Activity activity){

        this.activity= activity;


        rootView = ((Activity) activity).getWindow().getDecorView().findViewById(android.R.id.content);

        viewGroup = (ViewGroup) rootView.findViewById(R.id.rel_layout_loginpage);

        viewGroup.removeAllViewsInLayout();

        ProgressBar progressBar = new ProgressBar(activity);

        viewGroup.addView(progressBar);
    }//end interface


    public void loginGuy(){

        //callback
        Session.StatusCallback statusCallback =
                new SessionStatusCallback();

        session = Session.getActiveSession();
        String[] PERMISSION_ARRAY_READ = {"email","public_profile","user_friends","user_photos","user_likes"};
        List<String> permissionList = Arrays.asList(PERMISSION_ARRAY_READ);


        if (session==null) {

          /*  Dialog d = new Dialog(activity);
            d.setTitle("session null");
            TextView t = new TextView(activity);
            t.setText("");
            d.setContentView(t);
            d.show();*/

            session = new Session(activity);
            session.openForRead(new Session.OpenRequest(activity)
                    .setPermissions(permissionList)
                    .setCallback(statusCallback));
            //session.requestNewReadPermissions(new Session.NewPermissionsRequest(activity, permissionList));
            Session.setActiveSession(session);


        }else if(session.isClosed()){

            // session.openForRead(new Session.OpenRequest(activity));

            // session.openActiveSessionFromCache(activity);



           /* session.openForRead(new Session.OpenRequest(activity)
                    .setPermissions(permissionList)
                    .setCallback(statusCallback));*/

            //if session closed
            Dialog d = new Dialog(activity);
            d.setTitle("closed!!");
            TextView t = new TextView(activity);
            t.setText(""+session.toString()
            );
            d.setContentView(t);
            d.show();

            // session.requestNewReadPermissions(new Session.NewPermissionsRequest(activity, permissionList));
            //login
            sendFBData_async();


        }else if(session.isOpened()) {

            session.requestNewReadPermissions(new Session.NewPermissionsRequest(activity, permissionList));

            sendFBData_async();

        }


    }//end login guy


    public void sendFBData_async(){


        session = Session.getActiveSession();

        Request.newMeRequest(session, new Request.GraphUserCallback() {

            // callback after Graph API response with user object
            @Override
            public void onCompleted(GraphUser user, Response response) {
                if (user != null) {


                    /*Dialog d = new Dialog(activity);
                    d.setTitle("working!!");
                    TextView t = new TextView(activity);
                    t.setText(""+user.getId()+"  \n"+user.toString()
                    );
                    d.setContentView(t);
                    d.show();*/


                    String email = user.asMap().get("email").toString();
                    String username = user.asMap().get("name").toString();
                    String access_token = session.getAccessToken();
                    String FID = user.getId();
//send the data async to server
                    new SendFB_Data(
                            email,
                            username,
                            FID,
                            access_token ,
                            activity ).execute();

                    //dialog
                 /*   Dialog d = new Dialog(activity);
                    d.setTitle("Hello " + user.getName() + "!");
                    TextView t = new TextView(activity);
                    t.setText(user.toString()+user.asMap().get("email")+""
                                    +session.getPermissions().toString()
                    );
                    d.setContentView(t);
                    d.show();*/



                }else { //no user

                  /*  Dialog d = new Dialog(activity);
                    d.setTitle("Login with facebook ");

                    d.show();*/
                }
            }
        }).executeAsync();


    }//end send data


    class SessionStatusCallback implements Session.StatusCallback {


        @Override
        public void call(Session session, SessionState state, Exception exception) {
            // Respond to session state changes, ex: updating the view

           /* Dialog d = new Dialog(activity);
            d.setTitle("callback fuck ");

            d.show();*/
            sendFBData_async();

        }//call

    }//session callback


}
