package br.edu.cesupa.model;

import java.util.ArrayList;

public class Cliente {
    private String nome;
    private String cpf;
    private String email;
    private ArrayList<Compra> compras;
    private ArrayList<Carro> carros;
    private int quantidadePontos;

    public Cliente(String nome, String cpf, String email) {
        this.nome = nome;
        //pq?
        this.cpf = (cpf != null) ? cpf.replace(".","").replace("-","").replace("/", ""):null;
        this.email = email;
        this.compras = new ArrayList<Compra>();
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
    
    public ArrayList<Compra> getCompras(){
        return compras;

    }
    public ArrayList<Carro> getCarros(){
        return carros;

    }
    public int getQuantidadePontos(){
        return quantidadePontos;

    }
    public void cadastrarCompra(Compra c){
        if(c == null||c.getNome() == null || c.getNome().isBlank() || c.getNome().equalsIgnoreCase("invalido") ){
            System.out.println("Compra invalida, nao cadastrada!");

        }else{
            compras.add(c);
          int pontosGanhos = (int) (c.getValorTotal() / 10.0);         
          this.quantidadePontos += pontosGanhos;

            System.out.println("Compra cadastrada com sucesso!");
        }

    }
    public double calcularTotalGasto(){
        double total = 0.0;
        for (Compra c : compras) {
            total += c.getValorTotal();
        }
        return total;
    }
    

    
    
}