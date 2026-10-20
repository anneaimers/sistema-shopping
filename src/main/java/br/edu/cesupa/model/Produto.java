package br.edu.cesupa.model;

public class Produto {

    private final int id;
    private String nome;
    private int estoque;
    private double preco;

    public Produto(int id, String nome, int estoque, double preco) {
        this.id = id;
        this.nome = Shopping.texto(nome );
        if (estoque < 0) throw new IllegalArgumentException("Estoque nao pode ser negativo!" );
        this.estoque = estoque;
        this.preco = Shopping.valorNaoNegativo(preco, "preco" );
    }

    public int getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = Shopping.texto(nome );
    }

    public int getEstoque() {
        return estoque;
    }

    public double getPreco() {
        return preco;
    }

    public void setPreco(double preco) {
        this.preco = Shopping.valorNaoNegativo(preco, "preco" );
    }

    public void adicionarEstoque(int quantidade) {
        if (quantidade <= 0) {
            throw new IllegalArgumentException("Quantidade invalida!");
        }
        this.estoque += quantidade;
    }

    public void removerEstoque(int quantidade) {
        if (quantidade <= 0) {
            throw new IllegalArgumentException("Quantidade invalida!");
        }
        if (quantidade > estoque) {
            throw new IllegalArgumentException("Estoque insuficiente: " + nome);
        }
        this.estoque -= quantidade;
    }

    public void exibirProduto() {
        System.out.println("Produto: " + nome + " (id " + id + ")");
        System.out.println("Preco: R$ " + preco);
        System.out.println("Estoque: " + estoque);
    }
}