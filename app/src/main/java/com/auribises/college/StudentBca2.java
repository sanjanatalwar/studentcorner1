package com.auribises.college;

/**
 * Created by Admin on 04-05-2017.
 */

public class StudentBca2 {

    int id;
    String stuName, stuPhone, stuEmail, stuAddress, gender, studentClass, studentBirthMonth;
    int  studentBirthDate, StudentBirthYear,mathMarks,unixMarks,dataStructureMarks;

    public StudentBca2() {
    }

    public StudentBca2(int id, String stuName, String stuPhone, String stuEmail, String stuAddress, String gender, String studentClass, String studentBirthMonth, int studentBirthDate, int studentBirthYear, int mathMarks, int unixMarks, int dataStructureMarks) {
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
        this.mathMarks = mathMarks;
        this.unixMarks = unixMarks;
        this.dataStructureMarks = dataStructureMarks;
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

    public int getMathMarks() {
        return mathMarks;
    }

    public void setMathMarks(int mathMarks) {
        this.mathMarks = mathMarks;
    }

    public int getUnixMarks() {
        return unixMarks;
    }

    public void setUnixMarks(int unixMarks) {
        this.unixMarks = unixMarks;
    }

    public int getDataStructureMarks() {
        return dataStructureMarks;
    }

    public void setDataStructureMarks(int dataStructureMarks) {
        this.dataStructureMarks = dataStructureMarks;
    }

    @Override
    public String toString() {
        return "StudentBca2{" +
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
                ", unixMarks=" + unixMarks +
                ", dataStructureMarks=" + dataStructureMarks +
                '}';
    }
}
