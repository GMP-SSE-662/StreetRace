package com.frijolie.streetrace.model.cards;

import java.util.HashMap;
import java.util.Map;
import javafx.scene.image.Image;

public class CardImages {

    public Map<String,Image> cards;
    public final String IMAGE_LOCATION = "/images/cards/";
    public final String IMAGE_SUFFIX = ".png";

    public CardImages() {
        cards = new HashMap<>();
    }

    public Image getCardImage(Card card) {
        Image image = cards.get(card);
        String name = card.getType().getName();
        if (image == null) {
            image = new Image(CardImages.class.getClassLoader().getResourceAsStream(IMAGE_LOCATION + name + IMAGE_SUFFIX));
            cards.put(name, image);
        }
        return image;
    }

}