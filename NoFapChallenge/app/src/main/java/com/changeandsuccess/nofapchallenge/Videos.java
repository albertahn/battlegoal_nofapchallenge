package com.changeandsuccess.nofapchallenge;

import android.content.Intent;
import android.net.Uri;
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

/**
 * Created by albert on 5/19/14.
 */
public class Videos extends Fragment {


    Button youtubeButton;

    WebView mWeb;
    ProgressBar progressBar;
    String site;
        View rootView;

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {

            rootView = inflater.inflate(R.layout.videos_____videos_frag, container, false);


            youtubeButton = (Button) rootView.findViewById(R.id.youtube_button);

            //youtubeButton
            youtubeButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://youtube.com/user/SuccessAlbert"));
                    startActivity(browserIntent);
                }
            });

            //the webview

           // site = "http://m.changeandsuccess.com";

            site = "http://changingalbert.blogspot.kr";
            mWeb = (WebView) rootView.findViewById(R.id.blog_videos_WebView);
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
        }


    }

