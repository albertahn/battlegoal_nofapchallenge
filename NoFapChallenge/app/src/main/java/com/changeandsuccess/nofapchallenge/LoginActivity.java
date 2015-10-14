package com.changeandsuccess.nofapchallenge;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

//import com.al.tanggoal.app.FB_stuff.FB_loginClass;
import com.changeandsuccess.nofapchallenge.FB_stuff.FB_loginClass;
import com.changeandsuccess.nofapchallenge.model.LoginItem;
import com.facebook.Session;
//import com.facebook.Session;

import java.util.ArrayList;

/**
 * Created by albert on 6/30/14.
 */
public class LoginActivity extends ActionBarActivity {

    final LoginHelper loginHelper = new LoginHelper();
    final Context context = this;

    final Activity activity = this;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        setContentView(R.layout.loginactivity_____login_page);

        //final LoginHelper loginHelper = new LoginHelper();
        //final Context context = this;
//        View rootView = inflater.inflate(R.layout.login_page, null, false );

        final EditText email = (EditText) findViewById(R.id.user_email);
        final EditText password = (EditText) findViewById(R.id.user_password_input);

        Button loginTanggoalBTN = (Button)  findViewById(R.id.send_apply_btn);

        loginTanggoalBTN.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {

                loginHelper.loginUser(email.getText().toString(), password.getText().toString(), context);

            } //click view
        }); //onclick listener

     //register new person button
        Button register_new_btn = (Button) findViewById(R.id.register_new_btn);

        register_new_btn.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(context,
                        RegisterActivity.class);
                startActivity(i);

            }
        });

        //login_facebook

//button
        Button login_facebook = (Button) findViewById(R.id.login_facebook);
        login_facebook.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) { // onclick

                FB_loginClass fb_loginClass = new FB_loginClass(activity);
                fb_loginClass.loginGuy();

            }//end onclick
        });
//check
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

    }//finish oncreate


     //when facebook login
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Session.getActiveSession()
                .onActivityResult(this, requestCode, resultCode, data);
    }


    public boolean isLoggedIn(Context contextman){

        String[][] loginData = loginHelper.checkLogin(contextman);
        ArrayList<LoginItem> generatedLoginItem = generateData(loginData);

        if(generatedLoginItem.toString() !="[]") {

           return true;

        }else{
            return false;
        }
    }

    public void loginSuccess(Context context){

        Activity activity = (Activity) context;
        activity.finish();

    }

    public static ArrayList<LoginItem> generateData(String[][] data){
        ArrayList<LoginItem> items = new ArrayList<LoginItem>();

        for (int i =0; i<data.length ; i++){

            items.add(new LoginItem( data[i][1], data[i][2], data[i][3],data[i][4],data[i][5],data[i][6],data[i][7]));

        }
        return items;
    } //end generate


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {

            case android.R.id.home:
                finish();
                return true;
            default:

                finish();
                return super.onOptionsItemSelected(item);
        }
    } //end onoptions selected


}
