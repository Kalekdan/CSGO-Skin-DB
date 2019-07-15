package main.java.com.pixolestudios.csgodb;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

public class Utils {
    /**
     * Reads the content of passed and returns the file content as a string
     * Returns null if exception
     *
     * @param url the url to read from
     * @return the contents of the url
     */
    public static String URLContentstoString(String url) {
        URL urlToRead = null;
        try {
            urlToRead = new URL(url);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        BufferedReader in;
        try {
            URLConnection openConnection = urlToRead.openConnection();
            openConnection.addRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64; rv:25.0) Gecko/20100101 Firefox/25.0");
            in = new BufferedReader(new InputStreamReader(openConnection.getInputStream()));
            String fileString = "";
            String inputLine;
            while ((inputLine = in.readLine()) != null)
                fileString += inputLine;
            in.close();
            return fileString;
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("No data found for that URL");
            return null;
        }
    }
}
