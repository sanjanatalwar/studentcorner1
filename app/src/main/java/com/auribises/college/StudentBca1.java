package com.auribises.college;


import java.io.Serializable;

public class StudentBca1 implements Serializable{
    int id;
    String stuName, stuPhone, stuEmail, stuAddress, gender, studentClass, studentBirthMonth;
    int  studentBirthDate, StudentBirthYear,mathMarks, cMArks, punjabiMarks;
    String password;



    public StudentBca1() {
    }

    public StudentBca1(int id, String stuName, String stuPhone, String stuEmail, String stuAddress, String gender, String studentClass, String studentBirthMonth, int mathMarks, int cMArks, int punjabiMarks, int studentBirthDate, int studentBirthYear, String password) {
        this.id = id;
        this.stuName = stuName;
        this.stuPhone = stuPhone;
        this.stuEmail = stuEmail;
        this.stuAddress = stuAddress;
        this.gender = gender;
        this.studentClass = studentClass;
        this.studentBirthMonth = studentBirthMonth;
        this.mathMarks = mathMarks;
        this.cMArks = cMArks;
        this.punjabiMarks = punjabiMarks;
        this.studentBirthDate = studentBirthDate;
        this.StudentBirthYear = studentBirthYear;
        this.password=password;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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

    public int getMathMarks() {
        return mathMarks;
    }

    public void setMathMarks(int mathMarks) {
        this.mathMarks = mathMarks;
    }

    public int getcMArks() {
        return cMArks;
    }

    public void setcMArks(int cMArks) {
        this.cMArks = cMArks;
    }

    public int getPunjabiMarks() {
        return punjabiMarks;
    }

    public void setPunjabiMarks(int punjabiMarks) {
        this.punjabiMarks = punjabiMarks;
    }

    public String getGender() {
        return gender;
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

    public void setGender(String gender) {
        this.gender = gender;
    }

    @Override
    public String toString() {
        return "StudentBca1{" +
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
                ", mathMarks=" + mathMarks +
                ", cMArks=" + cMArks +
                ", punjabiMarks=" + punjabiMarks +
                ", password='" + password + '\'' +
                '}';
    }
}
