package com.lyn.entity;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

@Data
@Builder
public class action implements Serializable {
    private Integer actId;
    private String name;
    private String image;
    private String videoPath;

    public Integer getActId() {
        return actId;
    }
    public void setActId(Integer actId) {
        this.actId = actId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getVideoPath() {
        return videoPath;
    }
    public void setVideoPath(String videoPath) {
        this.videoPath = videoPath;
    }
}
