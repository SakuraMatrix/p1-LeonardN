import reactor.netty.tcp.TcpServer;
import reactor.core.publisher.Mono;
import reactor.netty.DisposableServer;
import reactor.netty.http.server.HttpServer;

public class Application {
    public static void main(String[] args) {

        DisposableServer server = HttpServer.create()
                .handle((request, response)-> response.sendString(Mono.just("Welcome to Tocomo")))
                .host("localhost")
                .port(8080)
                .bindNow();

        Mono.when(server.onDispose())
                .block();


    }
}
