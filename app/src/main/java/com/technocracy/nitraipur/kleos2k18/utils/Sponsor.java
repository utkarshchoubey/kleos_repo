package com.technocracy.nitraipur.kleos2k18.utils;

import java.net.URL;

public class Sponsor {
    String name;
    String description;
    URL imageUrl;
    public Sponsor(String name, String description ,URL imageUrl){}

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

    public URL getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(URL imageUrl) {
        this.imageUrl = imageUrl;
    }
}
