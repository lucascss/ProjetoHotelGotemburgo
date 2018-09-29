package restaurante.comida;

import verificacao.excecoes.ValoresException;

/**
 * Classe abstrata que descreve a abstracao de "comida" no sistema.
 * 
 */
public abstract class Comida {

	protected String nome;
	protected String descricao;

	/**
	 * Construtor da classe.
	 * 
	 * @param nome
	 * @param descricao
	 */
	public Comida(String nome, String descricao) {
		this.nome = nome;
		this.descricao = descricao;
	}

	public abstract String getDescricao();

	public abstract void setNome(String nome) throws ValoresException;

	public abstract void setDescricao(String descricao) throws ValoresException;

	public abstract double getPreco();

	public String getNome() {
		return nome;
	}

}
