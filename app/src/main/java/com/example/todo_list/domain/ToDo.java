package com.example.todo_list.domain;

import android.os.Parcel;
import android.os.Parcelable;

public class ToDo implements Parcelable {

    private String name;
    private String description;
    private  String id;

    public ToDo(String id, String name, String description) {
        this.name = this.name;
        this.description = this.description;
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    protected ToDo(Parcel in, String id) {
        name = in.readString();
        description = in.readString();
        this.id = id;
    }


    public final Creator<ToDo> CREATOR = new Creator<ToDo>() {
        @Override
        public ToDo createFromParcel(Parcel in) {
            return new ToDo(in, id);
        }

        @Override
        public ToDo[] newArray(int size) {
            return new ToDo[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeString(description);
    }
}
