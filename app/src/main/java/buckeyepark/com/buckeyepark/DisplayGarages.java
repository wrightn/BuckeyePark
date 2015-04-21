package buckeyepark.com.buckeyepark;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.IntentSender;
import android.content.ServiceConnection;
import android.content.SharedPreferences;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.res.AssetManager;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.UserHandle;
import android.support.annotation.Nullable;
import android.util.ArrayMap;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.webkit.JavascriptInterface;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.ProgressBar;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

/**
 * Created by Owner on 4/16/2015.
 */
public class DisplayGarages extends Activity {
    private ProgressBar mActivityIndicator;
    ListView listview;
    ProgressDialog mProgressDialog;
    private final String tag = "tagged point";
    ArrayList<String> percentList = new ArrayList();
    ArrayList<String> nameList = new ArrayList();
    int counter = 1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        listview = (ListView)findViewById(R.id.display_garages);
        mActivityIndicator = (ProgressBar) findViewById(R.id.address_progress);
        mProgressDialog = new ProgressDialog(DisplayGarages.this);
        mProgressDialog.setTitle("Finding Garages");
        mProgressDialog.setMessage("Loading...");
        mProgressDialog.setIndeterminate(false);
        mProgressDialog.show();
        /* An instance of this class will be registered as a JavaScript interface */
        class MyJavaScriptInterface {
            @JavascriptInterface
            public void addPercent(String percent) {
                Log.d(tag, "I am adding garage percents to the list");
                percentList.add(percent);
                counter++;
                if(counter>16)
                {
                    timeToEnd();
                }
            }
            @JavascriptInterface
            public void addName(String name) {
                Log.d(tag, "I am adding garage name to the list");
                String[] vars = name.split("[<>]");
                nameList.add(vars[2]);
            }

            public void timeToEnd()
            {
                runOnUiThread(new Runnable() {

                    public void run() {
                        displayStationList(percentList, nameList);
                    }
                });

            }
        }

