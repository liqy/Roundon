package com.roundon.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by liqy on 15/12/17.
 */
public class Category implements Parcelable {
    public long id;
    public String title;
    public long photo_count;
    public PhotoLink links;

    @Override
    public String toString() {
        return "Category{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", photo_count=" + photo_count +
                ", links=" + links +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(this.id);
        dest.writeString(this.title);
        dest.writeLong(this.photo_count);
        dest.writeParcelable(this.links, flags);
    }

    public Category() {
    }

    protected Category(Parcel in) {
        this.id = in.readLong();
        this.title = in.readString();
        this.photo_count = in.readLong();
        this.links = in.readParcelable(PhotoLink.class.getClassLoader());
    }

    public static final Parcelable.Creator<Category> CREATOR = new Parcelable.Creator<Category>() {
        public Category createFromParcel(Parcel source) {
            return new Category(source);
        }

        public Category[] newArray(int size) {
            return new Category[size];
        }
    };
}
