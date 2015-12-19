package com.roundon.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by liqy on 15/12/19.
 */
public class Total implements Parcelable {
    public long photo_downloads;
    public long batch_downloads;


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(this.photo_downloads);
        dest.writeLong(this.batch_downloads);
    }

    public Total() {
    }

    protected Total(Parcel in) {
        this.photo_downloads = in.readLong();
        this.batch_downloads = in.readLong();
    }

    public static final Parcelable.Creator<Total> CREATOR = new Parcelable.Creator<Total>() {
        public Total createFromParcel(Parcel source) {
            return new Total(source);
        }

        public Total[] newArray(int size) {
            return new Total[size];
        }
    };
}
