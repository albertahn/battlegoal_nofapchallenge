package com.changeandsuccess.nofapchallenge.store_puchase_stuff;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.View;

import com.changeandsuccess.nofapchallenge.R;
import com.changeandsuccess.nofapchallenge.util.IabHelper;
import com.changeandsuccess.nofapchallenge.util.IabResult;
import com.changeandsuccess.nofapchallenge.util.Inventory;
import com.changeandsuccess.nofapchallenge.util.Purchase;

/**
 * Created by tanggames on 2015-09-18.
 */
public class StoreLastScene  extends ActionBarActivity {

    private static final String TAG = "HelloWorld";
    IabHelper mHelper;
    String ITEM_SKU;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.allstoretabsfrag_____last_scene);

        Intent intent = getIntent();
        if(intent!=null){
            ITEM_SKU = intent.getStringExtra("sku");
        }

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
    }

    public void onLastSceneButtonClicked(View v){
        //ITEM_SKU = "android.test.purchased";
        mHelper.launchPurchaseFlow(this, ITEM_SKU, 10001, mPurchaseFinishedListener, "mypurchasetoken");
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        Log.d(TAG, "0");
        if (!mHelper.handleActivityResult(requestCode, resultCode, data)) {
            Log.d(TAG, "00");
            super.onActivityResult(requestCode, resultCode, data);
        }
    }

    IabHelper.OnIabPurchaseFinishedListener mPurchaseFinishedListener = new IabHelper.OnIabPurchaseFinishedListener() {
        public void onIabPurchaseFinished(IabResult result, Purchase purchase)
        {
            if (result.isFailure()) {
                Log.d(TAG, "1");
                // Handle error
                return;
            }
            else if (purchase.getSku().equals(ITEM_SKU)) {
                Log.d(TAG, "2");
                consumeItem();
                Log.d(TAG, "3");
                finish();
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
}
