package com.auribises.college;

/**
 * Created by Admin on 05-05-2017.
 */

public class Teachers {
    int id;
    String teacherName,teacherAddress,teacherSubject,teacherEmail,teacherPhone,gender,password;

    public Teachers() {
    }

    public Teachers(int id, String teacherName, String teacherAddress, String teacherSubject, String teacherEmail, String teacherPhone, String gender,String password) {
        this.id = id;
        this.teacherName = teacherName;
        this.teacherAddress = teacherAddress;
        this.teacherSubject = teacherSubject;
        this.teacherEmail = teacherEmail;
        this.teacherPhone = teacherPhone;
        this.gender = gender;
        this.password=password;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTeacherName() {
        return teacherName;
    }

    public void setTeacherName(String teacherName) {
        this.teacherName = teacherName;
    }

    public String getTeacherAddress() {
        return teacherAddress;
    }

    public void setTeacherAddress(String teacherAddress) {
        this.teacherAddress = teacherAddress;
    }

    public String getTeacherSubject() {
        return teacherSubject;
    }

    public void setTeacherSubject(String teacherSubject) {
        this.teacherSubject = teacherSubject;
    }

    public String getTeacherEmail() {
        return teacherEmail;
    }

    public void setTeacherEmail(String teacherEmail) {
        this.teacherEmail = teacherEmail;
    }

    public String getTeacherPhone() {
        return teacherPhone;
    }

    public void setTeacherPhone(String teacherPhone) {
        this.teacherPhone = teacherPhone;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "Teachers{" +
                "id=" + id +
                ", teacherName='" + teacherName + '\'' +
                ", teacherAddress='" + teacherAddress + '\'' +
                ", teacherSubject='" + teacherSubject + '\'' +
                ", teacherEmail='" + teacherEmail + '\'' +
                ", teacherPhone='" + teacherPhone + '\'' +
                ", gender='" + gender + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
