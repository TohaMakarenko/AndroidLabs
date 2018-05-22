package com.example.makarenko.lab4;

import android.media.MediaPlayer;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import android.widget.Button;
import android.widget.MediaController;
import android.widget.Toast;
import android.widget.VideoView;

import com.example.makarenko.lab4.R;


public class MainActivity extends AppCompatActivity {

    MediaPlayer mPlayer1;
    Button startButton1, pauseButton1;
    Button  stopButton1;
    VideoView videoPlayer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        videoPlayer =  (VideoView)findViewById(R.id.videoPlayer);
        Uri myVideoUri= Uri.parse( "android.resource://" + getPackageName() + "/" + R.raw.video);
        videoPlayer.setVideoURI(myVideoUri);
        MediaController mediaController = new MediaController(this);
        videoPlayer.setMediaController(mediaController);
        mediaController.setMediaPlayer(videoPlayer);


        mPlayer1=MediaPlayer.create(this, R.raw.song);
        mPlayer1.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                stopPlay();
            }
        });
        startButton1 = (Button) findViewById(R.id.start1);
        pauseButton1 = (Button) findViewById(R.id.pause1);
        stopButton1 = (Button) findViewById(R.id.stop1);

        pauseButton1.setEnabled(false);
        stopButton1.setEnabled(false);

    }
    public void StartMedia(View view)
    {
        mPlayer1.start();
        startButton1.setEnabled(false);
        pauseButton1.setEnabled(true);
        stopButton1.setEnabled(true);
        videoPlayer.pause();
    }

    private void stopPlay(){
        mPlayer1.stop();
        pauseButton1.setEnabled(false);
        stopButton1.setEnabled(false);
        try {
            mPlayer1.prepare();
            mPlayer1.seekTo(0);
            startButton1.setEnabled(true);
        }
        catch (Throwable t) {
            Toast.makeText(this, t.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

    public void PauseMedia(View view)
    {
        mPlayer1.pause();
        startButton1.setEnabled(true);
        pauseButton1.setEnabled(false);
        stopButton1.setEnabled(true);
    }
    public void StopMedia(View view)
    {
        stopPlay();
    }
}
