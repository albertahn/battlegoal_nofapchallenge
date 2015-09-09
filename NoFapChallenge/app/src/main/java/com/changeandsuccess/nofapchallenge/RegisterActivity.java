package com.changeandsuccess.nofapchallenge;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.database.Cursor;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.changeandsuccess.nofapchallenge.register_acitvity.SaveRegister;

/**
 * Created by albert on 8/12/14.
 */
public class RegisterActivity extends ActionBarActivity {


    final Context context = this;
    final Activity activity = this;

    int RESULT_LOAD_IMAGE= 1;

    EditText username, email, password, repassword;
    TextView  pro_pic;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setContentView(R.layout.registeractivity_____register_activity);

//set the back btn
        ActionBar actionBar = getSupportActionBar();

        actionBar.setDisplayHomeAsUpEnabled(true);

        //click on the photo just set image

        ImageView imgView = (ImageView) findViewById(R.id.imgView);

        imgView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(
                        Intent.ACTION_PICK,
                        MediaStore.Images.Media.EXTERNAL_CONTENT_URI);

                startActivityForResult(i, RESULT_LOAD_IMAGE);

            }
        });

 //create the account butn

        username = (EditText) findViewById(R.id.name_edit);

        email= (EditText) findViewById(R.id.email_edit);
        password= (EditText) findViewById(R.id.pass_edit);
        repassword= (EditText) findViewById(R.id.re_pass_edit);
        pro_pic = (TextView) findViewById(R.id.photo_uri);

        Button create_account_btn = (Button) findViewById(R.id.create_account_btn);

        create_account_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


               if(password.getText().toString().equals(repassword.getText().toString())){
                   //pic empty?
                   if(!pro_pic.getText().toString().isEmpty()){
                       new SaveRegister(
                               username.getText().toString(),
                               email.getText().toString(),
                               password.getText().toString(),
                               pro_pic.getText().toString(),
                               activity).execute();

                        }else{ //no propic

                       Dialog dk = new Dialog(activity);
                       dk.setTitle("Select a Picture");
                       TextView t = new TextView(activity);
                       t.setText("Please Select a Picture For Profile.");
                       dk.setContentView(t);
                       dk.show();

                        }

                }else{ //password not same
                    Dialog d = new Dialog(activity);
                    d.setTitle("Password and RePassword are different! Please Retype");
                    d.show();
                }

            }
        });


    }//finish oncreate

    //image part

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == RESULT_LOAD_IMAGE && resultCode == RESULT_OK && null != data) {
            Uri selectedImage = data.getData();
            String[] filePathColumn = { MediaStore.Images.Media.DATA };

            Cursor cursor = getContentResolver().query(selectedImage,
                    filePathColumn, null, null, null);
            cursor.moveToFirst();

            int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
            String picturePath = cursor.getString(columnIndex);
            cursor.close();

            ImageView imageView = (ImageView) findViewById(R.id.imgView);
            imageView.setImageBitmap(BitmapFactory.decodeFile(picturePath));

            TextView photo_uri = (TextView) findViewById(R.id.photo_uri);
            photo_uri.setText(picturePath);

        }


    }//end activityresult


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