        WebView webview = new WebView(this);
        webview.getSettings().setJavaScriptEnabled(true);
        /* Register a new JavaScript interface called HTMLOUT */
        webview.addJavascriptInterface(new MyJavaScriptInterface(), "HTMLOUT");
        webview.removeAllViews();
        webview.setWebViewClient(mClient);
        webview.loadUrl("http://osu.campusparc.com/");
    }

    private WebViewClient mClient = new WebViewClient(){
        @Override
        public void onPageFinished(WebView view, String url) {
            view.loadUrl("javascript:window.HTMLOUT.addName( document.getElementsByClassName('graphDataCellGarageNameAlt')[0].innerHTML );");
            view.loadUrl("javascript:window.HTMLOUT.addPercent( document.getElementsByClassName('graphDataCellPercentFullAlt')[0].innerHTML );");

            view.loadUrl("javascript:window.HTMLOUT.addName( document.getElementsByClassName('graphDataCellGarageName')[0].innerHTML );");
            view.loadUrl("javascript:window.HTMLOUT.addPercent( document.getElementsByClassName('graphDataCellPercentFull')[0].innerHTML );");

            view.loadUrl("javascript:window.HTMLOUT.addName( document.getElementsByClassName('graphDataCellGarageNameAlt')[1].innerHTML );");
            view.loadUrl("javascript:window.HTMLOUT.addPercent( document.getElementsByClassName('graphDataCellPercentFullAlt')[1].innerHTML );");

            view.loadUrl("javascript:window.HTMLOUT.addName( document.getElementsByClassName('graphDataCellGarageName')[1].innerHTML );");
            view.loadUrl("javascript:window.HTMLOUT.addPercent( document.getElementsByClassName('graphDataCellPercentFull')[1].innerHTML );");

            view.loadUrl("javascript:window.HTMLOUT.addName( document.getElementsByClassName('graphDataCellGarageNameAlt')[2].innerHTML );");
            view.loadUrl("javascript:window.HTMLOUT.addPercent( document.getElementsByClassName('graphDataCellPercentFullAlt')[2].innerHTML );");

            view.loadUrl("javascript:window.HTMLOUT.addName( document.getElementsByClassName('graphDataCellGarageName')[2].innerHTML );");
            view.loadUrl("javascript:window.HTMLOUT.addPercent( document.getElementsByClassName('graphDataCellPercentFull')[2].innerHTML );");

            view.loadUrl("javascript:window.HTMLOUT.addName( document.getElementsByClassName('graphDataCellGarageNameAlt')[3].innerHTML );");
            view.loadUrl("javascript:window.HTMLOUT.addPercent( document.getElementsByClassName('graphDataCellPercentFullAlt')[3].innerHTML );");

            view.loadUrl("javascript:window.HTMLOUT.addName( document.getElementsByClassName('graphDataCellGarageName')[3].innerHTML );");
            view.loadUrl("javascript:window.HTMLOUT.addPercent( document.getElementsByClassName('graphDataCellPercentFull')[3].innerHTML );");

            view.loadUrl("javascript:window.HTMLOUT.addName( document.getElementsByClassName('graphDataCellGarageNameAlt')[4].innerHTML );");
            view.loadUrl("javascript:window.HTMLOUT.addPercent( document.getElementsByClassName('graphDataCellPercentFullAlt')[4].innerHTML );");

            view.loadUrl("javascript:window.HTMLOUT.addName( document.getElementsByClassName('graphDataCellGarageName')[4].innerHTML );");
            view.loadUrl("javascript:window.HTMLOUT.addPercent( document.getElementsByClassName('graphDataCellPercentFull')[4].innerHTML );");

            view.loadUrl("javascript:window.HTMLOUT.addName( document.getElementsByClassName('graphDataCellGarageNameAlt')[5].innerHTML );");
            view.loadUrl("javascript:window.HTMLOUT.addPercent( document.getElementsByClassName('graphDataCellPercentFullAlt')[5].innerHTML );");

            view.loadUrl("javascript:window.HTMLOUT.addName( document.getElementsByClassName('graphDataCellGarageName')[5].innerHTML );");
            view.loadUrl("javascript:window.HTMLOUT.addPercent( document.getElementsByClassName('graphDataCellPercentFull')[5].innerHTML );");

            view.loadUrl("javascript:window.HTMLOUT.addName( document.getElementsByClassName('graphDataCellGarageNameAlt')[6].innerHTML );");
            view.loadUrl("javascript:window.HTMLOUT.addPercent( document.getElementsByClassName('graphDataCellPercentFullAlt')[6].innerHTML );");

            view.loadUrl("javascript:window.HTMLOUT.addName( document.getElementsByClassName('graphDataCellGarageName')[6].innerHTML );");
            view.loadUrl("javascript:window.HTMLOUT.addPercent( document.getElementsByClassName('graphDataCellPercentFull')[6].innerHTML );");

            view.loadUrl("javascript:window.HTMLOUT.addName( document.getElementsByClassName('graphDataCellGarageNameAlt')[7].innerHTML );");
            view.loadUrl("javascript:window.HTMLOUT.addPercent( document.getElementsByClassName('graphDataCellPercentFullAlt')[7].innerHTML );");

            view.loadUrl("javascript:window.HTMLOUT.addName( document.getElementsByClassName('graphDataCellGarageName')[7].innerHTML );");
            view.loadUrl("javascript:window.HTMLOUT.addPercent( document.getElementsByClassName('graphDataCellPercentFull')[7].innerHTML );");
        }

        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            view.loadUrl(url);
            return true;
        }
    };

    public void displayStationList(ArrayList<String> percentList, ArrayList<String> nameList)
    {
       // mActivityIndicator.setVisibility(View.GONE);
       // mProgressDialog.dismiss();

        Intent i = new Intent(this, DisplayGarageList.class);
        i.putExtra("percent", percentList);
        i.putExtra("name", nameList);
        startActivity(i);
    }
}
