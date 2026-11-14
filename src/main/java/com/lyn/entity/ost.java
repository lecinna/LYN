package com.lyn.entity;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

@Data
@Builder
public class ost implements Serializable {
    private Integer oId;
    private String musicId;
    private String time;
    private String opera;
    private String song;
    private String musicPath;
    private String source;

    public Integer getOId() {
        return oId;
    }

    public void setOId(Integer oId) {
        this.oId = oId;
    }


    public String getMusicId() {
        return musicId;
    }

    public void setMusicId(String musicId) {
        this.musicId = musicId;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String date) {
        this.time = time;
    }

    public String getOpera() {
        return opera;
    }

    public void setOpera(String opera) {
        this.opera = opera;
    }

    public String getSong() {
        return song;
    }

    public void setSong(String song) {
        this.song = song;
    }

    public String getMusicPath() {
        return musicPath;
    }
    public void setMusicPath(String musicPath) {
        this.musicPath = musicPath;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }
}
