package com.zfj.pojo;

public class Test {
    private int id;
    private String username;

    public Test() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String toString() {
        return "Test{" +
                "id=" + id +
                ", username='" + username + '\'' +
                '}';
    }
}