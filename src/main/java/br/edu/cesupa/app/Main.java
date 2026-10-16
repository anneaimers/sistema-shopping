package br.edu.cesupa.app;

import br.edu.cesupa.model.Carro;
import br.edu.cesupa.model.Cliente;
import br.edu.cesupa.model.Compra;
import br.edu.cesupa.model.Estacionamento;
import br.edu.cesupa.model.Loja;
import br.edu.cesupa.model.Shopping;
import br.edu.cesupa.service.FluxoCompra;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    private static final Scanner scanner = new Scanner(System.in);

    private static final List<Shopping> shoppings = new ArrayList<>();
    private static final List<Loja> lojas = new ArrayList<>();
    private static final List<Cliente> clientes = new ArrayList<>();
    private static final List<Compra> compras = new ArrayList<>();
    private static final List<Carro> carros = new ArrayList<>();
    private static final List<Estacionamento> estacionamentos = new ArrayList<>();

    public static void main(String[] args) {
        int opcao;

        do {
            exibirMenu();
            opcao = lerOpcao();
            processarOpcao(opcao);
        } while (opcao != 0);

        scanner.close();
        System.out.println("Encerrando o sistema. Até logo!");
    }

    private static void exibirMenu() {
        System.out.println("\n===== SISTEMA SHOPPING =====");
        System.out.println("1 - Cadastrar Shopping");
        System.out.println("2 - Cadastrar Loja");
        System.out.println("3 - Cadastrar Cliente");
        System.out.println("4 - Cadastrar Carro");
        System.out.println("5 - Cadastrar Estacionamento");
        System.out.println("6 - Listar tudo");
        System.out.println("7 - Realizar Fluxo de Compra");
        System.out.println("0 - Sair");
        System.out.print("Escolha uma opção: ");
    }

    private static int lerOpcao() {
        try {
            return Integer.parseInt(scanner.nextLine().trim());
        } catch (NumberFormatException e) {
            return -1;
        }
    }

    private static void processarOpcao(int opcao) {
        switch (opcao) {
            case 1 -> shoppings.add(Shopping.cadastrar(scanner));
            case 2 -> cadastrarLoja();
            case 3 -> cadastrarCliente();
            case 4 -> carros.add(Carro.cadastrar(scanner));
            case 5 -> estacionamentos.add(Estacionamento.cadastrar(scanner));
            case 6 -> listarTudo();
            case 7 -> FluxoCompra.executar(scanner, shoppings, lojas, clientes, carros, estacionamentos);
            case 0 -> { /* sai pelo while */ }
            default -> System.out.println("Opção inválida, tente novamente.");
        }
    }

    private static void cadastrarLoja() {
        System.out.println("\n--- CADASTRO DE LOJA ---");
        System.out.print("Nome da Loja: ");
        String nome = scanner.nextLine();
        System.out.print("Categoria: ");
        String categoria = scanner.nextLine();
        System.out.print("CNPJ: ");
        String cnpj = scanner.nextLine();

        lojas.add(new Loja(nome, categoria, cnpj));
        System.out.println("Loja cadastrada com sucesso!");
    }

    private static void cadastrarCliente() {
        System.out.println("\n--- CADASTRO DE CLIENTE ---");
        System.out.print("Nome: ");
        String nome = scanner.nextLine();
        System.out.print("CPF: ");
        String cpf = scanner.nextLine();
        System.out.print("E-mail: ");
        String email = scanner.nextLine();

        clientes.add(new Cliente(nome, cpf, email));
        System.out.println("Cliente cadastrado com sucesso!");
    }

    private static void listarTudo() {
        System.out.println("\n===== RELATÓRIO GERAL =====");
        System.out.println("-- Shoppings (" + shoppings.size() + ") --");
        shoppings.forEach(System.out::println);
        System.out.println("-- Lojas (" + lojas.size() + ") --");
        lojas.forEach(System.out::println);
        System.out.println("-- Clientes (" + clientes.size() + ") --");
        clientes.forEach(System.out::println);
        System.out.println("-- Carros (" + carros.size() + ") --");
        carros.forEach(System.out::println);
        System.out.println("-- Estacionamentos (" + estacionamentos.size() + ") --");
        estacionamentos.forEach(System.out::println);
        System.out.println("-- Compras (" + compras.size() + ") --");
        compras.forEach(System.out::println);
    }
}