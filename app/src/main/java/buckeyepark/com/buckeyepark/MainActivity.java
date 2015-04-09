package buckeyepark.com.buckeyepark;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;

public class MainActivity extends ActionBarActivity implements OnClickListener {
    private final String tag = "tagged point";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d(tag, "onCreate for main screen");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        View btnFindGarages = findViewById(R.id.buttonFindGarages);
        btnFindGarages.setOnClickListener(this);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void onClick(View v) {
        Log.d(tag, "on click for main screen");
        switch (v.getId()) {
            case R.id.buttonFindGarages:
                startActivity(new Intent(this, FindGarages.class));
                break;
        }
    }
}
