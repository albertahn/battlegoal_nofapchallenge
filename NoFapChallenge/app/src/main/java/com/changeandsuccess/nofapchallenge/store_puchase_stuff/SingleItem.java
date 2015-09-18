package com.changeandsuccess.nofapchallenge.store_puchase_stuff;

/**
 * Created by tanggames on 2015-09-17.
 */

public class SingleItem {
    String title;
    String price;

    public SingleItem() {
    }

    public SingleItem(String title, String price) {
        this.title = title;
        this.price = price;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
