package com.github.vazidev.tocomo.service;

import com.github.vazidev.tocomo.domain.Customer;
import com.github.vazidev.tocomo.domain.Transactions;
import com.github.vazidev.tocomo.repository.CustomerRepository;
import com.github.vazidev.tocomo.repository.TrxRepository;
import org.springframework.data.cassandra.repository.AllowFiltering;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
@Service
public class CustomerService {

    public CustomerRepository customerRepository;
    public TrxRepository trxRepository;

    @AllowFiltering
    public Flux<Customer> getAllCust() { return customerRepository.findAll(); }

    public  Mono<Customer> getCust( String cust) { return customerRepository.findById(Integer.parseInt(cust));} //get single Customer by user_name/name

    public  Mono<Customer> getCust( int cust) { return customerRepository.findById(cust);} //get single Customer by user_name/name

    public Mono<Customer> createCust(Customer cust) { return customerRepository.save(cust); }

    public Flux<Transactions> getAllTrx() { return trxRepository.findAll();}

    public Mono<Transactions> getTrx(String user_name) { return trxRepository.findById(Integer.parseInt(user_name)); }

    public Mono<Transactions> getTrx(int trx) { return trxRepository.findById(trx); } // get single Transaction by name or user_name

    public Mono<Transactions> createTrx(Transactions trx) { return trxRepository.save(trx); }


}
