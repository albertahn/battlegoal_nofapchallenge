package com.changeandsuccess.nofapchallenge;

/**
 * Created by contendera on 2014. 1. 11..
 */
public class Item {


    private String indexman;
    private String day;
    private String note;
    private String date;

    public Item(String indexman, String day, String note, String date) {
        super();

        this.day= day;
        this.note = note;
        this.date = date;
        this.indexman = indexman;

    }
    // getters and setters...

    public CharSequence getDay() {
        // TODO Auto-generated method stub
        return day;
    }


    public CharSequence getNote() {
        // TODO Auto-generated method stub
        return note;
    }


    public CharSequence getDate() {
        // TODO Auto-generated method stub
        return date;
    }

    public  CharSequence get_id(){

        return indexman;
    }
}
