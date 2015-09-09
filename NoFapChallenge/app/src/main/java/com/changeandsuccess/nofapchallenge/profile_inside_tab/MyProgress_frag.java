package com.changeandsuccess.nofapchallenge.profile_inside_tab;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.changeandsuccess.nofapchallenge.LoginActivity;
import com.changeandsuccess.nofapchallenge.LoginHelper;
import com.changeandsuccess.nofapchallenge.R;
import com.changeandsuccess.nofapchallenge.model.LoginItem;
import com.changeandsuccess.nofapchallenge.utils.UserDatabase;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.display.RoundedBitmapDisplayer;

import java.util.ArrayList;
import java.util.zip.Inflater;

/**
 * Created by albert on 11/20/14.
 */
public class MyProgress_frag extends Fragment {



    String  userIndex ;

    LoginHelper loginHelper;
    ListView mycourseListView;

    Inflater inflater;
    ArrayList<LoginItem> generatedLoginItem;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        View rootView;

        //init the helper class
        loginHelper = new LoginHelper();

        String[][] loginData = loginHelper.checkLogin(getActivity());
        generatedLoginItem = generateData(loginData);


        if( generatedLoginItem.toString() !="[]"){

            rootView = inflater.inflate(R.layout.myprogress_frag_____profile_frag, container, false);

            mycourseListView = (ListView) rootView.findViewById(R.id.profile_course_list);


            View headerView = inflater.inflate(R.layout.myprogress_frag_____profile_pic_top, null, false);
            mycourseListView.addHeaderView(headerView);
            // rootView= headerView;
            //set the header
            readSavedUser(getActivity(), generatedLoginItem, rootView);
            //set the make course button



            UserDatabase info = new UserDatabase(getActivity());
            info.open();
            String[][] data = info.getData();
            info.close();

            userIndex = data[0][1];

            //set the list of my courses
            new LoadMyProgress(getActivity(), rootView).execute(userIndex);

            return rootView;

        }else{ //not logged in

            View unrootView = inflater.inflate(R.layout.message_____click_to_login, container, false);

            Intent i = new Intent(getActivity(),
                    LoginActivity.class);
            startActivity(i);
            Button loginTanggoalBTN = (Button)  unrootView.findViewById(R.id.to_logon_page_button);

            loginTanggoalBTN.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    Intent i = new Intent(getActivity(),
                            LoginActivity.class);
                    startActivity(i);

                }

            });

            // Close Registration View

            return unrootView;
        }// end not logged in

    }//end oncreate view


    public static ArrayList<LoginItem> generateData(String[][] data){
        ArrayList<LoginItem> items = new ArrayList<LoginItem>();

        for (int i =0; i<data.length ; i++){
            items.add(new LoginItem( data[i][1], data[i][2], data[i][3],data[i][4],data[i][5],data[i][6],data[i][7]));
        }
        return items;
    } //end generate


    public void readSavedUser(Context context, ArrayList<LoginItem> generatedLoginItem, View rootView) {

        TextView usernameText = (TextView) rootView.findViewById(R.id.user_name_profile);

        String username = generatedLoginItem.get(0).getUsername().toString();

        usernameText.setText(username);

        String propic = generatedLoginItem.get(0).getProfile_picture();

        // LoadImage loadImage = new LoadImage(context);
        //loadImage.execute(propic);
        ImageView profile_photo = (ImageView) rootView.findViewById(R.id.myprofile_picture);
        String imageurl = "http://tanggoal.com/public/uploads/members_pic/" + propic;


        ImageLoader imageloader = ImageLoader.getInstance();

        //imageloader.displayImage(url, view);

        DisplayImageOptions options = new DisplayImageOptions.Builder()
                .showImageForEmptyUri(R.drawable.ic_launcher)
                .displayer(new RoundedBitmapDisplayer(250))
                .cacheInMemory(true)
                .considerExifParams(true)
                .build();

        //kill it first

        //imageloader.destroy();

        Boolean isInit = imageloader.isInited();

        if(!isInit) {
            imageloader.init(ImageLoaderConfiguration.createDefault(context));
        }

        imageloader.displayImage(imageurl, profile_photo, options);


    } //end readSavedUser

}