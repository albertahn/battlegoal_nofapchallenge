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

import com.changeandsuccess.nofapchallenge.profile_inside_tab.SaveChangeProfile;
import com.changeandsuccess.nofapchallenge.utils.UserDatabase;

/**
 * Created by albert on 3/19/15.
 */
public class ChangeProfileActivity extends ActionBarActivity {


    final Context context = this;
    final Activity activity = this;

    EditText name_edit;

    String userIndex, username, email;

    int RESULT_LOAD_IMAGE= 1;


    TextView pro_pic;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setContentView(R.layout.changeprofileactivity_____change_profile_activity);


        UserDatabase info = new UserDatabase(context);
        info.open();
        String[][] data = info.getData();
        info.close();

        userIndex = data[0][1];
        username = data[0][2];
        email = data[0][3];

        name_edit = (EditText) findViewById(R.id.name_edit);

        name_edit.setText(""+username);


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

        pro_pic = (TextView) findViewById(R.id.photo_uri);

        Button create_account_btn = (Button) findViewById(R.id.create_account_btn);

        create_account_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                    //pic empty?
                    if(!pro_pic.getText().toString().isEmpty()){


          new SaveChangeProfile(  userIndex,
                  name_edit.getText().toString(),
                  email,
                  pro_pic.getText().toString(),
                                activity).execute();
                       /* Dialog d = new Dialog(context);
                        d.setTitle(""+userIndex);

                        TextView t = new TextView(context);

                        t.setText( ""+ name_edit.getText().toString());
                        d.setContentView(t);
                        d.show();*/

                    }else{ //no propic

                        Dialog dk = new Dialog(activity);
                        dk.setTitle("Select a Picture");
                        TextView t = new TextView(activity);
                        t.setText("Please Select a Picture For Profile.");
                        dk.setContentView(t);
                        dk.show();

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
