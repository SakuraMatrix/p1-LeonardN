package com.github.vazidev.tocomo;

import com.datastax.oss.driver.api.core.CqlSession;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.vazidev.tocomo.domain.Customer;
import com.github.vazidev.tocomo.domain.Transactions;
import com.github.vazidev.tocomo.repository.CustomerRepository;
import com.github.vazidev.tocomo.service.CustomerService;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufAllocator;
import reactor.netty.http.server.HttpServer;

import java.io.ByteArrayOutputStream;
import java.net.URISyntaxException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Objects;

public class Application {
    static final ObjectMapper OBJECT_MAPPER =  new ObjectMapper();
        
    public static void main(String[] args) throws URISyntaxException {
        Path indexHtml = Paths.get(Objects.requireNonNull(Application.class.getResource("/index.html")).toURI());
        Path error404 = Paths.get(Objects.requireNonNull(Application.class.getResource("/404.html")).toURI());
        Path newId  = Paths.get(Objects.requireNonNull(Application.class.getResource("/data.cql")).toURI());

        //Create a CQL session
        CqlSession session = CqlSession.builder().build();

        CustomerRepository customerRepository =new CustomerRepository(session);
        CustomerService customerService = new CustomerService(customerRepository);


        //set Netty Server
        HttpServer.create()
                .port(8080)   //setting the port to '0' lets the system chose an ephemeral port to use
                .route(routes -> routes
                        .get("/customers", (request, response) ->
                                response.send(customerService.getAllCust()
                                        .map(Application::toByteBuf)
                                        .log("http-server")))

                        .get("/trx",(request, response) ->
                                response.send(customerService.getAllTrx()
                                        .map(Application::toByteBuf)
                                        .log("http-server")))

                       .post("customers/new/{param}", (request, response) ->   //get new customer data
                                response.send(request.receive().asString()
                                        .map(Application::parseCustomer)
                                        .map(customerService::createCust)
                                        .map(Application::toByteBuf)
                                    .log("http-server")))

                        .post("trx/send/{param}", (request, response) ->   //get new Transaction record
                                response.send(request.receive().asString()
                                        .map(Application::parseSend)
                                        .map(customerService::createTrx)
                                        .map(Application::toByteBuf)
                                        .log("http-server")))

                        .get("/customers/{param}", (request, response) ->
                                response.send(customerService.getCust(request.param("param"))
                                        .map(Application::toByteBuf)
                                        .log("http-server")))

                        .get("/", ((Request, response) ->
                                response.sendFile(indexHtml)))

                        .get("/404", ((request, response) ->
                                response.status(404).addHeader("Message", "Made Boo Boo!")
                                        .sendFile(error404)))
                )
    //create  I/O handler
                  .bindNow();
        }


    //create a mapping object
        static ByteBuf toByteBuf(Object o) {
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            try{
                OBJECT_MAPPER.writeValue(out, o);
            }catch(Exception ex){
                ex.printStackTrace();
            }
            return ByteBufAllocator.DEFAULT.buffer().writeBytes(out.toByteArray());
    }



        static Customer parseCustomer(String str){ //collectd data from browser ../new customer
            Customer customer = new Customer();
            try{
                customer = OBJECT_MAPPER.readValue(str, Customer.class);

            }catch( JsonProcessingException ex) {
                String[] params = str.split("&");
                String user_name = params[0].split("=")[1];
                String name = params[1].split("=")[1];
                //String user_id = "*";
                customer = customer.customerQuery(user_name, name);
                System.out.println(customer);
            }
            return customer;
        }

        static Transactions parseSend(String str){ //collects data from browser ../new send Transaction
            Transactions transactions = new Transactions();
            try{
                transactions = OBJECT_MAPPER.readValue(str, Transactions.class);

            }catch( JsonProcessingException ex) {
                String[] params = str.split("&");
                String user_name = params[0].split("=")[1];
                String client_name = params[1].split("=")[1];
                boolean status = true;
                String trx_type= "Send";
                double amount = Double.parseDouble( params[2]. split("=")[1]);
                transactions = transactions.trxQuery(user_name, client_name, amount, trx_type);
            }
            return transactions;
    }
}

