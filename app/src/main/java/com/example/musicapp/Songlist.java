package com.example.musicapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.io.BufferedOutputStream;

public class Songlist extends AppCompatActivity {
    SearchView Search;
    Button btnsong, btnsinger, btnplaylist, btnalbum;
    ImageButton btnbackmain, miniprev, miniplaypause, mininext, btnbackscene;
    ListView listviewsonglist;
    TextView txtTitle;
    SeekBar songtimeLine;
//    public static TextView miniTitle;
//    public static ImageView minisceneimage;
//    public static ConstraintLayout miniscene;
    public static MediaPlayer mediaPlayer;
    NotificationManager notificationManager;
    int pos;
    String str_pos;
    Animation animation;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_songlist);
        AnhXa();

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            createChannel();
        }

        SongAdapter adapter = new SongAdapter(SongData.generateSongData(), getApplicationContext());

        AdapterView.OnItemClickListener onitemclick = new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                //Mini scene
//                    minisceneimage.setImageResource(SongData.generateSongData().get(position).getDisc());
//                    miniTitle.setText(SongData.generateSongData().get(position).getTitle());
//                    animation = AnimationUtils.loadAnimation(Songlist.this, R.anim.slide_title);
//                    miniTitle.startAnimation(animation);

                Intent intent = new Intent(getBaseContext(), SongScene.class);
                // lấy vị trí bài hát khi click
                str_pos = String.valueOf(position);
                intent.putExtra("myKey", str_pos);
                intent.putExtra("id", listviewsonglist.getAdapter().getItemId(position));
                intent.putExtra("songname",SongData.generateSongData().get(position).getTitle());
                intent.putExtra("songdisc",SongData.generateSongData().get(position).getDisc());
                startActivity(intent);

                //Neu co bai hat dang phat thi dung bai dang phat de phat bai tiep theo con khong thi phat binh thuong
                if(mediaPlayer != null){
                    mediaPlayer.stop();
                    mediaPlayer.release();
                    mediaPlayer = MediaPlayer.create(Songlist.this, SongData.generateSongData().get(position).getFile());
                    mediaPlayer.start();
                }else {
                    mediaPlayer = MediaPlayer.create(Songlist.this, SongData.generateSongData().get(position).getFile());
                    mediaPlayer.start();
                }
                CreateNotification.createNotification(Songlist.this, SongData.generateSongData(), pos, SongData.generateSongData().size() - 1);
            }
        };
//        miniscene.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(Songlist.this, SongScene.class);
//                intent.putExtra("myKey", str_pos);
//                intent.putExtra("id", listviewsonglist.getAdapter().getItemId(pos));
//                intent.putExtra("songname",SongData.generateSongData().get(pos).getTitle());
//                intent.putExtra("songdisc",SongData.generateSongData().get(pos).getDisc());
//                startActivity(intent);
//            }
//        });
        //Xóa bài hát
        AdapterView.OnItemLongClickListener onItemLongClickListener = new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                AlertDialog.Builder alertDialog = new AlertDialog.Builder(Songlist.this);
                alertDialog.setTitle("Bạn có chắc?");
                alertDialog.setMessage("Bạn có muốn xóa bài hát này không?");
                alertDialog.setPositiveButton("Có", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        final int positionToRemove = position;
                        SongData.generateSongData().remove(positionToRemove);

                        Toast.makeText(Songlist.this,"Đã xóa " + SongData.generateSongData().get(position).getTitle(),Toast.LENGTH_SHORT).show();
                        adapter.notifyDataSetChanged();
                    }
                });
                alertDialog.setNegativeButton("Không", null);
                alertDialog.show();
                return false;
            }
        };
        listviewsonglist.setAdapter(adapter);
        listviewsonglist.setOnItemClickListener(onitemclick);

        //Searchview
        Search.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {

                return false;
            }
        });
        // Sự kiện back về màn hình chính
        btnbackmain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Songlist.this, MainActivity.class);
                startActivity(intent);
            }
        });


        //Sự kiện đi đến màn hình Ca sĩ
        btnsinger.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Songlist.this, Singer.class);
                startActivity(intent);
            }
        });

        btnbackscene.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Songlist.this, SongScene.class);
                intent.putExtra("id", listviewsonglist.getAdapter().getItemId(pos));
                intent.putExtra("songname",SongData.generateSongData().get(pos).getTitle());
                intent.putExtra("songdisc",SongData.generateSongData().get(pos).getDisc());
                startActivity(intent);
            }
        });
    }

    private void createChannel(){
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            NotificationChannel channel = new NotificationChannel(CreateNotification.CHANNEL_ID,
                    "MusicNoti",NotificationManager.IMPORTANCE_LOW);
            notificationManager = getSystemService(NotificationManager.class);
            if (notificationManager != null)
                notificationManager.createNotificationChannel(channel);
        }
    }

    public void AnhXa(){
        Search              = (SearchView) findViewById(R.id.Search);
        btnsong             = (Button) findViewById(R.id.btnsong);
        btnbackmain         = (ImageButton) findViewById(R.id.btnbackmain);
        btnsinger           = (Button) findViewById(R.id.buttonsinger);
        listviewsonglist    = (ListView) findViewById(R.id.listviewsonglist);
        txtTitle            = (TextView) findViewById(R.id.txtTitle);
        songtimeLine        = (SeekBar) findViewById(R.id.songtimeline);
        btnbackscene        = (ImageButton) findViewById(R.id.btnbackscene);
//        miniprev            = (ImageButton) findViewById(R.id.miniprev);
//        miniplaypause       = (ImageButton) findViewById(R.id.miniplaypause);
//        mininext            = (ImageButton) findViewById(R.id.mininext);
//        miniTitle           = (TextView) findViewById(R.id.miniTitle);
//        minisceneimage      = (ImageView) findViewById(R.id.minisceneimage);
//        miniscene           = (ConstraintLayout) findViewById(R.id.miniscene);
    }
}