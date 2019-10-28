package com.example.huhaichang.learn3.seven.widget;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by huhaichang on 2019/8/14.
 */

public class BookZ implements Parcelable {
    private int id;
    private String author;
    private double price;
    private int pages;
    private String name;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getPages() {
        return pages;
    }

    public void setPages(int pages) {
        this.pages = pages;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public int describeContents() {
        return 0;
    }
        //写入要用到的变量
    @Override
    public void writeToParcel(Parcel dest, int flags) {
        //读写顺序一定要一样(这里是先id后name）
        dest.writeInt(id);
        dest.writeString(name);

    }
    //读的时候自动执行的
    public static final Parcelable.Creator<BookZ> CREATOR = new Parcelable.Creator<BookZ>(){

        @Override
        public BookZ createFromParcel(Parcel source) {
            BookZ bookZ =new BookZ();
            bookZ.id = source.readInt();
            bookZ.name =source.readString();
            return bookZ;
        }

        @Override
        public BookZ[] newArray(int size) {
            return new BookZ[size];
        }
    };
}
