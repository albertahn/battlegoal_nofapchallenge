package com.changeandsuccess.nofapchallenge.comment_stuff;

import android.app.Activity;
import android.content.Intent;
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
import android.widget.ImageView;
import android.widget.TextView;

import com.changeandsuccess.nofapchallenge.R;

/**
 * Created by albert on 1/9/15.
 */
public class LargeCommentActivity extends ActionBarActivity {

    int RESULT_LOAD_IMAGE= 1;

    View rootViewman;
    Activity activity;

    Button upload_image_btn;

    ImageView image_view_comment;

   /* public LargeCommentActivity( View view, Activity activity){

        rootViewman = view;
        this.activity =activity;

    }*/

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //setContentView(R.layout.comment_large_input);

        setContentView(R.layout.largecommentactivity_____coming_soon);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
/*
        upload_image_btn =(Button) findViewById(R.id.upload_image_btn);

        upload_image_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(
                        Intent.ACTION_PICK,
                        MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(i, RESULT_LOAD_IMAGE);

            }
        });

        image_view_comment = (ImageView) findViewById(R.id.image_view_comment);

        image_view_comment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(
                        Intent.ACTION_PICK,
                        MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(i, RESULT_LOAD_IMAGE);

            }
        });
*/
    }//oncreate


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


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == RESULT_LOAD_IMAGE && resultCode == RESULT_OK && null != data){
            Uri selectedImage = data.getData();

            String[] filePathColumn = { MediaStore.Images.Media.DATA };
            Cursor cursor = getContentResolver().query(selectedImage,
                    filePathColumn, null, null, null);
            cursor.moveToFirst();

            int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
            String picturePath = cursor.getString(columnIndex);
            cursor.close();

            ImageView imageView = (ImageView) findViewById(R.id.image_view_comment);

            imageView.setImageBitmap(BitmapFactory.decodeFile(picturePath));

           TextView photo_uri = (TextView) findViewById(R.id.comment_photo_uri);
            photo_uri.setText(picturePath);

        }//endif


    }//end activityresult




}
