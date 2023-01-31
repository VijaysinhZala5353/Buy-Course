package com.example.test1;

public class selectedCourse {
    private String courseName;
    private int hours;
    private int fees;

    public selectedCourse(String courseName, int hours, int fees) {
        this.courseName = courseName;
        this.hours = hours;
        this.fees = fees;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public int getHours() {
        return hours;
    }

    public void setHours(int hours) {
        this.hours = hours;
    }

    public int getFees() {
        return fees;
    }

    public void setFees(int fees) {
        this.fees = fees;
    }
}
