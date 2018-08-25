package com.zyl.kuaikan.bean;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class UserBean extends RealmObject{
    private String name;
    private String password;
    private String cookie;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getCookie() {
        return cookie;
    }

    public void setCookie(String cookie) {
        this.cookie = cookie;
    }
}
