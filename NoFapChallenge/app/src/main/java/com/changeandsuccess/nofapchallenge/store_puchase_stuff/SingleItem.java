package com.changeandsuccess.nofapchallenge.store_puchase_stuff;

/**
 * Created by tanggames on 2015-09-17.
 */

public class SingleItem {
    int id;
    String title;
    String price;

    public SingleItem() {
    }

    public SingleItem(int id,String title, String price) {
        this.id = id;
        this.title = title;
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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
