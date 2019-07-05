package com.hard.code.tech.myretrofit.models;

import android.databinding.BaseObservable;
import android.databinding.Bindable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.hard.code.tech.myretrofit.BR;

public class Users extends BaseObservable {


    @SerializedName("id")
    @Expose
    private int id;
    @SerializedName("email")
    @Expose
    private String email;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("school")
    @Expose
    private String school;

    public Users() {
    }


    public Users(String email, String name, String school) {
        this.email = email;
        this.name = name;
        this.school = school;
    }

    public Users(int id, String email, String name, String school) {
        this.id = id;
        this.email = email;
        this.name = name;
        this.school = school;
    }

    @Bindable
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Bindable
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
        notifyPropertyChanged(BR.email);
    }

    @Bindable
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
        notifyPropertyChanged(BR.name);
    }

    @Bindable
    public String getSchool() {
        return school;
    }

    public void setSchool(String school) {
        this.school = school;
        notifyPropertyChanged(BR.school);
    }
}
