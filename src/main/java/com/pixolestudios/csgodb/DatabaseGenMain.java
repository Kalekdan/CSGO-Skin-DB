package main.java.com.pixolestudios.csgodb;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.List;

public class DatabaseGenMain {

    private static String ExistingSkindbpath = "data/skinsdb.csv";
    private static String UpdatedSkindbpath = "data/skinsdb_updated.csv";

    // holds contents of each web page
    private static String fnURLString;
    private static String mwURLString;
    private static String ftURLString;
    private static String wwURLString;
    private static String bsURLString;

    private static String fnOutStr;
    private static String mwOutStr;
    private static String ftOutStr;
    private static String wwOutStr;
    private static String bsOutStr;

    private DatabaseGenMain() {
    }

    public static void main(String[] args) {
        readExistingDB();
    }

    /**
     * Loads all the skins and their data from skinsdb.csv into array
     */
    private static void readExistingDB() {

        BufferedReader reader;
        try (FileWriter fw = new FileWriter(UpdatedSkindbpath, false); BufferedWriter bw = new BufferedWriter(fw); PrintWriter out = new PrintWriter(bw)) {
            reader = new BufferedReader(new FileReader(ExistingSkindbpath));
            out.println(reader.readLine());
            String line = reader.readLine();
            while (line != null) {
                List<String> vals = Arrays.asList(line.split(","));
                // Create a new SkinDBItem using the values provided in the .csv
                line = reader.readLine();


                fnURLString = getItemURLContents(vals.get(0) + " (Factory New)");
                mwURLString = getItemURLContents(vals.get(0) + " (Minimal Wear)");
                ftURLString = getItemURLContents(vals.get(0) + " (Field-Tested)");
                wwURLString = getItemURLContents(vals.get(0) + " (Well-Worn)");
                bsURLString = getItemURLContents(vals.get(0) + " (Battle-Scarred)");

                if (fnURLString.equals("{\"success\":\"false\"}")) {
                    fnOutStr = "0.0";
                } else {
                    JSONObject jo = new JSONObject(fnURLString);
                    fnOutStr = (String) jo.get("average_price");
                }
                if (mwURLString.equals("{\"success\":\"false\"}")) {
                    mwOutStr = "0.0";
                } else {
                    JSONObject jo = new JSONObject(mwURLString);
                    mwOutStr = (String) jo.get("average_price");
                }
                if (ftURLString.equals("{\"success\":\"false\"}")) {
                    ftOutStr = "0.0";
                } else {
                    JSONObject jo = new JSONObject(ftURLString);
                    ftOutStr = (String) jo.get("average_price");
                }
                if (wwURLString.equals("{\"success\":\"false\"}")) {
                    wwOutStr = "0.0";
                } else {
                    JSONObject jo = new JSONObject(wwURLString);
                    wwOutStr = (String) jo.get("average_price");
                }
                if (bsURLString.equals("{\"success\":\"false\"}")) {
                    bsOutStr = "0.0";
                } else {
                    JSONObject jo = new JSONObject(bsURLString);
                    bsOutStr = (String) jo.get("average_price");
                }
                System.out.println("=========");

                out.println(vals.get(0) + "," + vals.get(1) + "," + vals.get(2) + "," + vals.get(3) + "," + vals.get(4) + "," + fnOutStr + "," + mwOutStr + "," + ftOutStr + "," + wwOutStr + "," + bsOutStr + ",0,0,0,0,0");
                out.close();
            }
            reader.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private static String getItemURLContents(String itemName) {
        String url = null;
        try {
            url = URLEncoder.encode(itemName, StandardCharsets.UTF_8.toString());
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return Utils.URLContentstoString("http://csgobackpack.net/api/GetItemPrice/?id=" + url);
    }


}
