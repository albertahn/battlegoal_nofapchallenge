package com.changeandsuccess.nofapchallenge;

import android.app.Activity;
import android.content.Context;
import android.content.res.Configuration;
import android.os.Bundle;

import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.SpinnerAdapter;
import android.widget.TabHost;

import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;

import com.changeandsuccess.nofapchallenge.adapter.TitleNavigationAdapter;
import com.changeandsuccess.nofapchallenge.comment_stuff.LoadComments;
import com.changeandsuccess.nofapchallenge.comment_stuff.CommentsFrag;
import com.changeandsuccess.nofapchallenge.message_activity.Message;
import com.changeandsuccess.nofapchallenge.model.SpinnerNavItem;
import com.changeandsuccess.nofapchallenge.utils.UserDatabase;

import java.util.ArrayList;
import java.util.Locale;

/**
 * Created by albert on 8/25/14.
 */
public class All_Message_Frag extends Fragment implements TabHost.OnTabChangeListener {


    TabHost allTabHost;
    Context context;
    View rootView;
Menu menu;
    String userIndex;
    // Navigation adapter
    private TitleNavigationAdapter adapter;


    // Title navigation Spinner data
    private ArrayList<SpinnerNavItem> navSpinner;


    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        context =getActivity();
        rootView = inflater.inflate(R.layout.allbattletab_____all_message_frag, container, false);


        allTabHost = (TabHost) rootView.findViewById(R.id.all_message_tab_host);
        allTabHost.setup();

        setupnewsTab(allTabHost, getResources().getString(R.string.chat_tab), "chat", R.id.product_comment_tab);
        setupnewsTab(allTabHost, getResources().getString(R.string.inbox_tab), "inbox", R.id.inbox_tab);

        getFragmentManager().beginTransaction()
                .add(R.id.product_comment_tab, new CommentsFrag())
                .commit();

        /*getFragmentManager().beginTransaction()
                .add(R.id.inbox_tab, new Message())
                .commit();
*/

        //check if logged in

        LoginActivity loginActivity = new LoginActivity();

       if(loginActivity.isLoggedIn(getActivity())){

           UserDatabase info = new UserDatabase(context);
           info.open();
           String[][] data = info.getData();
           info.close();
           userIndex = data[0][1];

       }else{
           userIndex= "1";
       }


        ActionBar actionBar =((ActionBarActivity)getActivity()).getSupportActionBar();
//set the spinner element
        setbarNavSpinner();

//set the default language
        int navInt = getActivity().getResources().getInteger(R.integer.navigation_int);

        actionBar.setSelectedNavigationItem(navInt);



        getActivity().getWindow().setSoftInputMode(
                WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);

