package com.changeandsuccess.nofapchallenge;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.changeandsuccess.nofapchallenge.quote_stuff.GetFreeQuote;

/**
 * Created by albert on 12/31/14.
 */
public class QuoteActivity extends ActionBarActivity {



    final Context context = this;
    final Activity activity = this;

    int RESULT_LOAD_IMAGE= 1;

    EditText username, email, password, repassword;
    TextView pro_pic;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);



        setContentView(R.layout.quoteactivity_____quote_activity);

//set the back btn
        ActionBar actionBar = getSupportActionBar();

        actionBar.setDisplayHomeAsUpEnabled(true);



        //create the account butn

        new GetFreeQuote(this).execute();


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
