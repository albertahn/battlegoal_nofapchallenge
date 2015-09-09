package com.changeandsuccess.nofapchallenge;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.TypedArray;
import android.os.Bundle;

import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;

import com.changeandsuccess.nofapchallenge.adapter.NavDrawerListAdapter;
import com.changeandsuccess.nofapchallenge.battle_stuff.AllBattleTab;
import com.changeandsuccess.nofapchallenge.coaches_tab_stuff.AllCoachTabs;
import com.changeandsuccess.nofapchallenge.fragments.ComingSoon;
import com.changeandsuccess.nofapchallenge.comment_stuff.CommentsFrag;
import com.changeandsuccess.nofapchallenge.fragments.SettingsFrag;
import com.changeandsuccess.nofapchallenge.level_stuff.LevelFrag;
import com.changeandsuccess.nofapchallenge.fragments.Message;
import com.changeandsuccess.nofapchallenge.live_chat.LiveFragShow;
import com.changeandsuccess.nofapchallenge.model.NavDrawerItem;
import com.changeandsuccess.nofapchallenge.store_puchase_stuff.AllStoreTabsFrag;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

import java.util.ArrayList;


/**
 * Created by Albert AN on 8/25/14.
 * All Rights Reserved
 *
 *  If you are not the intended recipient, any disclosure, copying, distribution or any action taken or omitted to be taken in reliance on it, is prohibited and may be unlawful.
 *  If you have received this by mistake, please e-mail the sender by replying to this message, and deleting the original and any printout thereof.

 이 코드의 정보는 대외비이며, 법적으로 보호될 수 있습니다.
 코드는 오직 받는 사람만을 위한 것이고,
 다른 사람의 접근은 허가되지 않습니다.
 의도된 수신자가 아니라면, 발표, 복사,
 배포 등의 행위는 금지되어 있으며, 불법입니다.
 이 코드를 실수로 받은 것이라면, 송신자에게 반송해고, 원본/ 출력물을 삭제해 주십시오.​
 */

public class MainActivity extends ActionBarActivity {


       Context context = this;

    final Activity activity = this;


    //the side menu stuff

    private DrawerLayout mDrawerLayout;
    private View mDrawerList;
    private ActionBarDrawerToggle mDrawerToggle;

    // nav drawer title
    private CharSequence mDrawerTitle;

    // used to store app title
    private CharSequence mTitle;

    // slide menu items
    private String[] navMenuTitles;
    private TypedArray navMenuIcons;

    private ArrayList<NavDrawerItem> navDrawerItems;
    private NavDrawerListAdapter adapter;

