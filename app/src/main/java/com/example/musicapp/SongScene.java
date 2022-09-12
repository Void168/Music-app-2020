package com.example.musicapp;

import androidx.appcompat.app.AppCompatActivity;


import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Random;


public class SongScene extends AppCompatActivity {

    TextView txtTitle, starttime, endtime;
    ImageButton btnprev, btnnext, btnplay, btnback;
    SeekBar songtimeline;
    ImageView disc;
    Animation animation;
    int i = SongData.generateSongData().size();
    int position;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_song_scene);
        AnhXa();

        SetTimeTotal();
        UpdateTimer();
        SetSongInfo();
        //Truyền chuỗi vị trí bài hát từ listview
        Bundle extras = getIntent().getExtras();
        String tmp = extras.getString("myKey");
        //Đổi chuỗi vị trí thành mảng số
        final int[] tmps = {Integer.parseInt(tmp)};

        //Quay đĩa
        animation = AnimationUtils.loadAnimation(SongScene.this, R.anim.disc);
        disc.startAnimation(animation);


        // Sự kiện bắt đầu bài hát
        btnplay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(Songlist.mediaPlayer.isPlaying() == true){
                    btnplay.setImageResource(R.drawable.playbutton);
                    Songlist.mediaPlayer.pause();
                    disc.clearAnimation();
                } else{
                    Songlist.mediaPlayer.start();
                    btnplay.setImageResource(R.drawable.pausebutton);
                    disc.startAnimation(animation);
                }
            }
        });

        //Sự kiện click nút previous
        btnprev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Songlist.mediaPlayer.isPlaying()) {
                    Songlist.mediaPlayer.stop();
                    tmps[0]--;
                    if (tmps[0] < 0) {
                        tmps[0] = i - 1;
                    }
                }
                Songlist.mediaPlayer = MediaPlayer.create(SongScene.this, SongData.generateSongData().get(tmps[0]).getFile());
                Songlist.mediaPlayer.start();
                txtTitle.setText(SongData.generateSongData().get(tmps[0]).getTitle());
                disc.setImageResource(SongData.generateSongData().get(tmps[0]).getDisc());
                animation = AnimationUtils.loadAnimation(SongScene.this, R.anim.disc);
                disc.startAnimation(animation);
                SetTimeTotal();
                UpdateTimer();
            }
        });

        //Sự kiện click nút next
        btnnext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Songlist.mediaPlayer.isPlaying()){
                    Songlist.mediaPlayer.stop();
                    tmps[0]++;
                    if (tmps[0] > i - 1)
                        tmps[0] = 0;
                }
                Songlist.mediaPlayer = MediaPlayer.create(SongScene.this, SongData.generateSongData().get(tmps[0]).getFile());
                Songlist.mediaPlayer.start();
                txtTitle.setText(SongData.generateSongData().get(tmps[0]).getTitle());
                disc.setImageResource(SongData.generateSongData().get(tmps[0]).getDisc());
                animation = AnimationUtils.loadAnimation(SongScene.this, R.anim.disc);
                disc.startAnimation(animation);
                SetTimeTotal();
                UpdateTimer();
            }
        });


        //Sự kiện back về màn hình Danh sách nhạc
        btnback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SongScene.this, Songlist.class);
//                Songlist.minisceneimage.setImageResource(SongData.generateSongData().get(tmps[0]).getDisc());
//                Songlist.miniTitle.setText(SongData.generateSongData().get(tmps[0]).getTitle());
//                animation = AnimationUtils.loadAnimation(SongScene.this, R.anim.slide_title);
//                Songlist.miniTitle.startAnimation(animation);
                startActivity(intent);
            }
        });

        //Sự kiện trên thanh nhạc
        songtimeline.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            //
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                SimpleDateFormat dinhdanggio = new SimpleDateFormat("mm:ss");
                endtime.setText(dinhdanggio.format(Songlist.mediaPlayer.getDuration()));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            //Tua bài hát tới vị trí cần đến
            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                Songlist.mediaPlayer.seekTo(songtimeline.getProgress());
            }
        });
    }

    //Cập nhật thông tin bài hát
    private void SetSongInfo(){
        Intent intent = getIntent();
        String songname = intent.getStringExtra("songname");
        txtTitle.setText(songname);

        Bundle bundle = getIntent().getExtras();
        int songdisc = bundle.getInt("songdisc");
        if(bundle != null){
            disc.setImageResource(songdisc);
        }
    }

    //Cài đặt thời gian tổng
    private void SetTimeTotal(){
        SimpleDateFormat dinhdanggio = new SimpleDateFormat("mm:ss");
        endtime.setText(dinhdanggio.format(Songlist.mediaPlayer.getDuration()));
        songtimeline.setMax(Songlist.mediaPlayer.getDuration());
    }

    //Cập nhật thời gian
    private void UpdateTimer(){
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                SimpleDateFormat dinhdanggio = new SimpleDateFormat("mm:ss");
                starttime.setText(dinhdanggio.format(Songlist.mediaPlayer.getCurrentPosition()));
                // update progress
                songtimeline.setProgress(Songlist.mediaPlayer.getCurrentPosition());
                handler.postDelayed(this,400);
                int a = i - 1;
                //play random
                Random rd = new Random();
                int number = rd.nextInt(i - 1);
                Songlist.mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                    @Override
                    public void onCompletion(MediaPlayer mp) {

                        if (Songlist.mediaPlayer.isPlaying()){
                            Songlist.mediaPlayer.stop();
                        }
                        Songlist.mediaPlayer = MediaPlayer.create(SongScene.this, SongData.generateSongData().get(number).getFile());
                        Songlist.mediaPlayer.start();
                        txtTitle.setText(SongData.generateSongData().get(number).getTitle());
                        disc.setImageResource(SongData.generateSongData().get(number).getDisc());
                        animation = AnimationUtils.loadAnimation(SongScene.this, R.anim.disc);
                        disc.startAnimation(animation);
                    }
                });
                SetTimeTotal();
                UpdateTimer();
            }
        },100);
    };

    //Ánh xạ elements
    public void AnhXa(){
        txtTitle        = (TextView) findViewById(R.id.txtTitle);
        starttime       = (TextView) findViewById(R.id.starttime);
        endtime         = (TextView) findViewById(R.id.endtime);
        btnprev         = (ImageButton) findViewById(R.id.btnprev);
        btnnext         = (ImageButton) findViewById(R.id.btnnext);
        btnplay         = (ImageButton) findViewById(R.id.btnplay);
        btnback         = (ImageButton) findViewById(R.id.btnback);
        songtimeline    = (SeekBar) findViewById(R.id.songtimeline);
        disc            = (ImageView) findViewById(R.id.disc);
    }
}