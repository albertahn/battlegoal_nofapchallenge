package com.changeandsuccess.nofapchallenge.store_puchase_stuff;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;

import com.changeandsuccess.nofapchallenge.R;

import java.util.ArrayList;

/**
 * Created by albert on 1/18/15.
 */

public class AllStoreTabsFrag extends Fragment {
    View rootView;

    ListView listView;
    SingleAdapter adapter;

    int[] ids = {0,1};
    String[] titles = {"Donate to BattleGoal","Live 1 Hour Coaching Session With Albert"};
    String[] prices = {"$ 1","$ 15"};

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        rootView =  inflater.inflate(R.layout.allstoretabsfrag_____store_tabs_frag, container, false);

 new LoadStoreProducts(getActivity()).execute();



/*
        coach_info layout1 = new coach_info(rootView.getContext());
        LinearLayout coach_info_container = (LinearLayout)rootView.findViewById(R.id.coach_info_container);
        coach_info_container.addView(layout1);

        listView = (ListView)rootView.findViewById(R.id.listView);
        adapter = new SingleAdapter();

        adapter.addItem(new SingleItem(ids[0],titles[0], prices[0]));
        adapter.addItem(new SingleItem(ids[1],titles[1], prices[1]));

        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                ListView listView = (ListView) parent;

                SingleItem item = (SingleItem) adapter.getItem(position);

                Intent intent = new Intent(rootView.getContext(), StoreLastScene.class);
                if(item.id == 0) {  //donate
                    Toast.makeText(rootView.getContext(), item.getTitle(), Toast.LENGTH_LONG).show();
                  //  intent.putExtra("sku", "android.test.purchased");.
                    intent.putExtra("title", item.getTitle());
                    intent.putExtra("price", item.getPrice());
                    intent.putExtra("sku", "donate_battlegoal");
                }else if(item.id == 1){ //attach with albert
                    Toast.makeText(rootView.getContext(), item.getTitle(), Toast.LENGTH_LONG).show();
                  //  intent.putExtra("sku", "android.test.purchased");
                    intent.putExtra("title", item.getTitle());
                    intent.putExtra("price", item.getPrice());
                    intent.putExtra("sku", "albert_29_skype_call_training_session");
                }

              //  intent.putExtra("sku","donate_battlegoal");
                startActivity(intent);
            }
        });
        */

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
    } //single adapter
}
