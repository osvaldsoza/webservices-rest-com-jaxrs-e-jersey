package br.com.alura.loja;

import org.glassfish.grizzly.http.server.HttpServer;
import org.glassfish.jersey.grizzly2.httpserver.GrizzlyHttpServerFactory;
import org.glassfish.jersey.server.ResourceConfig;

import javax.ws.rs.client.WebTarget;
import java.io.IOException;
import java.net.URI;

public class Servidor {

    public static void main(String[] args) throws IOException {
        HttpServer server = staringServer();
        System.out.println("server rodando...");
        System.in.read();
        server.stop();
    }

    public static HttpServer staringServer() {
        ResourceConfig config = new ResourceConfig().packages("br.com.alura.loja");
        URI uri = URI.create("http://localhost:8080");
        HttpServer server = GrizzlyHttpServerFactory.createHttpServer(uri, config);
        return server;
    }
}
