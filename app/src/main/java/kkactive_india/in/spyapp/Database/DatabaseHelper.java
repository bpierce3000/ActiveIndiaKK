package kkactive_india.in.spyapp.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "SpyApp";

    private static final String TABLE1 = "Msgs";
    private static final String TABLE2 = "Calls";

    private static final String Id = "id";
    private static final String Phone = "phone";
    private static final String Body = "body";
    private static final String Type = "type";
    private static final String Date = "date";
    private static final String Duration = "duration";


    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        String CREATE_CONTACTS_TABLE1 = "CREATE TABLE " + TABLE1 + "(" + Phone + " TEXT,"
                + Body + " TEXT," + Type + " TEXT," + Date + " TEXT)";
        sqLiteDatabase.execSQL(CREATE_CONTACTS_TABLE1);

        String CREATE_CONTACTS_TABLE2 = "CREATE TABLE " + TABLE2 + "(" + Phone + " TEXT,"
                + Duration + " TEXT," + Type + " TEXT," + Date + " TEXT)";
        sqLiteDatabase.execSQL(CREATE_CONTACTS_TABLE2);


    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    public boolean insertCalls(String phone, String duration, String type, String date){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        //contentValues.put(Id, id);
        contentValues.put(Phone, phone);
        contentValues.put(Duration, duration);
        contentValues.put(Type, type);
        contentValues.put(Date, date);

        long result = db.insert(TABLE2, null, contentValues);

        db.close();

        if (result == -1)
            return false;
        else
            return true;


    }


    public boolean insert(String phone, String body, String type, String date) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
       // contentValues.put(Id, id);
        contentValues.put(Phone, phone);
        contentValues.put(Body, body);
        contentValues.put(Type, type);
        contentValues.put(Date, date);

        long result = db.insert(TABLE1, null, contentValues);

        db.close();

        if (result == -1)
            return false;
        else
            return true;

    }

    public Cursor getMsgs() {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("select * from " + TABLE1, null);

        return res;
    }

    public Cursor getCalls(){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("select * from " + TABLE2, null);

        return res;
    }

    public void delete1() {

        SQLiteDatabase db = this.getWritableDatabase();

        db.execSQL("delete from " + TABLE1);

        db.close();


    }



}