        return rootView;
    }



    private void setupnewsTab(final View view, String tabName, final String tag, int tabcontentid) {

        //tabNameView tabview = createTabView(mTabHost.getContext(), tag, drawableId);



        TabHost.TabSpec setContent = allTabHost.newTabSpec(tag).setIndicator(tabName).setContent(tabcontentid);

        allTabHost.addTab(setContent);
    }

    @Override
    public void onTabChanged(String tabId) {

        if ("chat".equals(tabId)) {


            getFragmentManager().beginTransaction()
                    .add(R.id.product_comment_tab, new CommentsFrag())
                    .commit();

        }else if("inbox".equals(tabId)){
            getFragmentManager().beginTransaction()
                    .add(R.id.inbox_tab, new Message())
                    .commit();

        }

    }//end on tab changed


    public void setTabToComments(){


        //TabHost host = (TabHost) getActivity().findViewById(android.R.id.tabhost);

        //host.setCurrentTab(2);

       // MainActivity mainActivity = new MainActivity();
      // mainActivity.displayView(2);

        //allTabHost = (TabHost) getActivity().findViewById(R.id.all_message_tab_host);
        //allTabHost.setCurrentTab(0);
    }



    public void setbarNavSpinner(){

        ActionBar actionBar =((ActionBarActivity)getActivity()).getSupportActionBar();
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_LIST);


        SpinnerAdapter mSpinnerAdapter = ArrayAdapter.createFromResource(getActivity(),
                R.array.action_list, android.R.layout.simple_spinner_dropdown_item);


        // assigning the spinner navigation
        actionBar.setListNavigationCallbacks(mSpinnerAdapter, new ActionBar.OnNavigationListener( ) {

            @Override
            public boolean onNavigationItemSelected ( int arg0 , long arg1 ) {

//                Log.e("navid", ""+arg0+":"+ arg1);

                if (arg0 ==0) {



                    String languageToLoad  = "en"; // your language
                    Locale locale = new Locale(languageToLoad);
                    Locale.setDefault(locale);
                    Configuration config = new Configuration();
                    config.locale = locale;
                    getActivity().getResources().updateConfiguration(config,
                            getActivity().getResources().getDisplayMetrics());

                    View rootView = ((Activity) getActivity()).getWindow().getDecorView().findViewById(android.R.id.content);


                  /*  Dialog d = new Dialog(getActivity());
                    d.setTitle(""+rootView);
                    d.show();*/
                    setTabToComments();
                    new LoadComments(userIndex, rootView, getActivity()).execute();



                } else if (arg0==1) {



                    String languageToLoad  = "es"; // your language
                    Locale locale = new Locale(languageToLoad);
                    Locale.setDefault(locale);
                    Configuration config = new Configuration();
                    config.locale = locale;
                    getActivity().getResources().updateConfiguration(config,
                            getActivity().getResources().getDisplayMetrics());

                    View rootView = ((Activity) getActivity()).getWindow().getDecorView().findViewById(android.R.id.content);

                    setTabToComments();
                    new LoadComments(userIndex, rootView,getActivity()).execute();





                } else if (arg0== 2 ) {


                    String languageToLoad  = "ko"; // your language
                    Locale locale = new Locale(languageToLoad);
                    Locale.setDefault(locale);
                    Configuration config = new Configuration();
                    config.locale = locale;
                    getActivity().getResources().updateConfiguration(config,
                            getActivity().getResources().getDisplayMetrics());

                    View rootView = ((Activity) getActivity()).getWindow().getDecorView().findViewById(android.R.id.content);

                    setTabToComments();
                    new LoadComments(userIndex, rootView,getActivity()).execute();

                } else if ( arg0== 3 ) {


                    String languageToLoad  = "pt"; // your language
                    Locale locale = new Locale(languageToLoad);
                    Locale.setDefault(locale);
                    Configuration config = new Configuration();
                    config.locale = locale;
                    getActivity().getResources().updateConfiguration(config,
                            getActivity().getResources().getDisplayMetrics());

                    View rootView = ((Activity) getActivity()).getWindow().getDecorView().findViewById(android.R.id.content);
                    setTabToComments();
                    new LoadComments(userIndex, rootView,getActivity()).execute();

                } else if ( arg0==4  ) {


                    String languageToLoad  = "de"; // your language
                    Locale locale = new Locale(languageToLoad);
                    Locale.setDefault(locale);
                    Configuration config = new Configuration();
                    config.locale = locale;
                    getActivity().getResources().updateConfiguration(config,
                            getActivity().getResources().getDisplayMetrics());

                    View rootView = ((Activity) getActivity()).getWindow().getDecorView().findViewById(android.R.id.content);
                    setTabToComments();
                    new LoadComments(userIndex, rootView,getActivity()).execute();

                }else if ( arg0==5 ) {


                    String languageToLoad  = "fr"; // your language
                    Locale locale = new Locale(languageToLoad);
                    Locale.setDefault(locale);
                    Configuration config = new Configuration();
                    config.locale = locale;
                    getActivity().getResources().updateConfiguration(config,
                            getActivity().getResources().getDisplayMetrics());

                    View rootView = ((Activity) getActivity()).getWindow().getDecorView().findViewById(android.R.id.content);
                    setTabToComments();
                    new LoadComments(userIndex, rootView,getActivity()).execute();

                }else if ( arg0==6 ) {


                    String languageToLoad  = "ja"; // your language
                    Locale locale = new Locale(languageToLoad);
                    Locale.setDefault(locale);
                    Configuration config = new Configuration();
                    config.locale = locale;
                    getActivity().getResources().updateConfiguration(config,
                            getActivity().getResources().getDisplayMetrics());

                    View rootView = ((Activity) getActivity()).getWindow().getDecorView().findViewById(android.R.id.content);
                    setTabToComments();
                    new LoadComments(userIndex, rootView,getActivity()).execute();

                }else if ( arg0==7 ) {


                    String languageToLoad  = "it"; // your language
                    Locale locale = new Locale(languageToLoad);
                    Locale.setDefault(locale);
                    Configuration config = new Configuration();
                    config.locale = locale;
                    getActivity().getResources().updateConfiguration(config,
                            getActivity().getResources().getDisplayMetrics());

                    View rootView = ((Activity) getActivity()).getWindow().getDecorView().findViewById(android.R.id.content);
                    setTabToComments();
                    new LoadComments(userIndex, rootView,getActivity()).execute();

                }else{
                    //email suggest
                }

                return false;

            }

        } );

    }

}
