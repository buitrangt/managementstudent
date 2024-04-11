package com.example.students.dto;

public class StudentsResponse extends StudentsRequest{
    private String id;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "StudentsResponse{" +
                "id='" + id + '\'' +
                ", name='" + getName() + '\'' +
                ", password='" + getPassword() + '\'' +
                ", lastname='" + getLastname() + '\'' +
                '}';
    }
}
