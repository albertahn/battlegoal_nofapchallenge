package com.changeandsuccess.nofapchallenge.admob_stuff;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.provider.Settings;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.changeandsuccess.nofapchallenge.R;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Created by albert on 11/3/14.
 */
public class LoadMessageListAd  extends Fragment {

    private AdView mAdView;

    boolean clickedAd = false;
    int clickedIntValue;
    ViewGroup container;
    LayoutInflater inflater;

    public LoadMessageListAd() {

    }



   @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        this.inflater = (LayoutInflater) inflater;
        this.container = (ViewGroup) container;

        return inflater.inflate(R.layout.loadmessagelistad_____fragment_ad, container, false);


    }

    @Override
    public void onActivityCreated(Bundle bundle) {
        super.onActivityCreated(bundle);

        // Gets the ad view defined in layout/ad_fragment.xml with ad unit ID set in
        // values/strings.xml.
        //

        View rootView = ((Activity) getActivity()).getWindow().getDecorView().findViewById(android.R.id.content);

        mAdView = (AdView) rootView.findViewById(R.id.adView);

        // Create an ad request. Check logcat output for the hashed device ID to
        // get test ads on a physical device. e.g.
        // "Use AdRequest.Builder.addTestDevice("ABCDEF012345") to get test ads on this device."



        String android_id = Settings.Secure.getString(getActivity().getContentResolver(), Settings.Secure.ANDROID_ID);
        String deviceId = md5(android_id).toUpperCase();
        AdRequest adRequest = new AdRequest.Builder()
                //.addTestDevice(deviceId)
                .build();

        /*boolean testis = adRequest.isTestDevice(getActivity());

        Dialog d = new Dialog(getActivity());
        d.setTitle(""+testis);
        d.show();*/


        // Set the AdListener.
        mAdView.setAdListener(new AdListener() {
            /** Called when an ad is clicked and about to return to the application. */
            @Override
            public void onAdClosed() {
                Log.d("LOG_TAG", "onAdClosed");
                Toast.makeText(getActivity(), "onAdClosed", Toast.LENGTH_SHORT).show();
            }

            /** Called when an ad failed to load. */
            @Override
            public void onAdFailedToLoad(int error) {
                String message = "onAdFailedToLoad: " ;
                Log.d("LOG_TAG", message);
                Toast.makeText(getActivity(), message, Toast.LENGTH_SHORT).show();
            }

            /**
             * Called when an ad is clicked and going to start a new Activity that will
             * leave the application (e.g. breaking out to the Browser or Maps
             * application).
             */
            @Override
            public void onAdLeftApplication() {

                Log.d("LOG_TAG", "onAdLeftApplication");
                Toast.makeText(getActivity(), "onAdLeftApplication", Toast.LENGTH_SHORT).show();
            }

            /**
             * Called when an Activity is created in front of the app (e.g. an
             * interstitial is shown, or an ad is clicked and launches a new Activity).
             */
            @Override
            public void onAdOpened() {
                Log.d("LOG_TAG", "onAdOpened");
                Toast.makeText(getActivity(), "onAdOpened", Toast.LENGTH_SHORT).show();

                /*Dialog d = new Dialog(getActivity());
                d.setTitle(""+clickedIntValue);
                d.show();*/

                clickedAd = true;

                SharedPreferences sp = getActivity().getSharedPreferences("your_prefs", getActivity().MODE_PRIVATE);
                SharedPreferences.Editor editor = sp.edit();
                int myIntValue = sp.getInt("clicked_ad",0)+1;

                editor.putInt("clicked_ad",myIntValue++);
                editor.commit();


                mAdView.setVisibility(View.INVISIBLE);

                Log.e("myIntValue", ""+myIntValue);
            }

            /** Called when an ad is loaded. */
            @Override
            public void onAdLoaded() {
                Log.d("LOG_TAG", "onAdLoaded");
                //Toast.makeText(getActivity(), "onAdLoaded", Toast.LENGTH_SHORT).show();
            }
        });



        // Start loading the ad in the background.

        SharedPreferences sp = getActivity().getSharedPreferences("your_prefs", getActivity().MODE_PRIVATE);
        clickedIntValue = sp.getInt("clicked_ad", 0);

        if(clickedIntValue<2) {

            /*Dialog d = new Dialog(getActivity());
            d.setTitle(""+clickedIntValue);
            d.show();*/

            mAdView.loadAd(adRequest);

        }else{

            TextView t = new TextView(getActivity());
            t.setText("You can click an ad only once");

            mAdView.addView(t);
        }
    }//end on create



    /** Called when leaving the activity */
    @Override
    public void onPause() {
        if (mAdView != null) {

            mAdView.pause();
        }
        super.onPause();
    }

    /** Called when returning to the activity */
    @Override
    public void onResume() {
        super.onResume();
        if (mAdView != null) {
            mAdView.resume();
        }
    }

    /** Called before the activity is destroyed */
    @Override
    public void onDestroy() {
        if (mAdView != null) {
            mAdView.destroy();
        }
        super.onDestroy();
    }

    public static final String md5(final String s) {
        try {
            // Create MD5 Hash
            MessageDigest digest = java.security.MessageDigest
                    .getInstance("MD5");
            digest.update(s.getBytes());
            byte messageDigest[] = digest.digest();

            // Create Hex String
            StringBuffer hexString = new StringBuffer();
            for (int i = 0; i < messageDigest.length; i++) {
                String h = Integer.toHexString(0xFF & messageDigest[i]);
                while (h.length() < 2)
                    h = "0" + h;
                hexString.append(h);
            }
            return hexString.toString();

        } catch (NoSuchAlgorithmException e) {

        }
        return "";
    }

}