package com.example.musicapp;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Bitmap;
import android.media.MediaPlayer;
import android.media.ThumbnailUtils;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.MediaController;
import android.widget.SearchView;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.VideoView;

import com.bumptech.glide.Glide;
import com.synnapps.carouselview.CarouselView;
import com.synnapps.carouselview.ImageClickListener;
import com.synnapps.carouselview.ImageListener;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    SearchView search;
    CarouselView carousel;
    Button btnsong, btnsinger, btnplaylist, btnalbum;
    ImageButton btnbackscene, mvplay;
    VideoView mv;
    ImageView mvthumbnail;

    private int [] slider = new int[] {
        R.drawable.nangtho, R.drawable.diquamuaha,R.drawable.buocquamuacodon
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        AnhXa();

        carousel.setPageCount(slider.length);
        carousel.setImageListener((position, imageView) -> imageView.setImageResource(slider[position]));
        carousel.setImageClickListener(new ImageClickListener() {
            @Override
            public void onClick(int position) {

           }
        });

        //Sự kiện đi đến màn hình Danh sach nhac
        btnsong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Songlist.class);
                startActivity(intent);
            }
        });

        //Sự kiện đi đến màn hình Ca sĩ
        btnsinger.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Singer.class);
                startActivity(intent);
            }
        });


        mvplay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mvplay.setVisibility(View.INVISIBLE);
                mvthumbnail.setVisibility(View.GONE);
                Uri uri = Uri.parse("https://c4-ex-swe.nixcdn.com/PreNCT18/NangTho-HoangDung-6413514.mp4");
                mv.setVideoURI(uri);
                mv.setMediaController(new MediaController(MainActivity.this));
                mv.start();
            }
        });
    }

    public void AnhXa(){
        carousel    = (CarouselView) findViewById(R.id.carousel);
        search      = (SearchView) findViewById(R.id.Search);
        btnsong     = (Button) findViewById(R.id.btnsong);
        btnsinger   = (Button) findViewById(R.id.buttonsinger);
        mv          = (VideoView) findViewById(R.id.mv);
        mvplay      = (ImageButton) findViewById(R.id.mvplay);
        mvthumbnail = (ImageView) findViewById(R.id.mvthumbnail);
    }
}