package com.example.huhaichang.learn3.eight.widget;

import org.litepal.crud.LitePalSupport;

/**
 * Created by huhaichang on 2019/7/29.
 */

public class UserManage extends LitePalSupport {
   private int id;
   private String user;
   private String password;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
