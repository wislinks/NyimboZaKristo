package com.wislinks.nyimbozakristo;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;
import android.util.Log;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * Created by wislinks on 6/12/15.
 */
public class DBAdapter {
    private static final String SONGS_TABLE_NAME = "songs";

    private static final String[] ALL_COLUMNS = new String[] {
            SongColumns._ID,
            SongColumns.TITLE_IN_SWAHILI,
            SongColumns.TITLE_IN_ENG,
            SongColumns.WRITER,
            SongColumns.KEY,
            SongColumns.LYRICS
    };

    /* Inner class that defines the table contents */
    public interface SongColumns extends BaseColumns {
        public static final String TITLE_IN_SWAHILI = "title_in_swahili";
        public static final String TITLE_IN_ENG = "title_in_english";
        public static final String WRITER = "writer";
        public static final String KEY = "key";
        public static final String LYRICS = "lyrics";
    }

    //////////////////////////////////////////////////////

    private static SQLiteDatabase db = null;
    private DBHelper myDBHelper;

    //////////////////////////////////////////////////////

    public DBAdapter(Context context) {
        myDBHelper = new DBHelper(context);
    }

    public void open() {
        try {
            db = myDBHelper.getReadableDatabase();
            //db = SQLiteDatabase.openDatabase(DB_PATH + DB_NAME, null, SQLiteDatabase.OPEN_READONLY);
        } catch (Exception e) {
            Log.d("NZK", "Error openning the database");
        }
    }

    public void close() {
        try {
            db.close();
        } catch (Exception e) {
            Log.d("NZK", "Error occurred during closing");
        }
    }

    public Cursor getAllSongs() {
        String[] columns = {SongColumns._ID, SongColumns.TITLE_IN_SWAHILI};

        String criteria = null;
        Cursor cursor = null;

        try {
            cursor = db.query(true,
                              SONGS_TABLE_NAME,
                              columns,
                              criteria,
                              null, null, null, null, null);

            cursor.moveToFirst();

        } catch (Exception e) {
            Log.d("NZK", "Error at getAllSongs");
        }

        return cursor;
    }

    public Cursor getSong(long rowId) {
        String criteria = SongColumns._ID + " = " + rowId;
        Cursor cursor = null;

        try {
            cursor = db.query(true,
                              SONGS_TABLE_NAME,
                              ALL_COLUMNS,
                              criteria,
                              null, null, null, null, null);

            cursor.moveToFirst();

        } catch (Exception e) {
            Log.d("NZK", "Error at getSong");
        }

        return cursor;
    }

    private class DBHelper extends SQLiteOpenHelper {
        private String DB_PATH = null;
        private static final String DB_NAME = "SongDatabase.db";
        private static final int DB_VERSION = 1;

        public DBHelper(Context context) {
            super(context, DB_NAME, null, DB_VERSION);

            DB_PATH = "/data/data/" + context.getPackageName() + "/databases/";

            // see if file exists
            File file = new File(DB_PATH + DB_NAME);

            /**
             * Creates a empty database on the system and rewrites it with your own database.
             * if it doesn't exist yet
             * */
            if (!file.exists()) {
                Log.d("NZK", "File Doesn't Exist");

                //By calling this method an empty database will be created into the default system path
                //of your application so we are gonna be able to overwrite that database with our database.
                this.getReadableDatabase();

                try {
                    Log.d("NZK", "Creating the database file");

                    //Open your local db as the input stream
                    InputStream myInput = context.getAssets().open(DB_NAME);

                    //Open the empty db as the output stream
                    OutputStream myOutput = new FileOutputStream(DB_PATH + DB_NAME);

                    //transfer bytes from the inputfile to the outputfile
                    byte[] buffer = new byte[1024];
                    int length;
                    while ((length = myInput.read(buffer))>0){
                        myOutput.write(buffer, 0, length);
                    }

                    //Close the streams
                    myOutput.flush();
                    myOutput.close();
                    myInput.close();

                    Log.d("NZK", "Database created successfully");
                } catch (IOException e) {
                    Log.d("NZK", "Error creating the database");
                }
            }

        }

        @Override
        public void onCreate(SQLiteDatabase sqLiteDatabase) {

        }

        @Override
        public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

        }

    }

}
