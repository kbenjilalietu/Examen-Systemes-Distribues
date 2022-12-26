package com.example.commonapi.commands;

import lombok.Getter;

public class CreateCategoryCommand extends  BaseCommand<String>{

   @Getter private String nom ;
   @Getter private String description ;

    public CreateCategoryCommand(String id, String nom, String description) {
        super(id);
        this.nom = nom;
        this.description = description;
    }
}
