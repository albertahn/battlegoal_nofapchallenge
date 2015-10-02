package com.changeandsuccess.nofapchallenge.store_puchase_stuff;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.changeandsuccess.nofapchallenge.R;
import com.changeandsuccess.nofapchallenge.utils.JsonReader;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by albert on 1/6/15.
 */
public class LoadStoreProducts extends AsyncTask<String, Integer, String> {
    ArrayList<StoreItem> items;
        JSONArray jsonArray;
        Activity activity;
        String userID;
        JSONObject jsonOb;
        View rootView;
// ProgressBar progressBar;

        public LoadStoreProducts( Activity activity) {
        this.activity = activity;
        // progressBar = (ProgressBar) rootView.findViewById(R.id.input_progress_bar);
        // progressBar.setVisibility(View.VISIBLE);

        } //loadstoreproducts


//interface to get result
@Override
protected String doInBackground(String... params){

        try {

final String STREAMURL = "http://mobile.tanggoal.com/quote/get_all_store_products";//activity.getResources().getString(R.string.default_comments_url);

        jsonArray = JsonReader.readJsonArrayFromUrl(STREAMURL);
        // return jsonArray;
        } catch (IOException e) {

        // TODO Auto-generated catch block
        e.printStackTrace();
        } catch (JSONException e) {

        // TODO Auto-generated catch block
        e.printStackTrace();
        Log.d("here", "noJSON");
        }

        return null;
        }

@Override
protected void onPostExecute(String result) {
        super.onPostExecute(result);

           /*Dialog d = new Dialog(activity);
            TextView t = new TextView(activity);
            t.setText(""+jsonArray.toString());
            d.setContentView(t);
            d.show();*/

        if (jsonArray != null) {



        View rootview = ((Activity) activity).getWindow().getDecorView().findViewById(android.R.id.content);



            StoreListAdapter proAdapter = new StoreListAdapter(activity, generateData(jsonArray), userID);

            final  ListView storelist = (ListView) rootview.findViewById(R.id.listView);

            if(storelist !=null){

                storelist.setAdapter(proAdapter);
                storelist.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                        int cock = view.getId();
                        //String  otherguyname = view.getTag(position).toString();

                        Dialog d = new Dialog(activity);
                        d.setTitle(""+cock);
                        d.show();


                        Intent intent = new Intent(activity, StoreLastScene.class);
                        if(cock == 3) {  //donate
                            //Toast.makeText(rootView.getContext(), items[2].getProduct_name(), Toast.LENGTH_LONG).show();
                            //  intent.putExtra("sku", "android.test.purchased");.
                            intent.putExtra("product_index", cock);
                            intent.putExtra("title", items.get(2).getProduct_name());
                            intent.putExtra("price", items.get(2).getProduct_description());
                            intent.putExtra("sku", "donate_battlegoal");
                        }else if(cock == 1){ //attach with albert
                           // Toast.makeText(rootView.getContext(), item.getTitle(), Toast.LENGTH_LONG).show();
                            //  intent.putExtra("sku", "android.test.purchased");
                            intent.putExtra("title", items.get(0).getProduct_name());
                            intent.putExtra("price", items.get(0).getProduct_description());
                            intent.putExtra("sku", "albert_29_skype_call_training_session");
                        }

                        //  intent.putExtra("sku","donate_battlegoal");
                        activity.startActivity(intent);

                    }
                });
            }//if

        } else {

        Log.d("emptyarray", "sptmey man");
        }


        }// end post ex


        ArrayList<StoreItem> generateData(JSONArray jsondata) {

        items = new ArrayList<StoreItem>();

        for (int i = 0; i < jsondata.length(); i++) {


        try {
        //check if course comment
        //if(jsondata.getJSONObject(i).getString("courses_index")!="") {

        items.add(new StoreItem(

        jsondata.getJSONObject(i).getString("product_index"),
        jsondata.getJSONObject(i).getString("product_name"),
        jsondata.getJSONObject(i).getString("product_description"),
        jsondata.getJSONObject(i).getString("product_picture"),
        jsondata.getJSONObject(i).getString("price"),
        jsondata.getJSONObject(i).getString("username"),
        jsondata.getJSONObject(i).getString("profile_picture"),
        jsondata.getJSONObject(i).getString("timestamp")

        ));

        // }//end if
        } catch (JSONException e) {

        e.printStackTrace();

        }

        }

        return items;

        }// end generate



        }