package com.roundon.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by liqy on 15/12/19.
 */
public class Exif implements Parcelable {
    public String make;
    public String model;
    public String exposure_time;
    public String aperture;
    public String focal_length;
    public int iso;

    public Exif() {
    }

    @Override
    public String toString() {
        return "Exif{" +
                "make='" + make + '\'' +
                ", model='" + model + '\'' +
                ", exposure_time='" + exposure_time + '\'' +
                ", aperture='" + aperture + '\'' +
                ", focal_length='" + focal_length + '\'' +
                ", iso=" + iso +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.make);
        dest.writeString(this.model);
        dest.writeString(this.exposure_time);
        dest.writeString(this.aperture);
        dest.writeString(this.focal_length);
        dest.writeInt(this.iso);
    }

    protected Exif(Parcel in) {
        this.make = in.readString();
        this.model = in.readString();
        this.exposure_time = in.readString();
        this.aperture = in.readString();
        this.focal_length = in.readString();
        this.iso = in.readInt();
    }

    public static final Parcelable.Creator<Exif> CREATOR = new Parcelable.Creator<Exif>() {
        public Exif createFromParcel(Parcel source) {
            return new Exif(source);
        }

        public Exif[] newArray(int size) {
            return new Exif[size];
        }
    };
}
