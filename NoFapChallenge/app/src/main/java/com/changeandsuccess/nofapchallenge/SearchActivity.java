package com.changeandsuccess.nofapchallenge;

import android.app.Activity;
import android.os.Bundle;

import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.MenuItem;
import android.widget.EditText;

import com.changeandsuccess.nofapchallenge.searchActivity.LoadSearch;

/**
 * Created by albert on 7/9/14.
 */
public class SearchActivity extends ActionBarActivity {

    EditText search_input;
    Activity activity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.searchactivity_____search_page);

        ActionBar actionBar = getSupportActionBar();

        actionBar.setDisplayHomeAsUpEnabled(true);

        activity = this;

        search_input = (EditText) findViewById(R.id.search_input);

        search_input.addTextChangedListener(new TextWatcher() {
            public void afterTextChanged(Editable s) {

              /*  Dialog d = new Dialog(activity);
                d.setTitle(""+search_input.getText().toString());
                d.show();*/

                new LoadSearch(activity).execute(search_input.getText().toString());

            }

            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }
        });


    } //end create


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:

                finish();
                //NavUtils.navigateUpFromSameTask(this);
                return true;
            default:

                finish();
                return super.onOptionsItemSelected(item);
        }
    }
}
