package com.wislinks.nyimbozakristo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

/**
 * Created by wislinks on 6/12/15.
 */
public class SplashActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        Thread timer = new Thread() {
            @Override
            public void run() {
                super.run();

                try {
                    sleep(5000);
                    Intent openSongListActivity = new Intent(getApplicationContext(), SongListActivity.class);
                    startActivity(openSongListActivity);
                } catch (InterruptedException e) {
                    Log.d("NZK", "Error starting the song list activity");
                }
            }
        };

        timer.start();
    }

    @Override
    protected void onPause() {
        super.onPause();
        this.finish();
    }
}
