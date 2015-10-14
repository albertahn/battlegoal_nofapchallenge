package com.changeandsuccess.nofapchallenge.profile_inside_tab;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.changeandsuccess.nofapchallenge.LoginHelper;
import com.changeandsuccess.nofapchallenge.R;
import com.changeandsuccess.nofapchallenge.model.LoginItem;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.display.RoundedBitmapDisplayer;

import java.util.ArrayList;

/**
 * Created by tanggames on 2015-10-13.
 */
public class UserInfoEdit extends ActionBarActivity{
    TextView id_text;
    EditText goal_text,moto_text,intro_text,contact_text,language_text,interesting_text;
    ImageView profile_image;
    Button close_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.settingsfrag_____user_info_edit);

        id_text = (TextView)findViewById(R.id.content_id);
        goal_text = (EditText)findViewById(R.id.content_goal);
        moto_text = (EditText)findViewById(R.id.content_moto);
        intro_text = (EditText)findViewById(R.id.content_intro);
        contact_text = (EditText)findViewById(R.id.content_contact);
        language_text = (EditText)findViewById(R.id.content_language);
        interesting_text = (EditText)findViewById(R.id.content_interesting);
        profile_image = (ImageView)findViewById((R.id.imageProfileButton));

        close_btn = (Button)findViewById(R.id.closeBtn);
        close_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        LoginHelper loginHelper = new LoginHelper();
        String[][] loginData = loginHelper.checkLogin(this);
        ArrayList<LoginItem> generatedLoginItem = generateData(loginData);
        if( generatedLoginItem.toString() !="[]"){
            readSavedUser(this, generatedLoginItem);
        }

    }


    public void readSavedUser(Context context, ArrayList<LoginItem> generatedLoginItem) {
        String username = generatedLoginItem.get(0).getUsername().toString();
        //   String lv = generatedLoginItem.get(0).getLevel().toString();

        id_text.setText(username);
        //lv = "LV."+lv;
        //lvText.setText(lv);

        String propic = generatedLoginItem.get(0).getProfile_picture();

        ImageView profile_photo = (ImageView) findViewById(R.id.profilePicture);
        String imageurl = "http://tanggoal.com/public/uploads/members_pic/" + propic;

        ImageLoader imageloader = ImageLoader.getInstance();
        DisplayImageOptions options = new DisplayImageOptions.Builder()
                .showImageForEmptyUri(R.drawable.ic_launcher)
                .displayer(new RoundedBitmapDisplayer(250))
                .cacheInMemory(true)
                .considerExifParams(true)
                .build();

        Boolean isInit = imageloader.isInited();

        if(!isInit) {
            imageloader.init(ImageLoaderConfiguration.createDefault(context));
        }

        imageloader.displayImage(imageurl, profile_photo, options);
    }
    public static ArrayList<LoginItem> generateData(String[][] data){
        ArrayList<LoginItem> items = new ArrayList<LoginItem>();

        for (int i =0; i<data.length ; i++){
            items.add(new LoginItem( data[i][1], data[i][2], data[i][3],data[i][4],data[i][5],data[i][6],data[i][7]));
        }
        return items;
    } //end generate
}
