package com.example.cgz.bloodsoulnote2.ipc.aidl;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by cgz on 18-3-28.
 */

public class Game implements Parcelable {

    public String gameName;
    public String gameDescribe;

    public Game(String gameName, String gameDescribe) {
        this.gameName = gameName;
        this.gameDescribe = gameDescribe;
    }

    protected Game(Parcel in) {
        this.gameName= in.readString();
        this.gameDescribe = in.readString();
    }

    public static final Creator<Game> CREATOR = new Creator<Game>() {
        @Override
        public Game createFromParcel(Parcel in) {
            return new Game(in);
        }

        @Override
        public Game[] newArray(int size) {
            return new Game[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(gameName);
        dest.writeString(gameDescribe);
    }

    @Override
    public String toString() {
        return "Game{" +
                "gameName='" + gameName + '\'' +
                ", gameDescribe='" + gameDescribe + '\'' +
                '}';
    }
}
