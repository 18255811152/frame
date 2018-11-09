package com.example.qwert.bean;

public class User extends UserBean {
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

    private String name;
    private String password;
    private String imageDimon;

    public boolean isLogin() {
        return isLogin;
    }

    public void setLogin(boolean login) {
        isLogin = login;
    }

    private boolean isLogin;


    public String getImageDimon() {
        return imageDimon;
    }

    public void setImageDimon(String imageDimon) {
        this.imageDimon = imageDimon;
    }

}
