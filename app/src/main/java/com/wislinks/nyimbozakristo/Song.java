package com.wislinks.nyimbozakristo;

import android.database.Cursor;

/**
 * Created by wislinks on 6/13/15.
 */
public class Song {
    private long number;
    private String title_in_swahili, title_in_english, writer, key, lyrics;

    public enum TITLE {
        TITLE_IN_ENGLISH,
        TITLE_IN_SWAHILI
    };

    public Song() {

    }

    public Song(Cursor rs) {
        this.number = rs.getLong(rs.getColumnIndex(DBAdapter.SongColumns._ID));
        this.title_in_swahili = rs.getString(rs.getColumnIndex(DBAdapter.SongColumns.TITLE_IN_SWAHILI));
        this.title_in_english = rs.getString(rs.getColumnIndex(DBAdapter.SongColumns.TITLE_IN_ENG));
        this.writer = rs.getString(rs.getColumnIndex(DBAdapter.SongColumns.WRITER));
        this.key = rs.getString(rs.getColumnIndex(DBAdapter.SongColumns.KEY));
        this.lyrics = rs.getString(rs.getColumnIndex(DBAdapter.SongColumns.LYRICS));
    }

    public long getNumber() {
        return number;
    }

    public void setNumber(long number) {
        this.number = number;
    }

    public String getTitle(TITLE t) {
        String title = "";

        if (t == t.TITLE_IN_ENGLISH) {
            title = title_in_english;
        } else if (t == t.TITLE_IN_SWAHILI) {
            title = title_in_swahili;
        }

        return title;
    }

    public void setTitle(TITLE t, String title) {
        if (t == t.TITLE_IN_ENGLISH) {
            this.title_in_english = title;
        } else if (t == t.TITLE_IN_SWAHILI) {
            this.title_in_swahili = title;
        }
    }

    public String getWriter() {
        return writer;
    }

    public void setWriter(String writer) {
        this.writer = writer;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getLyrics() {
        return lyrics;
    }

    public void setLyrics(String lyrics) {
        this.lyrics = lyrics;
    }
}
