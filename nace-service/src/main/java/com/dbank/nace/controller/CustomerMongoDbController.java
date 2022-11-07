package com.dbank.nace.controller;

import com.dbank.nace.entity.Customer;
import com.dbank.nace.service.MongoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/customers")
public class CustomerMongoDbController {

    @Autowired
    MongoService mongoService;

    @PostMapping("/")
    public Customer saveCustomrData(@RequestBody Customer customerInfo){
        mongoService.save(customerInfo);
        return customerInfo;
    }

    @GetMapping("/all")
    public List<Customer> getCustomerData(){
        return mongoService.getAllCustomers();
    }

/*    @GetMapping("/")
    public Customer getCustomerByFirstName(@PathVariable String firstname){
        return mongoService.getCustomerByName(firstname);
    }*/

    @GetMapping("/{id}")
    public Optional<Customer> getCustomerById(@PathVariable String id){
        return mongoService.getCustomerById(id);
    }
}
