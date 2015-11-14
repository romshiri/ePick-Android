package com.libify.epick.homePage;

import com.libify.epick.models.Pick;
import com.libify.epick.models.Product;

import java.util.ArrayList;

public class PickViewModelMapper {
    public PickItemViewModel map(Pick pick){
        String title = pick.pickTitle;
        String id  = pick.pickId;
        ArrayList<String> images = new ArrayList<>();

        for (Product p : pick.products) {
            images.add(p.imageUrl);
        }

        int size = images.size();

        if(images.size() < 4){
            for(int i = 0; i <= 4 - size; i++){
                images.add("error");
            }
        }

        return new PickItemViewModel(title,images,id);

    }
}
