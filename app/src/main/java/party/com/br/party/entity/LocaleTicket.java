package party.com.br.party.entity;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Isabelly on 25/04/2018.
 */

public class LocaleTicket implements Parcelable {
    private String id;
    private String adress;
    private String phone;
    private String email;

    public LocaleTicket() {
    }

    public LocaleTicket(String id, String adress, String phone, String email) {
        this.id = id;
        this.adress = adress;
        this.phone = phone;
        this.email = email;
    }

    protected LocaleTicket(Parcel in) {
        id = in.readString();
        adress = in.readString();
        phone = in.readString();
        email = in.readString();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAdress() {
        return adress;
    }

    public void setAdress(String adress) {
        this.adress = adress;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public static final Creator<LocaleTicket> CREATOR = new Creator<LocaleTicket>() {
        @Override
        public LocaleTicket createFromParcel(Parcel in) {
            return new LocaleTicket(in);
        }

        @Override
        public LocaleTicket[] newArray(int size) {
            return new LocaleTicket[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(id);
        dest.writeString(adress);
        dest.writeString(phone);
        dest.writeString(email);
    }
}
