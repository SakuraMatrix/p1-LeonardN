package com.github.vazidev.tocomo;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.web.bind.annotation.RestController;
import reactor.netty.http.server.HttpServer;
import java.net.URISyntaxException;
import java.time.Duration;

@RestController
//@SpringBootApplication
public class TocomoApp {
    static final ObjectMapper OBJECT_MAPPER =  new ObjectMapper();
        
    public static void main(String[] args) throws URISyntaxException {
       // SpringApplication.run(TocomoApp.class, args)

        //utilizing spring bean
       AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(AppConfig.class);

        //Bind the  Http Server using the Spring Bean/ running @ port 8080
        applicationContext.getBean(HttpServer.class).bindUntilJavaShutdown(Duration.ofSeconds(60), null);


        /** Initial Diposable Server replaced  by the Spring HTTP Server
         applicationContext.getBean(DisposableServer.class)
               .onDispose()
                .block();  **/


}

   /** Deprecated  ByteBuf content after Spring
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



        static Customer parseCustomer(String str){ //collects data from browser ../new customer
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
    } **/
}

