package br.edu.cesupa.model;

import java.util.ArrayList;

public class Cliente {

    private String nome;
    private String cpf;
    private String email;
    private ArrayList<CompraEVenda> CompraEVendas;
    private ArrayList<Carro> carros;
    private int quantidadePontos;

    public Cliente(String nome, String cpf, String email) {
        this.nome = nome;
        //pq?
        this.cpf = (cpf != null) ? cpf.replace(".","").replace("-","").replace("/", ""):null;
        this.email = email;
        this.CompraEVendas = new ArrayList<CompraEVenda>();
        this.carros = new ArrayList<Carro>();
        this.quantidadePontos = 0;
    }

    public String getNome(){
        return nome;
    }

    public void setNome(String nome){
        this.nome = nome;
    }
   
    public String getCpf(){
        return cpf;
    }

    public String getEmail(){
        return email;
    }

    public void setEmail(String email){
        this.email = email;
    }
    
    public ArrayList<CompraEVenda> getCompraEVendas(){
        return CompraEVendas;
    }

    public ArrayList<Carro> getCarros(){
        return carros;
    }

    public int getQuantidadePontos(){
        return quantidadePontos;

    }

    public void cadastrarCompraEVenda(CompraEVenda c){
        if(c == null||c.getNomeLoja() == null || c.getNomeLoja().isBlank() || c.getNomeLoja().equalsIgnoreCase("invalido") ){
            System.out.println("CompraEVenda invalida, nao cadastrada!");
        }

        else{
            CompraEVendas.add(c);
            int pontosGanhos = (int) (c.get(i).Valores / 10.0);         
            this.quantidadePontos += pontosGanhos;

            System.out.println("CompraEVenda cadastrada com sucesso!");
        }

    }

    public double calcularTotalGasto(){
        double total = 0.0;

        for (CompraEVenda c : CompraEVendas) {
            total += c.get(i).Valores;
        }
        return total;
    }
    

    
    
}