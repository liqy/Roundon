package com.roundon.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by liqy on 15/12/17.
 */
public class PhotoUrl implements Parcelable {
    public String full;
    public String regular;
    public String small;
    public String thumb;
    public String custom;

    @Override
    public String toString() {
        return "PhotoUrl{" +
                "full='" + full + '\'' +
                ", regular='" + regular + '\'' +
                ", small='" + small + '\'' +
                ", thumb='" + thumb + '\'' +
                ", custom='" + custom + '\'' +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.full);
        dest.writeString(this.regular);
        dest.writeString(this.small);
        dest.writeString(this.thumb);
        dest.writeString(this.custom);
    }

    public PhotoUrl() {
    }

    protected PhotoUrl(Parcel in) {
        this.full = in.readString();
        this.regular = in.readString();
        this.small = in.readString();
        this.thumb = in.readString();
        this.custom = in.readString();
    }

    public static final Parcelable.Creator<PhotoUrl> CREATOR = new Parcelable.Creator<PhotoUrl>() {
        public PhotoUrl createFromParcel(Parcel source) {
            return new PhotoUrl(source);
        }

        public PhotoUrl[] newArray(int size) {
            return new PhotoUrl[size];
        }
    };
}
