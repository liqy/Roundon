package com.roundon.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.activeandroid.Model;
import com.activeandroid.annotation.Table;
import com.google.gson.annotations.SerializedName;

/**
 * Created by liqy on 15/12/17.
 */

@Table(name = "Splash_User")
public class User extends Model implements Parcelable {

    public String uuid;

    @SerializedName("id")
    public String uid;
    public String username;
    public String name;
    public String first_name;
    public String last_name;
    public String portfolio_url;
    public PhotoUrl profile_image;
    public long downloads;
    public String bio;
    public long uploads_remaining;
    public String instagram_username;
    public String email;

    public PhotoUrl getProfileImage() {
        return profile_image;
    }

    @Override
    public String toString() {
        return "User{" +
                "uuid='" + uuid + '\'' +
                ", uid='" + uid + '\'' +
                ", username='" + username + '\'' +
                ", name='" + name + '\'' +
                ", first_name='" + first_name + '\'' +
                ", last_name='" + last_name + '\'' +
                ", portfolio_url='" + portfolio_url + '\'' +
                ", profile_image=" + profile_image +
                ", downloads=" + downloads +
                ", bio='" + bio + '\'' +
                ", uploads_remaining=" + uploads_remaining +
                ", instagram_username='" + instagram_username + '\'' +
                ", email='" + email + '\'' +
                '}';
    }

    public User() {
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.uuid);
        dest.writeString(this.uid);
        dest.writeString(this.username);
        dest.writeString(this.name);
        dest.writeString(this.first_name);
        dest.writeString(this.last_name);
        dest.writeString(this.portfolio_url);
        dest.writeParcelable(this.profile_image, 0);
        dest.writeLong(this.downloads);
        dest.writeString(this.bio);
        dest.writeLong(this.uploads_remaining);
        dest.writeString(this.instagram_username);
        dest.writeString(this.email);
    }

    protected User(Parcel in) {
        this.uuid = in.readString();
        this.uid = in.readString();
        this.username = in.readString();
        this.name = in.readString();
        this.first_name = in.readString();
        this.last_name = in.readString();
        this.portfolio_url = in.readString();
        this.profile_image = in.readParcelable(PhotoUrl.class.getClassLoader());
        this.downloads = in.readLong();
        this.bio = in.readString();
        this.uploads_remaining = in.readLong();
        this.instagram_username = in.readString();
        this.email = in.readString();
    }

    public static final Creator<User> CREATOR = new Creator<User>() {
        public User createFromParcel(Parcel source) {
            return new User(source);
        }

        public User[] newArray(int size) {
            return new User[size];
        }
    };
}
