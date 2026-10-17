package br.edu.cesupa.app;

import br.edu.cesupa.model.Carro;
import br.edu.cesupa.model.Shopping;
import br.edu.cesupa.model.Estacionamento;
import br.edu.cesupa.model.Cliente;
import br.edu.cesupa.model.Loja;
import br.edu.cesupa.model.Produto;
import java.util.List;

public class AppTeste {

    public static int carregar(List<Loja> lojas, List<Produto> produtos,
                              List<Cliente> clientes, int proximoIdProduto) {
        for (Produto produto : produtos) {
            if (produto.getId() >= proximoIdProduto) {
                proximoIdProduto = produto.getId() + 1;
            }
        }

        Loja lojaRoupas = new Loja("Aurora Moda e Acessorios", "Moda e vestuario", "12.345.678/0001-95");
        Loja lojaEletronicos = new Loja("Conecta Eletronicos", "Informatica e eletronicos", "23.456.789/0001-95");
        Loja lojaPapelaria = new Loja("Papelaria Horizonte", "Papelaria e material escolar", "34.567.890/0001-30");

        lojas.add(lojaRoupas);
        lojas.add(lojaEletronicos);
        lojas.add(lojaPapelaria);

        Produto camiseta = new Produto(proximoIdProduto++, "Camiseta de algodao tamanho M", 30, 59.90);
        Produto calcaJeans = new Produto(proximoIdProduto++, "Calca jeans feminina tamanho 38", 18, 159.90);
        Produto mochila = new Produto(proximoIdProduto++, "Mochila casual preta", 12, 129.90);
        Produto foneBluetooth = new Produto(proximoIdProduto++, "Fone de ouvido Bluetooth", 20, 149.90);
        Produto teclado = new Produto(proximoIdProduto++, "Teclado mecanico USB", 10, 289.90);
        Produto mouse = new Produto(proximoIdProduto++, "Mouse sem fio", 25, 79.90);
        Produto caderno = new Produto(proximoIdProduto++, "Caderno universitario 10 materias", 50, 34.90);
        Produto canetas = new Produto(proximoIdProduto++, "Kit com 3 canetas esferograficas", 80, 12.90);
        Produto estojo = new Produto(proximoIdProduto++, "Estojo escolar com dois compartimentos", 35, 24.90);

        lojaRoupas.cadastrarProduto(camiseta);
        lojaRoupas.cadastrarProduto(calcaJeans);
        lojaRoupas.cadastrarProduto(mochila);
        lojaEletronicos.cadastrarProduto(foneBluetooth);
        lojaEletronicos.cadastrarProduto(teclado);
        lojaEletronicos.cadastrarProduto(mouse);
        lojaPapelaria.cadastrarProduto(caderno);
        lojaPapelaria.cadastrarProduto(canetas);
        lojaPapelaria.cadastrarProduto(estojo);

        produtos.add(camiseta);
        produtos.add(calcaJeans);
        produtos.add(mochila);
        produtos.add(foneBluetooth);
        produtos.add(teclado);
        produtos.add(mouse);
        produtos.add(caderno);
        produtos.add(canetas);
        produtos.add(estojo);

        Cliente anaCarolina = new Cliente("Ana Carolina Lima", "123.456.789-09", "ana.carolina@example.com");
        Cliente brunoHenrique = new Cliente("Bruno Henrique Costa", "234.567.890-92", "bruno.costa@example.com");
        Cliente camilaFernandes = new Cliente("Camila Fernandes Rocha", "345.678.901-75", "camila.rocha@example.com");
        Cliente diegoAlmeida = new Cliente("Diego Almeida Santos", "456.789.012-49", "diego.santos@example.com");
        Cliente julianaMartins = new Cliente("Juliana Martins Oliveira", "567.890.123-03", "juliana.oliveira@example.com");

        clientes.add(anaCarolina);
        clientes.add(brunoHenrique);
        clientes.add(camilaFernandes);
        clientes.add(diegoAlmeida);
        clientes.add(julianaMartins);

        Loja lojaCalcados = new Loja("Passo Certo Calcados", "Calcados e bolsas", "45.000.001/0001-74");
        lojas.add(lojaCalcados);
        Loja lojaEsportes = new Loja("Movimento Esportes", "Artigos esportivos", "45.000.002/0001-19");
        lojas.add(lojaEsportes);
        Loja lojaCosmeticos = new Loja("Essencia Beleza", "Cosmeticos e perfumaria", "45.000.003/0001-63");
        lojas.add(lojaCosmeticos);
        Loja lojaLivraria = new Loja("Livraria Entre Paginas", "Livros e leitura", "45.000.004/0001-08");
        lojas.add(lojaLivraria);
        Loja lojaCasa = new Loja("Casa Viva Decoracao", "Casa e decoracao", "45.000.005/0001-52");
        lojas.add(lojaCasa);
        Produto vestido = new Produto(proximoIdProduto++, "Vestido midi floral tamanho M", 14, 189.90);
        lojaRoupas.cadastrarProduto(vestido);
        produtos.add(vestido);

        Produto jaqueta = new Produto(proximoIdProduto++, "Jaqueta jeans tamanho G", 9, 229.90);
        lojaRoupas.cadastrarProduto(jaqueta);
        produtos.add(jaqueta);

        Produto carregador = new Produto(proximoIdProduto++, "Carregador USB-C 30W", 32, 99.90);
        lojaEletronicos.cadastrarProduto(carregador);
        produtos.add(carregador);

        Produto caixaSom = new Produto(proximoIdProduto++, "Caixa de som Bluetooth portatil", 12, 219.90);
        lojaEletronicos.cadastrarProduto(caixaSom);
        produtos.add(caixaSom);

        Produto lapisCor = new Produto(proximoIdProduto++, "Caixa de lapis de cor 24 cores", 42, 39.90);
        lojaPapelaria.cadastrarProduto(lapisCor);
        produtos.add(lapisCor);

        Produto agenda = new Produto(proximoIdProduto++, "Agenda semanal capa dura", 22, 49.90);
        lojaPapelaria.cadastrarProduto(agenda);
        produtos.add(agenda);

        Produto tenisCasual = new Produto(proximoIdProduto++, "Tenis casual branco numero 38", 18, 199.90);
        lojaCalcados.cadastrarProduto(tenisCasual);
        produtos.add(tenisCasual);

        Produto sandalia = new Produto(proximoIdProduto++, "Sandalia feminina numero 37", 16, 89.90);
        lojaCalcados.cadastrarProduto(sandalia);
        produtos.add(sandalia);

        Produto sapatoSocial = new Produto(proximoIdProduto++, "Sapato social masculino numero 41", 10, 249.90);
        lojaCalcados.cadastrarProduto(sapatoSocial);
        produtos.add(sapatoSocial);

        Produto chinelo = new Produto(proximoIdProduto++, "Chinelo de borracha numero 39", 35, 39.90);
        lojaCalcados.cadastrarProduto(chinelo);
        produtos.add(chinelo);

        Produto bolsa = new Produto(proximoIdProduto++, "Bolsa transversal caramelo", 12, 159.90);
        lojaCalcados.cadastrarProduto(bolsa);
        produtos.add(bolsa);

        Produto bola = new Produto(proximoIdProduto++, "Bola de futebol de campo", 20, 119.90);
        lojaEsportes.cadastrarProduto(bola);
        produtos.add(bola);

        Produto garrafa = new Produto(proximoIdProduto++, "Garrafa termica 750 ml", 28, 69.90);
        lojaEsportes.cadastrarProduto(garrafa);
        produtos.add(garrafa);

        Produto tapeteYoga = new Produto(proximoIdProduto++, "Tapete de yoga antiderrapante", 15, 89.90);
        lojaEsportes.cadastrarProduto(tapeteYoga);
        produtos.add(tapeteYoga);

        Produto halteres = new Produto(proximoIdProduto++, "Par de halteres de 3 kg", 8, 109.90);
        lojaEsportes.cadastrarProduto(halteres);
        produtos.add(halteres);

        Produto camisetaEsportiva = new Produto(proximoIdProduto++, "Camiseta esportiva tamanho M", 25, 79.90);
        lojaEsportes.cadastrarProduto(camisetaEsportiva);
        produtos.add(camisetaEsportiva);

        Produto perfume = new Produto(proximoIdProduto++, "Perfume floral feminino 100 ml", 14, 179.90);
        lojaCosmeticos.cadastrarProduto(perfume);
        produtos.add(perfume);

        Produto hidratante = new Produto(proximoIdProduto++, "Hidratante corporal 400 ml", 30, 49.90);
        lojaCosmeticos.cadastrarProduto(hidratante);
        produtos.add(hidratante);

        Produto shampoo = new Produto(proximoIdProduto++, "Shampoo hidratante 300 ml", 45, 29.90);
        lojaCosmeticos.cadastrarProduto(shampoo);
        produtos.add(shampoo);

        Produto protetorSolar = new Produto(proximoIdProduto++, "Protetor solar facial FPS 50", 24, 59.90);
        lojaCosmeticos.cadastrarProduto(protetorSolar);
        produtos.add(protetorSolar);

        Produto batom = new Produto(proximoIdProduto++, "Batom cremoso rosa nude", 36, 24.90);
        lojaCosmeticos.cadastrarProduto(batom);
        produtos.add(batom);

        Produto romance = new Produto(proximoIdProduto++, "Livro de romance Caminhos do Amanha", 18, 44.90);
        lojaLivraria.cadastrarProduto(romance);
        produtos.add(romance);

        Produto livroInfantil = new Produto(proximoIdProduto++, "Livro infantil Aventuras na Floresta", 25, 29.90);
        lojaLivraria.cadastrarProduto(livroInfantil);
        produtos.add(livroInfantil);

        Produto livroJava = new Produto(proximoIdProduto++, "Livro Introducao a Programacao Java", 12, 89.90);
        lojaLivraria.cadastrarProduto(livroJava);
        produtos.add(livroJava);

        Produto livroReceitas = new Produto(proximoIdProduto++, "Livro Receitas para o Dia a Dia", 15, 54.90);
        lojaLivraria.cadastrarProduto(livroReceitas);
        produtos.add(livroReceitas);

        Produto livroFantasia = new Produto(proximoIdProduto++, "Livro de fantasia O Reino das Estrelas", 20, 59.90);
        lojaLivraria.cadastrarProduto(livroFantasia);
        produtos.add(livroFantasia);

        Produto almofada = new Produto(proximoIdProduto++, "Almofada decorativa bege 45 cm", 22, 49.90);
        lojaCasa.cadastrarProduto(almofada);
        produtos.add(almofada);

        Produto luminaria = new Produto(proximoIdProduto++, "Luminaria de mesa LED", 14, 129.90);
        lojaCasa.cadastrarProduto(luminaria);
        produtos.add(luminaria);

        Produto jogoCopos = new Produto(proximoIdProduto++, "Jogo com 6 copos de vidro", 18, 69.90);
        lojaCasa.cadastrarProduto(jogoCopos);
        produtos.add(jogoCopos);

        Produto vaso = new Produto(proximoIdProduto++, "Vaso de ceramica branco 20 cm", 16, 59.90);
        lojaCasa.cadastrarProduto(vaso);
        produtos.add(vaso);

        Produto manta = new Produto(proximoIdProduto++, "Manta para sofa cinza", 12, 119.90);
        lojaCasa.cadastrarProduto(manta);
        produtos.add(manta);

        Cliente cliente6 = new Cliente("Gabriel Pereira Almeida", "610.000.001-68", "gabriel.almeida@example.com");
        clientes.add(cliente6);
        Cliente cliente7 = new Cliente("Mariana Ribeiro Costa", "610.000.002-49", "mariana.costa@example.com");
        clientes.add(cliente7);
        Cliente cliente8 = new Cliente("Lucas Henrique Barbosa", "610.000.003-20", "lucas.barbosa@example.com");
        clientes.add(cliente8);
        Cliente cliente9 = new Cliente("Beatriz Souza Mendes", "610.000.004-00", "beatriz.mendes@example.com");
        clientes.add(cliente9);
        Cliente cliente10 = new Cliente("Rafael Augusto Nunes", "610.000.005-91", "rafael.nunes@example.com");
        clientes.add(cliente10);
        Cliente cliente11 = new Cliente("Fernanda Araujo Dias", "610.000.006-72", "fernanda.dias@example.com");
        clientes.add(cliente11);
        Cliente cliente12 = new Cliente("Pedro Henrique Carvalho", "610.000.007-53", "pedro.carvalho@example.com");
        clientes.add(cliente12);
        Cliente cliente13 = new Cliente("Larissa Gomes Ferreira", "610.000.008-34", "larissa.ferreira@example.com");
        clientes.add(cliente13);
        Cliente cliente14 = new Cliente("Matheus Oliveira Castro", "610.000.009-15", "matheus.castro@example.com");
        clientes.add(cliente14);
        Cliente cliente15 = new Cliente("Isabela Rocha Freitas", "610.000.010-59", "isabela.freitas@example.com");
        clientes.add(cliente15);
        Cliente cliente16 = new Cliente("Gustavo Martins Lopes", "610.000.011-30", "gustavo.lopes@example.com");
        clientes.add(cliente16);
        Cliente cliente17 = new Cliente("Amanda Cristina Silva", "610.000.012-10", "amanda.silva@example.com");
        clientes.add(cliente17);
        Cliente cliente18 = new Cliente("Thiago Moreira Alves", "610.000.013-00", "thiago.alves@example.com");
        clientes.add(cliente18);
        Cliente cliente19 = new Cliente("Leticia Fernandes Pinto", "610.000.014-82", "leticia.pinto@example.com");
        clientes.add(cliente19);
        Cliente cliente20 = new Cliente("Vinicius Lima Cardoso", "610.000.015-63", "vinicius.cardoso@example.com");
        clientes.add(cliente20);

        return proximoIdProduto;
    }

