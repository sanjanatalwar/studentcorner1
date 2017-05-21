package com.auribises.college;

/**
 * Created by Admin on 01-05-2017.
 */

public class Util {
    // Information for my Database
    public static final int DB_VERSION = 1;
    public static final String DB_NAME = "dbase.db";

    // Information for my Table
    public static final String TAB_NAME = "BcaOneStudent";
    public static final String COL_ID = "_ID";
    public static final String COL_NAME = "NAME";
    public static final String COL_PHONE = "PHONE";
    public static final String COL_EMAIL = "EMAIL";

    public static final String COL_CITY = "CITY";
    public static final String COL_PASSWORD = "PASSWORD";

    public static final String CREATE_TAB_QUERY = "create table Student(" +
            "_ID integer primary key autoincrement," +
            "NAME varchar(256)," +
            "PHONE varchar(20)," +
            "EMAIL varchar(256)," +

            "CITY varchar(256)" +
            "PASSWORD varchar(256)" +
            ")";


    // URL

    public static final String PREFS_NAME = "StudentCorner";
    public static final String KEY_NAME = "keyName";
    public static final String KEY_SUBJECT = "keySubject";


    public static final String INSERT_STUDENT_PHP = "http://studentedu.esy.es/student_corner/insertstudent.php";
    public static final String RETERIEVE_STUDENT_PHP = "http://studentedu.esy.es/student_corner/reterieve.php";
    public static final String UPDATE_STUDENT_PHP = "http://studentedu.esy.es/student_corner/updatestudents.php";

    public static final String INSERT_BCA_TWO_STUDENT_PHP = "http://studentedu.esy.es/student_corner/insertBcaTwoStudent.php";
    public static final String INSERT_BCA_THREE_STUDENT_PHP = "http://studentedu.esy.es/student_corner/insertBcaThreeStudent.php";


    public static final String INSERT_TEACHER_PHP = "http://studentedu.esy.es/student_corner/insertTeacher.php";
    public static final String EMAIL_TEACHER_PHP = "http://studentedu.esy.es/student_corner/admingmail.php";
    public static final String TEACHER_Login_PHP = "http://studentedu.esy.es/student_corner/teacherLogin.php";
    public static final String RETERIEVE_BCA2_PHP = "http://studentedu.esy.es/student_corner/reterieveBca2.php";
    public static final String UPDATE_BCA2_PHP = "http://studentedu.esy.es/student_corner/bca2update.php";
    public static final String RETERIEVE_BCA3_PHP = "http://studentedu.esy.es/student_corner/reterieveBca3.php";
    public static final String UPDATE_BCA3_PHP="http://studentedu.esy.es/student_corner/bca3update.php";
    public static final String BCA_ONE_Login_PHP = "http://studentedu.esy.es/student_corner/BcaOneLogin.php";

    public static final String BCA_TWO_Login_PHP = "http://studentedu.esy.es/student_corner/Bca2Login.php";
    public static final String BCA_THREE_Login_PHP = "http://studentedu.esy.es/student_corner/Bca3Login.php";
    public static final String ADMIN_Login_PHP = "http://studentedu.esy.es/student_corner/adminLogin.php";
    public static final String RETERIEVE_TEACHER_PHP = "http://studentedu.esy.es/student_corner/TeacherReterieve.php";

    public static final String BCA_ONE_DELETE_PHP = "http://studentedu.esy.es/student_corner/BcaOneDelete.php";

    public static final String BCA_TWO_DELETE_PHP = "http://studentedu.esy.es/student_corner/Bca2Delete.php";
    public static final String BCA_THREE_DELETE_PHP = "http://studentedu.esy.es/student_corner/Bca3Delete.php";
    public static final String TEACHER_DELETE_PHP = "http://studentedu.esy.es/student_corner/TeachersDelete.php";
}