package com.lyn.entity;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class achievement {
    private Integer achieveId;
    private String incident;
    private String endorse;
    private String image;

    public Integer getAchieveId() {
        return achieveId;
    }

    public void setAchieveId(Integer achieveId) {
        this.achieveId = achieveId;
    }

    public String getIncident() {
        return incident;
    }

    public void setIncident(String incident) {
        this.incident = incident;
    }

    public String getEndorse() {
        return endorse;
    }

    public void setEndorse(String endorse) {
        this.endorse = endorse;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
