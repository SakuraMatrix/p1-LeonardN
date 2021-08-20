package com.github.vazidev.tocomo.service;

import com.github.vazidev.tocomo.domain.Customer;
import com.github.vazidev.tocomo.domain.Transactions;
import com.github.vazidev.tocomo.repository.CustomerRepository;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
@Service
public class CustomerService {

    private CustomerRepository customerRepository;

    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public Flux<Customer> getAllCust() {
        return customerRepository.getAllCust();
    }

    public Flux<Transactions> getAllTrx() { return customerRepository.getAllTrx();}

    public Mono<Customer> getCust(String Id) { return customerRepository.getCust(Integer.parseInt(Id)); } //get Single Customer by user_id

    public  Mono<Customer> getCust(int Id) { return customerRepository.getCust(Id);} //get single Customer by user_name/name

    public Mono<Transactions> getTrx(String Id) { return customerRepository.getTrx(Id); } // get single Transaction by name or user_name

    public Customer createCust(Customer customer) { return customerRepository.createCust(customer); }

    public Transactions createTrx(Transactions transactions) { return (Transactions) customerRepository.createTrx(transactions); }

    public Customer updateCust(Customer customer) {return customerRepository.updateCust(customer); }


}
