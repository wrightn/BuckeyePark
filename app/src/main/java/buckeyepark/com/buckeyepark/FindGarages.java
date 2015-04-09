package buckeyepark.com.buckeyepark;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.location.Location;
import android.os.AsyncTask;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.ProgressBar;

import java.util.ArrayList;

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
    private Location mLocation = new Location("Find Garages:");
    ProgressDialog mProgressDialog;
    SharedPreferences pref;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.find_garage);
        pref = PreferenceManager.getDefaultSharedPreferences(this);
        Log.d(tag, "create views");
        listview = (ListView)findViewById(R.id.findgaragelist);
        mActivityIndicator = (ProgressBar) findViewById(R.id.address_progress);
        //set bogus location, will be updated later with correct location
        mLocation.setLatitude(40);
        mLocation.setLongitude(-83);
        //ensure location services are turned on
        if (Build.VERSION.SDK_INT >=
                Build.VERSION_CODES.GINGERBREAD &&Geocoder.isPresent()) {

            (new DoOnlineStuffTask(this)).execute(mLocation);
        }
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
                Log.d(tag, "I will try to display the results now");
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
            Log.d(tag, "I am adding garages to the list");
            prettyList.add("Garage Name: " + garage.getName() + " Percentage Full: " + garage.getPercentage());
        }
        Log.d(tag, "If there were garages to add I would have done so by now");
        ArrayAdapter<String> adapter;
        adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, prettyList);
        listview.setAdapter(adapter);
    }

}
