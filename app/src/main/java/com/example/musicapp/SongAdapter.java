package com.example.musicapp;

import android.content.Context;
import android.provider.ContactsContract;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class SongAdapter extends BaseAdapter {
    private ArrayList<Song> song_list;
    private Context context;

    public SongAdapter(ArrayList<Song> song_list, Context context) {
        this.song_list = song_list;
        this.context = context;
    }


    @Override
    public int getCount() {
        return song_list.size();
    }

    @Override
    public Object getItem(int position) {
        return song_list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return song_list.get(position).getId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final MyView dataitem;
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        if(convertView == null){
            dataitem = new MyView();
            convertView = inflater.inflate(R.layout.songlist,null);
            dataitem.txtTitle       = convertView.findViewById(R.id.songtitle);
            dataitem.songimage      = convertView.findViewById(R.id.songimage);
            dataitem.singername     = convertView.findViewById(R.id.singername);
            convertView.setTag(dataitem);
        }
        else {
            dataitem = (MyView) convertView.getTag();
        }

        Picasso.get().load(song_list.get(position).getImage()).resize(50,50).centerCrop().into(dataitem.songimage);
        dataitem.txtTitle.setText(song_list.get(position).getTitle());
        dataitem.singername.setText("Ca sĩ: " + song_list.get(position).getSinger());
        return convertView;
    }


    public static class MyView{
        ImageView songimage;
        TextView txtTitle;
        TextView singername;
    }
}
