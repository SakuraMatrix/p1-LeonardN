package com.github.vazidev.tocomo;

import com.github.vazidev.tocomo.repository.CustomerRepository;
import com.github.vazidev.tocomo.service.CustomerService;
import reactor.netty.tcp.TcpServer;
import reactor.core.publisher.Mono;
import reactor.netty.http.server.HttpServer;

import java.io.FileInputStream;
import java.net.InetSocketAddress;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.Path;
import java.nio.file.Paths;

import com.datastax.oss.driver.api.core.CqlSession;
import com.datastax.oss.driver.api.core.cql.*;

public class Application {
    public static void main(String[] args) throws URISyntaxException {
        Path myHtml = Paths.get(Application.class.getResource("/index.html").toURI());
        CustomerRepository customerRepository = new CustomerRepository();
        CustomerService customerService = new CustomerService();

        HttpServer.create()   // Prepares an HTTP server ready for configuration
                .port(8080)   //setting the port to '0' lets the system chose an ephemeral port to use
                .route(routes -> routes
                        .get("/customers", (request, response) ->
                                response.sendString(Mono.just("Welcome to Tocomo")
                                        .log("http-server")))
                        /**  .get("/customers/service", (request, response) ->
                         response.sendString(customerService.getAll())
                         .log("http-server")))
                         .get("/customers/list", (request, response) ->
                         response.sendString(customerRepository.getAll())
                         .log("http-server")))**/
                        .get("/customers/{param}", (request, response) ->
                                response.sendString(Mono.just(request.param("param"))
                                        .log("http-server")))
                        .get("/", ((Request, response) ->
                                response.status(404).addHeader("Message", "Made Boo Boo!")
                                        .sendFile(myHtml)))
                )
                .bindNow()
                .onDispose()
                .block();


        try (CqlSession session = CqlSession.builder()
                .addContactPoint(new InetSocketAddress("localhost", 9041))
                .addContactPoint(new InetSocketAddress("127.0.0.1", 9042))
                .withLocalDatacenter("datacenter")
                .build();
        ) {                                  // (1)
            ResultSet rs = session.execute("select release_version from system.local");              // (2)
            Row row = rs.one();
            System.out.println(row.getString("release_version"));
        }
    }
}

