package com.roundon.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by liqy on 15/12/17.
 */
public class PhotoLink implements Parcelable {

    /**
     * 个人信息
     */
    public String self;
    /**
     * 个人主页（web）
     */
    public String html;
    /**
     * 发布照片
     */
    public String photos;
    /**
     * 点赞照片
     */
    public String likes;

    /**
     * 下载链接
     */
    public String download;

    @Override
    public String toString() {
        return "PhotoLink{" +
                "self='" + self + '\'' +
                ", html='" + html + '\'' +
                ", photos='" + photos + '\'' +
                ", likes='" + likes + '\'' +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.self);
        dest.writeString(this.html);
        dest.writeString(this.photos);
        dest.writeString(this.likes);
        dest.writeString(this.download);
    }

    public PhotoLink() {
    }

    protected PhotoLink(Parcel in) {
        this.self = in.readString();
        this.html = in.readString();
        this.photos = in.readString();
        this.likes = in.readString();
        this.download = in.readString();
    }

    public static final Parcelable.Creator<PhotoLink> CREATOR = new Parcelable.Creator<PhotoLink>() {
        public PhotoLink createFromParcel(Parcel source) {
            return new PhotoLink(source);
        }

        public PhotoLink[] newArray(int size) {
            return new PhotoLink[size];
        }
    };
}
