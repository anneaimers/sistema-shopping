package br.edu.cesupa.app;

import br.edu.cesupa.model.Carro;
import br.edu.cesupa.model.Cliente;
import br.edu.cesupa.model.CompraEVenda;
import br.edu.cesupa.model.Estacionamento;
import br.edu.cesupa.model.Loja;
import br.edu.cesupa.model.Produto;
import br.edu.cesupa.model.Shopping;
import br.edu.cesupa.service.FluxoCompra;
import br.edu.cesupa.service.GerenciadorJSON;

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
        // ALTERADO: carrega TODAS as entidades salvas, nao apenas clientes
        GerenciadorJSON.importarTudo(shoppings, lojas, produtos, clientes, compras, carros, estacionamentos);

        // ALTERADO: recalcula o proximo ID de produto com base no maior ID importado,
        // evitando IDs duplicados depois de carregar os dados salvos
        for (Produto p : produtos) {
            if (p.getId() >= proximoIdProduto) {
                proximoIdProduto = p.getId() + 1;
            }
        }

        int opcao;

        do {
            exibirMenu();
            opcao = lerOpcao();
            processarOpcao(opcao);
        } while (opcao != 0);

        // ALTERADO: salva TODAS as entidades no encerramento, nao apenas clientes
        GerenciadorJSON.exportarTudo(shoppings, lojas, produtos, clientes, compras, carros, estacionamentos);
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
        System.out.println("9 - Editar Cliente");
        System.out.println("10 - Remover Produto");
        System.out.println("11 - Gerar Relatorios Obrigatorios");
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
            case 9 -> editarCliente();
            case 10 -> removerProduto();
            case 11 -> gerarRelatorios();
            case 0 -> {}
            default -> System.out.println("Opcao invalida, tente novamente.");
        }
    }

    private static void editarCliente() {
        System.out.println("\n--- EDITAR CLIENTE ---");
        if (clientes.isEmpty()) {
            System.out.println("Nenhum cliente cadastrado.");
            return;
        }

        System.out.print("Digite o CPF do cliente que deseja editar: ");
        String cpf = scanner.nextLine().trim();

        for (Cliente c : clientes) {
            if (c.getCpf().equals(cpf)) {
                System.out.println("Cliente encontrado: " + c.getNome());
                System.out.print("Novo Nome (ou Enter para manter): ");
                String novoNome = scanner.nextLine().trim();
                if (!novoNome.isEmpty()) {
                    c.setNome(novoNome);
                }

                System.out.print("Novo E-mail (ou Enter para manter): ");
                String novoEmail = scanner.nextLine().trim();
                if (!novoEmail.isEmpty()) {
                    c.setEmail(novoEmail);
                }

                System.out.println("Dados atualizados com sucesso!");
                return;
            }
        }
        System.out.println("Cliente com CPF " + cpf + " nao encontrado.");
    }

    private static void removerProduto() {
        System.out.println("\n--- REMOVER PRODUTO ---");
        if (produtos.isEmpty()) {
            System.out.println("Nenhum produto cadastrado.");
            return;
        }
        System.out.println("Produtos disponiveis:");
        for (int i = 0; i < produtos.size(); i++) {
            System.out.println((i + 1) + " - " + produtos.get(i).getNome());
        }

        int indice = lerInteiro("Qual produto deseja remover (numero): ") - 1;
        if (indice >= 0 && indice < produtos.size()) {
            Produto removido = produtos.remove(indice);
            System.out.println("Produto '" + removido.getNome() + "' removido com sucesso!");
        } else {
            System.out.println("Opcao invalida.");
        }
    }

    private static void gerarRelatorios() {
        System.out.println("\n===== RELATORIOS OBRIGATORIOS =====");

        if (produtos.isEmpty() && clientes.isEmpty()) {
            System.out.println("Dados insuficientes para gerar relatorios.");
            return;
        }

        double somaPrecos = 0;
        for (Produto p : produtos) {
            somaPrecos += p.getPreco();
        }
        double mediaPreco = produtos.isEmpty() ? 0 : somaPrecos / produtos.size();
        System.out.printf("1. MEDIA: Preco medio dos produtos: R$ %.2f\n", mediaPreco);

        double totalArrecadado = 0;
        for (CompraEVenda cv : compras) {
            totalArrecadado += cv.getValorTotal();
        }
        System.out.printf("2. TOTAL: Faturamento total do shopping: R$ %.2f\n", totalArrecadado);

        if (!produtos.isEmpty()) {
            Produto maisCaro = produtos.get(0);
            Produto maisBarato = produtos.get(0);
            for (Produto p : produtos) {
                if (p.getPreco() > maisCaro.getPreco()) maisCaro = p;
                if (p.getPreco() < maisBarato.getPreco()) maisBarato = p;
            }
            System.out.println("3. MAIOR/MENOR VALOR:");
            System.out.println("   - Produto Mais Caro: " + maisCaro.getNome() + " (R$ " + maisCaro.getPreco() + ")");
            System.out.println("   - Produto Mais Barato: " + maisBarato.getNome() + " (R$ " + maisBarato.getPreco() + ")");
        }

        System.out.println("4. RANKING:");
        listarClientesPorGasto();

        System.out.println("5. INDICADOR DA EQUIPE: Total de veiculos que ja visitaram o shopping: " + carros.size());
        System.out.println("===================================");
    }

    private static void listarClientesPorGasto() {
        if (clientes.isEmpty()) {
            System.out.println("   Nenhum cliente cadastrado para ranking.");
            return;
        }

        Cliente.ordenarPorGasto(clientes);

        for (int i = 0; i < clientes.size(); i++) {
            Cliente c = clientes.get(i);
            System.out.println("   " + (i + 1) + "o Lugar: " + c.getNome() + " - Gasto total: R$ " + c.calcularTotalGasto());
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
            shopping.adicionarLoja(loja);
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
            shopping.adicionarEstacionamento(estacionamento);
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

        lojas.get(indice).cadastrarProduto(produto);
        produtos.add(produto);
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
