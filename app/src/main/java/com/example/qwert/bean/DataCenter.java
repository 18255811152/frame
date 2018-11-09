package com.example.qwert.bean;

public class DataCenter {
    private User user = new User();
    private static DataCenter instance;

    /*单利模式调用*/
    public static DataCenter getInstance() {
        if (instance == null) {
            instance = new DataCenter();
        }
        return instance;
    }


    public void setUser(User user) {
        user.setPassword(user.getPassword());
        user.setName(user.getName());
        user.setLogin(user.isLogin());
        user.setImageDimon(user.getImageDimon());
    }

    public User getUser() {
        return user;
    }


    public DataCenter setDimon(String dimon) {
        user.setImageDimon(dimon);
        return instance;
    }


    public String getDimon() {
        return user.getImageDimon();
    }


    public boolean isLogin() {
        return user.isLogin();
    }


}
