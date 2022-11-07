package com.dbank.nace.service;

import com.dbank.nace.entity.Customer;
import com.dbank.nace.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MongoService {

    @Autowired
    CustomerRepository customerRepository;

    public Customer save(Customer customer){
        return customerRepository.save(customer);
    }

    public Customer getCustomerByName(String firstname){
        return customerRepository.findByFirstName(firstname);
    }

    public Optional<Customer> getCustomerById(String id){
        return customerRepository.findById(id);
    }

    public List<Customer> getAllCustomers(){
        return customerRepository.findAll();
    }
}
