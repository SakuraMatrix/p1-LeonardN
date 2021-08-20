package com.github.vazidev.tocomo;

import com.github.vazidev.tocomo.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;
import org.springframework.data.cassandra.config.AbstractReactiveCassandraConfiguration;
import org.springframework.data.cassandra.config.SchemaAction;
import org.springframework.data.cassandra.core.cql.keyspace.CreateKeyspaceSpecification;
import org.springframework.data.cassandra.core.cql.keyspace.KeyspaceOption;
import org.springframework.data.cassandra.repository.config.EnableCassandraRepositories;
import org.springframework.http.MediaType;
import org.springframework.http.server.reactive.HttpHandler;
import org.springframework.http.server.reactive.ReactorHttpHandlerAdapter;
import org.springframework.web.reactive.function.server.RequestPredicates;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;
import org.springframework.web.server.adapter.WebHttpHandlerBuilder;
import reactor.netty.http.server.HttpServer;

import java.util.Arrays;
import java.util.List;

@Configuration
@ComponentScan
@EnableCassandraRepositories(basePackages = {"com.github.vazidev.tocomo.repository"})
// basePackageClasses  = {AppConfig.class})
public class AppConfig extends AbstractReactiveCassandraConfiguration {
    @Autowired
    CustomerService customerService;

    /* @Bean   //Section replaced with the HTTP Server below
    public CqlSession session(){
    return CqlSession.builder().build();
    }**/

    /**   @Bean //Section replaced by the Access Controller Class, as part of Spring reactive Implementation
    public DisposableServer server() throws URISyntaxException { //set Netty Disposable Server @ port 8080
        Path indexHtml = Paths.get(Objects.requireNonNull(Application.class.getResource("/index.html")).toURI());
        Path error404 = Paths.get(Objects.requireNonNull(Application.class.getResource("/404.html")).toURI());
        Path newId  = Paths.get(Objects.requireNonNull(Application.class.getResource("/data.cql")).toURI());

         return  HttpServer.create()
                    .port(8080)   //setting the port to '0' lets the system chose an ephemeral port to use
                    .route(routes -> routes
                            .get("/cust", (request, response) ->
                                    response.send(customerService.getAllCust()
                                            .map(Application::toByteBuf)
                                            .log("http-server")))

                            .get("/trx",(request, response) ->
                                    response.send(customerService.getAllTrx()
                                            .map(Application::toByteBuf)
                                            .log("http-server")))

                            .post("cust/new/{param}", (request, response) ->   //get new customer data
                                    response.send(request.receive().asString()
                                            .map(Application::parseCustomer)
                                            .map(customerService::createCust)
                                            .map(Application::toByteBuf)
                                            .log("http-server")))

                            .post("trx/new/{param}", (request, response) ->   //get new Transaction record
                                    response.send(request.receive().asString()
                                            .map(Application::parseSend)
                                            .map(customerService::createTrx)
                                            .map(Application::toByteBuf)
                                            .log("http-server")))

                            .get("/cust/{param}", (request, response) ->
                                    response.send(customerService.getCust(request.param("param"))
                                            .map(Application::toByteBuf)
                                            .log("http-server")))

                            .get("/", ((Request, response) ->
                                    response.sendFile(indexHtml)))

                            .get("/404", ((request, response) ->
                                    response.status(404).addHeader("Message", "Made Boo Boo!")
                                            .sendFile(error404)))
                    )
                    .bindNow();
    }**/

    @Bean //http Server @port 8080
    public HttpServer httpServer(ApplicationContext context){
        HttpHandler handler = WebHttpHandlerBuilder.applicationContext(context).build();
        ReactorHttpHandlerAdapter adapter = new ReactorHttpHandlerAdapter(handler);
        return HttpServer.create().port(8080).handle(adapter);
    }
    @Override
    protected String getKeyspaceName(){
        return "tocomo";
    }

    @Override
    protected String getContactPoints(){
       return "localhost";
    }

    @Override
    public SchemaAction getSchemaAction(){
        return SchemaAction.CREATE_IF_NOT_EXISTS;
    }

    @Override
    protected List<CreateKeyspaceSpecification> getKeyspaceCreations() {
        CreateKeyspaceSpecification specification = CreateKeyspaceSpecification.createKeyspace("tocomo")
                .ifNotExists()
                .with(KeyspaceOption.DURABLE_WRITES, true);
        return Arrays.asList(specification);
    }


    //creates a bean pathways to an HTML File; for HTML Data entry
    @Bean
    public RouterFunction<ServerResponse> indexRouter(@Value("classpath:index.html") Resource IndexHTMLFile) {
        return RouterFunctions.route(RequestPredicates.GET("/"), request -> ServerResponse.ok().contentType(MediaType.TEXT_HTML).bodyValue(IndexHTMLFile));
    }

}