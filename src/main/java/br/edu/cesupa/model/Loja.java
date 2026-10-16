package br.edu.cesupa.model;

import java.util.ArrayList;

public class Loja {
    private String nome;
    private String categoria;
    private String cnpj;
    private ArrayList<Produto> produtos;
    private ArrayList<CompraEVenda> CompraEVendas;
    private double faturamentoTotal;

    public Loja(String nome, String categoria, String cnpj) {
        this.nome = nome;
        this.categoria = categoria;
        this.cnpj = (cnpj != null) ? cnpj.replace(".", "").replace("-", "").replace("/", "") : null;
        this.produtos = new ArrayList<Produto>();
        this.CompraEVendas = new ArrayList<CompraEVenda>();
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

    public ArrayList<CompraEVenda> getCompraEVendas() {
        return CompraEVendas;
    }

    public double getFaturamentoTotal() {
        return faturamentoTotal;
    }

    public void cadastrarProduto(Produto p) {
        if (p == null || p.getNome() == null || p.getNome().equalsIgnoreCase("invalido") || p.getNome().isBlank()) {
            System.out.println("Produto invalido, nao cadastrado!");
        } else {
            produtos.add(p);
            System.out.println("Produto cadastrado com sucesso!");
        }
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
        if (v == null || v.getValorTotal() <= 0) {
            System.out.println("CompraEVenda invalida, nao registrada!");
        } else {
            CompraEVendas.add(v);
            this.faturamentoTotal += v.getValorTotal();
            System.out.println("CompraEVenda registrada com sucesso!");
        }
    }

    public double calcularTicketMedio() {
        if (CompraEVendas.isEmpty()) {
            return 0.0;
        }
        return this.faturamentoTotal / CompraEVendas.size();
    }
}