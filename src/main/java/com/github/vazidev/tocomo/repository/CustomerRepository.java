package com.github.vazidev.tocomo.repository;

import com.datastax.oss.driver.api.core.CqlSession;
import com.datastax.oss.driver.api.core.cql.SimpleStatement;
import com.github.vazidev.tocomo.domain.Customer;
import com.github.vazidev.tocomo.domain.Transactions;
import org.springframework.data.cassandra.repository.ReactiveCassandraRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.UUID;

@Repository
// makes call to the Customers database
public interface CustomerRepository extends ReactiveCassandraRepository<Customer, Integer> {

}
/**All the statements belwo replace by Spring Cassandra with the in build classes
 public class CustomerRepository {
    private CqlSession session;

        Customer cust = new Customer();
        Transactions trx = new Transactions();

    public CustomerRepository(CqlSession session) {
                    this.session = session;
                }

    public Flux<Customer> getAllCust() {
        return Flux.from(session.executeReactive("SELECT * FROM tocomo.customers;"))
                .map(row -> cust.customerQuery(row.getString("name"), row.getString("user_name")));
    }

    public Flux<Transactions> getAllTrx() {
        return Flux.from(session.executeReactive("SELECT  user_name, client_name, amount, trx_type  FROM tocomo.transactions;"))
                .map(row -> trx.trxQuery(row.getString("user_name"), row.getString("client_name"), row.getDouble("amount"),   row.getString("trx_type")));
    }

    public Mono<Customer> getCust(String id) {
        return Mono.from(session.executeReactive("SELECT * FROM tocomo.customers WHERE user_name = ?  OR name = ? ;" ))
                .map(row -> cust.getCustomer(row.getString("user_id"), row.getString("name"), row.getString("user_name")));
    }

    public Mono<Customer> getCust(Integer id) {
        return Mono.from(session.executeReactive("SELECT * FROM tocomo.customers WHERE user_name = ?  OR name = ? ;" ))
                .map(row -> cust.getCustomer(row.getString("user_id"), row.getString("name"), row.getString("user_name")));
    }



    public Customer createCust(Customer customer){
        SimpleStatement statement =
                SimpleStatement.builder( "INSERT INTO tocomo.customers (user_name, name, user_id, trx_id) VALUES (?,?) IF not EXISTS;")
                        .addPositionalValues(customer.getUserName(), customer.getName(), customer.getUserId(), customer.getTrxId())
                        .build();
        Flux.from(session.executeReactive(statement)).subscribe();
        return customer;
    }


    public Customer updateCust(Customer customer){
        SimpleStatement statement  = SimpleStatement.builder("INSERT tocomo.customers where user_name = ? OR name=? or user_id =?")  //: TODO
                .addPositionalValues( customer.getUserName(),customer.getName(), customer.getTrxId())
                .build();
        Flux.from(session.executeReactive(statement)).subscribe();
        return customer;
    }


    public Object createTrx(Transactions transaction){
        SimpleStatement statement =
                SimpleStatement.builder(" INSERT INTO tocomo.transactions (trx_key, user_id, user_name, name, amount, client_id, client_name, status, trx_type) VALUES (?,?,?,?,?,?,?,?,?)")
                        .addPositionalValues(UUID.randomUUID(), transaction.getUserId(), transaction.getUserName(), transaction.getName(), transaction.getAmount(),
                                transaction.getClientId(), transaction.getClientName(), true, "Send").build();
        Flux.from(session.executeReactive(statement)).share();
                return transaction;
    }


    public Mono<Transactions> getTrx(String id) {
        return Mono.from(session.executeReactive("SELECT * FROM tocomo.transactions WHERE user_name = ?  OR name = ? ;" ))
                .map(row -> trx.trxQuery(row.getString("user_name"), row.getString("name"), row.getInt("amount"), row.getString("client_name"), row.getString("trx_type")));
    }
}**/
