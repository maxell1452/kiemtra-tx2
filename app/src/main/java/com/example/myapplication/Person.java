package com.example.myapplication;

import android.text.TextUtils;

import java.io.Serializable;

public class Person implements Serializable {

    public static final String TABLE_NAME = "people";
    public static final String COL_ID = "id";
    public static final String COL_NAME = "full_name";
    public static final String COL_CMT = "cmt";
    public static final String COL_NOTE = "note";
    public static final String COL_DEGREE = "degree";
    public static final String COL_FAV = "fav";


    public static String HIGH_SCHOOL_DEGREE = "Trung cấp";
    public static String COLLEGE_DEGREE = "Cao đẳng";
    public static String UNIVERSITY_DEGREE = "Đại học";

    public static String READ_NEWS = "Đọc báo";
    public static String READ_BOOK = "Đọc sách";
    public static String CODE = "Code";

    int id;
    String name, cmt, note, degree;
    String[] fav;

    public Person(int id, String name, String cmt, String note, String degree, String[] fav) {
        this.id = id;
        this.name = name;
        this.cmt = cmt;
        this.note = note;
        this.degree = degree;
        this.fav = fav;
    }

    public Person() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCmt() {
        return cmt;
    }

    public void setCmt(String cmt) {
        this.cmt = cmt;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getDegree() {
        return degree;
    }

    public void setDegree(String degree) {
        this.degree = degree;
    }

    public String[] getFav() {
        return fav;
    }

    public void setFav(String[] fav) {
        this.fav = fav;
    }

    @Override
    public String toString() {
        return name + " - " + cmt + " - " + degree + " - " + TextUtils.join(", ", fav) + " - " + note;
    }
}
