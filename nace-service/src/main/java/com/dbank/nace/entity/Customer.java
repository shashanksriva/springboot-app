package com.dbank.nace.entity;

import lombok.*;

import org.springframework.data.mongodb.core.mapping.Document;
import javax.persistence.Id;

@Document
@AllArgsConstructor
@NoArgsConstructor
public class Customer {

    @Id
    public String id;

    public String firstName;
    public String lastName;

    public Customer(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    @Override
    public String toString() {
        return String.format(
                "Customer[id=%s, firstName='%s', lastName='%s']",
                id, firstName, lastName);
    }
}
