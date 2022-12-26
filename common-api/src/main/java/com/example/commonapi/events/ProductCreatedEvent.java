package com.example.commonapi.events;

import lombok.Getter;
import com.example.commonapi.enums.ProductEtat;

public class ProductCreatedEvent extends BaseEvent<String> {
    @Getter private String nom ;
    @Getter private double prix ;
    @Getter private  int qteStock  ;
    @Getter private ProductEtat etat ;
    @Getter private String category;


    public ProductCreatedEvent(String id, String nom, double prix, int qteStock, ProductEtat etat, String category) {
        super(id);
        this.nom = nom;
        this.prix = prix;
        this.qteStock = qteStock;
        this.etat = etat;
        this.category = category;
    }
}
