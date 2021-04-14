package com.app.smartepay.model;

import android.os.Parcel;
import android.os.Parcelable;

public class InvoiceItem implements Parcelable {

    private Item item;
    private int quantity;
    private double totalPrice;

    public InvoiceItem() {
    }

    protected InvoiceItem(Parcel in) {
        quantity = in.readInt();
        totalPrice = in.readDouble();
        item = (Item) in.readSerializable();
    }

    public static final Creator<InvoiceItem> CREATOR = new Creator<InvoiceItem>() {
        @Override
        public InvoiceItem createFromParcel(Parcel in) {
            return new InvoiceItem(in);
        }

        @Override
        public InvoiceItem[] newArray(int size) {
            return new InvoiceItem[size];
        }
    };

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(quantity);
        dest.writeDouble(totalPrice);
        dest.writeSerializable(item);
    }
}
