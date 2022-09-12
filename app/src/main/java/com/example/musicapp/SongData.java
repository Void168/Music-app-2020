package com.example.musicapp;

import java.util.ArrayList;

public class SongData {
    public static ArrayList<Song> generateSongData(){
        ArrayList<Song> songs = new ArrayList<>();
        songs.add(new Song(1, "Nàng thơ",R.raw.nang_tho, R.drawable.nangthodisc,"Hoàng Dũng", "Nàng thơ", "","https://avatar-ex-swe.nixcdn.com/song/2020/07/31/c/5/8/9/1596188259603_500.jpg"));
        songs.add(new Song(2,"Đi qua mùa hạ",R.raw.di_qua_mua_ha,R.drawable.diquamuahadisc,"Thái Đinh","Đi qua mùa hạ (Single)","","https://avatar-ex-swe.nixcdn.com/song/2019/07/19/3/8/8/f/1563500239222_500.jpg"));
        songs.add(new Song(3,"Có em bên đời bỗng vui",R.raw.co_em_ben_doi_bong_vui,R.drawable.coembendoibongvui,"Chillies","Chillies","","https://avatar-ex-swe.nixcdn.com/song/2020/02/07/2/0/7/2/1581043804283_500.jpg"));
        songs.add(new Song(4,"11h11",R.raw.thaidinh_namkun_11h11, R.drawable.muoimotgiomuoimot, "Thái Đinh & Nam Kun","11h11","","https://photo-resize-zmp3.zadn.vn/w320_r1x1_jpeg/cover/a/1/d/5/a1d53f76aa3f3fdbdbbf8198ad30b1dd.jpg"));
        songs.add(new Song(5,"Bước qua mùa cô đơn",R.raw.buocquamuacodon,R.drawable.buocquamuacodondisc,"Vũ.","Bước qua mùa cô đơn - Single","","https://avatar-ex-swe.nixcdn.com/song/2020/12/11/4/0/f/e/1607643612541_500.jpg"));
        songs.add(new Song(6,"Mascara",R.raw.mascara,R.drawable.mascara,"Chillies","Chillies","","https://avatar-ex-swe.nixcdn.com/song/2019/12/25/6/3/c/6/1577268566289_500.jpg"));
        songs.add(new Song(7,"Tinh Yêu Xanh Lá",R.raw.tinhyeuxanhla,R.drawable.tinhyeuxanhla,"Thịnh Suy","Tình yêu xanh lá - single","","https://avatar-ex-swe.nixcdn.com/song/2020/11/16/a/9/3/7/1605518601946_500.jpg"));
        songs.add(new Song(8,"Cho tôi đi theo",R.raw.chotoiditheo,R.drawable.chotoiditheo,"Ngọt","Ngọt","","https://photo-resize-zmp3.zadn.vn/w240_r1x1_jpeg/covers/9/e/9e0f8784ffebf6865c83c5e526274f31_1465465806.jpg"));
        songs.add(new Song(9,"Lần cuối",R.raw.lancuoi,R.drawable.lancuoi,"Ngọt","3","","https://i1.sndcdn.com/artworks-000605743393-0030j2-t500x500.jpg"));
        songs.add(new Song(10,"Em dạo này",R.raw.emdaonay,R.drawable.emdaonay,"Ngọt","Ngbthg","","https://s.mxmcdn.net/images-storage/albums4/3/8/6/4/5/6/39654683_350_350.jpg"));
        songs.add(new Song(11,"Lạ Lùng",R.raw.lalung,R.drawable.lalung,"Vũ.","Xin phép (được) cô đơn","","https://avatar-ex-swe.nixcdn.com/song/2018/01/26/1/8/9/0/1516930244148_500.jpg"));
        return songs;
    }

    public static Song getSongFromId (int id){
        ArrayList<Song> phs = generateSongData();
        for (int i = 0; i<phs.size(); i++)
            if (phs.get(i).getId() == id)
                return phs.get(i);
        return null;
    }
}
//https://firebasestorage.googleapis.com/v0/b/music-storage-64315.appspot.com/o/nang_tho.mp3?alt=media&token=b4c39e0a-1f9d-4adb-91e1-ddf8594ccaa2
//https://firebasestorage.googleapis.com/v0/b/music-storage-64315.appspot.com/o/%C4%90i%20Qua%20M%C3%B9a%20H%E1%BA%A1%20-%20Th%C3%A1i%20%C4%90inh.mp3?alt=media&token=d8a7506d-f903-4f1c-8c01-bd40076caef5