package com.login.sanidhya.announcements;

import android.content.Context;
import android.content.res.Resources;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;

import Wrapper.AnnouncementWrapper;

/**
 * Created by Saurabh on 3/28/2016.
 */
public class Utils {

    private final static String TAG = Utils.class.getSimpleName();

    /**
     * Method to convert the Input stream to string.
     *
     * @param is
     * @return
     * @throws IOException
     */
    public static String convertStreamToString(InputStream is) throws IOException {
        // Converting input stream into string
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        int i = is.read();
        while (i != -1) {
            baos.write(i);
            i = is.read();
        }
        return baos.toString();
    }


    /**
     * Method to read the json file from RAW folder.
     *
     * @param context
     * @param resourceId
     * @return
     * @throws IOException
     */
    public static String getStringFromRaw(Context context, int resourceId)
            throws IOException {
        // Reading File from resource folder
        Resources r = context.getResources();
        InputStream is = r.openRawResource(resourceId);
        String statesText = convertStreamToString(is);
        is.close();

        Log.d(TAG, statesText);

        return statesText;
    }

    public static ArrayList<AnnouncementWrapper> pasreLocalFacultyJson(String json_string) {


        ArrayList<AnnouncementWrapper> mFacultyDataList = new ArrayList<AnnouncementWrapper>();
        try {
            //Converting multiple json data (String) into Json array
            //Every faculty is represented as a json object in the file.
            //so we need to store them in json array.
            JSONArray facultyArray = new JSONArray(json_string);
            Log.d(TAG, facultyArray.toString());
            // Iterating json array into json objects
            for (int i = 0; facultyArray.length() > i; i++) {

                // Extracting json object from particular index of array
                JSONObject facultyJsonObject = facultyArray.getJSONObject(i);

                // Design patterns
                AnnouncementWrapper facultyObject = new AnnouncementWrapper(facultyJsonObject);

                printObject(facultyObject);

                mFacultyDataList.add(facultyObject);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return mFacultyDataList;
    }

    public static void printObject(AnnouncementWrapper obj) {
        // Operator Overloading
        Log.d(TAG, "Announcements : " + obj.getAnnouncement());
        Log.d(TAG, "Source : " + obj.getSource());
        Log.d(TAG, "Audience : " + obj.getAudience());
        Log.d(TAG, "Date : " + obj.getDate());
    }


    public static InputStream openHttpConnection(String urlStr) {
        InputStream in = null;
        int resCode = -1;

        try {
            URL url = new URL(urlStr);
            URLConnection urlConn = url.openConnection();

            if (!(urlConn instanceof HttpURLConnection)) {
                throw new IOException("URL is not an Http URL");
            }

            HttpURLConnection httpConn = (HttpURLConnection) urlConn;
            httpConn.setAllowUserInteraction(false);
            httpConn.setInstanceFollowRedirects(true);
            httpConn.setRequestMethod("GET");
            httpConn.connect();

            resCode = httpConn.getResponseCode();
            if (resCode == HttpURLConnection.HTTP_OK) {
                in = httpConn.getInputStream();
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return in;
    }
}
