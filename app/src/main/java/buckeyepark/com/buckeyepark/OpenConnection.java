package buckeyepark.com.buckeyepark;
import android.os.AsyncTask;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by Owner on 11/2/2014.
 */
public class OpenConnection {

    public ArrayList<Garages> getGarages() {
        Elements elements;
        ArrayList<Garages> garageList;
        org.jsoup.nodes.Document doc;
        String garageName = null;
        String percentage = null;
        Garages garages;
        String html ="http://osu.campusparc.com/";
        try {
            doc = Jsoup.connect(html).get();
            elements = doc.body().select("div#GarageAvailability");
            garageList = new ArrayList();
            elements.remove(0);
            for(Element element : elements) {
                garageName = element.select("td:nth-of-type(1) a").first().text();
                percentage = element.select("td:nth-of-type(3)").first().text();
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