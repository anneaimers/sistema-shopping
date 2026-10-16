package br.edu.cesupa.model;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Scanner;

public class Carro {

    private String placa;
    private String modelo;
    private String cor;
    private LocalDateTime entrada;
    private LocalDateTime saida;

    public Carro(String placa, String modelo, String cor) {
        this.placa = placa;
        this.modelo = modelo;
        this.cor = cor;
    }

    public String getPlaca() {
        return placa;
    }

    public String getModelo() {
        return modelo;
    }

    public String getCor() {
        return cor;
    }

    public LocalDateTime getEntrada() {
        return entrada;
    }

    public LocalDateTime getSaida() {
        return saida;
    }

    public void registrarEntrada() {
        entrada = LocalDateTime.now();
        saida = null;
    }

    public void registrarSaida() {
        saida = LocalDateTime.now();
    }

    public long calcularTempo() {
        if (entrada == null) {
            return 0;
        }
        if (saida == null) {
            return ChronoUnit.MINUTES.between(entrada, LocalDateTime.now());
        }
        return ChronoUnit.MINUTES.between(entrada, saida);
    }

    public static Carro cadastrar(Scanner scanner) {
        System.out.println("\n--- CADASTRO DE CARRO ---");
        System.out.print("Placa: ");
        String placa = scanner.nextLine();
        System.out.print("Modelo: ");
        String modelo = scanner.nextLine();
        System.out.print("Cor: ");
        String cor = scanner.nextLine();
        return new Carro(placa, modelo, cor);
    }

    public void exibirCarro() {
        System.out.println("Placa: " + placa);
        System.out.println("Modelo: " + modelo);
        System.out.println("Cor: " + cor);
        System.out.println("Entrada: " + entrada);
        System.out.println("Saida: " + saida);
    }
}