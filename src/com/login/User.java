package com.login;

public class User {

    private String u_name;
    private String password;

    public User(String u_name, String password) {
        this.u_name = u_name;
        this.password = password;
    }

    public String getU_name() {
        return u_name;
    }

    public String getPassword() {
        return password;
    }

    public boolean equals(User user){
        if(this.u_name.equals(user.u_name)&&this.password.equals(user.password)) {
            return true;
        }
        System.out.println(user.u_name+"   "+this.u_name+"  "+user.password+"     "+this.password);
        return false;
    }
}
