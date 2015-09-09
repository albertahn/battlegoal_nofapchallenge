package com.changeandsuccess.nofapchallenge.store_puchase_stuff;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TabHost;

import android.support.v4.app.Fragment;
import android.widget.TextView;

import com.changeandsuccess.nofapchallenge.R;
import com.changeandsuccess.nofapchallenge.coaches_tab_stuff.Featured;

/**
 * Created by albert on 1/18/15.
 */
public class AllStoreTabsFrag extends Fragment implements TabHost.OnTabChangeListener {

    TabHost mTabHost;
    View rootView;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);


        rootView =  inflater.inflate(R.layout.allstoretabsfrag_____store_tabs_frag, container, false);
        mTabHost = (TabHost) rootView.findViewById(android.R.id.tabhost);
        mTabHost.setup();

        setupTab(new TextView(getActivity()), "All", R.drawable.all_coaches, R.id.free_products_tab);
        setupTab(new TextView(getActivity()), "Paid Products", R.drawable.my_coach, R.id.paid_products_tab);
        setupTab(new TextView(getActivity()), "My Products", R.drawable.apply, R.id.make_product_tab);

        mTabHost.setOnTabChangedListener(this);

        mTabHost.getTabWidget().setDividerDrawable(null);

        getFragmentManager().beginTransaction()
                .add(R.id.free_products_tab,  new FreeStoreFrag())
                .commit();


        return rootView;
    }//end oncreate



    private void setupTab(final View view, final String tag, int drawableId, int tabcontentid) {


        View tabview = createTabView(mTabHost.getContext(), tag, drawableId);

        TabHost.TabSpec setContent = mTabHost.newTabSpec(tag).setIndicator(tabview).setContent(tabcontentid);

        mTabHost.addTab(setContent);
    }//private



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
        String msg = "id of messnea is: "+ tabId;

          if ("All".equals(tabId)) {

            //show the homefrag
            getFragmentManager().beginTransaction()
                    .add(R.id.free_products_tab,  new FreeStoreFrag())
                    .commit();

        } else if ("Paid Products".equals(tabId)) {

            //show the homefrag
            getFragmentManager().beginTransaction()
                    .add(R.id.paid_products_tab,  new Featured())
                    .commit();

              Log.e("workingtabs?", msg);
              //System.out.println(msg);

        } else if ("My Products".equals(tabId)) {

            getFragmentManager().beginTransaction()
                    .add(R.id.make_product_tab,  new Featured())
                    .commit();

              Log.e("workingtabs?", msg);
        }

    }//end tabchange


}
