package com.example.inventoryservice.inventoryQuery.controller;


import com.example.commonapi.query.GetAllCategoriesQuery;
import com.example.commonapi.query.GetCatgoryById;
import com.example.inventoryservice.inventoryQuery.entities.Category;
import com.example.inventoryservice.inventoryQuery.repositories.CategoryRepository;
import lombok.AllArgsConstructor;
import org.axonframework.messaging.responsetypes.ResponseTypes;
import org.axonframework.queryhandling.QueryGateway;
import org.axonframework.queryhandling.QueryHandler;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/category/queries")
@CrossOrigin("*")
public class CategoryQueryController {
    private QueryGateway queryGateway;
    private CategoryRepository vehiculeRepository;

    @GetMapping("/allCategory")
    public List<Category> getAllVehicules() {
        return queryGateway.query(new GetAllCategoriesQuery(), ResponseTypes.multipleInstancesOf(Category.class)).join();
    }

    @QueryHandler
    public List<Category> on(GetAllCategoriesQuery query) {
        return vehiculeRepository.findAll();
    }

    @GetMapping("/getCategory/{id}")
    public Category getVehicule(@PathVariable String id) {
        return queryGateway.query(new GetCatgoryById(id),ResponseTypes.instanceOf(Category.class)).join();
    }

    @QueryHandler
    public Category on(GetCatgoryById query) {
        return vehiculeRepository.findById(query.getId()).orElse(null);
    }

}
