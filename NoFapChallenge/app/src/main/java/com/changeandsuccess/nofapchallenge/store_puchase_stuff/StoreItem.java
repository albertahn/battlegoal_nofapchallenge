package com.changeandsuccess.nofapchallenge.store_puchase_stuff;

/**
 * Created by albert on 1/6/15.
 */
public class StoreItem {

    private String
            product_index,
            product_name,
            product_description,
            product_pic,
            price,
            username,
            profile_picture,
            timestamp;


    public StoreItem(
            String product_index,
            String product_name,
            String product_description,
            String product_pic,
            String price,
            String username,
            String profile_picture,
            String timestamp){


                        this.product_index=product_index;
                        this.product_name=product_name;
                        this.product_description = product_description;
                        this.product_pic = product_pic;
                        this.price=price;
                        this.username = username;
                        this.profile_picture = profile_picture;
                        this.timestamp = timestamp;

    }// end loginitem


    public CharSequence getUsername(){

        return username;
    }

    public String getProfile_picture(){

        return profile_picture;


    }


    public String getProduct_index(){

        return product_index;
    }

    public  String getProduct_name(){

        return product_name;
    }

    public String getTimestamp(){

        return timestamp;
    }
    public String getProduct_description(){

        return product_description;
    }

    public String getPrice(){

        return price;
    }
}
