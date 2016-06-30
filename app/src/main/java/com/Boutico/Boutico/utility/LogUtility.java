package com.Boutico.Boutico.utility;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;

public class LogUtility {
    private static final String logEnteredRegionUrl = "http://www.boutico.ca/database/enteredRegionLog.php";
    private LogUtility(){

    }
    public static void logEnteredRegion(final String user_id, final String beacon_id){
        Thread thread = new Thread() {
            @Override
            public void run() {
                try {
                    //Setup URL
                    URL url = new URL(logEnteredRegionUrl);
                    URLConnection conn = url.openConnection();
                    conn.setDoOutput(true);
                    //Setup data
                    String data = URLEncoder.encode("user_id", "UTF-8")
                            + "=" + URLEncoder.encode(user_id, "UTF-8");

                    data += "&" + URLEncoder.encode("beacon_id", "UTF-8") + "="
                            + URLEncoder.encode(beacon_id, "UTF-8");

                    String text = "";
                    BufferedReader reader=null;
                    //Post to server
                    OutputStreamWriter wr = new OutputStreamWriter(conn.getOutputStream());
                    wr.write( data );
                    wr.flush();
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        };
        thread.start();
    }

}
