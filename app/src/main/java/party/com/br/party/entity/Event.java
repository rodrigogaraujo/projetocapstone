package party.com.br.party.entity;

import java.util.Date;
import java.util.List;

/**
 * Created by g3infotech on 22/03/18.
 */

public class Event {

    private String id;
    private String name;
    private String description;
    private String type;
    private String picture;
    private String location;
    private String adress;
    private String schedule;
    private String contact;
    private Date date;
    private List<String> localeTickets;

    public Event() {
    }

    public Event(String id, String name, String description, String type, String picture, String location, String adress,
                 String schedule, String contact, Date date, List<String> localeTickets) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.type = type;
        this.picture = picture;
        this.location = location;
        this.adress = adress;
        this.schedule = schedule;
        this.contact = contact;
        this.date = date;
        this.localeTickets = localeTickets;
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

    public String getSchedule() {
        return schedule;
    }

    public void setSchedule(String schedule) {
        this.schedule = schedule;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public List<String> getLocaleTickets() {
        return localeTickets;
    }

    public void setLocaleTickets(List<String> localeTickets) {
        this.localeTickets = localeTickets;
    }
}
