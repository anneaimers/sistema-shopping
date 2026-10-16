package br.edu.cesupa.service;

import br.edu.cesupa.model.Carro;
import br.edu.cesupa.model.Cliente;
import br.edu.cesupa.model.CompraEVenda;
import br.edu.cesupa.model.Estacionamento;
import br.edu.cesupa.model.Loja;
import br.edu.cesupa.model.Produto;

import java.util.List;
import java.util.Scanner;

public class FluxoCompra {

    public static void executar(Scanner scanner,
                                List<Loja> lojas,
                                List<Produto> produtos,
                                List<Cliente> clientes,
                                List<Carro> carros,
                                List<Estacionamento> estacionamentos,
                                List<CompraEVenda> comprasEVendas) {

        System.out.println("\n===== PROCESSO COMPLETO =====");
        System.out.println("Estacionar -> Vender -> Registrar -> Sair do estacionamento");

        if (lojas.isEmpty() || produtos.isEmpty() || clientes.isEmpty()
                || carros.isEmpty() || estacionamentos.isEmpty()) {
            System.out.println("Cadastre antes: 1 loja, 1 produto, 1 cliente, 1 carro e 1 estacionamento.");
            return;
        }

        // ETAPA 1 - ESTACIONAR (adicionarCarro valida lotacao e placa duplicada)
        System.out.println("\n--- Etapa 1: Estacionar ---");
        Estacionamento estacionamento = escolher(scanner, estacionamentos, "estacionamento");
        Carro carro = escolher(scanner, carros, "carro");

        estacionamento.adicionarCarro(carro);

        if (estacionamento.buscarCarro(carro.getPlaca()) == null) {
            System.out.println("Nao foi possivel estacionar. Processo cancelado.");
            return;
        }

        // ETAPA 2 - VENDER (documento com varios itens, preco de tabela do Produto)
        System.out.println("\n--- Etapa 2: Vender produtos ---");
        Loja loja = escolher(scanner, lojas, "loja");
        Cliente cliente = escolher(scanner, clientes, "cliente");

        CompraEVenda venda = new CompraEVenda(loja.getNome(), "venda");

        boolean adicionando = true;
        while (adicionando) {
            Produto produto = escolher(scanner, produtos, "produto");
            int quantidade = lerInteiro(scanner, "Quantidade: ");

            if (quantidade <= 0) {
                System.out.println("Quantidade invalida, tente novamente.");
                continue;
            }

            venda.adicionarProduto(produto, quantidade, produto.getPreco());
            System.out.println(produto.getNome() + " x" + quantidade + " adicionado ao carrinho!");

            System.out.print("Adicionar outro produto? (s/n): ");
            adicionando = scanner.nextLine().trim().equalsIgnoreCase("s");
        }

        // ETAPA 3 - FINALIZAR E REGISTRAR (finalizar da baixa no estoque)
        System.out.println("\n--- Etapa 3: Finalizar e registrar ---");
        try {
            venda.finalizar();
        } catch (IllegalStateException e) {
            System.out.println("Venda nao finalizada: " + e.getMessage());
            System.out.println("(O carro continua estacionado.)");
            return;
        }

        double totalVenda = venda.getTotal();
        System.out.println("Total da venda: R$ " + totalVenda);

        loja.registrarCompraEVenda(venda);        // alimenta faturamento e ticket medio
        cliente.cadastrarCompraEVenda(venda);     // alimenta os pontos do cliente
        comprasEVendas.add(venda);                // historico geral do sistema

        System.out.println("Pontos do cliente: " + cliente.getQuantidadePontos());

        // ETAPA 4 - ENCERRAR (saida do carro + cobranca da vaga por hora)
        System.out.println("\n--- Etapa 4: Saida do estacionamento ---");
        estacionamento.removerCarro(carro.getPlaca());
        double valorVaga = estacionamento.calcularValor(carro);

        // RESUMO FINAL
        System.out.println("\n===== RESUMO DO PROCESSO =====");
        System.out.println("Cliente: " + cliente.getNome());
        System.out.println("Loja: " + loja.getNome());
        System.out.println("Data: " + venda.getData());
        System.out.println("Compra (venda): R$ " + totalVenda);
        System.out.println("Estacionamento: R$ " + valorVaga);
        System.out.println("TOTAL GASTO: R$ " + (totalVenda + valorVaga));
        System.out.println("Pontos acumulados pelo cliente: " + cliente.getQuantidadePontos());
    }

    private static <T> T escolher(Scanner scanner, List<T> lista, String tipo) {
        System.out.println("\nEscolha o " + tipo + ":");
        for (int i = 0; i < lista.size(); i++) {
            System.out.println((i + 1) + " - " + lista.get(i));
        }

        while (true) {
            int indice = lerInteiro(scanner, "Numero: ") - 1;
            if (indice >= 0 && indice < lista.size()) {
                return lista.get(indice);
            }
            System.out.println("Opcao invalida, tente novamente.");
        }
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
}