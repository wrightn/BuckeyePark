package buckeyepark.com.buckeyepark;

import android.app.Activity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.Window;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import java.net.URL;
import java.util.ArrayList;

/**
 * Created by Owner on 4/21/2015.
 */
public class GarageWebView extends Activity {
    private WebView mWebView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle b = getIntent().getExtras();
        int garageNum = b.getInt("garage-num");
        String url = getGarageURL(garageNum);
        getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        mWebView = new WebView(this);
        mWebView.loadUrl(url);
        mWebView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }
        });

        this.setContentView(mWebView);
    }

    @Override
    public boolean onKeyDown(final int keyCode, final KeyEvent event) {
        if ((keyCode == KeyEvent.KEYCODE_BACK) && mWebView.canGoBack()) {
            mWebView.goBack();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
    
    public String getGarageURL(int garageNum) {
        switch (garageNum) {
            case 0:
                //   "West Lane Avenue";
               
                return "http://osu.campusparc.com/osu/garages/west-lane";
            case 1:
                //  "Arps Hall";
                return "" ;
            case 2:
                //   "Tuttle Park Place";
                return "" ;
            case 3:
                //   "9th Avenue East";
                return "";
            case 4:
                //  "Neil Avenue";
                return "";
            case 5:
                //  "11th Avenue";
                return "";
            case 6:
                //  "South Gateway";
                return "";
            case 7:
                //  "Lane Avenue";
                return "";
            case 8:
                //  "Ohio Union North";
                return "";
            case 9:
                // "North Cannon";
                return "";
            case 10:
                //  "Ohio Union South";
                return "";
            case 11:
                //"12th Avenue";
                return "";
            case 12:
                // "SafeAuto Hospital";
                return "";
            case 13:
                // "South Cannon";
                return "";
            case 14:
                //  "Northwest";
                return "";
            case 15:
                // "9th Avenue West";
                return "";

        }
        return "";
    }
}
