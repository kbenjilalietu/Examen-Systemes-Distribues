package com.example.commonapi.commands;

import lombok.Getter;

public class UpdateCategoryCommand extends  BaseCommand<String>{

   @Getter private String nom ;
   @Getter private String description ;

    public UpdateCategoryCommand(String id, String nom, String description) {
        super(id);
        this.nom = nom;
        this.description = description;
    }
}
