package com.changeandsuccess.nofapchallenge;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TabHost;
import android.widget.TabHost.OnTabChangeListener;

/**
 * Created by albert on 5/20/14.
 */
public class AllNewsBlog extends Fragment implements OnTabChangeListener {



    TabHost allTabHost;
Context context;

    View rootView;

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        context =getActivity();

        rootView = inflater.inflate(R.layout.allnewsblog_____all_news_frag, container, false);


        allTabHost = (TabHost) rootView.findViewById(R.id.allnewstabHost);
        allTabHost.setup();

        setupnewsTab(allTabHost, "videos", R.id.blog_videos_tab);
        setupnewsTab(allTabHost, "blog", R.id.blog_inspire_tab);
        setupnewsTab(allTabHost, "meditate", R.id.blog_meditate_tab);


        getFragmentManager().beginTransaction()
                .add(R.id.blog_videos_tab, new Videos())
                .commit();

        getFragmentManager().beginTransaction()
                .add(R.id.blog_inspire_tab, new Blog())
                .commit();

        getFragmentManager().beginTransaction()
                .add(R.id.blog_meditate_tab, new Meditate())
                .commit();


        return rootView;
    }



    private void setupnewsTab(final View view, final String tag, int tabcontentid) {

       // View tabview = createTabView(mTabHost.getContext(), tag, drawableId);



        TabHost.TabSpec setContent = allTabHost.newTabSpec(tag).setIndicator(tag).setContent(tabcontentid);

        allTabHost.addTab(setContent);
    }

    @Override
    public void onTabChanged(String tabId) {

        if ("videos".equals(tabId)) {


            getFragmentManager().beginTransaction()
                    .add(R.id.blog_videos_tab, new Videos())
                    .commit();

        }else if("blog".equals(tabId)){


            getFragmentManager().beginTransaction()
                    .add(R.id.blog_inspire_tab, new Blog())
                    .commit();


        }else if("meditate".equals(tabId)){
            getFragmentManager().beginTransaction()
                    .add(R.id.blog_meditate_tab, new Meditate())
                    .commit();

        }

    }



}
