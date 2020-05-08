package com.AndoidApp.ProductsApplication;

public class UploadImage {
    private String name, ImageUrl;

    public UploadImage(String name, String imageUrl) {
        if(name.trim().equals(""))name="fara nume";
        this.name = name;
        ImageUrl = imageUrl;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImageUrl() {
        return ImageUrl;
    }

    public void setImageUrl(String imageUrl) {
        ImageUrl = imageUrl;
    }
}
