package com.auribises.college;

import java.io.Serializable;

/**
 * Created by Admin on 04-05-2017.
 */

public class StudentBca3  implements Serializable{
    int id;
    String stuName, stuPhone, stuEmail, stuAddress, gender, studentClass, studentBirthMonth;
    int  studentBirthDate, StudentBirthYear,graphicsMarks,operatingSystemMarks,mathMarks;

    public StudentBca3() {
    }

    public StudentBca3(int id, String stuName, String stuPhone, String stuEmail, String stuAddress, String gender, String studentClass, String studentBirthMonth, int studentBirthDate, int studentBirthYear, int graphicsMarks, int operatingSystemMarks, int mathMarks) {
        this.id = id;
        this.stuName = stuName;
        this.stuPhone = stuPhone;
        this.stuEmail = stuEmail;
        this.stuAddress = stuAddress;
        this.gender = gender;
        this.studentClass = studentClass;
        this.studentBirthMonth = studentBirthMonth;
        this.studentBirthDate = studentBirthDate;
        StudentBirthYear = studentBirthYear;
        this.graphicsMarks = graphicsMarks;
        this.operatingSystemMarks = operatingSystemMarks;
        this.mathMarks = mathMarks;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getStuName() {
        return stuName;
    }

    public void setStuName(String stuName) {
        this.stuName = stuName;
    }

    public String getStuPhone() {
        return stuPhone;
    }

    public void setStuPhone(String stuPhone) {
        this.stuPhone = stuPhone;
    }

    public String getStuEmail() {
        return stuEmail;
    }

    public void setStuEmail(String stuEmail) {
        this.stuEmail = stuEmail;
    }

    public String getStuAddress() {
        return stuAddress;
    }

    public void setStuAddress(String stuAddress) {
        this.stuAddress = stuAddress;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getStudentClass() {
        return studentClass;
    }

    public void setStudentClass(String studentClass) {
        this.studentClass = studentClass;
    }

    public String getStudentBirthMonth() {
        return studentBirthMonth;
    }

    public void setStudentBirthMonth(String studentBirthMonth) {
        this.studentBirthMonth = studentBirthMonth;
    }

    public int getStudentBirthDate() {
        return studentBirthDate;
    }

    public void setStudentBirthDate(int studentBirthDate) {
        this.studentBirthDate = studentBirthDate;
    }

    public int getStudentBirthYear() {
        return StudentBirthYear;
    }

    public void setStudentBirthYear(int studentBirthYear) {
        StudentBirthYear = studentBirthYear;
    }

    public int getGraphicsMarks() {
        return graphicsMarks;
    }

    public void setGraphicsMarks(int graphicsMarks) {
        this.graphicsMarks = graphicsMarks;
    }

    public int getOperatingSystemMarks() {
        return operatingSystemMarks;
    }

    public void setOperatingSystemMarks(int operatingSystemMarks) {
        this.operatingSystemMarks = operatingSystemMarks;
    }

    public int getMathMarks() {
        return mathMarks;
    }

    public void setMathMarks(int mathMarks) {
        this.mathMarks = mathMarks;
    }

    @Override
    public String toString() {
        return "StudentBca3{" +
                "id=" + id +
                ", stuName='" + stuName + '\'' +
                ", stuPhone='" + stuPhone + '\'' +
                ", stuEmail='" + stuEmail + '\'' +
                ", stuAddress='" + stuAddress + '\'' +
                ", gender='" + gender + '\'' +
                ", studentClass='" + studentClass + '\'' +
                ", studentBirthMonth='" + studentBirthMonth + '\'' +
                ", studentBirthDate=" + studentBirthDate +
                ", StudentBirthYear=" + StudentBirthYear +
                ", graphicsMarks=" + graphicsMarks +
                ", operatingSystemMarks=" + operatingSystemMarks +
                ", mathMarks=" + mathMarks +
                '}';
    }
}
