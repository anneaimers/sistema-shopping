package br.edu.cesupa.model;

import java.text.Normalizer;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

public class Shopping {

    private String nome;
    private String cnpj;
    private String endereco;
    private ArrayList<Loja> lojas;
    private ArrayList<Estacionamento> estacionamentos;
    private ArrayList<Cliente> clientes;

    public Shopping(String nome, String cnpj, String endereco) {
        this.nome = nome;
        this.cnpj = (cnpj != null) ? cnpj.replace(".", "").replace("-", "").replace("/", "") : null;
        this.endereco = endereco;
        this.lojas = new ArrayList<>();
        this.estacionamentos = new ArrayList<>();
        this.clientes = new ArrayList<>();
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCnpj() {
        return cnpj;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public ArrayList<Loja> getLojas() {
        return lojas;
    }

    public ArrayList<Estacionamento> getEstacionamentos() {
        return estacionamentos;
    }

    public ArrayList<Cliente> getClientes() {
        return clientes;
    }

    public void adicionarCliente(Cliente cliente) {
        if (cliente == null) {
            System.out.println("Cliente invalido, nao cadastrado!");
            return;
        }
        clientes.add(cliente);
        System.out.println("Cliente vinculado ao shopping com sucesso!");
    }

    // REGRA DE NEGOCIO: ranking de clientes por gasto em compras (com desconto),
    // do maior para o menor. A ordenacao crescente fica na Cliente (clientesOrdenadosPorGasto)
    // e o percurso de tras para frente entrega o 1o lugar = maior gasto.
    public void exibirRankingClientes() {
        System.out.println("RANKING: clientes por gasto em compras (com desconto)");
        if (clientes.isEmpty()) {
            System.out.println("Nenhum cliente cadastrado.");
            return;
        }
        List<Cliente> ranking = Cliente.clientesOrdenadosPorGasto(clientes);
        int posicao = 1;
        for (int i = ranking.size() - 1; i >= 0; i--) {
            Cliente cliente = ranking.get(i);
            System.out.printf("%d - %s: R$ %.2f%n", posicao,
                    cliente.getNome(),
                    cliente.calcularTotalGastoComDesconto());
            posicao++;
        }
    }

    public Loja buscarLoja(String nome) {
        for (Loja loja : lojas) {
            if (loja.getNome().equalsIgnoreCase(nome)) {
                return loja;
            }
        }
        return null;
    }

    public void adicionarLoja(Loja loja) {
        if (loja == null || loja.getNome() == null || loja.getNome().isBlank()) {
            System.out.println("Loja invalida, nao cadastrada!");
            return;
        }
        if (buscarLoja(loja.getNome()) != null) {
            System.out.println("Loja ja cadastrada!");
            return;
        }
        lojas.add(loja);
        System.out.println("Loja cadastrada no shopping com sucesso!");
    }

    public void removerLoja(String nome) {
        Loja loja = buscarLoja(nome);
        if (loja == null) {
            System.out.println("Loja nao encontrada!");
            return;
        }
        lojas.remove(loja);
        System.out.println("Loja removida!");
    }

    public void adicionarEstacionamento(Estacionamento estacionamento) {
        if (estacionamento == null) {
            System.out.println("Estacionamento invalido, nao cadastrado!");
            return;
        }
        estacionamentos.add(estacionamento);
        System.out.println("Estacionamento cadastrado no shopping com sucesso!");
    }

    public int vagasDisponiveis() {
        int total = 0;
        for (Estacionamento estacionamento : estacionamentos) {
            total += estacionamento.vagasDisponiveis();
        }
        return total;
    }

    public void listarLojas() {
        if (lojas.isEmpty()) {
            System.out.println("Nenhuma loja cadastrada.");
            return;
        }
        for (Loja loja : lojas) {
            loja.exibirLoja();
            System.out.println("----------------");
        }
    }

    public void exibirShopping() {
        System.out.println("Nome: " + nome);
        System.out.println("CNPJ: " + cnpj);
        System.out.println("Endereco: " + endereco);
        System.out.println("Quantidade de lojas: " + lojas.size());
        System.out.println("Vagas livres no shopping: " + vagasDisponiveis());
    }

    public static Shopping cadastrar(Scanner scanner) {
        System.out.println("\n--- CADASTRO DE SHOPPING ---");
        System.out.print("Nome: ");
        String nome = scanner.nextLine();
        System.out.print("CNPJ: ");
        String cnpj = scanner.nextLine();
        System.out.print("Endereco: ");
        String endereco = scanner.nextLine();
        return new Shopping(nome, cnpj, endereco);
    }

    // ==================== ADICIONADO: identidade do shopping ====================

    // Remove espaco invisivel (U+00A0), acento, espaco duplicado e maiuscula.
    // " Shopping  Center " e "SHOPPING CENTER" viram "shopping center".
    public static String normalizarTexto(String texto) {
        if (texto == null) {
            return "";
        }
        String semEspacoInvisivel = texto.replace('\u00A0', ' ');
        String semAcento = Normalizer.normalize(semEspacoInvisivel, Normalizer.Form.NFD)
                .replaceAll("\\p{M}", "");
        return semAcento.strip()
                .replaceAll("\\s+", " ")
                .toLowerCase(Locale.ROOT);
    }

    // Chave de comparacao: nome + endereco normalizados
    public String chaveIdentificacao() {
        return normalizarTexto(nome) + "|" + normalizarTexto(endereco);
    }

    // Dois shoppings sao o mesmo se tiverem a mesma chave OU o mesmo CNPJ
    public boolean mesmaIdentidade(Shopping outro) {
        if (outro == null) {
            return false;
        }
        if (this.chaveIdentificacao().equals(outro.chaveIdentificacao())) {
            return true;
        }
        String cnpjA = normalizarTexto(this.cnpj);
        String cnpjB = normalizarTexto(outro.cnpj);
        return !cnpjA.isEmpty() && cnpjA.equals(cnpjB);
    }

    public static String texto(String valor) {
    if (valor == null) {
        return "";
    }
    String semEspacoInvisivel = valor.replace('\u00A0', ' ');
    String semAcento = java.text.Normalizer
            .normalize(semEspacoInvisivel, java.text.Normalizer.Form.NFD)
            .replaceAll("\\p{M}", "");
    return semAcento.strip()
            .replaceAll("\\s+", " ")
            .toLowerCase(java.util.Locale.ROOT);
}

    public static String semEspacos(String valor, String string) {
        throw new UnsupportedOperationException("Unimplemented method 'semEspacos'");
    }

    public static double valorNaoNegativo(double preco, String string) {
        throw new UnsupportedOperationException("Unimplemented method 'valorNaoNegativo'");
    }
}