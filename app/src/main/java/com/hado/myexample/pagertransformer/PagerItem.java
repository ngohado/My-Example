package com.hado.myexample.pagertransformer;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Ngo Hado on 08-Jul-16.
 */
public class PagerItem implements Parcelable {
    public String day;
    public String temperature;
    public int backgroundRes;

    public PagerItem(String day, String temperature, int backgroundRes) {
        this.day = day;
        this.temperature = temperature;
        this.backgroundRes = backgroundRes;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.day);
        dest.writeString(this.temperature);
        dest.writeInt(this.backgroundRes);
    }

    protected PagerItem(Parcel in) {
        this.day = in.readString();
        this.temperature = in.readString();
        this.backgroundRes = in.readInt();
    }

    public static final Creator<PagerItem> CREATOR = new Creator<PagerItem>() {
        @Override
        public PagerItem createFromParcel(Parcel source) {
            return new PagerItem(source);
        }

        @Override
        public PagerItem[] newArray(int size) {
            return new PagerItem[size];
        }
    };
}