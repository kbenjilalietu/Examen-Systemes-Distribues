package com.example.commonapi.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import com.example.commonapi.enums.ProductEtat;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateProductRequestDTO {
    private String nom ;
    private double prix ;
    private  int qteStock  ;
    private ProductEtat etat ;
    private String category;

}
