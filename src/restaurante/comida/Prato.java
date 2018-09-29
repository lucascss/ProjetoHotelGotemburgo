package restaurante.comida;

import verificacao.excecoes.Excecoes;
import verificacao.excecoes.StringException;
import verificacao.excecoes.ValorException;
import verificacao.excecoes.ValoresException;

/**
 * Define um objeto do tipo Prato, que possui um nome, um preco e uma descricao.
 * 
 */
public class Prato extends Comida {

	private double preco;

	/**
	 * O Construtor recebe o nome do prato, preco e sua descricao.
	 * 
	 * @param nome
	 * @param preco
	 * @param descricao
	 * @throws ValoresException
	 */
	public Prato(String nome, double preco, String descricao) throws ValoresException {

		super(nome, descricao);
		Excecoes.checaString(nome, "O nome do prato nao pode ser nulo ou vazio.");
		Excecoes.checaString(descricao, "A descricao do prato nao pode ser vazia ou nula.");
		Excecoes.checaDouble(preco, "O preco do prato nao pode ser negativo.");

		this.preco = preco;
	}

	public double getPreco() {
		return this.preco;
	}

	public String getDescricao() {
		return descricao;
	}

	/**
	 * Altera o atributo "nome" do prato pelo valor passado como parâmetro.
	 * 
	 * @param nome
	 * @throws ValoresException
	 */
	public void setNome(String nome) throws ValoresException {
		Excecoes.checaString(nome, "O nome do prato nao pode ser nulo ou vazio.");
		this.nome = nome;
	}

	/**
	 * Altera o atributo "descricao" do prato pelo valor passado como parâmetro.
	 * 
	 * @param descricao
	 * @throws ValoresException
	 */
	public void setDescricao(String descricao) throws ValoresException {
		if (descricao == null || nome.trim().isEmpty()) {
			throw new StringException("O nome do prato nao pode ser nulo ou vazio.");
		}
		this.descricao = descricao;
	}

	/**
	 * Altera o atributo "preco" de um prato pelo valor passado como parâmetro.
	 * 
	 * @param preco
	 * @throws ValoresException
	 */
	public void setPreco(double preco) throws ValoresException {
		if (preco < 0) {
			throw new ValorException("O preco do prato nao pode ser negativo.");
		}

		this.preco = preco;
	}

	/**
	 * Representacao em String de um objeto "Prato".
	 */
	@Override
	public String toString() {
		return String.format("%s: %s. (R$ %.2f)", getNome(), getDescricao(), getPreco());
	}

	/**
	 * Verifica se dois pratos sao iguais, caso possuam o mesmo nome.
	 */
	@Override
	public boolean equals(Object anotherObject) {

		if (anotherObject == null)
			return false;

		if (!anotherObject.getClass().equals(this.getClass()))
			return false;

		Prato outro = (Prato) anotherObject;
		return this.getNome().equalsIgnoreCase(outro.getNome());
	}

	/**
	 * Retorna o codigo hash de um objeto do tipo "Prato".
	 */
	@Override
	public int hashCode() {

		final int PRIME = 7;
		int result = 1;
		return PRIME * result + (this.nome == null ? 0 : this.nome.hashCode());
	}

}
