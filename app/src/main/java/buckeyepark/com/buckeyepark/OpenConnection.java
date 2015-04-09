package buckeyepark.com.buckeyepark;
import android.os.AsyncTask;
import android.util.Log;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by Owner on 11/2/2014.
 */
public class OpenConnection {
    private final String tag = "tagged point";
    public ArrayList<Garages> getGarages() {

        Log.d(tag, "I am trying to open a connection");
        Elements elements;
        ArrayList<Garages> garageList;
        org.jsoup.nodes.Document doc;
        String garageName = null;
        String percentage = null;
        Garages garages;
        String html ="http://osu.campusparc.com";
        try {
            doc = Jsoup.connect(html).get();
            Log.d(tag, "I am tconnected");
            garageList = new ArrayList();
            Log.d(tag, "I am retrieved" );
            //not currently working need to rework css selectors
            for(Element table : doc.select("#GarageAvailability")) {
                Log.d(tag, "I was able to get elements from jsoup");
                garageName = table.select("tbody:nth-of-type(1) > tr > td.graphDataCellGarageNameAlt > a").first().text();
                percentage = table.select("tbody:nth-of-type(1) > tr > td.graphDataCellPercentFullAlt").first().text();
                garages = new Garages(garageName, percentage);
                garageList.add(garages);
            }
            return garageList;

        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void OpenConnection () { }


}