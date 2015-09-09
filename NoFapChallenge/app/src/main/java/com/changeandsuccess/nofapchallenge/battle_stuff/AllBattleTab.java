package com.changeandsuccess.nofapchallenge.battle_stuff;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.TabHost;

import com.changeandsuccess.nofapchallenge.LoginActivity;
import com.changeandsuccess.nofapchallenge.R;
import com.changeandsuccess.nofapchallenge.adapter.TitleNavigationAdapter;
import com.changeandsuccess.nofapchallenge.utils.UserDatabase;

/**
 * Created by albert on 11/10/14.
 */
public class AllBattleTab  extends Fragment implements TabHost.OnTabChangeListener {

    TabHost allTabHost;
    Context context;
    View rootView;
    Menu menu;
    String userIndex;
    // Navigation adapter
    private TitleNavigationAdapter adapter;


    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState){

        context = getActivity();
        rootView = inflater.inflate(R.layout.allbattletab_____all_message_frag, container, false);

        allTabHost = (TabHost) rootView.findViewById(R.id.all_message_tab_host);
        allTabHost.setup();

        setupnewsTab(allTabHost, "Battles", "chat", R.id.product_comment_tab);
        setupnewsTab(allTabHost, "Requests", "inbox", R.id.inbox_tab);

//set the tabs for the buttons

        getFragmentManager().beginTransaction()
                .add(R.id.product_comment_tab, new BattleFrag())
                .commit();

        getFragmentManager().beginTransaction()
                .add(R.id.inbox_tab, new AcceptBattle_frag())
                .commit();



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




        getActivity().getWindow().setSoftInputMode(
                WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);

        return rootView;
    }//end


    private void setupnewsTab(final View view, String tabName, final String tag, int tabcontentid) {

        //tabNameView tabview = createTabView(mTabHost.getContext(), tag, drawableId);



        TabHost.TabSpec setContent = allTabHost.newTabSpec(tag).setIndicator(tabName).setContent(tabcontentid);

        allTabHost.addTab(setContent);
    }// end setup tabs


    @Override
    public void onTabChanged(String tabId) {

        if ("chat".equals(tabId)) {


            getFragmentManager().beginTransaction()
                    .add(R.id.product_comment_tab, new BattleFrag())
                    .commit();

        }else if("inbox".equals(tabId)){


            getFragmentManager().beginTransaction()
                    .add(R.id.inbox_tab, new AcceptBattle_frag())
                    .commit();

        }

    }//end on tab changed

}
