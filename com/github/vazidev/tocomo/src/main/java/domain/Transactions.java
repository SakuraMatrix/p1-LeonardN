package com.github.vazidev.tocomo.domain;

import com.datastax.oss.driver.api.core.CqlSession;
import reactor.core.publisher.Mono;

import java.util.UUID;

public class Transactions {

  //Customer customer= new Customer();

        private String trx_key;
        private UUID user_id;
        private String user_name;
        private double amount;
        private String client_id;
        private String client_name;
        private String name;
        private Boolean status;
        private String trx_type;



    public Transactions() {

    }

    public Object setUserId(CqlSession session, String user_name ) {
            //retrieve the client Id- using the provided username,
            user_name = this.user_name;
            return client_id = Mono.from(session.executeReactive( "SELECT user_id FROM tocomo.customers WHERE user_name =" + user_name))
                    .toString();
    }

    public Object setTrxKey(){
        this.trx_key = UUID.randomUUID().toString();
        return this;
    }

    public String setClientId(CqlSession session, String clientName ) {
        //retrieve the client Id- using the provided username,
        clientName = this.client_name;
        return client_id = Mono.from(session.executeReactive( "SELECT user_id FROM tocomo.customers WHERE user_name =" + clientName))
                .toString();
    }

    public Object getUserName( CqlSession session, String clientName) {
        //retrieve the client name- using the provided username,
        clientName = this.client_name;
        return client_id = Mono.from(session.executeReactive("SELECT name FROM tocomo.customers WHERE user_name =" + clientName))
                .toString();
    }

    public String getName() {
      return name;
    }

    public Double getAmount() {
        return amount;
    }

    public String getClientName() {
        return client_name;
    }

    public String getUserId() {
    return user_id.toString();
    }

    public String getClientId() {
    return client_id;
    }

    public String getUserName() {
    return user_name;
    }

    public Transactions trxQuery( String user_name, String client_name, Double amount, String trx_type) {
        this.client_name = client_name;
        this.user_name = user_name;
        this.amount = amount;
        this.trx_type = trx_type;
        return this;
    }

}
