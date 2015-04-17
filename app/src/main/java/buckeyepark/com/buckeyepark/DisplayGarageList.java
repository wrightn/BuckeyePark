package buckeyepark.com.buckeyepark;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

/**
 * Created by Owner on 4/16/2015.
 */
public class DisplayGarageList extends Activity {
    ListView listview;
    private final String tag = "tagged point";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.display_garage_list);
        Bundle b = getIntent().getExtras();
        ArrayList<String> garageList = b.getStringArrayList("id");
        // Display the results of the lookup.
        ArrayList<String> prettyList = new ArrayList();
        int counter = 1;
        for(String percent : garageList)
        {
            prettyList.add(getGarageName(counter) + ": " + percent + " full");
            counter++;
        }
        Log.d(tag, "If there were garages to add I would have done so by now");
        if(prettyList !=null)
        {
            ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                    android.R.layout.simple_list_item_1, prettyList);
            listview = (ListView) findViewById(R.id.display_garage_list);
            listview.setAdapter(adapter);
        }

    }
    public String getGarageName(int counter){
        switch (counter) {
            case 1:
                return "West Lane Avenue";
            case 2:
                return "Arps Hall";
            case 3:
                return "Tuttle Park Place";
            case 4:
                return "9th Avenue East";
            case 5:
                return "Neil Avenue";
            case 6:
                return "11th Avenue";
            case 7:
                return "South Gateway";
            case 8:
                return "Lane Avenue";
            case 9:
                return "Ohio Union North";
            case 10:
                return "North Cannon";
            case 11:
                return "Ohio Union South";
            case 12:
                return "12th Avenue";
            case 13:
                return "SafeAuto Hospital";
            case 14:
                return "South Cannon";
            case 15:
                return "Northwest";
            case 16:
                return "9th Avenue West";

        }
        return "";
    }
    public DisplayGarageList() {
    }
}
