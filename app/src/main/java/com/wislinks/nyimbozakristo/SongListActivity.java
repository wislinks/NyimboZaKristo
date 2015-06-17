package com.wislinks.nyimbozakristo;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

/**
 * Created by wislinks on 6/12/15.
 */
public class SongListActivity extends ActionBarActivity {
    private DBAdapter db;
    private ListView lvSongList;
    private EditText editSearch;
    SimpleCursorAdapter simpleCursorAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_song_list);

        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setIcon(R.drawable.ic_launcher);

        lvSongList = (ListView) findViewById(R.id.listView);

        db = new DBAdapter(this);

        populateListView();

        lvSongList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Bundle dataBundle = new Bundle();

                dataBundle.putLong("song_number", id);

                Intent openDisplaySong = new Intent(view.getContext(), ViewSongActivity.class);

                openDisplaySong.putExtras(dataBundle);

                startActivity(openDisplaySong);
            }
        });
    }

    private void populateListView() {

        String[] Columns = {DBAdapter.SongColumns.TITLE_IN_SWAHILI, DBAdapter.SongColumns._ID};

        int[] ViewIDs = {R.id.list_item_song_tvTitle, R.id.list_item_song_tvNumber};

        db.open();

        Cursor cursor = db.getAllSongs();

        simpleCursorAdapter = new SimpleCursorAdapter(this,
                                                      R.layout.list_item_song,
                                                      cursor,
                                                      Columns,
                                                      ViewIDs,
                                                      SimpleCursorAdapter.IGNORE_ITEM_VIEW_TYPE);

        db.close();

        lvSongList.setAdapter(simpleCursorAdapter);

    }
}