    public static void carregarCarros(List<Carro> carros) {
        Carro carro1 = new Carro("QVA1A23", "Fiat Argo", "Branco");
        carros.add(carro1);
        Carro carro2 = new Carro("QVB2B34", "Volkswagen Gol", "Prata");
        carros.add(carro2);
        Carro carro3 = new Carro("QVC3C45", "Chevrolet Onix", "Preto");
        carros.add(carro3);
        Carro carro4 = new Carro("QVD4D56", "Hyundai HB20", "Vermelho");
        carros.add(carro4);
        Carro carro5 = new Carro("QVE5E67", "Toyota Corolla", "Cinza");
        carros.add(carro5);
        Carro carro6 = new Carro("QVF6F78", "Honda Civic", "Azul");
        carros.add(carro6);
        Carro carro7 = new Carro("QVG7G89", "Renault Kwid", "Branco");
        carros.add(carro7);
        Carro carro8 = new Carro("QVH8H90", "Jeep Renegade", "Preto");
        carros.add(carro8);
        Carro carro9 = new Carro("QVI9I01", "Nissan Kicks", "Prata");
        carros.add(carro9);
        Carro carro10 = new Carro("QVJ0J12", "Volkswagen Polo", "Cinza");
        carros.add(carro10);
        Carro carro11 = new Carro("QVK1K23", "Fiat Mobi", "Vermelho");
        carros.add(carro11);
        Carro carro12 = new Carro("QVL2L34", "Chevrolet Tracker", "Branco");
        carros.add(carro12);
        Carro carro13 = new Carro("QVM3M45", "Toyota Yaris", "Prata");
        carros.add(carro13);
        Carro carro14 = new Carro("QVN4N56", "Honda HR-V", "Preto");
        carros.add(carro14);
        Carro carro15 = new Carro("QVO5O67", "Renault Duster", "Marrom");
        carros.add(carro15);
        Carro carro16 = new Carro("QVP6P78", "Ford Ka", "Azul");
        carros.add(carro16);
        Carro carro17 = new Carro("QVQ7Q89", "Peugeot 208", "Amarelo");
        carros.add(carro17);
        Carro carro18 = new Carro("QVR8R90", "Citroen C3", "Branco");
        carros.add(carro18);
        Carro carro19 = new Carro("QVS9S01", "Fiat Cronos", "Cinza");
        carros.add(carro19);
        Carro carro20 = new Carro("QVT0T12", "Volkswagen T-Cross", "Verde");
        carros.add(carro20);
    }

