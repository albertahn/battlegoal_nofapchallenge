package com.changeandsuccess.nofapchallenge.store_puchase_stuff;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.changeandsuccess.nofapchallenge.LoginActivity;
import com.changeandsuccess.nofapchallenge.R;
import com.changeandsuccess.nofapchallenge.model.LoginItem;
import com.changeandsuccess.nofapchallenge.utils.UserDatabase;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.display.RoundedBitmapDisplayer;

import java.util.ArrayList;

/**
 * Created by albert on 1/6/15.
 */
public class StoreListAdapter extends ArrayAdapter<StoreItem> {

    View rowView;
    ImageView profile_photo;
    ArrayList<LoginItem> generatedLoginItem;
    ImageButton replyButton;

    String userIndex;
    static int rowList = R.layout.storelistadapter_____store_frag_list_row;

    private final Activity activity;
    private final Context context;
    private final ArrayList<StoreItem> itemsArrayList;
    ImageView product_pic;

    ImageLoader imageLoader = ImageLoader.getInstance();


    public StoreListAdapter(Activity context, ArrayList<StoreItem> itemsArrayList, String userID) {

        super(context, rowList, itemsArrayList);
        this.activity = context;

        this.context = context;
        this.itemsArrayList = itemsArrayList;
        this.userIndex = userID;


        LoginActivity loginActivity = new LoginActivity();

        if (loginActivity.isLoggedIn(activity)) {

            UserDatabase info = new UserDatabase(context);
            info.open();
            String[][] data = info.getData();
            info.close();
            userIndex = data[0][1];

        } else {
            //login person


        }

        //imageLoader

        if (!imageLoader.isInited()) {

            imageLoader.init(ImageLoaderConfiguration.createDefault(activity));
        }

    }//end interface


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        final int thisposition = position;
        // 1. Create inflater
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

//check if i sent the message

        // 2. Get rowView from inflater
        rowView = inflater.inflate(rowList, parent, false);

        // 3. Get the two text view from the rowView
        TextView product_description = (TextView) rowView.findViewById(R.id.product_description);
        TextView product_name = (TextView) rowView.findViewById(R.id.product_name);
        ImageView product_pic = (ImageView) rowView.findViewById(R.id.product_picture);

        // 4. Set the text for textView
        product_description.setText("price: $"+itemsArrayList.get(position).getPrice());
        product_name.setText(itemsArrayList.get(position).getProduct_name());


        String imageurl = "http://tanggoal.com/public/uploads/members_pic/" + itemsArrayList.get(position).getProfile_picture();

        DisplayImageOptions options = new DisplayImageOptions.Builder()
                .showImageForEmptyUri(R.drawable.ic_launcher)
                .displayer(new RoundedBitmapDisplayer(50))
                .cacheInMemory(true)
                .considerExifParams(true)
                .build();


        imageLoader.displayImage(imageurl, product_pic, options);

        int memindex = Integer.parseInt(itemsArrayList.get(position).getProduct_index().toString());

        rowView.setId(memindex);

        return rowView;
    }
}