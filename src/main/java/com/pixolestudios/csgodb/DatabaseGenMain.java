package main.java.com.pixolestudios.csgodb;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.List;

public class DatabaseGenMain {

    private static String ExistingSkindbpath = "../Tradeup-Buddy/data/skinsdb.csv";

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
        try {
            reader = new BufferedReader(new FileReader(ExistingSkindbpath));
            reader.readLine();
            String line = reader.readLine();
            while (line != null) {
                List<String> vals = Arrays.asList(line.split(","));
                // Create a new SkinDBItem using the values provided in the .csv
                line = reader.readLine();


                if(getItemURLContents(vals.get(0) + " (Factory New)").equals("{\"success\":\"false\"}")){
                    System.out.println(vals.get(0) + " (Factory New)");
                }
                if(getItemURLContents(vals.get(0) + " (Minimal Wear)").equals("{\"success\":\"false\"}")){
                    System.out.println(vals.get(0) + " (Minimal Wear)");
                }
                if(getItemURLContents(vals.get(0) + " (Field-Tested)").equals("{\"success\":\"false\"}")){
                    System.out.println(vals.get(0) + " (Field-Tested)");
                }
                if(getItemURLContents(vals.get(0) + " (Well-Worn)").equals("{\"success\":\"false\"}")){
                    System.out.println(vals.get(0) + " (Well Worn)");
                }
                if(getItemURLContents(vals.get(0) + " (Battle-Scarred)").equals("{\"success\":\"false\"}")){
                    System.out.println(vals.get(0) + " (Battle Scarred)");
                }
                System.out.println("=========");
            }
            reader.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static String getItemURLContents(String itemName){
        String url = null;
        try {
            url = URLEncoder.encode(itemName, StandardCharsets.UTF_8.toString());
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return Utils.URLContentstoString("http://csgobackpack.net/api/GetItemPrice/?id=" + url);
    }


}
