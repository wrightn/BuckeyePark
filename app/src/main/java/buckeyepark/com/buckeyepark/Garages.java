package buckeyepark.com.buckeyepark;

public class Garages {

    private String mGarageName;
    private String mPercentageFull;

    public Garages () {}

    public Garages (String name, String percentage){
        mGarageName = name;
        mPercentageFull = percentage;
    }
    public String getName() {
        return this.mGarageName;
    }
    public String getPercentage() {
        return this.mPercentageFull;
    }
}