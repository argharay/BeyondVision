package com.example.speechtotext;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextView;

import java.io.FileOutputStream;

public class DisplayWebView extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_webview);

        WebView myWebView = (WebView) findViewById(R.id.webview);
        myWebView.setDrawingCacheEnabled(true);
        myWebView.buildDrawingCache(true);
        myWebView.getSettings().setJavaScriptEnabled(true);
        myWebView.getSettings().setLoadWithOverviewMode(true);
        //WebSettings webSettings = myWebView.getSettings();
        //webSettings.setJavaScriptEnabled(true);
        myWebView.setWebViewClient(new WebViewClient());
        //myWebView.loadUrl("http://www.google.com");
        /*String summary = "<!DOCTYPE html>\n" +
                "<html>\n" +
                "<head>\n" +
                "<title>MathJax TeX Test Page</title>\n" +
                "<script type=\"text/x-mathjax-config\">\n" +
                "  MathJax.Hub.Config({tex2jax: {inlineMath: [['$','$'], ['\\\\(','\\\\)']]}});\n" +
                "</script>\n" +
                "<script type=\"text/javascript\" async\n" +
                "  src=\"\"https://cdnjs.cloudflare.com/ajax/libs/mathjax/2.7.1/MathJax.js?config=TeX-AMS_CHTML\">\n" +
                "</script>\n" +
                "</head>\n" +
                "<body>\n" +
                "When $a \\ne 0$, there are two solutions to \\(ax^2 + bx + c = 0\\) and they are\n" +
                "$$x = {-b \\pm \\sqrt{b^2-4ac} \\over 2a}.$$\n" +
                "</body>\n" +
                "</html>";
        myWebView.loadData(summary, "text/html", null);*/

        String summary = "There are two solutions to \\(ax^2 + bx + c = 0\\) and they are\n" +
                "$$x = {-b \\pm \\sqrt{b^2-4ac} \\over 2a}.$$\n";

        /*if (android.os.Build.VERSION.SDK_INT < 19)
        {
            myWebView.loadDataWithBaseURL("http://bar","<script type='text/javascript' "
                            +"src='https://cdnjs.cloudflare.com/ajax/libs/mathjax/2.7.1/MathJax.js?config=TeX-AMS_CHTML'>"
                            +"</script><math display='block'>"+summary+"</math>",
                    "text/html","utf-8","");
        }
        else
        {*/
            myWebView.loadDataWithBaseURL("http://bar","<script type='text/javascript' "
                            +"src='https://cdnjs.cloudflare.com/ajax/libs/mathjax/2.7.1/MathJax.js?config=TeX-AMS_CHTML'>"
                            +"</script><math display='block'>"+summary+"</math>",
                    "text/html","utf-8","");
        //}




    }

    public void saveImage (View view) {

        Log.d("Speech To Text","In saveImage");

        WebView myWebView = (WebView) findViewById(R.id.webview);
        myWebView.setDrawingCacheEnabled(true);
        myWebView.buildDrawingCache(true);
        //Bitmap bitmap = Bitmap.createBitmap(myWebView.getWidth(), myWebView.getHeight(), Bitmap.Config.ARGB_8888);
        Bitmap bitmap = Bitmap.createBitmap(myWebView.getDrawingCache());
        Canvas canvas = new Canvas(bitmap);
        myWebView.draw(canvas);

        try {
            FileOutputStream fos = new FileOutputStream( "/sdcard/"  + "page.jpg" );
            bitmap.compress(Bitmap.CompressFormat.JPEG, 90, fos );
            fos.close();

        }
        catch( Exception e ) {
            System.out.println("-----error--"+e);
        }
        myWebView.setDrawingCacheEnabled(false);
    }

}

