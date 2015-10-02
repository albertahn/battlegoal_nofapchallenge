package com.changeandsuccess.nofapchallenge.store_puchase_stuff;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.changeandsuccess.nofapchallenge.LoginActivity;
import com.changeandsuccess.nofapchallenge.R;
import com.changeandsuccess.nofapchallenge.payment_util.IabHelper;
import com.changeandsuccess.nofapchallenge.payment_util.IabResult;
import com.changeandsuccess.nofapchallenge.payment_util.Inventory;
import com.changeandsuccess.nofapchallenge.payment_util.Purchase;
import com.changeandsuccess.nofapchallenge.utils.UserDatabase;

/**
 * Created by tanggames on 2015-09-18.
 */
public class StoreLastScene  extends ActionBarActivity {

    private static final String TAG = "HelloWorld";
    IabHelper mHelper;
    String ITEM_SKU;
    Activity thisman = this;
    String user_index;
    int product_index;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.allstoretabsfrag_____last_scene);

//productindex


        Intent intent = getIntent();
        if(intent!=null){

            product_index = (int) intent.getIntExtra("product_index", 1);

            Dialog d = new Dialog(this);


            // ITEM_SKU = intent.getStringExtra("sku"); //comment out
            TextView textView = (TextView)findViewById(R.id.SLSTextView1);
            textView.setText(intent.getStringExtra("title"));
            textView = (TextView)findViewById(R.id.SLSTextView2);
            textView.setText(intent.getStringExtra("price"));

        }//

        String base64EncodedPublicKey =
                "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAntre0/BtawpWNYR20e1TUqd+exBKDv9QXJx1FqVhU2mpxoF1yM0JR6hVuKFLQMMV4kb4aCGDwrSwqhwpZh4g7TReg8Ljbw6iEd5JDAffNumWBjQ2fvKSI6ie2DIetWbib24SlKae44e2Eih/xiKWH+aoSbKr8sth5Egnbt4bh1SVpR6YCX8fHoGv4jFX+WZ5LW1ANPUBR3xvcVqiuUphmOSqI6FqlssHpvBRIb5dNGl44FR3Kgh7GjNwRdmOnU18C4KU46MiqpnJyS3uKreGDs686MeQBVp0BgY4l27EzPQpRCJoJ1QcvxiPUoIfPoTPdyMgggrWzUwYxV5zSoWabQIDAQAB";

        mHelper = new IabHelper(this, base64EncodedPublicKey);

        mHelper.startSetup(new IabHelper.OnIabSetupFinishedListener() {
            public void onIabSetupFinished(IabResult result) {
                if (!result.isSuccess()) {
                    Log.d(TAG, "In-app Billing setup failed: " + result);
                } else {
                    Log.d(TAG, "In-app Billing is set up OK");
                }
            }
        });

        //get useremail

        LoginActivity loginActivity = new LoginActivity();

        if(loginActivity.isLoggedIn(thisman)){
            UserDatabase info = new UserDatabase(thisman);
            info.open();
            String[][] data = info.getData();
            info.close();

            user_index = data[0][1];

        }else{

            Intent i = new Intent(thisman,
                    LoginActivity.class);
            startActivity(i);
        }
//backbutton
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

    }

    public void onLastSceneButtonClicked(View v){
        ITEM_SKU = "android.test.purchased"; //
        mHelper.launchPurchaseFlow(this, ITEM_SKU, 10001, mPurchaseFinishedListener, "mypurchasetoken");
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        if (!mHelper.handleActivityResult(requestCode, resultCode, data)) {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }

    IabHelper.OnIabPurchaseFinishedListener mPurchaseFinishedListener = new IabHelper.OnIabPurchaseFinishedListener() {
        public void onIabPurchaseFinished(IabResult result, Purchase purchase)
        {
            if (result.isFailure()) {
                // Handle error
                if(result.getResponse()==7) {
                    mHelper.queryInventoryAsync(mReceivedInventoryListener);
                }

                return;
            }
            else if (purchase.getSku().equals(ITEM_SKU)) {
                consumeItem();
                // buyButton.setEnabled(false);
            }

        }
    };
    public void consumeItem() {
        mHelper.queryInventoryAsync(mReceivedInventoryListener);
    }

    IabHelper.QueryInventoryFinishedListener mReceivedInventoryListener = new IabHelper.QueryInventoryFinishedListener() {
        public void onQueryInventoryFinished(IabResult result,Inventory inventory) {

            if (result.isFailure()) {
                // Handle failure
            } else {
                mHelper.consumeAsync(inventory.getPurchase(ITEM_SKU), mConsumeFinishedListener);
            }
        }
    };

    IabHelper.OnConsumeFinishedListener mConsumeFinishedListener =new IabHelper.OnConsumeFinishedListener() {
        public void onConsumeFinished(Purchase purchase,IabResult result) {
            if (result.isSuccess()) {
                //    clickButton.setEnabled(true);


                SendPuchase_to_db sending = new SendPuchase_to_db(user_index, product_index, thisman);

                sending.execute();

            } else {
                // handle error
            }
        }
    };

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mHelper != null) mHelper.dispose();
        mHelper = null;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {

            case android.R.id.home:
                finish();
                return true;
            default:

                finish();
                return super.onOptionsItemSelected(item);
        }
    } //end onoptions selected
}
