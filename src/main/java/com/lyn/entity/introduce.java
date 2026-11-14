package com.lyn.entity;

import lombok.Builder;
import lombok.Data;
import java.io.Serializable;

@Data

public class introduce implements Serializable {
    private Integer introId;
    private String incident;
    private String image;
    private String time;

    public Integer getIntroId() {
        return introId;
    }

    public void setIntroId(Integer introId) {
        this.introId = introId;
    }

    public String getIncident() {
        return incident;
    }

    public void setIncident(String incident) {
        this.incident = incident;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
