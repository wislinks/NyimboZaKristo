package com.wislinks.nyimbozakristo;

import android.app.ActionBar;
import android.app.Activity;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.widget.TextView;

/**
 * Created by wislinks on 5/24/15.
 */
public class ViewSongActivity extends ActionBarActivity {
    private DBAdapter db;
    private TextView tvSongNumber, tvTitle, tvTitleENG, tvWriter, tvKey, tvLyric;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_song);

        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setIcon(R.drawable.ic_launcher);

        tvSongNumber = (TextView) findViewById(R.id.tvSongNumber);
        tvTitle = (TextView) findViewById(R.id.tvTitle);
        tvTitleENG = (TextView) findViewById(R.id.tvTitleEng);
        tvWriter = (TextView) findViewById(R.id.tvWriter);
        tvKey = (TextView) findViewById(R.id.tvKey);
        tvLyric = (TextView) findViewById(R.id.tvLyrics);

        db = new DBAdapter(this);

        Bundle extras = getIntent().getExtras();
        if(extras != null) {
            long song_no = extras.getLong("song_number");

            db.open();

            Cursor rs = db.getSong(song_no);

            Song song = new Song(rs);

            tvSongNumber.setText(String.valueOf(song.getNumber()));
            tvTitle.setText(song.getTitle(Song.TITLE.TITLE_IN_SWAHILI));
            tvTitleENG.setText(song.getTitle(Song.TITLE.TITLE_IN_ENGLISH));
            tvWriter.setText(song.getWriter());
            tvKey.setText("Doh is " + song.getKey());
            tvLyric.setText(song.getLyrics());

            db.close();

        }
    }

}
