package com.lyn.entity;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

@Data
@Builder
public class single implements Serializable {
    private String singleId;
    private String name;
    private String music;
    private String source;
    private String musicId;

    public String getSingleId() {
        return singleId;
    }
    public void setSingleId(String singleId) {
        this.singleId = singleId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public String getMusic() {
        return music;
    }
    public void setMusic(String music) {
        this.music = music;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getMusicId() {
        return musicId;
    }

    public void setMusicId(String musicId) {
        this.musicId = musicId;
    }
}
