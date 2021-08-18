package com.github.vazidev.tocomo.service;

import com.github.vazidev.tocomo.domain.Customer;
import com.github.vazidev.tocomo.domain.Transactions;
import com.github.vazidev.tocomo.repository.CustomerRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public class CustomerService {

    private CustomerRepository customerRepository;

    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public Flux<Customer> getAllCust() {
        return customerRepository.getAllCust();
    }

    public Flux<Transactions> getAllTrx() { return customerRepository.getAllTrx();}

    public Mono<Customer> getCust(String Id) { return customerRepository.getCust(Id); }

    public Customer createCust(Customer customer) { return customerRepository.createCust(customer); }

    public Transactions createTrx(Transactions transactions) { return (Transactions) customerRepository.createTrx(transactions); }

    public Customer updateCust(Customer customer) {return customerRepository.updateCust(customer); }


}
