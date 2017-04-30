package Announcement;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.androidquery.AQuery;
import com.login.sanidhya.announcements.R;

import java.util.ArrayList;

import Wrapper.AnnouncementWrapper;

/**
 * Created by student on 29-04-2016.
 */
public class AnnouncementAdapter extends BaseAdapter {
    ViewHolder holder;

    @Override
    public int getCount() {
        return mFacultyDataList.size();
    }

    @Override
    public Object getItem(int position) {
        return mFacultyDataList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }



    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.activity_card_view, null);

            //init the View Holder object,when a new view is created for the first time.
            holder =  new ViewHolder(convertView);

            //Save this holder using the view tags
            convertView.setTag(holder);
        }else {
            //we need to retrieve the view holder object from the saved tags
            //this is called when the view is taken from the recycled views
            //this save lot of processing time for findViewByid
            holder =  (ViewHolder) convertView.getTag();
        }

        AnnouncementWrapper obj = mFacultyDataList.get(position);


        //Now you have to take view reference object from the view holder object. Which hold all the reference for you.
        holder.aTv.setText(obj.getAnnouncement());
        holder.sTv.setText("From: " + obj.getSource());
        holder.tTv.setText("For: " + obj.getAudience());
        holder.dTv.setText("On: "+obj.getDate());


        return convertView;
    }


    public static class ViewHolder {
        TextView aTv;
        TextView sTv;
        TextView tTv;
        TextView dTv;

        public ViewHolder(View convertView) {

            aTv = (TextView) convertView.findViewById(R.id.annonTv);
            sTv = (TextView) convertView.findViewById(R.id.sourceTv);
            tTv = (TextView) convertView.findViewById(R.id.toTv);
            dTv = (TextView) convertView.findViewById(R.id.dateTv);
        }
    }


    Context context;
    ArrayList<AnnouncementWrapper> mFacultyDataList;
    LayoutInflater inflater;
    AQuery aq;

    public AnnouncementAdapter(Context context, ArrayList<AnnouncementWrapper> mFacultyDataList) {
        this.context = context;
        this.mFacultyDataList = mFacultyDataList;

        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        aq = new AQuery(context);
    }


}
