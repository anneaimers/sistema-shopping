package br.edu.cesupa.model;

import java.util.ArrayList;

public class Loja {

    private String nome;
    private String categoria;
    private transient String shopping;
    private ArrayList<Produto> produtos = new ArrayList<>();
    private ArrayList<CompraEVenda> compras = new ArrayList<>();

    public Loja(String nome, String categoria, String cnpj) {
        setNome(nome);
        setCategoria(categoria);
        vincular(cnpj);
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        if (Shopping.normalizarTexto(nome).isEmpty()) {
            throw new IllegalArgumentException("Nome da loja é obrigatório.");
        }
        this.nome = nome.strip();
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        if (categoria == null || categoria.strip().isEmpty()) {
            this.categoria = "Não informada";
        } else {
            this.categoria = categoria.strip();
        }
    }

    public String getShopping() {
        return shopping;
    }

    public void vincular(String cnpj) {
        if (cnpj == null) {
            throw new IllegalArgumentException(
                    "A loja precisa estar vinculada a um shopping existente.");
        }
        this.shopping = cnpj;
    }

    public void validar() {
        setNome(nome);
        setCategoria(categoria);
    }

    @Override
    public String toString() {
        return "Loja{nome='" + nome + "', categoria='" + categoria + "'}";
    }

    public void cadastrarProduto(Produto produto) {
        if (produto == null) {
            throw new IllegalArgumentException("Produto invalido, nao cadastrado!");
        }
        if (produtos == null) {
            produtos = new ArrayList<>();
        }
        produtos.add(produto);
    }

    public void exibirLoja() {
        System.out.println("Loja: " + nome);
        System.out.println("Categoria: " + categoria);
        System.out.println("CNPJ do shopping: " + shopping);
        System.out.println("Produtos cadastrados: " + (produtos == null ? 0 : produtos.size()));
    }

    public void registrarCompraEVenda(CompraEVenda venda) {
        if (venda == null) {
            throw new IllegalArgumentException("Compra invalida, nao registrada!");
        }
        if (compras == null) {
            compras = new ArrayList<>();
        }
        compras.add(venda);
    }

    public ArrayList<Produto> getProdutos() {
        if (produtos == null) {
            produtos = new ArrayList<>();
        }
        return produtos;
    }

    public ArrayList<CompraEVenda> getCompras() {
        if (compras == null) {
            compras = new ArrayList<>();
        }
        return compras;
    }
}