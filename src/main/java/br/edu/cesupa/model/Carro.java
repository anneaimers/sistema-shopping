package br.edu.cesupa.model;

import java.util.ArrayList;

public class Carro {

    private String nome;
    private ArrayList<Loja> lojas = new ArrayList<>();
    private Estacionamento estacionamento;

    public void Shopping(String nome, int totalVagas) {
        this.nome = nome;
        this.estacionamento = new Estacionamento();
    }

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public ArrayList<Loja> getLojas() {
		return lojas;
	}

	public void setLojas(ArrayList<Loja> lojas) {
		this.lojas = lojas;
	}

	public Estacionamento getEstacionamento() {
		return estacionamento;
	}

	public void setEstacionamento(Estacionamento estacionamento) {
		this.estacionamento = estacionamento;
	}

    


}
