package com.example.customerservice.customerCommand.controller;

import com.example.commonapi.commands.CreateCustomerCommand;
import com.example.commonapi.commands.UpdateCustomerCommand;
import com.example.commonapi.dtos.CreateCustomerRequestDTO;
import com.example.commonapi.dtos.UpdateCustomerRequestDTO;
import lombok.AllArgsConstructor;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.eventsourcing.eventstore.EventStore;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Stream;

@RestController
@RequestMapping("/customer/commands")
@AllArgsConstructor

public class CustomerCommandController {
    private CommandGateway commandGateway ;
    private EventStore eventStore ;

    @PostMapping("/createCustomer")
    public CompletableFuture<String> createCustomer(@RequestBody CreateCustomerRequestDTO request)
    {
        return  commandGateway.send(
                new CreateCustomerCommand(
                                UUID.randomUUID().toString(),
                                request.getNom(),
                                request.getAdresse(),
                                request.getEmail(),
                                request.getTelephone()
                ));
    }

    @PutMapping("/updateCustomer")
    public CompletableFuture<String> updateCustomer(@RequestBody UpdateCustomerRequestDTO request){
        return commandGateway.send(
                new UpdateCustomerCommand(
                        request.getId(),
                        request.getNom(),
                        request.getAdresse(),
                        request.getEmail(),
                        request.getTelephone()
                )
        ) ;
    }
    @GetMapping("/eventStore/{customerId}")
    public Stream getEventsForAccount(@PathVariable(value = "customerId") String customerId)
    {
        return eventStore.readEvents(customerId).asStream();
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleException(Exception e) {
        ResponseEntity<String> entity = new ResponseEntity<String>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        return entity;
    }


}
