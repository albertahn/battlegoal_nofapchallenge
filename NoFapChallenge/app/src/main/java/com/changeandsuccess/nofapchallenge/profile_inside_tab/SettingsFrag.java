package com.changeandsuccess.nofapchallenge.profile_inside_tab;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TabHost;
import android.widget.TextView;
import android.widget.Toast;

import com.changeandsuccess.nofapchallenge.LoginHelper;
import com.changeandsuccess.nofapchallenge.R;
import com.changeandsuccess.nofapchallenge.model.LoginItem;
import com.changeandsuccess.nofapchallenge.notify_stuff.CheckNotify;
import com.changeandsuccess.nofapchallenge.notify_stuff.ToggleBellSend;
import com.changeandsuccess.nofapchallenge.utils.UserDatabase;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.display.RoundedBitmapDisplayer;

import org.w3c.dom.Text;

import java.util.ArrayList;

/**
 * Created by albert on 8/25/14.
 */
public class SettingsFrag extends Fragment {

    ArrayList<LoginItem> generatedLoginItem;
    View rootView;

    ImageButton notifyButton;
    String userIndex;

    TextView title;
    LinearLayout profileChangeBtn;

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        //if logged in else

        UserDatabase userDatabase= new UserDatabase(getActivity());
        userDatabase.open();
        String[][] data = userDatabase.getData();
        userDatabase.close();

        generatedLoginItem = generateData(data);

        if(generatedLoginItem.toString() !="[]"){
            userIndex = data[0][1];
        }else{

            userIndex ="1";
        }

        rootView = inflater.inflate(R.layout.settingsfrag_____settings_frag, container, false);

        title = (TextView)rootView.findViewById(R.id.textView);
        profileChangeBtn = (LinearLayout)rootView.findViewById(R.id.profileChangeBtn);
        profileChangeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(getActivity(),"클릭됨!!!!!",Toast.LENGTH_LONG).show();
                Intent intent = new Intent(getActivity(),UserInfoEdit.class);
                startActivity(intent);
            }
        });


        final TabHost tab_host = (TabHost)rootView.findViewById(R.id.tabhost);
        tab_host.setup();

        TabHost.TabSpec ts1 = tab_host.newTabSpec("tab1");
        ts1.setIndicator("User Info");
        ts1.setContent(R.id.tab1);
        tab_host.addTab(ts1);

        TabHost.TabSpec ts2 = tab_host.newTabSpec("tab2");
        ts2.setIndicator("App Setting");
        ts2.setContent(R.id.tab2);
        tab_host.addTab(ts2);

        tab_host.setOnTabChangedListener(new TabHost.OnTabChangeListener() {
            @Override
            public void onTabChanged(String tabId) {
                if(tab_host.getCurrentTab()==0){
                    title.setText("User Info");
                }else if(tab_host.getCurrentTab()==1){
                    title.setText("App Setting");
                }
            }
        });

        tab_host.setCurrentTab(0);


        LoginHelper loginHelper = new LoginHelper();
        String[][] loginData = loginHelper.checkLogin(getActivity());
        ArrayList<LoginItem> generatedLoginItem = generateData(loginData);
        if( generatedLoginItem.toString() !="[]"){
            readSavedUser(getActivity(), generatedLoginItem);
        }


        new CheckNotify(getActivity(), userIndex).execute();
        return  rootView;
    }//end on create




    public static ArrayList<LoginItem> generateData(String[][] data){
        ArrayList<LoginItem> items = new ArrayList<LoginItem>();

        for (int i =0; i<data.length ; i++){

            items.add(new LoginItem( data[i][1], data[i][2], data[i][3],data[i][4],data[i][5],data[i][6],data[i][7]));

        }//
        return items;
    } //end generate

    public void readSavedUser(Context context, ArrayList<LoginItem> generatedLoginItem) {
        TextView usernameText = (TextView) rootView.findViewById(R.id.profileID);
        //  TextView lvText =  (TextView) rootView.findViewById(R.id.profileLevel);

        String username = generatedLoginItem.get(0).getUsername().toString();
        //  String lv = generatedLoginItem.get(0).getLevel().toString();

        username = "ID : "+username;
        usernameText.setText(username);
        //   lv = "LV."+lv;
        //   lvText.setText(lv);

        String propic = generatedLoginItem.get(0).getProfile_picture();

        ImageView profile_photo = (ImageView) rootView.findViewById(R.id.profilePicture);
        String imageurl = "http://tanggoal.com/public/uploads/members_pic/" + propic;


        ImageLoader imageloader = ImageLoader.getInstance();

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
    }
}
