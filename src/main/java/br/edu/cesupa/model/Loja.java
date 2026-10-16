package br.edu.cesupa.model;

import java.util.ArrayList;

public class Loja {
    private String nome;
    private String categoria;
    private String cnpj;
    private ArrayList<Produto> produtos;
    private ArrayList<Venda> vendas;
    private double faturamentoTotal;

    public Loja(String nome, String categoria, String cnpj) {
        this.nome = nome;
        this.categoria = categoria;
        this.cnpj = (cnpj != null) ? cnpj.replace(".", "").replace("-", "").replace("/", "") : null;
        this.produtos = new ArrayList<Produto>();
        this.vendas = new ArrayList<Venda>();
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

    public ArrayList<Venda> getVendas() {
        return vendas;
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

    public void registrarVenda(Venda v) {
        if (v == null || v.getValorTotal() <= 0) {
            System.out.println("Venda invalida, nao registrada!");
        } else {
            vendas.add(v);
            this.faturamentoTotal += v.getValorTotal();
            System.out.println("Venda registrada com sucesso!");
        }
    }

    public double calcularTicketMedio() {
        if (vendas.isEmpty()) {
            return 0.0;
        }
        return this.faturamentoTotal / vendas.size();
    }
}