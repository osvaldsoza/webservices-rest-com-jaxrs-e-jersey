package br.com.alura.loja.resource;

import br.com.alura.loja.dao.CarrinhoDAO;
import br.com.alura.loja.modelo.Carrinho;
import com.thoughtworks.xstream.XStream;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.xml.bind.annotation.XmlSchema;

@Path("carrinhos")
public class CarrinhoResource {

    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_XML)
    public String buscar(@PathParam("id") Long id){
        Carrinho carrinho = new CarrinhoDAO().busca(id);
        return carrinho.toXML();
    }

    @POST
    @Produces(MediaType.APPLICATION_XML)
    public String adiciona(String conteudo){
        Carrinho carrinho = (Carrinho) new XStream().fromXML(conteudo);
        new CarrinhoDAO().adiciona(carrinho);
        return "<status>sucesso</status>";
    }
}
