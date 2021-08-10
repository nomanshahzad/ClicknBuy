package com.example.clicknbuy;

public class ModelUser {
    private String Name,Number,Email;

    public ModelUser(String name, String number, String email) {
        Name = name;
        Number = number;
        Email = email;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getNumber() {
        return Number;
    }

    public void setNumber(String number) {
        Number = number;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    @Override
    public String toString() {
        return "ModelUser{" +
                "Name='" + Name + '\'' +
                ", Number='" + Number + '\'' +
                ", Email='" + Email + '\'' +
                '}';
    }
}
