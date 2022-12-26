package com.example.inventoryservice.inventoryCommands.controllers;

import com.example.commonapi.commands.CreateCategoryCommand;
import com.example.commonapi.commands.UpdateCategoryCommand;
import com.example.commonapi.dtos.CreateCategoryRequestDTO;
import com.example.commonapi.dtos.UpdateCategoryRequestDTO;
import lombok.AllArgsConstructor;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.eventsourcing.eventstore.EventStore;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Stream;

@RestController
@RequestMapping("/category/commands")
@AllArgsConstructor
@CrossOrigin("*")
public class CategoryCommandController {
    private final CommandGateway commandGateway;
    private final EventStore eventStore;

    @PostMapping("/create")
    public CompletableFuture<String> createCategory(@RequestBody CreateCategoryRequestDTO request) {
        return commandGateway.send(new CreateCategoryCommand(
                UUID.randomUUID().toString(),
                request.getNom(),
                request.getDescription()

        ));
    }

    @PutMapping("/update")
    public CompletableFuture<String> updateCategory(@RequestBody UpdateCategoryRequestDTO request) {
        return commandGateway.send(new UpdateCategoryCommand(
                request.getId(),
                request.getNom(),
                request.getDescription()
        ));
    }

    @GetMapping("/eventStore/{id}")
    public Stream getEventStore(@PathVariable String id) {
        return eventStore.readEvents(id).asStream();
    }





}
