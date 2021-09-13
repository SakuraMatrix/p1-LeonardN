package com.github.vazidev.tocomo.repository;

import com.github.vazidev.tocomo.domain.Transactions;
import org.springframework.data.cassandra.repository.ReactiveCassandraRepository;
import org.springframework.stereotype.Repository;

@Repository
//makes the callas to the Transaction Database
public interface TrxRepository  extends ReactiveCassandraRepository<Transactions,Integer>{

}
