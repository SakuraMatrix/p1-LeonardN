package com.github.vazidev.tocomo.repository;


import com.github.vazidev.tocomo.domain.Customer;
import com.github.vazidev.tocomo.domain.Transactions;
import org.springframework.data.cassandra.repository.ReactiveCassandraRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

@Repository
// makes call to the Transaction database
public interface TrxRepository extends ReactiveCassandraRepository<Transactions, Integer> {

}