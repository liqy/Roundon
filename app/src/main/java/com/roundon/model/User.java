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
    public long downloads;
    public String bio;
    public long uploads_remaining;
    public String instagram_username;
    public Location location;
    public String email;
    public PhotoLink links;

    @Override
    public String toString() {
        return "User{" +
                "uuid='" + uuid + '\'' +
                ", id='" + uid + '\'' +
                ", username='" + username + '\'' +
                ", name='" + name + '\'' +
                ", first_name='" + first_name + '\'' +
                ", last_name='" + last_name + '\'' +
                ", portfolio_url='" + portfolio_url + '\'' +
                ", downloads=" + downloads +
                ", bio='" + bio + '\'' +
                ", uploads_remaining=" + uploads_remaining +
                ", instagram_username='" + instagram_username + '\'' +
                ", location=" + location +
                ", email='" + email + '\'' +
                ", links=" + links +
                '}';
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
        dest.writeLong(this.downloads);
        dest.writeString(this.bio);
        dest.writeLong(this.uploads_remaining);
        dest.writeString(this.instagram_username);
        dest.writeParcelable(this.location, 0);
        dest.writeString(this.email);
        dest.writeParcelable(this.links, 0);
    }

    public User() {
    }

    protected User(Parcel in) {
        this.uuid = in.readString();
        this.uid = in.readString();
        this.username = in.readString();
        this.name = in.readString();
        this.first_name = in.readString();
        this.last_name = in.readString();
        this.portfolio_url = in.readString();
        this.downloads = in.readLong();
        this.bio = in.readString();
        this.uploads_remaining = in.readLong();
        this.instagram_username = in.readString();
        this.location = in.readParcelable(Location.class.getClassLoader());
        this.email = in.readString();
        this.links = in.readParcelable(PhotoLink.class.getClassLoader());
    }

    public static final Parcelable.Creator<User> CREATOR = new Parcelable.Creator<User>() {
        public User createFromParcel(Parcel source) {
            return new User(source);
        }

        public User[] newArray(int size) {
            return new User[size];
        }
    };
}
