package party.com.br.party.entity;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.Date;

/**
 * Created by Isabelly on 24/04/2018.
 */

public class Day implements Parcelable {

    private String id;
    private double valueBasic;
    private double valueVip;
    private double valueTop;
    private int day;
    private Date date;
    private String picture;
    private String description;
    private String singer;

    public Day() {
    }

    public Day(String id, double valueBasic, double valueVip, double valueTop, int day, Date date, String picture, String description, String singer) {
        this.id = id;
        this.valueBasic = valueBasic;
        this.valueVip = valueVip;
        this.valueTop = valueTop;
        this.day = day;
        this.date = date;
        this.picture = picture;
        this.description = description;
        this.singer = singer;
    }

    protected Day(Parcel in) {
        id = in.readString();
        valueBasic = in.readDouble();
        valueVip = in.readDouble();
        valueTop = in.readDouble();
        day = in.readInt();
        picture = in.readString();
        description = in.readString();
        singer = in.readString();
    }

    public static final Creator<Day> CREATOR = new Creator<Day>() {
        @Override
        public Day createFromParcel(Parcel in) {
            return new Day(in);
        }

        @Override
        public Day[] newArray(int size) {
            return new Day[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(id);
        dest.writeDouble(valueBasic);
        dest.writeDouble(valueVip);
        dest.writeDouble(valueTop);
        dest.writeInt(day);
        dest.writeString(picture);
        dest.writeString(description);
        dest.writeString(singer);
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public double getValueBasic() {
        return valueBasic;
    }

    public void setValueBasic(double valueBasic) {
        this.valueBasic = valueBasic;
    }

    public double getValueVip() {
        return valueVip;
    }

    public void setValueVip(double valueVip) {
        this.valueVip = valueVip;
    }

    public double getValueTop() {
        return valueTop;
    }

    public void setValueTop(double valueTop) {
        this.valueTop = valueTop;
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getSinger() {
        return singer;
    }

    public void setSinger(String singer) {
        this.singer = singer;
    }
}
