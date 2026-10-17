package br.edu.cesupa;

import br.edu.cesupa.model.*;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class EstacionamentoTest {
    @Test
    void estacionamentoCheioNaoAceitaOutroCarro() {
        Estacionamento estacionamento = new Estacionamento("Principal", 1, 10);
        Carro primeiro = new Carro("AAA1234", "Uno", "Branco");
        Carro segundo = new Carro("BBB1234", "Gol", "Preto");
        estacionamento.adicionarCarro(primeiro);
        estacionamento.adicionarCarro(segundo);
        assertEquals(0, estacionamento.vagasDisponiveis());
        assertSame(primeiro, estacionamento.buscarCarro("AAA1234"));
        assertNull(estacionamento.buscarCarro("BBB1234"));
        assertNull(segundo.getEntrada());
    }

    @Test
    void naoPermitePlacaDuplicadaMesmoComLetrasDiferentes() {
        Estacionamento estacionamento = new Estacionamento("Principal", 3, 10);
        Carro primeiro = new Carro("AAA1234", "Uno", "Branco");
        estacionamento.adicionarCarro(primeiro);
        estacionamento.adicionarCarro(new Carro("aaa1234", "Gol", "Preto"));
        assertEquals(2, estacionamento.vagasDisponiveis());
        assertSame(primeiro, estacionamento.buscarCarro("aaa1234"));
    }

    @Test
    void cobraUmaHoraMinimaNaSaidaImediata() {
        Estacionamento estacionamento = new Estacionamento("Principal", 2, 10);
        Carro carro = new Carro("AAA1234", "Uno", "Branco");
        estacionamento.adicionarCarro(carro);
        double cobrado = estacionamento.removerCarro(carro.getPlaca());
        assertEquals(10, cobrado, 0.001);
    }

    @Test
    void entradaRegistraHorarioEOcupaVaga() {
        Estacionamento estacionamento = new Estacionamento("Principal", 2, 10);
        Carro carro = new Carro("AAA1234", "Uno", "Branco");
        assertNull(carro.getEntrada());
        estacionamento.adicionarCarro(carro);
        assertNotNull(carro.getEntrada());
        assertNull(carro.getSaida());
        assertEquals(1, estacionamento.vagasDisponiveis());
        assertSame(carro, estacionamento.buscarCarro(carro.getPlaca()));
    }

    @Test
    void carroPodeRetornarDepoisDeSair() {
        Estacionamento estacionamento = new Estacionamento("Principal", 1, 10);
        Carro carro = new Carro("AAA1234", "Uno", "Branco");
        estacionamento.adicionarCarro(carro);
        estacionamento.removerCarro(carro.getPlaca());
        assertNotNull(carro.getSaida());
        assertEquals(1, estacionamento.vagasDisponiveis());
        estacionamento.adicionarCarro(carro);
        assertNull(carro.getSaida());
        assertNotNull(carro.getEntrada());
        assertEquals(0, estacionamento.vagasDisponiveis());
        assertSame(carro, estacionamento.buscarCarro(carro.getPlaca()));
    }

    @Test
    void saidaLiberaVagaECobraMesmoSemCompra() {
        Estacionamento estacionamento = new Estacionamento("Principal", 1, 10);
        Cliente cliente = new Cliente("Ana", "123", "ana@email.com");
        Carro carro = new Carro("AAA1234", "Uno", "Branco");
        estacionamento.adicionarCarro(carro);
        double cobrado = estacionamento.removerCarro("AAA1234", cliente);
        assertEquals(10, cobrado, 0.001);
        assertEquals(cobrado, cliente.getTotalEstacionamento(), 0.001);
        assertEquals(1, estacionamento.vagasDisponiveis());
        assertNull(estacionamento.buscarCarro("AAA1234"));
        assertTrue(cliente.getCompras().isEmpty());
        assertNotNull(carro.getSaida());
    }

    @Test
    void carroInexistenteNaoGeraCobranca() {
        Estacionamento estacionamento = new Estacionamento("Principal", 1, 10);
        Cliente cliente = new Cliente("Ana", "123", "ana@email.com");
        assertEquals(0, estacionamento.removerCarro("ZZZ9999", cliente), 0.001);
        assertEquals(0, cliente.getTotalEstacionamento(), 0.001);
        assertEquals(1, estacionamento.vagasDisponiveis());
    }
}
