package com.app.smartepay.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;

public class Item implements Parcelable, Serializable {

    private String id;
    private String name;
    private String desc;
    private double price;
    private String imageUrl;
    private String brand;
    private String category;

    public Item() {
    }

    public Item(String itemName, String itemDesc, double itemPrice, String imageUri, String brandName, String categoryName) {
        name = itemName;
        desc = itemDesc;
        price = itemPrice;
        imageUrl = imageUri;
        brand = brandName;
        category = categoryName;
    }

    protected Item(Parcel in) {
        id = in.readString();
        name = in.readString();
        desc = in.readString();
        price = in.readDouble();
        imageUrl = in.readString();
        brand = in.readString();
        category = in.readString();
    }

    public static final Creator<Item> CREATOR = new Creator<Item>() {
        @Override
        public Item createFromParcel(Parcel in) {
            return new Item(in);
        }

        @Override
        public Item[] newArray(int size) {
            return new Item[size];
        }
    };

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

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(id);
        dest.writeString(name);
        dest.writeString(desc);
        dest.writeDouble(price);
        dest.writeString(imageUrl);
        dest.writeString(brand);
        dest.writeString(category);
    }
}
