package com.deneme.resttest;

public class userModel
{
    int albumId;
    int id;
    String title;
    String url;
    String thumbnailUrl;

    public userModel (int albumId,int id,String thumbnailUrl, String title, String url){
        this.albumId = albumId;
        this.id = id;
        this.thumbnailUrl = thumbnailUrl;
        this.title = title;
        this.url = url;
    }

    public int getAlbumId() {
        return albumId;
    }

    public int getId() {
        return id;
    }

    public String getThumbnailUrl() {
        return thumbnailUrl;
    }

    public String getTitle() {
        return title;
    }

    public String getUrl() {
        return url;
    }

    public void setAlbumId(int albumId) {
        this.albumId = albumId;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setThumbnailUrl(String thumbnailUrl) {
            this.thumbnailUrl = thumbnailUrl;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
