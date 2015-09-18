package com.changeandsuccess.nofapchallenge.store_puchase_stuff;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TabHost;
import android.widget.AdapterView;

import android.support.v4.app.Fragment;
import android.widget.TextView;
import android.widget.Toast;

import com.changeandsuccess.nofapchallenge.R;
import com.changeandsuccess.nofapchallenge.coaches_tab_stuff.Featured;
import com.changeandsuccess.nofapchallenge.util.IabHelper;
import com.changeandsuccess.nofapchallenge.util.IabResult;
import com.changeandsuccess.nofapchallenge.util.Inventory;
import com.changeandsuccess.nofapchallenge.util.Purchase;

import java.util.ArrayList;

/**
 * Created by albert on 1/18/15.
 */

public class AllStoreTabsFrag extends Fragment  {
    View rootView;

    ListView listView;
    SingleAdapter adapter;

    String[] titles = {"익명 상담 문자 서비스","30 Quotes"};
    String[] prices = {"$ 2.99","$ 3.39"};

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        rootView =  inflater.inflate(R.layout.allstoretabsfrag_____store_tabs_frag, container, false);

        coach_info layout1 = new coach_info(rootView.getContext());
        LinearLayout coach_info_container = (LinearLayout)rootView.findViewById(R.id.coach_info_container);
        coach_info_container.addView(layout1);

        listView = (ListView)rootView.findViewById(R.id.listView);
        adapter = new SingleAdapter();

        adapter.addItem(new SingleItem(titles[0], prices[0]));
        adapter.addItem(new SingleItem(titles[1], prices[1]));

        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                ListView listView = (ListView) parent;

                SingleItem item = (SingleItem) adapter.getItem(position);
                Toast.makeText(rootView.getContext(), item.getTitle(), Toast.LENGTH_LONG).show();
                Intent intent = new Intent(rootView.getContext(),StoreLastScene.class);
                intent.putExtra("sku","android.test.purchased");

              //  intent.putExtra("sku","donate_battlegoal");
                startActivity(intent);
            }
        });

        return rootView;
    }//end oncreate

    class SingleAdapter extends BaseAdapter{
        ArrayList<SingleItem> items = new ArrayList<SingleItem>();

        @Override
        public int getCount() {
            return items.size();
        }

        @Override
        public Object getItem(int position) {
            return items.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            SingleItemView view = new SingleItemView(rootView.getContext());
            SingleItem curItem = items.get(position);
            view.setPrice(curItem.getPrice());
            view.setTitle(curItem.getTitle());

            return view;
        }
        public void addItem(SingleItem item){
            items.add(item);
        }
    }
}
