package com.cg.model;

public class User {
    private int id;
    private String fullName;
    private int age;
    private String email;
    private String mobile;
    private String address;
    private String password;

    public User() {
    }

    public User(int id, String fullName, int age, String email, String mobile, String address) {
        this.id = id;
        this.fullName = fullName;
        this.age = age;
        this.email = email;
        this.mobile = mobile;
        this.address = address;
    }

    public User(String fullName, int age, String email, String mobile, String address) {
        this.fullName = fullName;
        this.age = age;
        this.email = email;
        this.mobile = mobile;
        this.address = address;
    }

    public User(int id, String fullName, int age, String email, String mobile, String address, String password) {
        this.id = id;
        this.fullName = fullName;
        this.age = age;
        this.email = email;
        this.mobile = mobile;
        this.address = address;
        this.password = password;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