    ImageButton home_menu, chat_menu, blog_menu, inbox_menu, battle_menu, coach_menu, level_menu, setting_menu;
View searchBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_____activity_main);

        AdView mAdView = (AdView) findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);

        mTitle = mDrawerTitle = getTitle();
        // load slide menu items
        navMenuTitles = getResources().getStringArray(R.array.nav_drawer_items);
        // nav drawer icons from resources


        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mDrawerList = (View) findViewById(R.id.list_slidermenu);


        //menubuttons

                 home_menu = (ImageButton) mDrawerList.findViewById(R.id.home_menu);//0
                chat_menu = (ImageButton) mDrawerList.findViewById(R.id.chat_menu); //2
                blog_menu = (ImageButton) mDrawerList.findViewById(R.id.blog_menu);  //1
                inbox_menu = (ImageButton) mDrawerList.findViewById(R.id.inbox_menu);
                battle_menu = (ImageButton) mDrawerList.findViewById(R.id.battle_menu); //8
                coach_menu = (ImageButton) mDrawerList.findViewById(R.id.coach_menu); //5
                level_menu = (ImageButton) mDrawerList.findViewById(R.id.level_menu);  //7
                setting_menu = (ImageButton) mDrawerList.findViewById(R.id.settings_menu);//6

        home_menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                displayView(0);
            }
        });

        chat_menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                displayView(2);
            }
        });

        blog_menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                 displayView(1);
            }
        });

        inbox_menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                displayView(3);
            }
        });

        battle_menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                displayView(8);
                //displayView(10);
            }
        });

        coach_menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                displayView(5);
            }
        });

        level_menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                displayView(7);
            }
        });
        setting_menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                displayView(6);
            }
        });

     //searchBar
        searchBar = (View) mDrawerList.findViewById(R.id.search_place);//6

        searchBar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(getBaseContext(),
                        SearchActivity.class);
                startActivity(i);
            }
        });


        // enabling action bar app icon and behaving it as toggle button
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);



        //getSupportActionBar().setBackgroundDrawable(getResources().getDrawable(R.drawable.tab_bg_selector));

        getSupportActionBar().setBackgroundDrawable(getResources().getDrawable(R.drawable.bartrans));

       // getSupportActionBar().setCustomView(getResources().getDrawable(R.style.ActionButtonStyle));

        mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout,
                R.drawable.ic_drawer, //nav menu toggle icon
                R.string.app_name, // nav drawer open - description for accessibility
                R.string.app_name // nav drawer close - description for accessibility
        ) {
            public void onDrawerClosed(View view) {

                getSupportActionBar().setTitle(mTitle);
                // calling onPrepareOptionsMenu() to show action bar icons
               // invalidateOptionsMenu();
                supportInvalidateOptionsMenu();

            }

            public void onDrawerOpened(View drawerView) {

                getSupportActionBar().setTitle(mDrawerTitle);
                // calling onPrepareOptionsMenu() to hide action bar icons
                //invalidateOptionsMenu();
                supportInvalidateOptionsMenu();

            }
        };

        mDrawerLayout.setDrawerListener(mDrawerToggle);

        if (savedInstanceState == null) {

            // on first time display view for first nav item
            displayView(0);

        }

        //title
        getSupportActionBar().setBackgroundDrawable(getResources().getDrawable(R.drawable.titlecolor));

        //start flurry



        // configure Flurry
       // FlurryAgent.setLogEnabled(true);

        // init Flurry
       // FlurryAgent.init(this, "B97Z5BMSGM97ZS4V7MBV");

        //onStart();

            }//end creat bundle

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        //Handle the back button
        if (keyCode == KeyEvent.KEYCODE_BACK && isTaskRoot()) {

            onBackPressed(this);
            return true;

        }else{

                return super.onKeyDown(keyCode, event);

        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    /* *
     * Called when invalidateOptionsMenu() is triggered
     */
    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        // if nav drawer is opened, hide the action items
        boolean drawerOpen = mDrawerLayout.isDrawerOpen(mDrawerList);
        menu.findItem(R.id.action_settings).setVisible(!drawerOpen);
        return super.onPrepareOptionsMenu(menu);
    }

    /**
     * Diplaying fragment view for selected nav drawer list item
     * */
    public void displayView(int position) {
        // update the main content by replacing fragments
        Fragment fragment = null;
        switch (position) {
            case 0:

                fragment = new Home();
                hideNavSpinnerLang();
                break;
            case 1:

                fragment = new LiveFragShow();//AllNewsBlog();//Videos();
                hideNavSpinnerLang();
                break;
            case 2:
                fragment = new CommentsFrag();

                break;
            case 3:
                //fragment = new ViewProgress();
                fragment = new Message();
                hideNavSpinnerLang();
                break;
            case 4:
                fragment = new ProfileTab();
                hideNavSpinnerLang();
                break;

            case 5:
                fragment = new AllCoachTabs();//Featured();
                hideNavSpinnerLang();
                break;

            case 6:
                fragment = new AllStoreTabsFrag();//new ComingSoon();//new AllStoreTabsFrag();//StoreFrag();
                hideNavSpinnerLang();
                break;

            case 7:
                fragment = new LevelFrag();

                hideNavSpinnerLang();
                break;

            case 8:
                fragment = new AllBattleTab();//new BattleFrag();
                hideNavSpinnerLang();
                break;

            case 9:
                fragment = new CommentsFrag();
                hideNavSpinnerLang();
                break;
            case 10:
                fragment = new ComingSoon();
                hideNavSpinnerLang();
                break;

            case 11:
                fragment = new SettingsFrag();
                hideNavSpinnerLang();
                break;

            default:
                hideNavSpinnerLang();
                break;
        }

        if (fragment != null) {

            FragmentManager fragmentManager = getSupportFragmentManager();
            fragmentManager.beginTransaction()
                    .replace(R.id.frame_container, fragment).commit();

            // update selected item and title, then close the drawer
        //    mDrawerList.setItemChecked(position, true);
        //    mDrawerList.setSelection(position);

            //set titles for tabs
            //setTitle(navMenuTitles[position]);
            mDrawerLayout.closeDrawer(mDrawerList);
            mDrawerLayout.setScrimColor(getResources().getColor(android.R.color.transparent));
        } else {
            // error in creating fragment
            Log.e("MainActivity", "Error in creating fragment");
        }
    }

    @Override
    public void setTitle(CharSequence title) {
        mTitle = title;
        getSupportActionBar().setTitle(mTitle);
    }

    /**
     * When using the ActionBarDrawerToggle, you must call it during
     * onPostCreate() and onConfigurationChanged()...
     */

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        // Sync the toggle state after onRestoreInstanceState has occurred.
        mDrawerToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        // Pass any configuration change to the drawer toggls
        mDrawerToggle.onConfigurationChanged(newConfig);
    }

    public void hideNavSpinnerLang(){

        ActionBar actionBar =((ActionBarActivity)context).getSupportActionBar();
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_STANDARD);
       // actionBar.setDisplayShowTitleEnabled(false);

    }//end hide spinner




    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        // toggle nav drawer on selecting action bar app icon/title
        if (mDrawerToggle.onOptionsItemSelected(item)) {
            return true;
        }

        // Handle action bar actions click
        switch (item.getItemId()) {
            case R.id.action_settings:

                displayView(11);

                return true;

            case R.id.inbox_message:

                displayView(3);

                return true;
            case R.id.action_search:
                // search action

                Intent i = new Intent(this,
                        SearchActivity.class);
                startActivity(i);

                return true;

            case R.id.action_commentsfrag:

                displayView(9);

                return true;

            case R.id.profile_menu:

                displayView(4);

                return true;

            case R.id.action_refresh:


                Intent inten = new Intent(this,
                        MainActivity.class);

                startActivity(inten);

                return true;

            case R.id.logout_button:

                LoginHelper loginHelper =  new LoginHelper();
                loginHelper.logoutUser(this);

                return true;

            default:
                return super.onOptionsItemSelected(item);
        }

    }


    //flurry stuff


    /*@Override
    protected void onStart()
    {
        super.onStart();

        FlurryAgent.init(this, "B97Z5BMSGM97ZS4V7MBV");
        FlurryAgent.onStartSession(context, "B97Z5BMSGM97ZS4V7MBV");


    }

    @Override
    protected void onStop()
    {
        super.onStop();
        FlurryAgent.onEndSession(context);
    }
    */

    public static void onBackPressed(final Activity activity) {
        AlertDialog.Builder builder = new AlertDialog.Builder(activity);
        builder.setTitle("Wanna Exit the app");
        builder.setMessage("exit");
        builder.setPositiveButton("yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                activity.finish();
            }
        });
        builder.setNegativeButton("no", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        builder.show();
    }

}