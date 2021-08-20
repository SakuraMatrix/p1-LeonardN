package com.github.vazidev.tocomo;


import com.datastax.oss.driver.api.core.CqlSession;
import com.github.vazidev.tocomo.repository.CustomerRepository;
import com.github.vazidev.tocomo.service.CustomerService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import reactor.netty.DisposableServer;
import reactor.netty.http.server.HttpServer;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Objects;

@Configuration
@ComponentScan

public class AppConfig {

    @Bean
    public CqlSession session(){
    return CqlSession.builder().build();
    }

    @Bean
    public CustomerRepository repository(){
        return new CustomerRepository(session());
    }

    @Bean
    public CustomerService service(){
        return new CustomerService(repository());
    }

    @Bean
    public DisposableServer server(){ //set Netty Server
        Path indexHtml = Paths.get(Objects.requireNonNull(Application.class.getResource("/index.html")).toURI());
        Path error404 = Paths.get(Objects.requireNonNull(Application.class.getResource("/404.html")).toURI());
        Path newId  = Paths.get(Objects.requireNonNull(Application.class.getResource("/data.cql")).toURI());

        CustomerService customerService = service();
           return  HttpServer.create()
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

}
