package edu.domain.usingvideo;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.MediaController;
import android.widget.SeekBar;
import android.widget.VideoView;

import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {

    MediaPlayer mediaPlayer;
    boolean playOn = false;
    ImageButton oneButton;
    SeekBar volumeSeekBar;
AudioManager audioManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
       /*   VideoView videoView = findViewById(R.id.videoView);
        videoView.setVideoPath("android.resource://" + getPackageName() + "/" + R.raw.oi_video);
        MediaController mediaController = new MediaController(this);
        videoView.setMediaController(mediaController);
        videoView.start();*/
        mediaPlayer = MediaPlayer.create(this, R.raw.bob_dylan);
        oneButton = findViewById(R.id.buttonPlay);
        audioManager = (AudioManager) getSystemService(AUDIO_SERVICE);
        int maxVolume = audioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC);

        volumeSeekBar = findViewById(R.id.volumeSeekBar);
        volumeSeekBar.setMax(mediaPlayer.getDuration());
        new Timer().scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                volumeSeekBar.setProgress(mediaPlayer.getCurrentPosition());
            }
        }, 0, 1000);

        volumeSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                //audioManager.setStreamVolume(AudioManager.STREAM_MUSIC, i, 0);
                if(b){
                    mediaPlayer.seekTo(i);
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
        // mediaPlayer.pause();
    }

    @SuppressLint("UseCompatLoadingForDrawables")
    public void playPause(View view) {

        if (!playOn) {
            //oneButton.setText("PAUSE");
            mediaPlayer.start();
            oneButton.setImageDrawable(getResources().getDrawable(R.drawable.baseline_pause_24, getApplicationContext().getTheme()));

        } else {
            //oneButton.setText("PLAY");
            mediaPlayer.pause();
            oneButton.setImageDrawable(getResources().getDrawable(R.drawable.baseline_play_arrow_24));
        }
        playOn = !playOn;
    }

    public void nextTrack(View view) {
    }

    public void prevTrack(View view) {
    }
}