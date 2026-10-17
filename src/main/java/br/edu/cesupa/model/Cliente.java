package br.edu.cesupa.model;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Cliente {

    private String nome;
    private String cpf;
    private String email;
    private final ArrayList<CompraEVenda> compras;
    private int quantidadePontos;
    private double totalEstacionamento;

    public Cliente(String nome, String cpf, String email) {
        this.nome = nome;
        this.cpf = (cpf != null) ? cpf.replace(".", "").replace("-", "").replace("/", "") : null;
        this.email = email;
        this.compras = new ArrayList<>();
        this.quantidadePontos = 0;
        this.totalEstacionamento = 0.0;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public ArrayList<CompraEVenda> getCompras() {
        return compras;
    }

    public int getQuantidadePontos() {
        return quantidadePontos;
    }

    public double getTotalEstacionamento() {
        return totalEstacionamento;
    }

    // REGRA DE NEGOCIO: registra o pagamento do estacionamento, com ou sem compra
    public void pagarEstacionamento(double valor) {
        if (valor <= 0) {
            System.out.println("Valor invalido, pagamento nao registrado!");
            return;
        }
        this.totalEstacionamento += valor;
        System.out.println("Estacionamento pago pelo cliente " + nome + ": R$ " + valor);
    }

    public void cadastrarCompraEVenda(CompraEVenda c) {
        if (c == null || !c.isFinalizada()) {
            System.out.println("Compra invalida, nao cadastrada!");
            return;
        }
        compras.add(c);
        int pontosGanhos = (int) (c.getTotal() / 10.0);
        this.quantidadePontos += pontosGanhos;
        System.out.println("Compra cadastrada com sucesso! Pontos ganhos: " + pontosGanhos);
    }

    public double calcularTotalGasto() {
        double total = 0.0;
        for (CompraEVenda c : compras) {
            total += c.getTotal();
        }
        return total;
    }

    // REGRA DE NEGOCIO: gasto total considerando o desconto.
    // O calculo do desconto (a partir de R$ 500) fica na CompraEVenda, via getTotalComDesconto().
    public double calcularTotalGastoComDesconto() {
        double total = 0.0;
        for (CompraEVenda c : compras) {
            total += c.getTotalComDesconto();
        }
        return total;
    }

    // Ordena a propria lista recebida (crescente pelo gasto com desconto)
    public static void ordenarPorGasto(List<Cliente> clientes) {
        clientes.sort(Comparator.comparingDouble(Cliente::calcularTotalGastoComDesconto));
    }

    // Devolve uma copia ordenada (crescente) sem alterar a lista original
    public static List<Cliente> clientesOrdenadosPorGasto(List<Cliente> clientes) {
        List<Cliente> copia = new ArrayList<>(clientes);
        copia.sort(Comparator.comparingDouble(Cliente::calcularTotalGastoComDesconto));
        return copia;
    }

    public void exibirCliente() {
        System.out.println("Cliente: " + nome);
        System.out.println("CPF: " + cpf);
        System.out.println("E-mail: " + email);
        System.out.println("Pontos: " + quantidadePontos);
        System.out.println("Total pago em estacionamentos: R$ " + totalEstacionamento);
    }
}