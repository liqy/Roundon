package com.roundon.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.activeandroid.Model;

/**
 * Created by liqy on 15/12/17.
 */
public class Batch extends Model implements Parcelable {
    public long id;
    public String published_at;
    public long downloads;
    public User curator;

    public User getCurator() {
        return curator;
    }

    @Override
    public String toString() {
        return "Batch{" +
                "id=" + id +
                ", published_at='" + published_at + '\'' +
                ", downloads=" + downloads +
                ", curator=" + curator +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(this.id);
        dest.writeString(this.published_at);
        dest.writeLong(this.downloads);
        dest.writeParcelable(this.curator, flags);
    }

    public Batch() {
    }

    protected Batch(Parcel in) {
        this.id = in.readLong();
        this.published_at = in.readString();
        this.downloads = in.readLong();
        this.curator = in.readParcelable(User.class.getClassLoader());
    }

    public static final Parcelable.Creator<Batch> CREATOR = new Parcelable.Creator<Batch>() {
        public Batch createFromParcel(Parcel source) {
            return new Batch(source);
        }

        public Batch[] newArray(int size) {
            return new Batch[size];
        }
    };
}
