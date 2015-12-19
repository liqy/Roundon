package com.roundon.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by liqy on 15/12/17.
 */
public class Location implements Parcelable {

    public String address;

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.address);
    }

    public Location() {
    }

    protected Location(Parcel in) {
        this.address = in.readString();
    }

    public static final Parcelable.Creator<Location> CREATOR = new Parcelable.Creator<Location>() {
        public Location createFromParcel(Parcel source) {
            return new Location(source);
        }

        public Location[] newArray(int size) {
            return new Location[size];
        }
    };
}
