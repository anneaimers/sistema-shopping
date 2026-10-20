package br.edu.cesupa.model;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class CompraEVenda {

    private final String nomeLoja;
    private final Cliente cliente;
    private final LocalDateTime data = LocalDateTime.now();
    private final String tipo;
    private boolean finalizada;
    private final ArrayList<Produto> produtos = new ArrayList<>();
    private final ArrayList<Integer> quantidades = new ArrayList<>();
    private final ArrayList<Double> valores = new ArrayList<>();
    public static final double VALOR_MINIMO_DESCONTO = 500.0;
    public static final double PERCENTUAL_DESCONTO = 0.10; // 10%

    public CompraEVenda(String nomeLoja, Cliente cliente, String tipo) {
        this.nomeLoja = Shopping.texto(nomeLoja);
        this.cliente = cliente;
        this.tipo = tipo;
    }

    public void adicionarProduto(Produto produto, int quantidade, double valor) {
        if (finalizada) {
            throw new IllegalStateException("Compra ja finalizada!");
        }
        if (produto == null || quantidade <= 0 || !Double.isFinite(valor) || valor < 0) {
            throw new IllegalArgumentException("Produto/quantidade/valor invalidos!");
        }
        produtos.add(produto);
        quantidades.add(quantidade);
        valores.add(valor);
    }

    public double getSubTotal(int indice) {
        return quantidades.get(indice) * valores.get(indice);
    }

    public double getTotal() {
        double total = 0;
        for (int i = 0; i < quantidades.size(); i++) {
            total += getSubTotal(i);
        }
        return total;
    }

    // REGRA DE NEGOCIO: desconto aplicado a partir de R$ 500,00
    public double calcularDesconto() {
        double total = getTotal();
        if (total >= VALOR_MINIMO_DESCONTO) {
            return total * PERCENTUAL_DESCONTO;
        }
        return 0.0;
    }

    public double getTotalComDesconto() {
        return getTotal() - calcularDesconto();
    }

    public void finalizar() {
        if (finalizada) {
            throw new IllegalStateException("Compra ja finalizada!");
        }
        if (produtos.isEmpty()) {
            throw new IllegalStateException("E necessario pelo menos um produto!");
        }

        if (tipo.equalsIgnoreCase("compra")) {
            for (int i = 0; i < produtos.size(); i++) {
                produtos.get(i).adicionarEstoque(quantidades.get(i));
            }
        } else if (tipo.equalsIgnoreCase("venda")) {
            // valida o total por produto (o mesmo produto pode aparecer 2x na lista)
            for (int i = 0; i < produtos.size(); i++) {
                int quantidadeTotal = 0;
                for (int j = 0; j < produtos.size(); j++) {
                    if (produtos.get(i) == produtos.get(j)) {
                        quantidadeTotal += quantidades.get(j);
                    }
                }
                if (quantidadeTotal > produtos.get(i).getEstoque()) {
                    throw new IllegalStateException("Estoque insuficiente: " + produtos.get(i).getNome());
                }
            }
            for (int i = 0; i < produtos.size(); i++) {
                produtos.get(i).removerEstoque(quantidades.get(i));
            }
        }
        finalizada = true;
    }

    public String getNomeLoja() {
        return nomeLoja;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public LocalDateTime getData() {
        return data;
    }

    public String getTipo() {
        return tipo;
    }

    public boolean isFinalizada() {
        return finalizada;
    }

    public void exibirCompraEVenda() {
        System.out.println("Tipo: " + tipo);
        System.out.println("Loja: " + nomeLoja);
        System.out.println("Cliente: " + (cliente != null ? cliente.getNome() : "-"));
        System.out.println("Data: " + data);
        System.out.println("Total: R$ " + getTotal());
        System.out.println("Finalizada: " + finalizada);
        System.out.println("Desconto: R$ " + calcularDesconto());
        System.out.println("Total com desconto: R$ " + getTotalComDesconto());
    }
}