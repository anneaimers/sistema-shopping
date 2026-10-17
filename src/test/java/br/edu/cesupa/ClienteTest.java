package br.edu.cesupa;

import br.edu.cesupa.model.*;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ClienteTest {
    @Test
    void pontosConsideramValorComDesconto() {
        Cliente cliente = new Cliente("Ana", "123", "ana@email.com");
        CompraEVenda venda = new CompraEVenda("Loja", cliente, "venda");
        venda.adicionarProduto(new Produto(1, "Produto", 5, 500), 1, 500);
        venda.finalizar();
        cliente.cadastrarCompraEVenda(venda);
        assertEquals(45, cliente.getQuantidadePontos());
        assertEquals(1, cliente.getCompras().size());
    }

    @Test
    void pontosAcumulamDescartandoFracaoDeCadaCompra() {
        Cliente cliente = new Cliente("Ana", "123", "ana@email.com");
        for (int i = 0; i < 2; i++) {
            CompraEVenda venda = new CompraEVenda("Loja", cliente, "venda");
            venda.adicionarProduto(new Produto(i, "Produto", 5, 29), 1, 29);
            venda.finalizar();
            cliente.cadastrarCompraEVenda(venda);
        }
        assertEquals(4, cliente.getQuantidadePontos());
        assertEquals(2, cliente.getCompras().size());
    }

    @Test
    void compraNaoFinalizadaNaoGeraPontosNemHistorico() {
        Cliente cliente = new Cliente("Ana", "123", "ana@email.com");
        CompraEVenda venda = new CompraEVenda("Loja", cliente, "venda");
        venda.adicionarProduto(new Produto(1, "Produto", 5, 500), 1, 500);
        cliente.cadastrarCompraEVenda(venda);
        assertEquals(0, cliente.getQuantidadePontos());
        assertTrue(cliente.getCompras().isEmpty());
    }
}
