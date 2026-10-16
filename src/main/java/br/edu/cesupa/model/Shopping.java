package br.edu.cesupa.model;

import java.util.ArrayList;

public class Shopping {

    private String nome;
    private String cnpj;
    private String endereco;
    private ArrayList<Loja> lojas;
    private ArrayList<Estacionamento> estacionamentos;

    public Shopping(String nome, String cnpj, String endereco) {
        this.nome = nome;
        // limpa o cnpj deixando apenas numeros 
        this.cnpj = (cnpj != null) ? cnpj.replace(".", "").replace("-", "").replace("/", "") : null;
        this.endereco = endereco;
        this.lojas = new ArrayList<Loja>();
        this.estacionamentos = new ArrayList<Estacionamento>();
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCnpj() {
        return cnpj;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public ArrayList<Loja> getLojas() {
        return lojas;
    }

    public ArrayList<Estacionamento> getEstacionamentos() {
        return estacionamentos;
    }

    public Loja buscarLoja(String nome) {
        for (Loja loja : lojas) {
            if (loja.getNome().equalsIgnoreCase(nome)) {
                return loja;
            }
        }
        return null;
    }

    public void adicionarLoja(Loja loja) {
        if (loja == null || loja.getNome() == null || loja.getNome().isBlank()) {
            System.out.println("Loja invalida, nao cadastrada!");
            return;
        }

        if (buscarLoja(loja.getNome()) != null) {
            System.out.println("Loja ja cadastrada!");
            return;
        }

        lojas.add(loja);
        System.out.println("Loja cadastrada no shopping com sucesso!");
    }

    public void removerLoja(String nome) {
        Loja loja = buscarLoja(nome);

        if (loja == null) {
            System.out.println("Loja nao encontrada!");
            return;
        }

        lojas.remove(loja);
        System.out.println("Loja removida!");
    }

    public void adicionarEstacionamento(Estacionamento estacionamento) {
        if (estacionamento == null) {
            System.out.println("Estacionamento invalido, nao cadastrado!");
            return;
        }

        estacionamentos.add(estacionamento);
        System.out.println("Estacionamento cadastrado no shopping com sucesso!");
    }

    public int vagasDisponiveis() {
        int total = 0;

        for (Estacionamento estacionamento : estacionamentos) {
            total += estacionamento.vagasDisponiveis();
        }
        return total;
    }

    public void listarLojas() {
        if (lojas.isEmpty()) {
            System.out.println("Nenhuma loja cadastrada.");
            return;
        }

        for (Loja loja : lojas) {
            System.out.println(loja);
            System.out.println("----------------");
        }
    }

    public void exibirShopping() {
        System.out.println("Nome: " + nome);
        System.out.println("CNPJ: " + cnpj);
        System.out.println("Endereco: " + endereco);
        System.out.println("Quantidade de lojas: " + lojas.size());
        System.out.println("Vagas livres no shopping: " + vagasDisponiveis());
    }

    @Override
    public String toString() {
        return "Shopping{nome='" + nome + "', cnpj='" + cnpj
                + "', endereco='" + endereco + "', lojas=" + lojas.size() + "}";
    }
}