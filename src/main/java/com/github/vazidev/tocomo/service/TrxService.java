package com.github.vazidev.tocomo.service;

import com.github.vazidev.tocomo.domain.Transactions;
import com.github.vazidev.tocomo.repository.TrxRepository;
import org.springframework.data.cassandra.repository.AllowFiltering;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

//@Service
public class TrxService {
    private final TrxRepository trxRepository;

    public TrxService(TrxRepository trxRepository) {
        this.trxRepository = trxRepository;
    }

    @AllowFiltering
    public Flux<Transactions> getAllTrx() { return trxRepository.findAll();}

    public Mono<Transactions> getTrx(String user_name) { return trxRepository.findById(Integer.parseInt(user_name)); }

    public Mono<Transactions> getTrx(int trx) { return trxRepository.findById(trx); } // get single Transaction by name or user_name

    public Mono<Transactions> createTrx(Transactions trx) { return trxRepository.save(trx); }


}
