package br.com.alura.loja;

import br.com.alura.loja.modelo.Carrinho;
import br.com.alura.loja.modelo.Produto;
import org.glassfish.grizzly.http.server.HttpServer;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

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
        String conteudo = target.path("/carrinhos/1").request().get(String.class);

        Assert.assertTrue(conteudo.contains("<rua>Rua Vergueiro 3185, 8 andar"));
    }

    @Test
    public void testeAdicionaUmNovoCarrinho(){
        Carrinho carrinho = new Carrinho();
        carrinho.adiciona(new Produto(314L, "Tablet", 999, 1));
        carrinho.setRua("Rua Vergueiro");
        carrinho.setCidade("Sao Paulo");
        String xml = carrinho.toXML();

        Client client = ClientBuilder.newClient();
        WebTarget target = client.target("http://localhost:8080");

        Entity<String> entity = Entity.entity(xml, MediaType.APPLICATION_XML);

        Response response = target.path("/carrinhos").request().post(entity);
        Assert.assertEquals("<status>sucesso</status>", response.readEntity(String.class));


    }
}
