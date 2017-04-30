package com.login.sanidhya.announcements;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import Announcement.AnnouncementAdapter;
import Wrapper.AnnouncementWrapper;

public class MainPage extends AppCompatActivity {


    private Context context;
    ListView myListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_view);

        context = this;

        myListView = (ListView)findViewById(R.id.listView1);
        new CustomAsyncTask().execute("http://demo5392516.mockable.io/faculty_json");

    }

    public void showJsonInListView(String raw_json){
        ArrayList<AnnouncementWrapper> AnnouncementWrapperArrayList = Utils.pasreLocalFacultyJson(raw_json);

        AnnouncementAdapter adapter = new AnnouncementAdapter(context, AnnouncementWrapperArrayList);
        myListView.setAdapter(adapter);
    }

    // Params, Progress, Result
    class CustomAsyncTask extends AsyncTask<String, Void, String> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected String doInBackground(String... params) {
            String response = readJsonFromInternet("http://demo2700756.mockable.io/annon");
            return response;
        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);

            showJsonInListView(result);
        }

    }

    public String readJsonFromInternet(String url) {
        String response = "";
        try {
            InputStream is = Utils.openHttpConnection(url);
            response = Utils.convertStreamToString(is);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return response;
    }
}

