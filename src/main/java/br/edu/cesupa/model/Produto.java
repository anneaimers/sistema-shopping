package br.edu.cesupa.model;

public class Produto {

    private final int id;
    private String nome;
    private int estoque; 
    private double preco;
    private int quantidade;

    public Produto(int id, String nome, int estoque, double preco, int quantidade) {
        this.id = id;
        this.nome = nome;
        this.estoque = estoque;
        this.preco = preco;
        this.quantidade = quantidade;
    }

    public int getId() {
        return id;
    }

    public String getNomeloja() {
        return nome;
    }

    public void setNomeloja(String nome) {
        this.nome = nome;
    }

    public int getEstoque() {
        return estoque;
    }

    public void setEstoque(int estoque) {
        this.estoque = estoque;
    }

    public double getPreco() {
        return preco;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }
   
    public void adicionarEstoque(int quantidade){
        if (quantidade<=0) {
            throw new IllegalArgumentException("quantidade invalida");
        }
        this.estoque += quantidade;
    }

    public void removerEstoque(int quantidade){
        if (quantidade<=0) {
            throw new IllegalArgumentException("quantiadade invalida");
        }

        if (quantidade>estoque) {
            throw new IllegalArgumentException("estoque insuficiente" + nome);
        }
        this.estoque -= quantidade;
    }
   

    
}