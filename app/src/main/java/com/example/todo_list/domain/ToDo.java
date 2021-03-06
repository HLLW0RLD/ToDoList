package com.example.todo_list.domain;

import android.os.Parcel;
import android.os.Parcelable;

public class ToDo implements Parcelable {

    private String name;
    private String description;
    private int date;

    public ToDo() {
        this.name = name;
        this.description = description;
        this.date = date;
    }

    protected ToDo(Parcel in) {
        name = in.readString();
        description = in.readString();
        date = in.readInt();
    }

    public static final Creator<ToDo> CREATOR = new Creator<ToDo>() {
        @Override
        public ToDo createFromParcel(Parcel in) {
            return new ToDo(in);
        }

        @Override
        public ToDo[] newArray(int size) {
            return new ToDo[size];
        }
    };

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public int getDate() {
        return date;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setDate(int date) {
        this.date = date;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeString(description);
        dest.writeInt(date);
    }
}
