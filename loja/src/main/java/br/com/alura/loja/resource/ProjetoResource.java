package br.com.alura.loja.resource;

import br.com.alura.loja.dao.ProjetoDAO;
import br.com.alura.loja.modelo.Projeto;
import com.thoughtworks.xstream.XStream;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

@Path("projetos")
public class ProjetoResource {

    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_XML)
    public String buscar(@PathParam("id") Long id){
        Projeto projeto = new ProjetoDAO().busca(id);
        return projeto.toXML();
    }

    @POST
    public String adiciona(String conteudo){
        Projeto projeto = (Projeto) new XStream().fromXML(conteudo);
        new ProjetoDAO().adiciona(projeto);
        return "<status>sucesso</status>";
    }
}
