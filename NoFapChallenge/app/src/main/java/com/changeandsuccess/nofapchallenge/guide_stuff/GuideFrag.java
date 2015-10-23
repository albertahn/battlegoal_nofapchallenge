package com.changeandsuccess.nofapchallenge.guide_stuff;

import android.app.ActionBar;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.ProgressBar;

import com.changeandsuccess.nofapchallenge.LoginActivity;
import com.changeandsuccess.nofapchallenge.LoginHelper;
import com.changeandsuccess.nofapchallenge.R;
import com.changeandsuccess.nofapchallenge.message_activity.LoadMessageFrag;
import com.changeandsuccess.nofapchallenge.model.LoginItem;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

import java.util.ArrayList;

/**
 * Created by albertan on 10/22/15.
 */
public class GuideFrag  extends Fragment

    {

        WebView mWeb;
        ProgressBar progressBar;
        String site;

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        View rootView = inflater.inflate(R.layout.guide_frag, container, false);


            mWeb = (WebView) rootView.findViewById(R.id.webView);


            site = "https://www.youtube.com/watch?v=cH-58plWVkU";//"http://www.youtube.com/embed/cH-58plWVkU";//https://www.youtube.com/watch?v=3i1A0CQ6qpwhttps://www.youtube.com/watch?v=cH-58plWVkU



            progressBar = (ProgressBar) rootView.findViewById(R.id.progressbar_video);


            WebSettings set = mWeb.getSettings();
            set.setJavaScriptEnabled(true);
            set.setBuiltInZoomControls(true);
            mWeb.getSettings().setJavaScriptCanOpenWindowsAutomatically(true);
            mWeb.setWebViewClient(new WebViewClient() {
                public boolean shouldOverrideUrlLoading(WebView view, String url) {
                    view.loadUrl(url);
                    return true;
                };


                public void onPageStarted(WebView view, String url,
                                          android.graphics.Bitmap favicon) {
                    super.onPageStarted(view, url, favicon);
                    progressBar.setVisibility(View.VISIBLE);
                };

                public void onPageFinished(WebView view, String url) {
                    super.onPageFinished(view, url);
                    progressBar.setVisibility(View.INVISIBLE);
                };

                public void onReceivedError(WebView view, int errorCode,
                                            String description, String failingUrl) {
                    super.onReceivedError(view, errorCode, description, failingUrl);

                };
            });

            mWeb.setWebChromeClient(new WebChromeClient() {
                @Override
                public void onProgressChanged(WebView view, int newProgress) {
                    progressBar.setProgress(newProgress);
                }
            });

            mWeb.loadUrl(site);



            return rootView;

        }//end else not logged in


    }//end class

