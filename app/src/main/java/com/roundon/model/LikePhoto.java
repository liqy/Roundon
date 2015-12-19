package com.roundon.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by liqy on 15/12/19.
 */
public class LikePhoto implements Parcelable {
    public Photo photo;
    public User user;


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeParcelable(this.photo, 0);
        dest.writeParcelable(this.user, 0);
    }

    public LikePhoto() {
    }

    protected LikePhoto(Parcel in) {
        this.photo = in.readParcelable(Photo.class.getClassLoader());
        this.user = in.readParcelable(User.class.getClassLoader());
    }

    public static final Parcelable.Creator<LikePhoto> CREATOR = new Parcelable.Creator<LikePhoto>() {
        public LikePhoto createFromParcel(Parcel source) {
            return new LikePhoto(source);
        }

        public LikePhoto[] newArray(int size) {
            return new LikePhoto[size];
        }
    };
}
