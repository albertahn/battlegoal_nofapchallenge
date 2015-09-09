package com.changeandsuccess.nofapchallenge;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;

public class Meditate extends Fragment {

    View rootView;

    WebView mWeb;
    ProgressBar progressBar;
    String site;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
         rootView = inflater.inflate(R.layout.meditate_____med_frag, container, false);






        mWeb = (WebView) rootView.findViewById(R.id.webView);
       /* web.setWebChromeClient(new WebChromeClient());

        web.getSettings().setJavaScriptEnabled(true);
        web.getSettings().getLoadsImagesAutomatically();
        web.getSettings().setAllowFileAccess(true);

        web.loadData("<iframe width=\"100%\" height=\"100%\" src=\"http://www.youtube.com/embed/VROrxw0FSAQ\" frameborder=\"0\" allowfullscreen></iframe>", "text/html",
                "utf-8");



        web.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("vnd.youtube://VROrxw0FSAQ"));
                WebView web = (WebView) rootView.findViewById(R.id.webView);
                web.loadUrl("http://www.youtube.com/watch?v=_K7IFfEloOQ");

                //intent.putExtra("force_fullscreen", true);

                startActivity(intent);

            }
        });


*/




        site = "http://www.youtube.com/embed/VROrxw0FSAQ";



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




//at the end return
        return rootView;
    }

}

