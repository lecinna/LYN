package com.lyn.entity;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

@Data
@Builder
public class endorsement implements Serializable {
    private Integer endorseId;
    private String name;
    private String time;
    private String image;

    public Integer getEndorseId() {
        return endorseId;
    }
    public void setEndorseId(Integer endorseId) {
        this.endorseId = endorseId;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
