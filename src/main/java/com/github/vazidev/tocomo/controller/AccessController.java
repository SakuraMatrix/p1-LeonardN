package com.github.vazidev.tocomo.controller;

import com.github.vazidev.tocomo.service.TrxService;
import org.springframework.web.bind.annotation.*;
import com.github.vazidev.tocomo.service.TocomoServices;
import com.github.vazidev.tocomo.domain.Customer;
import com.github.vazidev.tocomo.domain.Transactions;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

//@RestController
@RequestMapping(value ="/")
public class AccessController {
    private final TocomoServices tocomoServices;
    private final TrxService trxService;

    public AccessController(TocomoServices tocomoServices, TrxService trxService) {
        this.tocomoServices = tocomoServices;
        this.trxService = trxService;
    }


    @GetMapping("/cust")
    public Flux<Customer> getAllCust(){
        return tocomoServices.getAllCust();
    }

    @GetMapping("/trx")
    public Flux<Transactions> getAllTrx(){
        return trxService.getAllTrx();
    }

    @GetMapping("/cust/{name}")
    public Mono<Customer> getCust(@PathVariable("name")  String name) {
        return tocomoServices.getCust(name);
    }

    @GetMapping("/cust/{id}")
    public Mono<Customer> getCust(@PathVariable("id")  int id) {
        return tocomoServices.getCust(id);
    }

    @GetMapping("/trx/{id}")
        public Mono<Transactions> getTrx(@PathVariable("id") int id ){
            return trxService.getTrx(id);
    }

    @GetMapping("/trx/{id}")
    public Mono<Transactions> getTrx(@PathVariable("id") String user ){
        return trxService.getTrx(user);
    }

    @PostMapping("/cust/new/{param}")
    public Mono<Customer> createCust(@RequestBody Customer customer){
            return tocomoServices.createCust(customer);
    }

    @PostMapping("/trx/new/{param}")
    public Mono<Transactions> create(@RequestBody Transactions transactions) {
        return trxService.createTrx(transactions);
    }


}
