package br.edu.cesupa;

import br.edu.cesupa.model.*;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class CompraEVendaTest {
    @Test
    void aplicaDescontoNoLimiteDe500Reais() {
        CompraEVenda venda = new CompraEVenda("Loja", null, "venda");
        venda.adicionarProduto(new Produto(1, "Produto", 5, 500), 1, 500);
        assertEquals(50, venda.calcularDesconto(), 0.001);
        assertEquals(450, venda.getTotalComDesconto(), 0.001);
    }

    @Test
    void naoAplicaDescontoAbaixoDoLimite() {
        CompraEVenda venda = new CompraEVenda("Loja", null, "venda");
        venda.adicionarProduto(new Produto(1, "Produto", 5, 499.99), 1, 499.99);
        assertEquals(0, venda.calcularDesconto(), 0.001);
        assertEquals(499.99, venda.getTotalComDesconto(), 0.001);
    }

    @Test
    void aplicaDescontoAcimaDoLimite() {
        CompraEVenda venda = new CompraEVenda("Loja", null, "venda");
        venda.adicionarProduto(new Produto(1, "Produto", 5, 300), 2, 300);
        assertEquals(60, venda.calcularDesconto(), 0.001);
        assertEquals(540, venda.getTotalComDesconto(), 0.001);
    }

    @Test
    void vendaFinalizadaReduzEstoque() {
        Produto produto = new Produto(1, "Produto", 5, 20);
        CompraEVenda venda = new CompraEVenda("Loja", null, "venda");
        venda.adicionarProduto(produto, 2, 20);
        venda.finalizar();
        assertEquals(3, produto.getEstoque());
        assertTrue(venda.isFinalizada());
    }

    @Test
    void estoqueInsuficienteImpedeVendaSemAlterarProdutos() {
        Produto primeiro = new Produto(1, "Primeiro", 5, 20);
        Produto segundo = new Produto(2, "Segundo", 1, 10);
        CompraEVenda venda = new CompraEVenda("Loja", null, "venda");
        venda.adicionarProduto(primeiro, 2, 20);
        venda.adicionarProduto(segundo, 2, 10);
        assertThrows(IllegalStateException.class, venda::finalizar);
        assertEquals(5, primeiro.getEstoque());
        assertEquals(1, segundo.getEstoque());
        assertFalse(venda.isFinalizada());
    }

    @Test
    void somaQuantidadesDoMesmoProdutoAntesDeValidarEstoque() {
        Produto produto = new Produto(1, "Produto", 3, 20);
        CompraEVenda venda = new CompraEVenda("Loja", null, "venda");
        venda.adicionarProduto(produto, 2, 20);
        venda.adicionarProduto(produto, 2, 20);
        assertThrows(IllegalStateException.class, venda::finalizar);
        assertEquals(3, produto.getEstoque());
        assertFalse(venda.isFinalizada());
    }

    @Test
    void naoPermiteFinalizarDuasVezes() {
        Produto produto = new Produto(1, "Produto", 5, 20);
        CompraEVenda venda = new CompraEVenda("Loja", null, "venda");
        venda.adicionarProduto(produto, 2, 20);
        venda.finalizar();
        assertThrows(IllegalStateException.class, venda::finalizar);
        assertEquals(3, produto.getEstoque());
    }

    @Test
    void compraParaLojaAumentaEstoque() {
        Produto produto = new Produto(1, "Produto", 5, 20);
        CompraEVenda compra = new CompraEVenda("Loja", null, "compra");
        compra.adicionarProduto(produto, 2, 20);
        compra.finalizar();
        assertEquals(7, produto.getEstoque());
    }
}
