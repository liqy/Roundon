package com.roundon.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

/**
 * Created by liqy on 15/12/17.
 */
public class Photo implements Parcelable {

    public String id;
    public int  width;
    public int height;
    public String color;
    public User user;
    public PhotoUrl urls;
    public PhotoLink links;
    public ArrayList<Category> categories;

    @Override
    public String toString() {
        return "Photo{" +
                "id='" + id + '\'' +
                ", width=" + width +
                ", height=" + height +
                ", color='" + color + '\'' +
                ", user=" + user +
                ", urls=" + urls +
                ", links=" + links +
                ", categories=" + categories +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.id);
        dest.writeInt(this.width);
        dest.writeInt(this.height);
        dest.writeString(this.color);
        dest.writeParcelable(this.user, flags);
        dest.writeParcelable(this.urls, flags);
        dest.writeParcelable(this.links, flags);
        dest.writeTypedList(categories);
    }

    public Photo() {
    }

    protected Photo(Parcel in) {
        this.id = in.readString();
        this.width = in.readInt();
        this.height = in.readInt();
        this.color = in.readString();
        this.user = in.readParcelable(User.class.getClassLoader());
        this.urls = in.readParcelable(PhotoUrl.class.getClassLoader());
        this.links = in.readParcelable(PhotoLink.class.getClassLoader());
        this.categories = in.createTypedArrayList(Category.CREATOR);
    }

    public static final Parcelable.Creator<Photo> CREATOR = new Parcelable.Creator<Photo>() {
        public Photo createFromParcel(Parcel source) {
            return new Photo(source);
        }

        public Photo[] newArray(int size) {
            return new Photo[size];
        }
    };
}
