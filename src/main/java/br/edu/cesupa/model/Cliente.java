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
        this.nome = Shopping.texto(nome);
        this.cpf = Cliente.cpf(cpf);
        this.email = Cliente.email(email);
        this.compras = new ArrayList<>();
        this.quantidadePontos = 0;
        this.totalEstacionamento = 0.0;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = Shopping.texto(nome );
    }

    public String getCpf() {
        return cpf;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = Cliente.email(email);
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
        if (!Double.isFinite(valor) || valor <= 0) {
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
        int pontosGanhos = (int) (c.getTotalComDesconto() / 10.0);
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

    // REGRA DE NEGOCIO: gasto total considerando o desconto
    // O calculo do desconto (a partir de R$ 500) fica na CompraEVenda, via getTotalComDesconto()
    public double calcularTotalGastoComDesconto() {
        double total = 0.0;
        for (CompraEVenda c : compras) {
            total += c.getTotalComDesconto();
        }
        return total;
    }

    // ordena a propria lista recebida (crescente pelo gasto com desconto)
    public static void ordenarPorGasto(List<Cliente> clientes) {
        clientes.sort(Comparator.comparingDouble(Cliente::calcularTotalGastoComDesconto));
    }

    // devolve uma copia ordenada (crescente) sem alterar a lista original
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

    // regras compartilhada de validacao
    
    public static String cpf(String valor) {
        String texto = Shopping.semEspacos(valor, "CPF");
        if (!texto.matches("[0-9]{11}|[0-9]{3}\\.[0-9]{3}\\.[0-9]{3}-[0-9]{2}"))
            throw new IllegalArgumentException("CPF deve ter 11 digitos, com ou sem mascara!");
        return texto.replace(".", "").replace("-", "");
    }


    public static String email(String valor) {
        String texto = Shopping.semEspacos(valor, "E-mail").toLowerCase(java.util.Locale.ROOT);
        if (!texto.matches("[a-z0-9!#$%&'*+/=?^_`{|}~.-]+@[a-z0-9](?:[a-z0-9-]*[a-z0-9])?(?:\\.[a-z0-9](?:[a-z0-9-]*[a-z0-9])?)+")
                || texto.startsWith(".") || texto.contains("..") || texto.contains(".@"))
            throw new IllegalArgumentException("E-mail invalido! Exemplo: nome@dominio.com");
        return texto;
    }


    public static void validarCliente(List<Cliente> clientes, Cliente atual, String cpf, String email) {
        String documento = Cliente.cpf(cpf);
        String endereco = Cliente.email(email);
        for (Cliente existente : clientes) {
            if (existente == atual) continue;
            if (Cliente.cpf(existente.getCpf()).equals(documento))
                throw new IllegalArgumentException("CPF ja cadastrado!");
            if (Cliente.email(existente.getEmail()).equals(endereco))
                throw new IllegalArgumentException("E-mail ja cadastrado!");
        }
    }


    public static void adicionarCliente(List<Cliente> clientes, Cliente cliente) {
        validarCliente(clientes, null, cliente.getCpf(), cliente.getEmail());
        clientes.add(cliente);
    }


    public static void editarCliente(List<Cliente> clientes, Cliente cliente, String nome, String email) {
        if (!clientes.contains(cliente)) throw new IllegalArgumentException("Cliente nao cadastrado!");
        String novoNome = Shopping.texto(nome);
        String novoEmail = Cliente.email(email);
        validarCliente(clientes, cliente, cliente.getCpf(), novoEmail);
        cliente.setNome(novoNome);
        cliente.setEmail(novoEmail);
    }

}
