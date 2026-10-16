package br.edu.cesupa.model;

import java.util.ArrayList;

public class Loja {

    private String nome;
    private String categoria;
    private String cnpj;
    private final ArrayList<Produto> produtos;
    private final ArrayList<CompraEVenda> comprasEVendas;
    private double faturamentoTotal;

    public Loja(String nome, String categoria, String cnpj) {
        this.nome = nome;
        this.categoria = categoria;
        this.cnpj = (cnpj != null) ? cnpj.replace(".", "").replace("-", "").replace("/", "") : null;
        this.produtos = new ArrayList<>();
        this.comprasEVendas = new ArrayList<>();
        this.faturamentoTotal = 0.0;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public String getCnpj() {
        return cnpj;
    }

    public ArrayList<Produto> getProdutos() {
        return produtos;
    }

    public ArrayList<CompraEVenda> getComprasEVendas() {
        return comprasEVendas;
    }

    public double getFaturamentoTotal() {
        return faturamentoTotal;
    }

    public void cadastrarProduto(Produto p) {
        if (p == null || p.getNome() == null || p.getNome().isBlank()) {
            System.out.println("Produto invalido, nao cadastrado!");
            return;
        }
        produtos.add(p);
        System.out.println("Produto cadastrado com sucesso!");
    }

    public Produto buscarProdutoPorNome(String nomeBusca) {
        if (nomeBusca == null || nomeBusca.isBlank()) {
            return null;
        }
        for (Produto p : produtos) {
            if (p.getNome().equalsIgnoreCase(nomeBusca)) {
                return p;
            }
        }
        return null;
    }

    public void registrarCompraEVenda(CompraEVenda v) {
        if (v == null || v.getTotalComDesconto() <= 0) {
            System.out.println("Compra invalida, nao registrada!");
            return;
        }
        comprasEVendas.add(v);
        this.faturamentoTotal += v.getTotalComDesconto();
        System.out.println("Compra registrada com sucesso!");
    }

    public double calcularTicketMedio() {
        if (comprasEVendas.isEmpty()) {
            return 0.0;
        }
        return this.faturamentoTotal / comprasEVendas.size();
    }

    public void exibirLoja() {
        System.out.println("Loja: " + nome);
        System.out.println("Categoria: " + categoria);
        System.out.println("CNPJ: " + cnpj);
        System.out.println("Produtos cadastrados: " + produtos.size());
        System.out.println("Faturamento: R$ " + faturamentoTotal);
    }
}