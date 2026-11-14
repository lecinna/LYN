package com.lyn.entity;

import org.springframework.format.annotation.DateTimeFormat;
import java.util.Date;

public class concert {
    private Integer id;
    private String title;
    private String city;
    private String venue;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date date;
    private String description;
    private String image;
    private Integer status;

    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getCity() { return city; }
    public void setCity(String city) { this.city = city; }

    public String getVenue() { return venue; }
    public void setVenue(String venue) { this.venue = venue; }

    public Date getDate() { return date; }
    public void setDate(Date date) { this.date = date; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public String getImage() { return image; }
    public void setImage(String image) { this.image = image; }  // 👈 改成一致

    public Integer getStatus() { return status; }
    public void setStatus(Integer status) { this.status = status; }
}