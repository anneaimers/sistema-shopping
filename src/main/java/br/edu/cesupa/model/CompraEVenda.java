package br.edu.cesupa.model;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class CompraEVenda {

    private final String nomeloja;
    private final LocalDateTime data = LocalDateTime.now();
    private final String tipo;
    private boolean finalizada;
    private final ArrayList <Produto> produtos = new ArrayList<>();
    private final ArrayList<Integer> quantidades = new ArrayList<>();
    private final ArrayList <Double> valores = new ArrayList<>();


    public CompraEVenda(String nomeloja, String tipo) {
        this.nomeloja = nomeloja;
        this.tipo = tipo;
    }

    public void adicionarProduto(Produto produto, int quantidade , double Valores){
        if(finalizada){
            throw new IllegalStateException("compra ja finalizada");

        }

        if(produto == null || quantidade<=0 ||  Valores<0){
            throw new IllegalStateException("produto invalido");
        }

        produtos.add(produto);
        quantidades.add(quantidade);
        valores.add(Valores);
    }

    public double getSubTotal(int indice){
        return quantidades.get(indice) * valores.get(indice);
    }

    public double getTotal(){
        double total = 0;

        for (int i=0 ;i< quantidades.size(); i++){
            total+=getSubTotal(i);
        }
        return total;
    }

    public void finalizar(){
        if(finalizada){
            throw new IllegalStateException("compra ja finalizada");
        }

        if(produtos.isEmpty()){
            throw new IllegalStateException("e necessario um produto");
        }

        if(tipo.equalsIgnoreCase("compra")){


            for(int i =0 ;i<produtos.size();i++){
                produtos.get(i).adicionarEstoque(quantidades.get(i));
            }
            
        }

        else if(tipo.equalsIgnoreCase("venda")){

            for (int i = 0; i < produtos.size(); i++) {

                int quantidadeTotal = 0;

                for (int j = 0; j < produtos.size(); j++) {

                    if (produtos.get(i) == produtos.get(j)) {
                    quantidadeTotal += quantidades.get(j);
                    }
                }

                if (quantidadeTotal > produtos.get(i).getEstoque()) {
                throw new IllegalStateException("Estoque insuficiente");
                }
            }

        
            for (int i = 0; i < produtos.size(); i++) {
            produtos.get(i).removerEstoque(quantidades.get(i));
            }

        }
        finalizada = true;
    }

    public String getNomeLoja() {
        return nomeloja;
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

    public void setFinalizada(boolean finalizada) {
        this.finalizada = finalizada;
    }



}