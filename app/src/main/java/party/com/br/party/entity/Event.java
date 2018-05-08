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
    private String idAdmin;
    private String name;
    private String description;
    private String type;
    private String picture;
    private String location;
    private String adress;
    private String contact;
    private String email;
    private Date date;
    private int hours;
    private List<LocaleTicket> localeTickets;
    private List<Day> days;
    private List<String> idPersonGo;

    public Event() {
    }

    public Event(String id, String idAdmin, String name, String description, String type, String picture, String location, String adress, String contact, String email, Date date, int hours, List<LocaleTicket> localeTickets, List<Day> days, List<String> idPersonGo) {
        this.id = id;
        this.idAdmin = idAdmin;
        this.name = name;
        this.description = description;
        this.type = type;
        this.picture = picture;
        this.location = location;
        this.adress = adress;
        this.contact = contact;
        this.email = email;
        this.date = date;
        this.hours = hours;
        this.localeTickets = localeTickets;
        this.days = days;
        this.idPersonGo = idPersonGo;
    }


    protected Event(Parcel in) {
        id = in.readString();
        idAdmin = in.readString();
        name = in.readString();
        description = in.readString();
        type = in.readString();
        picture = in.readString();
        location = in.readString();
        adress = in.readString();
        contact = in.readString();
        email = in.readString();
        date = new Date(in.readLong());
        hours = in.readInt();
        localeTickets = in.createTypedArrayList(LocaleTicket.CREATOR);
        days = in.createTypedArrayList(Day.CREATOR);
        idPersonGo = in.createStringArrayList();
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
    public String toString() {
        return "Event{" +
                "id='" + id + '\'' +
                ", idAdmin='" + idAdmin + '\'' +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", type='" + type + '\'' +
                ", picture='" + picture + '\'' +
                ", location='" + location + '\'' +
                ", adress='" + adress + '\'' +
                ", contact='" + contact + '\'' +
                ", email='" + email + '\'' +
                ", date=" + date +
                ", hours=" + hours +
                ", localeTickets=" + localeTickets +
                ", days=" + days +
                ", idPersonGo=" + idPersonGo +
                '}';
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getIdAdmin() {
        return idAdmin;
    }

    public void setIdAdmin(String idAdmin) {
        this.idAdmin = idAdmin;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getHours() {
        return hours;
    }

    public void setHours(int hours) {
        this.hours = hours;
    }

    public List<LocaleTicket> getLocaleTickets() {
        return localeTickets;
    }

    public void setLocaleTickets(List<LocaleTicket> localeTickets) {
        this.localeTickets = localeTickets;
    }

    public List<Day> getDays() {
        return days;
    }

    public void setDays(List<Day> days) {
        this.days = days;
    }

    public List<String> getIdPersonGo() {
        return idPersonGo;
    }

    public void setIdPersonGo(List<String> idPersonGo) {
        this.idPersonGo = idPersonGo;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(id);
        dest.writeString(idAdmin);
        dest.writeString(name);
        dest.writeString(description);
        dest.writeString(type);
        dest.writeString(picture);
        dest.writeString(location);
        dest.writeString(adress);
        dest.writeString(contact);
        dest.writeString(email);
        dest.writeLong(date.getTime());
        dest.writeInt(hours);
        dest.writeTypedList(localeTickets);
        dest.writeTypedList(days);
        dest.writeStringList(idPersonGo);
    }


}
