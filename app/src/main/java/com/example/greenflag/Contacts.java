package com.example.greenflag;

import android.os.Parcel;
import android.os.Parcelable;

public class Contacts implements Parcelable {
    private String Name;
    private String UserName;
    private String BirthDate;
    private String Country;
    private String Gender;
    private String Address;
    private int Avatar;

    public static Creator<Contacts> getCREATOR() {
        return CREATOR;
    }

    public int getAvatar() {
        return Avatar;
    }

    public void setAvatar(int avatar) {
        Avatar = avatar;
    }

    public Contacts(){

    }

    protected Contacts(Parcel in) {
        Name = in.readString();
        UserName = in.readString();
        BirthDate = in.readString();
        Country = in.readString();
        Gender = in.readString();
        Address = in.readString();
        Avatar = in.readInt();
    }

    public static final Creator<Contacts> CREATOR = new Creator<Contacts>() {
        @Override
        public Contacts createFromParcel(Parcel in) {
            return new Contacts(in);
        }

        @Override
        public Contacts[] newArray(int size) {
            return new Contacts[size];
        }
    };

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getUserName() {
        return UserName;
    }

    public void setUserName(String userName) {
        UserName = userName;
    }

    public String getBirthDate() {
        return BirthDate;
    }

    public void setBirthDate(String birthDate) {
        BirthDate = birthDate;
    }

    public String getCountry() {
        return Country;
    }

    public void setCountry(String country) {
        Country = country;
    }

    public String getGender() {
        return Gender;
    }

    public void setGender(String gender) {
        Gender = gender;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        Address = address;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(Name);
        dest.writeString(UserName);
        dest.writeString(BirthDate);
        dest.writeString(Country);
        dest.writeString(Gender);
        dest.writeString(Address);
        dest.writeInt(Avatar);
    }
}
