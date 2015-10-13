package com.changeandsuccess.nofapchallenge.profile_inside_tab;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TabHost;
import android.widget.TextView;

import com.changeandsuccess.nofapchallenge.ChangeProfileActivity;
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
 * Created by albert on 4/27/14.
 */



public class ProfileTab extends Fragment implements TabHost.OnTabChangeListener {



    TabHost mTabHost;
    FragmentActivity mActivity;
    String  userIndex ;

    View rootView;
    LoginHelper loginHelper;
    ListView mycourseListView;

    Inflater inflater;
    ArrayList<LoginItem> generatedLoginItem;


    Activity activity;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        activity = getActivity();

        //init the helper class
        loginHelper = new LoginHelper();

        String[][] loginData = loginHelper.checkLogin(getActivity());
        generatedLoginItem = generateData(loginData);


        if( generatedLoginItem.toString() !="[]"){

           //rootView = inflater.inflate(R.layout.profile_frag, container, false);
            rootView =  inflater.inflate(R.layout.myprogress_frag_____profile_pic_top, container, false);
            mTabHost = (TabHost) rootView.findViewById(android.R.id.tabhost);
            mTabHost.setup();
            //setupTab(new TextView(getActivity()), "posts", R.drawable.featured, R.id.my_posts_tab);
            setupTab(new TextView(getActivity()), "progress", R.drawable.my_progress, R.id.my_progress_tab);
            setupTab(new TextView(getActivity()), "puchases", R.drawable.store, R.id.my_purchase_tab);
            setupTab(new TextView(getActivity()), "settings", R.drawable.my_setting, R.id.my_settings_tab);


            mTabHost.setOnTabChangedListener(this);
            mTabHost.getTabWidget().setDividerDrawable(null);

            //mycourseListView = (ListView) rootView.findViewById(R.id.profile_course_list);


            readSavedUser(getActivity(), generatedLoginItem, rootView);
            //set the make course button
            getFragmentManager().beginTransaction()
                    .add(R.id.my_progress_tab,  new ViewProgress())
                    .commit();


            getFragmentManager().beginTransaction()
                    .add(R.id.my_settings_tab,  new SettingsFrag())
                    .commit();


            getFragmentManager().beginTransaction()
                    .add(R.id.my_purchase_tab, new MyPurchase_frag())
                    .commit();


                            UserDatabase info = new UserDatabase(getActivity());
            info.open();
            String[][] data = info.getData();
            info.close();

            userIndex = data[0][1];

            //set the list of my courses
           // new LoadMyProgress(getActivity(), rootView).execute(userIndex);

//edit edit_picture button

            Button edit_picture = (Button) rootView.findViewById(R.id.edit_picture);

            edit_picture.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                   /* Dialog d = new Dialog(getActivity());
                    d.setTitle("works");
                    d.show();*/

                    Intent i = new Intent(getActivity(), ChangeProfileActivity.class);

                    startActivity(i);
                }
            });

            ImageButton my_product_sales = (ImageButton) rootView.findViewById(R.id.my_product_sales);
            my_product_sales.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {

                    Dialog d = new Dialog(getActivity());
                    d.setTitle("works");
                    d.show();

                }//
            });

            return rootView;

        }else{ //not logged in

            View unrootView = inflater.inflate(R.layout.message_____click_to_login, container, false);

            Intent i = new Intent(getActivity(),
                    LoginActivity.class);
            startActivity(i);
            Button loginTanggoalBTN = (Button)  unrootView.findViewById(R.id.to_logon_page_button);

            loginTanggoalBTN.setOnClickListener(new OnClickListener() {
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

        //imageloader.destroy();

        Boolean isInit = imageloader.isInited();

        if(!isInit) {
            imageloader.init(ImageLoaderConfiguration.createDefault(context));
        }

        imageloader.displayImage(imageurl, profile_photo, options);


    } //end readSavedUser

    private void setupTab(final View view, final String tag, int drawableId, int tabcontentid) {


        View tabview = createTabView(mTabHost.getContext(), tag, drawableId);

        TabHost.TabSpec setContent = mTabHost.newTabSpec(tag).setIndicator(tabview).setContent(tabcontentid);

        mTabHost.addTab(setContent);
    }



    private static View createTabView(final Context context, final String text, final int drawableId) {

        View view = LayoutInflater.from(context).inflate(R.layout.allcoachtabs_____tabs_bg, null);

        TextView title= (TextView) view.findViewById(R.id.title);

        title.setText(text);

        //image
        ImageView icon = (ImageView) view.findViewById(R.id.icon);
        icon.setImageResource(drawableId);

        return view;
    }

    //when menu clicked

    @Override
    public void onTabChanged(String tabId){

        //Context context = mTabHost.getContext();
        String msg = "id of messnea is: "+ mTabHost.getCurrentTab();

        if ("posts".equals(tabId)) {

            System.out.println(msg);
            //show the homefrag

        } else if ("progress".equals(tabId)) {
            System.out.println(msg);

            //show the homefrag
            getFragmentManager().beginTransaction()
                    .add(R.id.my_progress_tab,  new ViewProgress())
                    .commit();


        } else if ("settings".equals(tabId)) {

            getFragmentManager().beginTransaction()
                    .add(R.id.my_settings_tab,  new SettingsFrag())
                    .commit();
        }
    }//end tabchange
}