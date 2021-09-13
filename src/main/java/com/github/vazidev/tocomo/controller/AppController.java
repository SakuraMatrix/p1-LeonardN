package com.github.vazidev.tocomo.controller;

import com.github.vazidev.tocomo.service.TocomoServices;
import com.github.vazidev.tocomo.domain.Transactions;
import com.github.vazidev.tocomo.domain.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.logging.Logger;

@RestController
@RequestMapping("/")
public class AppController {
    private static final Logger log = Logger.getLogger("tocomo");

    @Autowired
    private TocomoServices tocomoServices;

    public AppController(TocomoServices tocomoServices){
        this.tocomoServices = tocomoServices;
    }

    @GetMapping("/cust")
    public Flux<Customer> getAllCust(){
        return tocomoServices.getAllCust();
    }

    @GetMapping("/trx")
    public Flux<Transactions> getAllTrx(){
        return tocomoServices.getAllTrx();
    }

    @GetMapping("/cust/{name}")
    public Mono<Customer> getCust(@PathVariable("name")  String name) {
        return tocomoServices.getCust(name);
    }

    @GetMapping("/cust/{id}")
    public Mono<Customer> getCust(@PathVariable("id")  int id) {
        return tocomoServices.getCust(String.valueOf(id));
    }

    @GetMapping("/trx/{id}")
    public Mono<Transactions> getTrx(@PathVariable("id") int id ){
        return tocomoServices.getTrx(id);
    }

    //@GetMapping("/trx/{id}")
    //public Mono<Transactions> getTrx(@PathVariable("id") String user ){
    //return customerService.getTrx(user);
    //

    @PostMapping("/cust/new/{param}")
    public Customer createCust(@RequestBody Customer customer){
        return tocomoServices.createCust(customer);
    }

    @PostMapping("/trx/new/{param}")
    public Transactions create(@RequestBody Transactions transactions) {
        return tocomoServices.createTrx(transactions);
    }

}
