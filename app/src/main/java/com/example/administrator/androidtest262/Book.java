package com.example.administrator.androidtest262;

import org.litepal.crud.DataSupport;

import java.io.Serializable;

public class Book extends DataSupport implements Serializable {
    private String name;
    private String author;
    private double price;
    private int pages;
    private String press;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public String getPress() {
        return press;
    }

    public void setPress(String press) {
        this.press = press;
    }

    @Override
    public String toString() {
        StringBuilder builder=new StringBuilder();
        builder.append("[书名："+name).append("]");
        builder.append("[作者："+author).append("]");
        builder.append("[价格："+price).append("]");
        builder.append("[页数："+pages).append("]");
        builder.append("[出版社："+press).append("]");

        return builder.toString();
    }
}
