package br.edu.cesupa.service;

import java.util.List;
import java.util.Scanner;

public class FluxoCompra {

    public static void executar(Scanner scanner,
                                List<Shopping> shoppings,
                                List<Loja> lojas,
                                List<Cliente> clientes,
                                List<Carro> carros,
                                List<Estacionamento> estacionamentos) {

        System.out.println("\n===== PROCESSO COMPLETO =====");

        // ETAPA 1 — Estacionar
        Estacionamento estacionamento = escolher(estacionamentos, "estacionamento");
        Carro carro = escolher(carros, "carro");
        estacionamento.estacionar(carro);

        // ETAPA 2 — Comprar
        Cliente cliente = escolher(clientes, "cliente");
        Loja loja = escolher(lojas, "loja");
        System.out.print("Valor da compra: R$ ");
        double valor = Double.parseDouble(scanner.nextLine().trim());
        Compra compra = new Compra(cliente, loja, valor);   

        // ETAPA 3 — Pagar
        compra.pagar();

        // ETAPA 4 — Encerrar
        estacionamento.registrarSaida(carro);
    }
}