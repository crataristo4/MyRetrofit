package com.hard.code.tech.myretrofit.models;

import java.util.List;

public class UsersResponse {
    private boolean error;
    private List<Users> users;

    public UsersResponse(boolean error, List<Users> users) {
        this.error = error;
        this.users = users;
    }

    public boolean isError() {
        return error;
    }

    public void setError(boolean error) {
        this.error = error;
    }

    public List<Users> getUsers() {
        return users;
    }

    public void setUsers(List<Users> users) {
        this.users = users;
    }

    @Override
    public String toString() {
        return "UsersResponse{" +
                "error=" + error +
                ", users=" + users +
                '}';
    }

}
