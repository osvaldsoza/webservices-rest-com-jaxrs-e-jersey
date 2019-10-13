package br.com.alura.loja;

import br.com.alura.loja.modelo.Projeto;
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

public class ProjetoTest {

    HttpServer server = new HttpServer();

    private WebTarget newClient() {
        Client client = ClientBuilder.newClient();
        return client.target("http://localhost:8080");
    }

    @Before
    public void startServer(){
        Servidor.staringServer();
    }

    @After
    public void stopServer(){
        server.stop();
    }

    @Test
    public void testaBuscaProjeto(){
        WebTarget target = newClient();
        String conteudo = target.path("/projetos/1").request().get(String.class);

        Assert.assertTrue(conteudo.contains("<nome>Minha loja"));

    }


    @Test
    public void testeAdiciona(){
        Projeto projeto = new Projeto(345l,"Algaworks",2015);
        String xml = projeto.toXML();

        WebTarget target = newClient();

        Entity<String> entity = Entity.entity(xml, MediaType.APPLICATION_XML);
        Response response = target.path("/projetos").request().post(entity);

        Assert.assertEquals("<status>sucesso</status>", response.readEntity(String.class));
    }


}
