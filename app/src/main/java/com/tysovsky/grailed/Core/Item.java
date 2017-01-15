package com.tysovsky.grailed.Core;

import com.google.gson.Gson;

/**
 * Created by tysovsky-mac on 1/14/17.
 */

public class Item {
    public int id;
    public String title;
    public String designer;
    public int price;
    public String description;
    public String size;
    public String category;
    public String strata;
    public String cover_photo_url;
    public Boolean saved;

    public Item(int id, String title, String designer, int price, String description, String size, String category, String strata, String cover_photo_url, Boolean saved){
        this.id = id;
        this.title = title;
        this.designer = designer;
        this.price = price;
        this.description = description;
        this.size = size;
        this.category = category;
        this.strata = strata;
        this.cover_photo_url = cover_photo_url;
        this.saved = saved;
    }




    public static Item fromJSON(String itemJson){
        Gson gson = new Gson();
        return gson.fromJson(itemJson, Item.class);
    }
}
