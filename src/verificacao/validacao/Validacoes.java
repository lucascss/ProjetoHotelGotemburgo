package verificacao.validacao;

public class Validacoes {

	/**
	 * Esse metodo determina um padrao de data a ser seguido no sistema, prevenindo
	 * possiveis erros.
	 * 
	 * @param data
	 * @return boolean
	 */
	public static boolean validaData(String data) {
		String regex = "^(0[1-9]|[12][0-9]|3[01])/(0[1-9]|1[012])/[12][0-9]{3}$";
		return data.matches(regex);
	}

	/**
	 * Metodo no qual determina um padrao de email a ser seguido no sistema.
	 * 
	 * @param email
	 * @return boolean
	 */
	public static boolean validaEmail(String email) {
		String regex = "[a-z0-9._%+-]+@[a-z0-9.-]+\\.[a-z]{2,4}";
		return email.matches(regex);
	}

	/**
	 * Determina um padrao de nome a ser seguido no sistema.
	 * 
	 * @param nome
	 * @return boolean
	 */
	public static boolean validaNome(String nome) {
		String regex = "[A-Z][a-z]+[[ ][A-Z][a-z]+]*";
		return nome.matches(regex);
	}

	/**
	 * Determina o padrao de nome de quartos a serem seguidos pelo sistema.
	 * 
	 * @param quarto
	 * @param tipo
	 * @return boolean
	 */
	public static boolean validaQuarto(String idQuarto) {
		String regex = "[[0-9]]*[[A-Z]]*";
		return idQuarto.matches(regex);
	}

}
