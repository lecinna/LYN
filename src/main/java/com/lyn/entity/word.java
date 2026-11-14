package com.lyn.entity;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

@Data
@Builder
public class word implements Serializable {
    private Integer wordId;
    private String saying;

    public Integer getWordId() {
        return wordId;
    }
    public void setWordId(Integer wordId) {
        this.wordId = wordId;
    }

    public String getSaying() {
        return saying;
    }

    public void setSaying(String saying) {
        this.saying = saying;
    }
}
