package br.com.controleacesso.model;

public class Usuario {

    private final String nome;
    private final String senhaHash;

    public Usuario(String nome, String senhaHash) {
        this.nome = nome;
        this.senhaHash = senhaHash;
    }

    public String getNome() {
        return nome;
    }

    public String getSenhaHash() {
        return senhaHash;
    }
}
