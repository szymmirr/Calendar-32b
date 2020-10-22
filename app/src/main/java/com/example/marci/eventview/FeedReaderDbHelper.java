//sauce: https://developer.android.com/training/data-storage/sqlite.html#java

package com.example.marci.eventview;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class FeedReaderDbHelper extends SQLiteOpenHelper {
    public FeedReaderDbHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    private static final String SQL_CREATE_ENTRIES =
            "CREATE TABLE " + FeedReaderContract.FeedEntry.TABLE_NAME + " (" +
                    FeedReaderContract.FeedEntry._ID + " INTEGER PRIMARY KEY," +
                    FeedReaderContract.FeedEntry.COLUMN_NAME1 + " TEXT," +
                    FeedReaderContract.FeedEntry.COLUMN_NAME2 + " TEXT," +
                    FeedReaderContract.FeedEntry.COLUMN_NAME3 + " TEXT," +
                    FeedReaderContract.FeedEntry.COLUMN_NAME4 + " TEXT," +
                    FeedReaderContract.FeedEntry.COLUMN_NAME5 + " TEXT," +
                    FeedReaderContract.FeedEntry.COLUMN_NAME6 + " TEXT," +
                    FeedReaderContract.FeedEntry.COLUMN_NAME7 + " TEXT)";

    private static final String SQL_DELETE_ENTRIES =
            "DROP TABLE IF EXISTS " + FeedReaderContract.FeedEntry.TABLE_NAME;

    // If you change the database schema, you must increment the database version.
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "FeedReader.db";

    public FeedReaderDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public static String output = "output123";
    //TextView textView10;

    //public LayoutInflater inflater;
    //View v = inflater.inflate(R.id.textview10);
    //View innerView = v.findViewById(textview10);

    public void onCreate(SQLiteDatabase db) {

        db.execSQL(SQL_CREATE_ENTRIES);

        //textView10 = (TextView)findViewById(R.id.textview10);
        //textView10.setText(output);
    }
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // This database is only a cache for online data, so its upgrade policy is
        // to simply to discard the data and start over
        db.execSQL(SQL_DELETE_ENTRIES);
        onCreate(db);
    }
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onUpgrade(db, oldVersion, newVersion);
    }

    private static Context context;

    public static Context getContext() {
        return context;
    }

    //FeedReaderDbHelper mDbHelper;

    FeedReaderDbHelper mDbHelper = new FeedReaderDbHelper(getContext()); //tu static rozwala wszystko


    public void putMethod() {
        // Gets the data repository in write mode
        SQLiteDatabase db2 = mDbHelper.getWritableDatabase();

        // Create a new map of values, where column names are the keys
        ContentValues values = new ContentValues();
        values.put(FeedReaderContract.FeedEntry.COLUMN_NAME1, "spotkanie");
        values.put(FeedReaderContract.FeedEntry.COLUMN_NAME7, "zolnierska 1");
        values.put(FeedReaderContract.FeedEntry.COLUMN_NAME1, "spotkanie2");
        values.put(FeedReaderContract.FeedEntry.COLUMN_NAME7, "zolnierska 2");

        // Insert the new row, returning the primary key value of the new row
        long newRowId = db2.insert(FeedReaderContract.FeedEntry.TABLE_NAME, null, values);
    }

    SQLiteDatabase db = mDbHelper.getReadableDatabase();

    // Define a projection that specifies which columns from the database
// you will actually use after this query.
    static String[] projection = {
            BaseColumns._ID,
            FeedReaderContract.FeedEntry.COLUMN_NAME1,
            FeedReaderContract.FeedEntry.COLUMN_NAME7
    };

    // Filter results WHERE "title" = 'My Title'
    static String selection = FeedReaderContract.FeedEntry.COLUMN_NAME1 + " = ?";
    static String[] selectionArgs = { "My Title" };

    // How you want the results sorted in the resulting Cursor
    static String sortOrder =
            FeedReaderContract.FeedEntry.COLUMN_NAME1 + " DESC";

    public static String spotkanko = "uhhhh";

    public void cursorMethod() {
        Cursor cursor = db.query(
                FeedReaderContract.FeedEntry.TABLE_NAME,   // The table to query
                projection,             // The array of columns to return (pass null to get all)
                selection,              // The columns for the WHERE clause
                selectionArgs,          // The values for the WHERE clause
                null,                   // don't group the rows
                null,                   // don't filter by row groups
                sortOrder               // The sort order
        );

        List nazwy = new ArrayList<>();
        while(cursor.moveToNext()) {
            long itemId = cursor.getLong(
                    cursor.getColumnIndexOrThrow(FeedReaderContract.FeedEntry.COLUMN_NAME1));
            nazwy.add(itemId);
        }
        spotkanko=(String)nazwy.get(0);
        cursor.close();
    }

    // Define 'where' part of query.
    String selection2 = FeedReaderContract.FeedEntry.COLUMN_NAME1 + " LIKE ?";
    // Specify arguments in placeholder order.
    String[] selectionArgs2 = { "MyTitle" };
    // Issue SQL statement.
    int deletedRows = db.delete(FeedReaderContract.FeedEntry.TABLE_NAME, selection2, selectionArgs2);



    public void updateMethod() {
        SQLiteDatabase db = mDbHelper.getWritableDatabase();

        // New value for one column
        String title = "MyNewTitle";
        ContentValues values = new ContentValues();
        values.put(FeedReaderContract.FeedEntry.COLUMN_NAME1, title);

        // Which row to update, based on the title
        String selection = FeedReaderContract.FeedEntry.COLUMN_NAME1 + " LIKE ?";
        String[] selectionArgs = {"MyOldTitle"};

        int count = db.update(
                FeedReaderContract.FeedEntry.TABLE_NAME,
                values,
                selection,
                selectionArgs);
    }

    protected void onDestroy() {
        mDbHelper.close();
        mDbHelper.onDestroy();
    }




}

