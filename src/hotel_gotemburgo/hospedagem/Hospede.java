package hotel_gotemburgo.hospedagem;

import hotel_gotemburgo.hospedagem.cartao.CartaoFidelidade;
import hotel_gotemburgo.hospedagem.cartao.PadraoStrategy;
import hotel_gotemburgo.hospedagem.cartao.PremiumStrategy;
import hotel_gotemburgo.hospedagem.cartao.VipStrategy;

import java.util.ArrayList;
import java.util.Iterator;

import verificacao.excecoes.Excecoes;
import verificacao.excecoes.StringException;
import verificacao.excecoes.ValorException;
import verificacao.excecoes.ValoresException;

/**
 * Classe responsavel por um objeto que representa um hospede do Hotel. O
 * hospede possui atributos (nome, email e ano de nascimento) e metodos que
 * retornam e alteram esses atributos.
 *
 */
public class Hospede {

	private String nome;
	private String email;
	private String dataNascimento;
	private ArrayList<Estadia> estadias;
	private CartaoFidelidade cartao;
	private int pontos;

	/**
	 * O construtor recebe 3 parametros, descritos abaixo, e realiza checagem de
	 * excecao em todos eles
	 * 
	 * @param nomeHospede
	 * @param emailHospede
	 * @param dataNascHospede
	 * @throws StringException
	 */
	public Hospede(String nomeHospede, String emailHospede, String dataNascHospede) throws StringException {

		Excecoes.checaString(nomeHospede, "O nome do hospede nao pode ser nulo ou vazio.");
		Excecoes.checaString(emailHospede, "O email do hospede nao pode ser nulo ou vazio.");
		Excecoes.checaString(dataNascHospede, "A data de nascimento do hospede nao pode ser nula ou vazia.");

		this.nome = nomeHospede;
		this.email = emailHospede;
		this.dataNascimento = dataNascHospede;
		this.pontos = 0;
		this.estadias = new ArrayList<Estadia>();
		this.cartao = new PadraoStrategy();
	}

	public void atualizaPontuacao(int valor) {
		int recompensa = this.cartao.adicionarPontos(valor);
		this.setPontos(this.pontos + recompensa);
	}

	/**
	 * Esse metodo eh utilizado para adicionar uma estadia ao array e calcular os
	 * gastos. Tem como entrada uma estadia, verifica se essa estadia eh nula depois
	 * calcula os gastos e adiciona no Array e nao retorna nada
	 * 
	 * @param estadia
	 * @throws ValoresException
	 */
	public void addEstadia(Estadia estadia) throws ValoresException {
		if (estadia == null) {
			throw new ValorException("Estadia nao pode ser null");
		}
		this.getEstadias().add(estadia);
	}

	/**
	 * Esse metodo verifica se o id esta contido na lista de estadias e depois
	 * remove o id passado como parametro
	 * 
	 * @param idQuarto
	 */
	public boolean removeEstadia(String idQuarto) {
		ArrayList<Estadia> estadias = this.getEstadias();
		Iterator<Estadia> i = estadias.iterator();
		while (i.hasNext()) {
			Estadia estadia = i.next();
			if (estadia.getIdQuarto().equalsIgnoreCase(idQuarto)) {
				i.remove();
				return true;
			}
		}
		return false;
	}

	public double aplicarDesconto(double valor) {
		return this.cartao.aplicarDesconto(valor);
	}

	public int adicionarPontos(double valor) {
		return this.cartao.adicionarPontos(valor);
	}

	/**
	 * Este metodo upa o cartao fidelidade de um Hospede. Todo hospede inicia com
	 * seu cartao padrao, no qual em meio a suas despesas e relacoes com as
	 * atividades do Hotel, pode upar de Padrao para Premium ou diretamente para
	 * VIP. Tambem ha possibilidade de passar de Premuium para VIP.
	 */
	public void upgradeFidelidade() {
		if (this.pontos >= 350 && this.pontos <= 1000) {
			this.cartao = new PremiumStrategy();
		} else if (this.pontos > 1000) {
			this.cartao = new VipStrategy();
		}
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) throws StringException {
		Excecoes.checaString(nome,
				"Erro na atualizacao do cadastro de Hospede. Nome do(a) hospede nao pode ser vazio.");
		this.nome = nome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) throws StringException {
		Excecoes.checaString(email, "Erro na atualizacao do cadastro de Hospede. Email do(a) hospede esta invalido.");
		this.email = email;
	}

	public String getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(String dataNascimento) throws ValoresException {
		Excecoes.checaString(dataNascimento,
				"Erro na atualizacao do cadastro de Hospede. Data de Nascimento do(a) hospede nao pode ser vazio.");
		this.dataNascimento = dataNascimento;
	}

	public CartaoFidelidade getCartao() {
		return this.cartao;
	}

	public int getPontos() {
		return pontos;
	}

	public void setPontos(int pontos) {
		this.pontos = pontos;
		this.upgradeFidelidade();

	}

	public String getRepresentaEstadias() {

		String info = "";
		for (Estadia estadia : this.estadias) {
			info += "," + estadia.getIdQuarto();
		}
		
		return info.replaceFirst(",", "");

	}

	public ArrayList<Estadia> getEstadias() {
		return this.estadias;
	}

	public int getQtdEstadias() {
		return this.estadias.size();
	}

	public double getGastosTotal() {

		double total = 0.0;
		for (Estadia estadia : estadias) {
			total += estadia.getCalculaEstadia();
		}
		return total;
	}

	public double getValorEstadia(String idQuarto) {

		for (Estadia estadia : this.estadias) {
			if (estadia.getIdQuarto().equalsIgnoreCase(idQuarto)) {
				return estadia.getCalculaEstadia();
			}
		}
		return 0.0;
	}

	/**
	 * Representacao em string de um objeto do tipo "Hospede".
	 */
	@Override
	public String toString() {
		return String.format("%s: %s (%s).", this.getNome(), this.getEmail(), this.getDataNascimento());
	}

	/**
	 * Verifica a se dois objetos do tipo "Hospede"; caso afirmativo, se possuam o
	 * mesmo email.
	 */
	@Override
	public boolean equals(Object outroObjeto) {

		if (outroObjeto instanceof Hospede) {
			Hospede outroHospede = (Hospede) outroObjeto;
			if (this.getEmail().equalsIgnoreCase(outroHospede.getEmail())) {
				return true;
			}
		}
		return false;
	}

	/**
	 * Codigo hash de um objeto do tipo "Hospede".
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		return prime * result + ((email == null) ? 0 : email.hashCode());
	}
}