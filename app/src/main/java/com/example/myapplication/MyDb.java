package com.example.myapplication;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.text.TextUtils;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class MyDb extends SQLiteOpenHelper {

    public MyDb(@Nullable Context context) {
        super(context, "demo-sql", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("CREATE TABLE " + Person.TABLE_NAME +
                "(" +
                Person.COL_ID + " INTEGER PRIMARY KEY ," +
                Person.COL_NAME + " TEXT ," +
                Person.COL_CMT + " TEXT ," +
                Person.COL_NOTE + " TEXT ," +
                Person.COL_DEGREE + " TEXT ," +
                Person.COL_FAV + " TEXT " +
                ");");

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
    }

    public List<Person> getPeople() {
        ArrayList<Person> people = new ArrayList<>();

        String query = "SELECT * FROM " + Person.TABLE_NAME;

        SQLiteDatabase db = this.getWritableDatabase();

        Cursor cursor = db.rawQuery(query, null);

        if (cursor.moveToFirst()) {
            do {
                Person n = new Person();
                n.setId(cursor.getInt(0));
                n.setName(cursor.getString(1));
                n.setCmt(cursor.getString(2));

                n.setDegree(cursor.getString(3));
                n.setFav(cursor.getString(4).split(","));
                n.setNote(cursor.getString(5));

                people.add(n);
            } while (cursor.moveToNext());
        }

        return people;
    }

    public long insertPerson(Person n) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put(Person.COL_NAME, n.getName());
        values.put(Person.COL_CMT, n.getCmt());
        values.put(Person.COL_DEGREE, n.getDegree());
        values.put(Person.COL_FAV, TextUtils.join(",", n.getFav()));
        values.put(Person.COL_NOTE, n.getNote());

        long id = db.insert(Person.TABLE_NAME, null, values);
        db.close();
        return id;
    }


    public int updatePerson(Person n) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put(Person.COL_NAME, n.getName());
        values.put(Person.COL_CMT, n.getCmt());
        values.put(Person.COL_DEGREE, n.getDegree());
        values.put(Person.COL_FAV, TextUtils.join(",", n.getFav()));
        values.put(Person.COL_NOTE, n.getNote());

        int affectedCount = db.update(Person.TABLE_NAME,
                values,
                " id = ?",
                new String[]{String.valueOf(n.getId())});

        return affectedCount;
    }

    public int deletePerson(Person n) {
        SQLiteDatabase db = this.getWritableDatabase();
        int deletedRows = db.delete(Person.TABLE_NAME,
                "id = ?",
                new String[]{String.valueOf(n.getId())});

        db.close();
        return deletedRows;
    }
}
