package buckeyepark.com.buckeyepark;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Owner on 4/16/2015.
 */
public class DisplayGarageList extends Activity {
    ArrayList<String> percentList;
    ArrayList<String> nameList;
    ListView listview;
    private final String tag = "tagged point";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.display_garage_list);
        Bundle b = getIntent().getExtras();
        percentList = b.getStringArrayList("percent");
        nameList = b.getStringArrayList("name");
        // Display the results of the lookup.
        ArrayList<String> prettyList = new ArrayList();
        int counter = 0;
        for(String percent : percentList)
        {
            prettyList.add(nameList.get(counter) + ": " + percent + " full");
            counter++;
        }
        Log.d(tag, "If there were garages to add I would have done so by now");
        if(prettyList !=null)
        {
            ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                    android.R.layout.simple_list_item_1, prettyList);
            listview = (ListView) findViewById(R.id.display_garage_list);
            listview.setOnItemClickListener(new ListClickHandler());
            listview.setAdapter(adapter);
        }

    }

    public class ListClickHandler implements AdapterView.OnItemClickListener {

        @Override
        public void onItemClick(AdapterView<?> adapter, View view, int position, long arg3) {
            // create intent to start another activity
            Intent intent = new Intent(DisplayGarageList.this, GarageWebView.class);
            /* add the selected text item to our intent. */
            intent.putExtra("garage-name", nameList.get(position));
            startActivity(intent);
        }

    }

    public DisplayGarageList() {
    }
}
