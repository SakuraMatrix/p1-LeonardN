package com.github.vazidev.tocomo.service;

import com.github.vazidev.tocomo.domain.Customer;
import com.github.vazidev.tocomo.domain.Transactions;
import com.github.vazidev.tocomo.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.cassandra.repository.AllowFiltering;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

//@Service
public class TocomoServices {

    //@Autowired
    private final CustomerRepository customerRepository ; //points EndPoint -> Controller -> Service -> Repository  -> Domain

    public TocomoServices(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }


    @AllowFiltering
    public Flux<Customer> getAllCust() { return customerRepository.findAll(); }

    public  Mono<Customer> getCust( String cust) { return customerRepository.findById(Integer.parseInt(cust));} //get single Customer by user_name/name

    public  Mono<Customer> getCust( int cust) { return customerRepository.findById(cust);} //get single Customer by user_name/name

    public Mono<Customer> createCust(Customer cust) { return customerRepository.save(cust); }




}
