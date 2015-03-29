package buckeyepark.com.buckeyepark;

import android.app.Activity;
import android.app.ListActivity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationManager;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Toast;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/**
 * Created by Emily on 10/18/2014.
 */
public class FindGarages extends Activity {
    private ProgressBar mActivityIndicator;
    private final String tag = "tagged point";
    ListView listview;
    ArrayList<Garages> garages;
    private Location mLocation = new Location("Something");
    ProgressDialog mProgressDialog;
    SharedPreferences pref;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.find_garage);
        pref = PreferenceManager.getDefaultSharedPreferences(this);

        listview = (ListView)findViewById(R.id.findgaragelist);
        mActivityIndicator = (ProgressBar) findViewById(R.id.address_progress);
    }

    private class DoOnlineStuffTask extends AsyncTask<Location, Void, ArrayList<Garages>> {
        Context mContext;

        public DoOnlineStuffTask(Context context) {
            super();
            mContext = context;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            mProgressDialog = new ProgressDialog(FindGarages.this);
            mProgressDialog.setTitle("Finding Garages");
            mProgressDialog.setMessage("Loading...");
            mProgressDialog.setIndeterminate(false);
            mProgressDialog.show();
        }

        @Override
        protected ArrayList<Garages> doInBackground(Location... params) {
            return new OpenConnection().getGarages();
        }

        @Override
        protected void onPostExecute(ArrayList<Garages> garageList) {
            // Set activity indicator visibility to "gone"
            mActivityIndicator.setVisibility(View.GONE);
            mProgressDialog.dismiss();
            // Display the results of the lookup.

            try {
                garages = garageList;
                displayStationList(garages);
            } catch (NumberFormatException nfe) {
                System.out.println("Could not parse " + nfe);
            }

        }

    }
    public void displayStationList(ArrayList<Garages> garageList)
    {
        ArrayList<String> prettyList = new ArrayList();
        for(Garages garage : garageList)
        {
            prettyList.add("Garage Name: " + garage.getName() + " Percentage Full: " + garage.getPercentage());
        }
        ArrayAdapter<String> adapter;
        adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, prettyList);
        listview.setAdapter(adapter);
    }

}