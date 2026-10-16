package br.edu.cesupa.app;

import br.edu.cesupa.model.Carro;
import br.edu.cesupa.model.Cliente;
import br.edu.cesupa.model.CompraEVenda;
import br.edu.cesupa.model.Estacionamento;
import br.edu.cesupa.model.Loja;
import br.edu.cesupa.model.Produto;
import br.edu.cesupa.model.Shopping;
import br.edu.cesupa.service.FluxoCompra;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    private static final Scanner scanner = new Scanner(System.in);

    private static final List<Shopping> shoppings = new ArrayList<>();
    private static final List<Loja> lojas = new ArrayList<>();
    private static final List<Produto> produtos = new ArrayList<>();
    private static final List<Cliente> clientes = new ArrayList<>();
    private static final List<CompraEVenda> compras = new ArrayList<>();
    private static final List<Carro> carros = new ArrayList<>();
    private static final List<Estacionamento> estacionamentos = new ArrayList<>();

    private static int proximoIdProduto = 1;

    public static void main(String[] args) {
        int opcao;

        do {
            exibirMenu();
            opcao = lerOpcao();
            processarOpcao(opcao);
        } while (opcao != 0);

        scanner.close();
        System.out.println("Encerrando o sistema. Ate logo!");
    }

    private static void exibirMenu() {
        System.out.println("\n===== SISTEMA SHOPPING =====");
        System.out.println("1 - Cadastrar Shopping");
        System.out.println("2 - Cadastrar Loja");
        System.out.println("3 - Cadastrar Cliente");
        System.out.println("4 - Cadastrar Carro");
        System.out.println("5 - Cadastrar Estacionamento");
        System.out.println("6 - Cadastrar Produto");
        System.out.println("7 - Listar tudo");
        System.out.println("8 - Realizar Fluxo de Compra");
        System.out.println("9 - Listar clientes por gasto");
        System.out.println("0 - Sair");
        System.out.print("Escolha uma opcao: ");
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
            case 5 -> cadastrarEstacionamento();
            case 6 -> cadastrarProduto();
            case 7 -> listarTudo();
            case 8 -> FluxoCompra.executar(scanner, lojas, produtos, clientes, carros, estacionamentos, compras);
            case 9 -> listarClientesPorGasto();
            case 0 -> { /* sai pelo while */ }
            default -> System.out.println("Opcao invalida, tente novamente.");
        }
    }

    private static void listarClientesPorGasto() {
        if (clientes.isEmpty()) {
            System.out.println("Nenhum cliente cadastrado.");
            return;
        }

        Cliente.ordenarPorGasto(clientes);

        System.out.println("\n===== CLIENTES POR GASTO (crescente) =====");
        for (Cliente c : clientes) {
            c.exibirCliente();
            System.out.println("Gasto total: R$ " + c.calcularTotalGasto());
            System.out.println("----------------");
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

        Loja loja = new Loja(nome, categoria, cnpj);
        lojas.add(loja);

        Shopping shopping = escolherShopping();
        if (shopping != null) {
            shopping.adicionarLoja(loja); // alimenta a composicao Shopping -> Loja
        }

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

    private static void cadastrarEstacionamento() {
        Estacionamento estacionamento = Estacionamento.cadastrar(scanner);
        estacionamentos.add(estacionamento);

        Shopping shopping = escolherShopping();
        if (shopping != null) {
            shopping.adicionarEstacionamento(estacionamento); // composicao Shopping -> Estacionamento
        }
    }

    private static void cadastrarProduto() {
        if (lojas.isEmpty()) {
            System.out.println("Cadastre uma loja antes de cadastrar produtos.");
            return;
        }

        System.out.println("\n--- CADASTRO DE PRODUTO ---");
        System.out.print("Nome do produto: ");
        String nome = scanner.nextLine();
        double preco = lerDouble("Preco: ");
        int estoque = lerInteiro("Estoque inicial: ");

        Produto produto = new Produto(proximoIdProduto++, nome, estoque, preco);

        System.out.println("Lojas disponiveis:");
        for (int i = 0; i < lojas.size(); i++) {
            System.out.println((i + 1) + " - " + lojas.get(i).getNome());
        }

        int indice = lerInteiro("Cadastrar em qual loja (numero): ") - 1;
        if (indice < 0 || indice >= lojas.size()) {
            System.out.println("Loja invalida, produto nao cadastrado.");
            return;
        }

        lojas.get(indice).cadastrarProduto(produto); // composicao Loja -> Produto
        produtos.add(produto); // lista geral usada pelo FluxoCompra
        System.out.println("Produto cadastrado com sucesso!");
    }

    private static Shopping escolherShopping() {
        if (shoppings.isEmpty()) {
            System.out.println("Nenhum shopping cadastrado - item nao vinculado a um shopping.");
            return null;
        }

        System.out.println("Shoppings disponiveis:");
        for (int i = 0; i < shoppings.size(); i++) {
            System.out.println((i + 1) + " - " + shoppings.get(i).getNome());
        }

        int indice = lerInteiro("Vincular a qual shopping (0 = nenhum): ") - 1;
        if (indice >= 0 && indice < shoppings.size()) {
            return shoppings.get(indice);
        }
        return null;
    }

    private static int lerInteiro(String mensagem) {
        while (true) {
            System.out.print(mensagem);
            try {
                return Integer.parseInt(scanner.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("Valor invalido, digite um numero inteiro.");
            }
        }
    }

    private static double lerDouble(String mensagem) {
        while (true) {
            System.out.print(mensagem);
            try {
                return Double.parseDouble(scanner.nextLine().trim().replace(",", "."));
            } catch (NumberFormatException e) {
                System.out.println("Valor invalido, digite um numero.");
            }
        }
    }

    private static void listarTudo() {
        System.out.println("\n===== RELATORIO GERAL =====");

        System.out.println("-- Shoppings (" + shoppings.size() + ") --");
        for (Shopping s : shoppings) {
            s.exibirShopping();
            System.out.println("----------------");
        }

        System.out.println("-- Lojas (" + lojas.size() + ") --");
        for (Loja l : lojas) {
            l.exibirLoja();
            System.out.println("----------------");
        }

        System.out.println("-- Produtos (" + produtos.size() + ") --");
        for (Produto p : produtos) {
            p.exibirProduto();
            System.out.println("----------------");
        }

        System.out.println("-- Clientes (" + clientes.size() + ") --");
        for (Cliente c : clientes) {
            c.exibirCliente();
            System.out.println("----------------");
        }

        System.out.println("-- Carros (" + carros.size() + ") --");
        for (Carro car : carros) {
            car.exibirCarro();
            System.out.println("----------------");
        }

        System.out.println("-- Estacionamentos (" + estacionamentos.size() + ") --");
        for (Estacionamento e : estacionamentos) {
            e.exibirEstacionamento();
            System.out.println("----------------");
        }

        System.out.println("-- Compras (" + compras.size() + ") --");
        for (CompraEVenda cv : compras) {
            cv.exibirCompraEVenda();
            System.out.println("----------------");
        }
    }
}