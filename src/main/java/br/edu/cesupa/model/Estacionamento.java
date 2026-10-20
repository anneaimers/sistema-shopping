package br.edu.cesupa.model;

import java.util.ArrayList;
import java.util.Scanner;

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

    // ALTERADO: compara sempre a placa normalizada
    public Carro buscarCarro(String placa) {
        String placaNormalizada = Carro.normalizarPlaca(placa);
        for (Carro carro : carros) {
            if (carro.getPlaca() != null && carro.getPlaca().equals(placaNormalizada)) {
                return carro;
            }
        }
        return null;
    }

    // ALTERADO: a duplicidade e verificada ANTES da lotacao,
    // para o usuario receber o motivo correto da recusa
    public void adicionarCarro(Carro carro) {
        if (carro == null) {
            System.out.println("Carro invalido, nao estacionado!");
            return;
        }
        if (buscarCarro(carro.getPlaca()) != null) {
            System.out.println("Carro ja cadastrado! Placa " + carro.getPlaca());
            return;
        }
        if (vagasDisponiveis() == 0) {
            System.out.println("Estacionamento cheio!");
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

    public double removerCarro(String placa) {
        return removerCarro(placa, null);
    }

    // cliente paga o estacionamento mesmo sem compra
    public double removerCarro(String placa, Cliente cliente) {
        Carro carro = buscarCarro(placa);

        if (carro == null) {
            System.out.println("Carro nao encontrado!");
            return 0.0;
        }

        carro.registrarSaida();
        double valor = calcularValor(carro);
        System.out.println("Valor a pagar: R$ " + valor);

        if (cliente != null && valor > 0) {
            cliente.pagarEstacionamento(valor);
        }

        carros.remove(carro);
        System.out.println("Carro saiu do estacionamento!");
        return valor;
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

    public static Estacionamento cadastrar(Scanner scanner) {
        System.out.println("\n--- CADASTRO DE ESTACIONAMENTO ---");
        System.out.print("Nome: ");
        String nome = scanner.nextLine();
        int capacidade = lerInteiro(scanner, "Capacidade (vagas): ");
        double valorHora = lerDouble(scanner, "Valor por hora: ");
        return new Estacionamento(nome, capacidade, valorHora);
    }

    private static double lerDouble(Scanner scanner, String string) {
        throw new UnsupportedOperationException("Unimplemented method 'lerDouble'");
    }

    private static int lerInteiro(Scanner scanner, String mensagem) {
        while (true) {
            System.out.print(mensagem);
            try {
                return Integer.parseInt(scanner.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("Valor invalido, digite um numero inteiro.");
            }
        }
    }

    // >>> MANTENHA AQUI O SEU METODO lerDouble EXATAMENTE COMO ESTA <<<
    // (ele nao foi alterado, por isso nao foi reescrito)
}