package com.app.smartepay.model;

import java.io.Serializable;
import java.util.List;

public class Brand implements Serializable {

    private String id;
    private String name;
    private String desc;
    private String imageUrl;
    private List<Item> items;

    public Brand() {
    }

    public Brand(String brandName, String brandDesc, String imageUri) {
        name = brandName;
        desc = brandDesc;
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

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }
}
