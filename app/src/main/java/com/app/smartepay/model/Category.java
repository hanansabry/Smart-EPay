package com.app.smartepay.model;

import java.io.Serializable;

public class Category implements Serializable {

    private String id;
    private String name;
    private String desc;
    private String imageUrl;

    public Category() {
    }

    public Category(String categoryName, String categoryDesc, String imageUri) {
        name = categoryName;
        desc = categoryDesc;
        imageUrl = imageUri;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}
