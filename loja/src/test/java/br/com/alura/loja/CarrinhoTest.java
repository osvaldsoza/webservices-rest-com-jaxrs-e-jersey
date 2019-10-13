package br.com.alura.loja;

import org.glassfish.grizzly.http.server.HttpServer;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;

public class CarrinhoTest {

    HttpServer server = new HttpServer();

    @Before
    public void startServer(){
        Servidor.staringServer();
    }

    @After
    public void stopServer(){
        server.stop();
    }

    @Test
    public void testaBuscarCarrinhoPorId(){
        Client cliente = ClientBuilder.newClient();
        WebTarget target = cliente.target("http://localhost:8080");
        String conteudo = target.path("/carrinhos").request().get(String.class);

        Assert.assertTrue(conteudo.contains("<rua>Rua Vergueiro 3185, 8 andar"));
    }
}
