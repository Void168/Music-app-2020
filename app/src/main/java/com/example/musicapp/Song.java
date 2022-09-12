package com.example.musicapp;

public class Song {
    private int id;
    private String Title;
    private int File;
    private int disc;
    private String Singer;
    private String Album;
    private String Playlist;
    private String Image;

    public Song(int id, String title, int file, int disc, String singer, String album, String playlist, String image) {
        this.id = id;
        Title = title;
        File = file;
        this.disc = disc;
        Singer = singer;
        Album = album;
        Playlist = playlist;
        Image = image;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public int getFile() {
        return File;
    }

    public void setFile(int file) {
        File = file;
    }

    public int getDisc() {
        return disc;
    }

    public void setDisc(int disc) {
        this.disc = disc;
    }

    public String getSinger() {
        return Singer;
    }

    public void setSinger(String singer) {
        Singer = singer;
    }

    public String getAlbum() {
        return Album;
    }

    public void setAlbum(String album) {
        Album = album;
    }

    public String getPlaylist() {
        return Playlist;
    }

    public void setPlaylist(String playlist) {
        Playlist = playlist;
    }

    public String getImage() {
        return Image;
    }

    public void setImage(String image) {
        Image = image;
    }
}
