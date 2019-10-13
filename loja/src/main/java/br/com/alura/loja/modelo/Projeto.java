package br.com.alura.loja.modelo;

import com.thoughtworks.xstream.XStream;

public class Projeto {

    private Long id;
    private String nome;
    private int anoDeInicio;

    public Projeto(Long id, String nome, int anoDeInicio) {
        this.id = id;
        this.nome = nome;
        this.anoDeInicio = anoDeInicio;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public int getAnoDeInicio() {
        return anoDeInicio;
    }

    public String toXML() {
        return new XStream().toXML(this);
    }
}
