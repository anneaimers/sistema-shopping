package br.edu.cesupa.model;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Locale;
import java.util.Scanner;

public class Carro {

    private String placa;
    private String modelo;
    private String cor;
    private LocalDateTime entrada;
    private LocalDateTime saida;

    public Carro(String placa, String modelo, String cor) {
        this.placa = normalizarPlaca(placa);
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

    // ==================== ADICIONADO: normalizacao de placa ====================

    // Deixa a placa sempre no mesmo formato: so letras e numeros, em maiuscula.
    // Assim "abc-1234", " ABC1234 " e "abc1234" viram todos "ABC1234".
    public static String normalizarPlaca(String placa) {
        if (placa == null) {
            return "";
        }
        return placa.replaceAll("[^A-Za-z0-9]", "").toUpperCase(Locale.ROOT);
    }

    // Usado na importacao do JSON, porque o Gson nao passa pelo construtor
    public void setPlaca(String placa) {
        this.placa = normalizarPlaca(placa);
    }

    // Aceita o padrao antigo (ABC1234) e o Mercosul (ABC1D23)
    public boolean placaValida() {
        if (placa == null || placa.isEmpty()) {
            return false;
        }
        return placa.matches("[A-Z]{3}[0-9][A-Z0-9][0-9]{2}");
    }

    // Dois carros sao iguais quando a placa normalizada e igual
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof Carro)) {
            return false;
        }
        Carro outro = (Carro) obj;
        if (this.placa == null || outro.placa == null) {
            return false;
        }
        return this.placa.equals(outro.placa);
    }

    @Override
    public int hashCode() {
        return (placa == null) ? 0 : placa.hashCode();
    }
}