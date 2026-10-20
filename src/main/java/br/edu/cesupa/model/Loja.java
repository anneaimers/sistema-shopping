package br.edu.cesupa.model;

public class Loja {

    private String nome;
    private String categoria;
    private transient String shopping;

    public Loja(String nome, String categoria, String cnpj) {
        setNome(nome);
        setCategoria(categoria);
        vincular(cnpj);
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        if (Shopping.normalizarTexto(nome).isEmpty()) {
            throw new IllegalArgumentException("Nome da loja é obrigatório.");
        }
        this.nome = nome.strip();
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        if (categoria == null || categoria.strip().isEmpty()) {
            this.categoria = "Não informada";
        } else {
            this.categoria = categoria.strip();
        }
    }

    public String getShopping() {
        return shopping;
    }

    public void vincular(String cnpj) {
        if (cnpj == null) {
            throw new IllegalArgumentException(
                    "A loja precisa estar vinculada a um shopping existente.");
        }
        this.shopping = cnpj;
    }

    public void validar() {
        setNome(nome);
        setCategoria(categoria);
    }

    @Override
    public String toString() {
        return "Loja{nome='" + nome + "', categoria='" + categoria + "'}";
    }

    public void cadastrarProduto(Produto produto) {
        
        throw new UnsupportedOperationException("Unimplemented method 'cadastrarProduto'");
    }

    public void exibirLoja() {
        throw new UnsupportedOperationException("Unimplemented method 'exibirLoja'");
    }

	public void registrarCompraEVenda(CompraEVenda venda) {
		throw new UnsupportedOperationException("Unimplemented method 'registrarCompraEVenda'");
	}
}