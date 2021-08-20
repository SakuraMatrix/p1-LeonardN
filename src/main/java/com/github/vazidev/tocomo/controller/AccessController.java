package com.github.vazidev.tocomo.controller;

import org.springframework.web.bind.annotation.*;
import com.github.vazidev.tocomo.service.CustomerService;
import com.github.vazidev.tocomo.domain.Customer;
import com.github.vazidev.tocomo.domain.Transactions;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping(value ="/")
public class AccessController {
    private CustomerService customerService;

    public AccessController (CustomerService customerService){
        this.customerService = customerService;
    }

    @GetMapping("/cust")
    public Flux<Customer> getAllCust(){
        return customerService.getAllCust();
    }

    @GetMapping("/trx")
    public Flux<Transactions> getAllTrx(){
        return customerService.getAllTrx();
    }

    @GetMapping("/cust/{name}")
    public Mono<Customer> getCust(@PathVariable("name")  String name) {
        return customerService.getCust(name);
    }

    @GetMapping("/cust/{id}")
    public Mono<Customer> getCust(@PathVariable("id")  int id) {
        return customerService.getCust(id);
    }

    @GetMapping("/trx/{id}")
        public Mono<Transactions> getTrx(@PathVariable("id") int id ){
            return customerService.getTrx(id);
    }

   /** @GetMapping("/trx/{id}")
    public Mono<Transactions> getTrx(@PathVariable("id") String user ){
        return customerService.getTrx(user);
    }**/

    @PostMapping("/cust/new/{param}")
    public Mono<Customer> createCust(@RequestBody Customer customer){
            return customerService.createCust(customer);
    }

    @PostMapping("/trx/new/{param}")
    public Mono<Transactions> create(@RequestBody Transactions transactions) {
        return customerService.createTrx(transactions);
    }


}
