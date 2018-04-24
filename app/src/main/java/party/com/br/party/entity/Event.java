package party.com.br.party.entity;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.Date;
import java.util.List;

/**
 * Created by g3infotech on 22/03/18.
 */

public class Event implements Parcelable {

    private String id;
    private String name;
    private String description;
    private String type;
    private String picture;
    private String location;
    private String adress;
    private String contact;
    private List<String> localeTickets;
    private List<Day> days;

    public Event() {
    }

    public Event(String id, String name, String description, String type, String picture, String location, String adress, String contact, List<String> localeTickets, List<Day> days) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.type = type;
        this.picture = picture;
        this.location = location;
        this.adress = adress;
        this.contact = contact;
        this.localeTickets = localeTickets;
        this.days = days;
    }

    protected Event(Parcel in) {
        id = in.readString();
        name = in.readString();
        description = in.readString();
        type = in.readString();
        picture = in.readString();
        location = in.readString();
        adress = in.readString();
        contact = in.readString();
        localeTickets = in.createStringArrayList();
    }

    public static final Creator<Event> CREATOR = new Creator<Event>() {
        @Override
        public Event createFromParcel(Parcel in) {
            return new Event(in);
        }

        @Override
        public Event[] newArray(int size) {
            return new Event[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getAdress() {
        return adress;
    }

    public void setAdress(String adress) {
        this.adress = adress;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public List<String> getLocaleTickets() {
        return localeTickets;
    }

    public void setLocaleTickets(List<String> localeTickets) {
        this.localeTickets = localeTickets;
    }

    public List<Day> getDays() {
        return days;
    }

    public void setDays(List<Day> days) {
        this.days = days;
    }

    public static Creator<Event> getCREATOR() {
        return CREATOR;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(id);
        dest.writeString(name);
        dest.writeString(description);
        dest.writeString(type);
        dest.writeString(picture);
        dest.writeString(location);
        dest.writeString(adress);
        dest.writeString(contact);
        dest.writeStringList(localeTickets);
    }
}
