package com.roundon.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by liqy on 15/12/17.
 */
public class Photo implements Parcelable {

    @SerializedName("id")
    public String photo_id;
    public int  width;
    public int height;
    public String color;
    public long likes;
    public long downloads;
    public User user;
    public PhotoUrl urls;
    public PhotoLink links;
    public ArrayList<Category> categories;
    public Exif exif;

    @Override
    public String toString() {
        return "Photo{" +
                "id='" + photo_id + '\'' +
                ", width=" + width +
                ", height=" + height +
                ", color='" + color + '\'' +
                ", likes=" + likes +
                ", downloads=" + downloads +
                ", user=" + user +
                ", urls=" + urls +
                ", links=" + links +
                ", categories=" + categories +
                ", exif=" + exif +
                '}';
    }

    public Photo() {
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.photo_id);
        dest.writeInt(this.width);
        dest.writeInt(this.height);
        dest.writeString(this.color);
        dest.writeLong(this.likes);
        dest.writeLong(this.downloads);
        dest.writeParcelable(this.user, 0);
        dest.writeParcelable(this.urls, 0);
        dest.writeParcelable(this.links, 0);
        dest.writeTypedList(categories);
        dest.writeParcelable(this.exif, 0);
    }

    protected Photo(Parcel in) {
        this.photo_id = in.readString();
        this.width = in.readInt();
        this.height = in.readInt();
        this.color = in.readString();
        this.likes = in.readLong();
        this.downloads = in.readLong();
        this.user = in.readParcelable(User.class.getClassLoader());
        this.urls = in.readParcelable(PhotoUrl.class.getClassLoader());
        this.links = in.readParcelable(PhotoLink.class.getClassLoader());
        this.categories = in.createTypedArrayList(Category.CREATOR);
        this.exif = in.readParcelable(Exif.class.getClassLoader());
    }

    public static final Creator<Photo> CREATOR = new Creator<Photo>() {
        public Photo createFromParcel(Parcel source) {
            return new Photo(source);
        }

        public Photo[] newArray(int size) {
            return new Photo[size];
        }
    };
}
