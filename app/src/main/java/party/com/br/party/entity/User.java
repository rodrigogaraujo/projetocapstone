package party.com.br.party.entity;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

/**
 * Created by Isabelly on 02/05/2018.
 */

public class User implements Parcelable{

    private String id;
    private String name;
    private String picture;
    private String email;
    private String phone;
    private String adress;
    private String type;
    private String text;
    private List<String> interest;
    private boolean status;

    public User() {
    }

    public User(String id, String name, String picture, String email, String phone, String adress, String type, String text, List<String> interest, boolean status) {
        this.id = id;
        this.name = name;
        this.picture = picture;
        this.email = email;
        this.phone = phone;
        this.adress = adress;
        this.type = type;
        this.text = text;
        this.interest = interest;
        this.status = status;
    }


    protected User(Parcel in) {
        id = in.readString();
        name = in.readString();
        picture = in.readString();
        email = in.readString();
        phone = in.readString();
        adress = in.readString();
        type = in.readString();
        text = in.readString();
        interest = in.createStringArrayList();
        status = in.readByte() != 0;
    }

    public static final Creator<User> CREATOR = new Creator<User>() {
        @Override
        public User createFromParcel(Parcel in) {
            return new User(in);
        }

        @Override
        public User[] newArray(int size) {
            return new User[size];
        }
    };

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

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAdress() {
        return adress;
    }

    public void setAdress(String adress) {
        this.adress = adress;
    }

    public List<String> getInterest() {
        return interest;
    }

    public void setInterest(List<String> interest) {
        this.interest = interest;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "User{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", picture='" + picture + '\'' +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                ", adress='" + adress + '\'' +
                ", type='" + type + '\'' +
                ", text='" + text + '\'' +
                ", interest=" + interest +
                ", status=" + status +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(id);
        dest.writeString(name);
        dest.writeString(picture);
        dest.writeString(email);
        dest.writeString(phone);
        dest.writeString(adress);
        dest.writeString(type);
        dest.writeString(text);
        dest.writeStringList(interest);
        dest.writeByte((byte) (status ? 1 : 0));
    }
}
