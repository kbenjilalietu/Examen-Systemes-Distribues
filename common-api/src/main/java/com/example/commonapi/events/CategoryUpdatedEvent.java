package com.example.commonapi.events;

import lombok.Getter;

public class CategoryUpdatedEvent extends BaseEvent<String> {
    @Getter private String nom ;
    @Getter  private String description ;

    public CategoryUpdatedEvent(String id, String nom, String description) {
        super(id);
        this.nom = nom;
        this.description = description;
    }
}
