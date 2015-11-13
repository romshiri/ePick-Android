package com.libify.epick.homePage;

import java.util.ArrayList;

public class PickItemViewModel {

    private String title;
    private ArrayList<String> imageUrls;

    public PickItemViewModel(String title, ArrayList<String> imageUrls) {
        this.title = title;
        this.imageUrls = imageUrls;
    }

    public String getTitle() {
        return title;
    }

    public ArrayList<String> getImageUrls() {
        return imageUrls;
    }
}
