package com.example.area;

class UserInfo {
    private static final UserInfo ourInstance = new UserInfo();



    static UserInfo getInstance() {
        return ourInstance;
    }

    private UserInfo() {
    }

    public String token;
    public String username;
    public String mail;
    public String id;
}
