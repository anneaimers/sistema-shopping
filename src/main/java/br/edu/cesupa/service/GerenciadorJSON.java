package br.edu.cesupa.service;

import br.edu.cesupa.model.Carro;
import br.edu.cesupa.model.Cliente;
import br.edu.cesupa.model.CompraEVenda;
import br.edu.cesupa.model.Estacionamento;
import br.edu.cesupa.model.Loja;
import br.edu.cesupa.model.Produto;
import br.edu.cesupa.model.Shopping;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializer;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.lang.reflect.Type;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class GerenciadorJSON {

    private static final String PASTA_DADOS = "dados";

    // O Gson nao entende LocalDateTime nativamente: registramos um conversor
    // simples que grava a data como texto ISO (ex.: 2026-10-16T14:30:15)
    private static final Gson GSON = new GsonBuilder()
            .registerTypeAdapter(LocalDateTime.class,
                    (JsonSerializer<LocalDateTime>) (data, tipo, contexto) -> new JsonPrimitive(data.toString()))
            .registerTypeAdapter(LocalDateTime.class,
                    (JsonDeserializer<LocalDateTime>) (json, tipo, contexto) -> LocalDateTime.parse(json.getAsString()))
            .setPrettyPrinting()
            .create();

    // ==================== CLIENTES (usados pela Main na abertura/encerramento) ====================

    // Carrega dados/clientes.json; se nao existir ou estiver invalido, devolve lista vazia
    public static List<Cliente> carregarClientes() {
        List<Cliente> lista = importar("clientes.json", Cliente.class);
        return (lista != null) ? lista : new ArrayList<>();
    }

    // Salva a lista de clientes em dados/clientes.json
    public static void salvarClientes(List<Cliente> clientes) {
        exportar(clientes, "clientes.json");
    }

    // ==================== EXPORTAR ====================

    public static <T> void exportar(List<T> lista, String nomeArquivo) {
        try {
            Files.createDirectories(Paths.get(PASTA_DADOS));
            Path caminho = Paths.get(PASTA_DADOS, nomeArquivo);
            Files.writeString(caminho, GSON.toJson(lista));
            System.out.println("Exportado: " + caminho + " (" + lista.size() + " registros)");
        } catch (IOException e) {
            System.out.println("Erro ao exportar " + nomeArquivo + ": " + e.getMessage());
        }
    }

    public static void exportarTudo(List<Shopping> shoppings, List<Loja> lojas, List<Produto> produtos,
                                    List<Cliente> clientes, List<CompraEVenda> compras,
                                    List<Carro> carros, List<Estacionamento> estacionamentos) {
        System.out.println("\n===== EXPORTANDO DADOS (JSON) =====");
        exportar(shoppings, "shoppings.json");
        exportar(lojas, "lojas.json");
        exportar(produtos, "produtos.json");
        exportar(clientes, "clientes.json");
        exportar(compras, "compras.json");
        exportar(carros, "carros.json");
        exportar(estacionamentos, "estacionamentos.json");
    }

    // ==================== IMPORTAR ====================

    // Devolve a lista lida do arquivo, ou null se o arquivo nao existir / estiver invalido
    public static <T> List<T> importar(String nomeArquivo, Class<T> classe) {
        Path caminho = Paths.get(PASTA_DADOS, nomeArquivo);

        if (!Files.exists(caminho)) {
            System.out.println("Arquivo nao encontrado: " + caminho + " (mantendo os dados atuais)");
            return null;
        }

        try {
            Type tipoLista = TypeToken.getParameterized(List.class, classe).getType();
            List<T> lista = GSON.fromJson(Files.readString(caminho), tipoLista);

            if (lista == null) {
                lista = new ArrayList<>();
            }
            System.out.println("Importado: " + caminho + " (" + lista.size() + " registros)");
            return lista;
        } catch (IOException | JsonSyntaxException e) {
            System.out.println("Erro ao importar " + nomeArquivo + ": " + e.getMessage());
            return null;
        }
    }

    public static void importarTudo(List<Shopping> shoppings, List<Loja> lojas, List<Produto> produtos,
                                    List<Cliente> clientes, List<CompraEVenda> compras,
                                    List<Carro> carros, List<Estacionamento> estacionamentos) {
        System.out.println("\n===== IMPORTANDO DADOS (JSON) =====");

        substituir(shoppings, importar("shoppings.json", Shopping.class));
        substituir(lojas, importar("lojas.json", Loja.class));
        substituir(produtos, importar("produtos.json", Produto.class));
        substituir(clientes, importar("clientes.json", Cliente.class));
        substituir(compras, importar("compras.json", CompraEVenda.class));
        substituir(carros, importar("carros.json", Carro.class));
        substituir(estacionamentos, importar("estacionamentos.json", Estacionamento.class));
    }

    // So troca o conteudo da lista se a importacao trouxe dados
    private static <T> void substituir(List<T> destino, List<T> novaLista) {
        if (novaLista == null) {
            return;
        }
        destino.clear();
        destino.addAll(novaLista);
    }
}