package com.changeandsuccess.nofapchallenge.coaches_tab_stuff;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TabHost;
import android.widget.TextView;

import com.changeandsuccess.nofapchallenge.R;

/**
 * Created by albert on 11/26/14.
 */
public class AllCoachTabs extends Fragment implements TabHost.OnTabChangeListener {


    TabHost mTabHost;
View rootView;




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);


        rootView =  inflater.inflate(R.layout.allcoachtabs_____all_coaches_tab, container, false);
        mTabHost = (TabHost) rootView.findViewById(android.R.id.tabhost);
        mTabHost.setup();

        setupTab(new TextView(getActivity()), "All Coaches", R.drawable.all_coaches, R.id.all_coaches_tab);
        setupTab(new TextView(getActivity()), "My Coaches", R.drawable.my_coach, R.id.my_coaches_tab);
        setupTab(new TextView(getActivity()), "Apply", R.drawable.apply, R.id.apply_tab);

        mTabHost.setOnTabChangedListener(this);

        mTabHost.getTabWidget().setDividerDrawable(null);

        getFragmentManager().beginTransaction()
                .add(R.id.all_coaches_tab,  new Featured())
                .commit();


return rootView;
    }



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


        @Override
    public void onTabChanged(String tabId){

        //Context context = mTabHost.getContext();
        String msg = "id of messnea is: "+ mTabHost.getCurrentTab();

        if ("All Coaches".equals(tabId)) {

            //show the homefrag
            getFragmentManager().beginTransaction()
                    .add(R.id.all_coaches_tab,  new Featured())
                    .commit();

        } else if ("My Coaches".equals(tabId)) {
            System.out.println(msg);

            //show the homefrag
            getFragmentManager().beginTransaction()
                    .add(R.id.my_coaches_tab,  new Featured())
                    .commit();


        } else if ("Apply".equals(tabId)) {

            getFragmentManager().beginTransaction()
                    .add(R.id.apply_tab,  new ApplyBeCoach())
                    .commit();


        }

    }//end tabchange


}
