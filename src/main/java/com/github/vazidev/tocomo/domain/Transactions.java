package com.github.vazidev.tocomo.domain;

import com.datastax.oss.driver.api.core.CqlSession;
import org.springframework.data.cassandra.core.cql.PrimaryKeyType;
import org.springframework.data.cassandra.core.mapping.PrimaryKeyColumn;
import org.springframework.data.cassandra.core.mapping.Table;
import reactor.core.publisher.Mono;

import java.util.UUID;

@Table("transactions")
public class Transactions {


        @PrimaryKeyColumn(name = "user_id", type = PrimaryKeyType.CLUSTERED)
        private int user_id;
        @PrimaryKeyColumn(name = "user_name", type = PrimaryKeyType.PARTITIONED)
        private String user_name;
        private String name;
        private int client_id;
        private String client_name;
        private String trx_type;
        private Boolean status;
        @PrimaryKeyColumn(name = "trx_key", type = PrimaryKeyType.PARTITIONED)
        private UUID trx_key;
        private double amount;


    public Transactions() {
    }

    public Transactions(int user_id, String user_name, String name, int client_id, String client_name, String trx_type, Boolean status, UUID trx_key, double amount){
        this.user_id = user_id;
        this.user_name = user_name;
        this.name =  name;
        this.client_id =  client_id;
        this.client_name =  client_name;
        this.trx_type =  trx_type;
        this.status  = status;
        this.trx_key =  trx_key;
        this.amount =  amount;
    }



    public int setUserId(CqlSession session, String user_name ) {
        //retrieve the client Id- using the provided username,
        this.user_name = user_name;
        return user_id= Integer.parseInt(Mono.from(session.executeReactive("SELECT user_id FROM tocomo.customers WHERE user_name =" + user_name + "ALLOW FILTERING")).toString());
    }

    public Object setTrxKey(){
        this.trx_key = UUID.randomUUID();
        return this;
    }

    public int setClientId(CqlSession session, String client_name ) {
        //retrieve the client Id- using the provided username,
        this.client_name= client_name;
        return client_id = Integer.parseInt(Mono.from(session.executeReactive( "SELECT user_id FROM tocomo.customers WHERE user_name =" + client_name)).toString());

    }

    public String getUserName( CqlSession session, String client_name) {
        //retrieve the client name- using the provided username,
        client_name = this.client_name;
        return client_name = Mono.from(session.executeReactive("SELECT name FROM tocomo.customers WHERE user_name =" + client_name)).toString();
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

    public int getUserId() {
    return user_id;
    }

    public int getClientId() {
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


    public Transactions trxQuery(String user_name, String name, int amount, String client_name, String trx_type) {
        this.client_name = client_name;
        this.user_name = user_name;
        this.name = name;
        this.amount = amount;
        this.trx_type = trx_type;
        return this;
    }
}
