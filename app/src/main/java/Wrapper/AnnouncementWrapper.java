package Wrapper;

import org.json.JSONObject;


//In general, a wrapper class is any class which "wraps" or "encapsulates" the functionality of another class
//or component. These are useful by providing a level of abstraction from the
//implementation of the underlying class or component
//http://www.javawithus.com/tutorial/wrapper-classes
public class AnnouncementWrapper {

    private String announcement = "";
    private String source= "";
    private String date = "";
    private String audience = "";


    public AnnouncementWrapper() {
        // TODO Auto-generated constructor stub
    }

    public AnnouncementWrapper(JSONObject jObj) {
        try {
            //Paste your code here for constructor.

            System.out.println(jObj.toString());

            announcement = jObj.getString("Announcement");
            source = jObj.getString("Source");
            audience = jObj.getString("Concerned_Audience");
            date = jObj.getString("Date");

        } catch (Exception e) {
            // TODO: handle exception
        }
    }


    public String getAnnouncement() {
        return announcement;
    }

    public String getSource() {
        return source;
    }

    public String getDate() {
        return date;
    }

    public String getAudience() {
        return audience;
    }

    public void setAnnouncement(String announcement) {
        this.announcement = announcement;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setAudience(String audience) {
        this.audience = audience;
    }
}
