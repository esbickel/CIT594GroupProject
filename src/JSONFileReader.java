import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

public class JSONFileReader {

    public ArrayList<JSONObject> jsonFile() {

        ArrayList<JSONObject> parkingViolations = new ArrayList<JSONObject>();
        JSONParser parser = new JSONParser();
        try {
            FileReader fileReader = new FileReader(("parking.json"));
            JSONArray parkViolations = (JSONArray) parser.parse(fileReader);
            Iterator iter = parkViolations.iterator();

            while (iter.hasNext()) {
                // Get the next json object
                JSONObject park = (JSONObject) iter.next();
                parkingViolations.add(park);

            }
        } catch (ParseException e) {
            e.printStackTrace();

        } catch (
                IOException ex) {
            ex.printStackTrace();
        }
        return (parkingViolations);

    }


    public static void main(String[] args) {

        JSONFileReader test = new JSONFileReader();
        System.out.println(test.jsonFile().get(1));

    }

}





