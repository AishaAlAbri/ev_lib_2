package com.example.user.ev_lib_2;

/**
 * Created by Mk Computer on 15/03/2016.
 */
public class BookData {




    private int Id;
    private String name;
    private String description ;
    private int price;
    private int category;
    private String image;


    public BookData(String description, String name, int category, int price) {
        this.description = description;
        this.name = name;
        this.category = category;
        this.price = price;
    }

    public BookData() {
    }


    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getCategory() {
        return category;
    }

    public void setCategory(int category) {
        this.category = category;
    }


    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