    public static void carregarShoppingsEEstacionamentos(List<Shopping> shoppings,
                                                        List<Estacionamento> estacionamentos,
                                                        List<Loja> lojas) {
        Shopping shoppingPatioCentral = new Shopping("Shopping Patio Central",
                "61.234.567/0001-57", "Avenida das Palmeiras, 1200 - Centro, Belem - PA");
        Shopping shoppingParqueNorte = new Shopping("Shopping Parque Norte",
                "72.345.678/0001-68", "Avenida dos Ipes, 850 - Parque Norte, Belem - PA");
        Shopping shoppingBoulevardSul = new Shopping("Shopping Boulevard Sul",
                "83.456.789/0001-10", "Avenida das Acacias, 2400 - Jardim Sul, Belem - PA");

        shoppings.add(shoppingPatioCentral);
        shoppings.add(shoppingParqueNorte);
        shoppings.add(shoppingBoulevardSul);

        Estacionamento estacionamentoCentralCoberto = new Estacionamento("Patio Central - Coberto", 150, 12.00);
        Estacionamento estacionamentoCentralExterno = new Estacionamento("Patio Central - Externo", 100, 8.00);
        Estacionamento estacionamentoNorteCoberto = new Estacionamento("Parque Norte - Coberto", 120, 10.00);
        Estacionamento estacionamentoNorteExterno = new Estacionamento("Parque Norte - Externo", 80, 7.00);
        Estacionamento estacionamentoSulCoberto = new Estacionamento("Boulevard Sul - Coberto", 200, 15.00);
        Estacionamento estacionamentoSulExterno = new Estacionamento("Boulevard Sul - Externo", 100, 9.00);

        estacionamentos.add(estacionamentoCentralCoberto);
        estacionamentos.add(estacionamentoCentralExterno);
        estacionamentos.add(estacionamentoNorteCoberto);
        estacionamentos.add(estacionamentoNorteExterno);
        estacionamentos.add(estacionamentoSulCoberto);
        estacionamentos.add(estacionamentoSulExterno);

        shoppingPatioCentral.adicionarEstacionamento(estacionamentoCentralCoberto);
        shoppingPatioCentral.adicionarEstacionamento(estacionamentoCentralExterno);
        shoppingParqueNorte.adicionarEstacionamento(estacionamentoNorteCoberto);
        shoppingParqueNorte.adicionarEstacionamento(estacionamentoNorteExterno);
        shoppingBoulevardSul.adicionarEstacionamento(estacionamentoSulCoberto);
        shoppingBoulevardSul.adicionarEstacionamento(estacionamentoSulExterno);

        for (int i = 0; i < lojas.size(); i++) {
            int destino = i % 3;
            if (destino == 0) {
                shoppingPatioCentral.adicionarLoja(lojas.get(i));
            } else if (destino == 1) {
                shoppingParqueNorte.adicionarLoja(lojas.get(i));
            } else {
                shoppingBoulevardSul.adicionarLoja(lojas.get(i));
            }
        }
    }
}
