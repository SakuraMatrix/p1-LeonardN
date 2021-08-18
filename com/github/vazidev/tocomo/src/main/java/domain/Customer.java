package com.github.vazidev.tocomo.domain;


import com.datastax.oss.driver.api.core.CqlSession;
import com.datastax.oss.driver.api.core.cql.ResultSet;
import com.datastax.oss.driver.api.core.cql.Row;
import com.datastax.oss.driver.api.core.cql.SimpleStatement;
import java.util.Objects;
import java.util.UUID;

public class Customer {
    private String user_id;
    private String name;
    private String user_name;
    private String trx_id;

    @Override
    public String toString() {
        return " Customer{" +
                "id=" + user_id +
                ", userName ='" + user_name + '\'' +
                ", Name = ' " + name + '\'' +
                '}';
    }

    public Customer(UUID user_id, String user_name, String name, UUID trx_key) {
        this.user_id = user_id.toString();
        this.user_name = user_name;
        this.name = name;
        this.trx_id = trx_id.toString();
    }

    public Customer() {
    }

    public void Customers(String user_name, String name) {
        this.user_name = user_name;
        this.name = name;
    }


    public Customer getCustomers(String user_id, String name, String user_name) {
        this.user_id =user_id;
        this.name = name;
        this.user_name = user_name;
        return this;
    }

    public Customer getCustomer(String user_id, String name, String user_name) {
        this.user_id =user_id;
        this.name = name;
        this.user_name = user_name;
        return this;
    }

    public Customer customerQuery( String user_name, String name) {
        this.name = name;
        this.user_name = user_name;
        this.user_id = user_id;
        return this;
    }

    public Customer customerCreate( String user_name, String name) {
        this.name = name;
        this.user_name = user_name;
        this.user_id = user_id;
        return this;
    }






    @Override
    public boolean equals(Object o){
    if(this == o ) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Customer customer = (Customer) o;
    return user_id == customer.user_id && Objects.equals(customer.user_name, user_name)  && Objects.equals(name, customer.name);
    }

    //@Override
    public int hashcode() {
    return Objects.hash(user_id, name, user_name);
    }

    public void setId(){ this.user_id = UUID.randomUUID().toString();}

    public String getUserId(){ return user_id; }

    public void setTrxId(){
        this.trx_id = UUID.randomUUID().toString();
    }

    public String getTrxId(){ return trx_id.toString();}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUserName() {
        return name;
    }

    public void setUserName(String userName) {
        this.user_name = userName;
    }


    public static void setUser(CqlSession session, String name, String user_name){
        //TO DO: execute SimpleStatement that inserts one user into the table
        session.execute(
                SimpleStatement.builder( "INSERT INTO users (user_id, name, user_name, amount) VALUES (?,?,?,?,?)")
                        .addPositionalValues(UUID.randomUUID(), name, user_name, UUID.randomUUID())
                        .build());
    }

    private static void getUser(CqlSession session, String user_name) {
        //TO DO: execute SimpleStatement that retrieves one user from the table
        //TO DO: print firstname and age of user
        ResultSet rs = session.execute(
                SimpleStatement.builder("SELECT * FROM users WHERE user_name=?")
                        .addPositionalValue(user_name)
                        .build());

        Row row = rs.one();
        System.out.format("%s %d\n", row.getString("user_name"), row.getString("name"), row.getUuid("user_id"));
    }

    private static void updateUser(CqlSession session, int user_id, String name) {
        //TO DO: execute SimpleStatement that updates
        session.execute(
                SimpleStatement.builder("UPDATE users SET name =?  WHERE user_id =? ")
                        .addPositionalValues(name, user_id)
                        .build());
    }

    private static void deleteUser(CqlSession session, int user_id) {
        //TO DO: execute SimpleStatement that deletes one user from the table
        session.execute(
                SimpleStatement.builder("DELETE FROM users WHERE user_id=?")
                        .addPositionalValue(user_id)
                        .build());

    }


}
