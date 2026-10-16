package br.edu.cesupa.model;

import java.util.ArrayList;

public class Estacionamento {

    private String nome;
    private int capacidade;
    private double valorHora;
    private ArrayList<Carro> carros;

    public Estacionamento(String nome, int capacidade, double valorHora) {
        this.nome = nome;
        this.capacidade = capacidade;
        this.valorHora = valorHora;
        carros = new ArrayList<>();
    }

    public String getNome() {
        return nome;
    }

    public int getCapacidade() {
        return capacidade;
    }

    public double getValorHora() {
        return valorHora;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setCapacidade(int capacidade) {
        this.capacidade = capacidade;
    }

    public void setValorHora(double valorHora) {
        this.valorHora = valorHora;
    }

    public int vagasDisponiveis() {
        return capacidade - carros.size();
    }

    public Carro buscarCarro(String placa) {
        for (Carro carro : carros) {
            if (carro.getPlaca().equalsIgnoreCase(placa)) {
                return carro;
            }
        }
        return null;
    }

    public void adicionarCarro(Carro carro) {
        if (vagasDisponiveis() == 0) {
            System.out.println("Estacionamento cheio!");
            return;
        }

        if (buscarCarro(carro.getPlaca()) != null) {
            System.out.println("Carro ja cadastrado!");
            return;
        }

        carro.registrarEntrada();
        carros.add(carro);

        System.out.println("Carro estacionado!");
    }

    public double calcularValor(Carro carro) {
        long minutos = carro.calcularTempo();
        long horas = minutos / 60;

        if (minutos % 60 != 0) {
            horas++;
        }

        if (horas == 0) {
            horas = 1;
        }
        return horas * valorHora;
    }

    public void removerCarro(String placa) {
        Carro carro = buscarCarro(placa);

        if (carro == null) {
            System.out.println("Carro nao encontrado!");
            return;
        }

        carro.registrarSaida();

        double valor = calcularValor(carro);

        System.out.println("Valor a pagar: R$ " + valor);

        carros.remove(carro);

        System.out.println("Carro saiu do estacionamento!");
    }

    public void listarCarros() {

        if (carros.isEmpty()) {
            System.out.println("Nenhum carro estacionado.");
            return;
        }

        for (Carro carro : carros) {
            carro.exibirCarro();
            System.out.println("----------------");
        }
    }

    public void exibirEstacionamento() {
        System.out.println("Nome: " + nome);
        System.out.println("Capacidade: " + capacidade);
        System.out.println("Vagas livres: " + vagasDisponiveis());
    }

    
    
}
