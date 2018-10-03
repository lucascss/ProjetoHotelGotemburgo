package hotel_gotemburgo;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import facade.Facade;
import verificacao.excecoes.HotelGotemburgoException;

public class TestHotel {

	private Facade facade;

	private String cliente_1;
	private String cliente_2;
	private String cliente_3;
	private String cliente_4;
	private String cliente_5;

	@Before
	public void test() throws HotelGotemburgoException {

		facade = new Facade();

		// Criando clientes.

		cliente_1 = facade.cadastraHospede("Lo Porco Voador", "resenhas_del_porco@lol.com", "13/10/1978");
		cliente_2 = facade.cadastraHospede("Insano Bubassalto", "altos_assalto@hotmail.com", "13/10/1991");
		cliente_3 = facade.cadastraHospede("Wisla Canibal", "toda_calibalesca@bol.com", "31/01/1970");
		cliente_4 = facade.cadastraHospede("Amigao Calinfon", "amigao98@yahoo.com.br", "12/02/1995");
		cliente_5 = facade.cadastraHospede("Mateus Mangueira", "mateus_mangueira@hotmail.com", "25/10/1996");

		// Hospedando os clientes.

		facade.realizaCheckin(cliente_1, 10, "101A", "Simples");
		facade.realizaCheckin(cliente_1, 5, "102A", "Simples");
		facade.realizaCheckin(cliente_2, 5, "102B", "Simples");
		facade.realizaCheckin(cliente_3, 2, "25A", "Presidencial");
		facade.realizaCheckin(cliente_3, 2, "25C", "Presidencial");
		facade.realizaCheckin(cliente_4, 5, "103C", "Luxo");
	}

	@Test
	public void testCadastraHospede() throws HotelGotemburgoException {

		Assert.assertEquals("resenhas_del_porco@lol.com", cliente_1);
		Assert.assertEquals("altos_assalto@hotmail.com", cliente_2);
		Assert.assertEquals("toda_calibalesca@bol.com", cliente_3);
		Assert.assertEquals("amigao98@yahoo.com.br", cliente_4);
		Assert.assertEquals("mateus_mangueira@hotmail.com", cliente_5);
	}

	@Test
	public void testeRemoveHospede() throws HotelGotemburgoException {
		Assert.assertTrue(facade.removeHospede(cliente_5));
	}

	@Test
	public void testeGetInfoHospedePorNome() throws HotelGotemburgoException {

		Assert.assertEquals("Lo Porco Voador", facade.getInfoHospede(cliente_1, "Nome"));
		Assert.assertEquals("Insano Bubassalto", facade.getInfoHospede(cliente_2, "Nome"));
		Assert.assertEquals("Wisla Canibal", facade.getInfoHospede(cliente_3, "Nome"));
		Assert.assertEquals("Amigao Calinfon", facade.getInfoHospede(cliente_4, "Nome"));

		Assert.assertNotEquals("Cacador de Insanidade", facade.getInfoHospede(cliente_1, "Nome"));
		Assert.assertNotEquals("Olavo do X-Oleo", facade.getInfoHospede(cliente_3, "Nome"));
	}

	@Test
	public void testGetInfoHospedePorEmail() throws HotelGotemburgoException {

		Assert.assertEquals("resenhas_del_porco@lol.com", facade.getInfoHospede(cliente_1, "Email"));
		Assert.assertEquals("altos_assalto@hotmail.com", facade.getInfoHospede(cliente_2, "Email"));
		Assert.assertEquals("toda_calibalesca@bol.com", facade.getInfoHospede(cliente_3, "Email"));
		Assert.assertEquals("amigao98@yahoo.com.br", facade.getInfoHospede(cliente_4, "Email"));

		Assert.assertNotEquals("insano_cacador@gmail.com", facade.getInfoHospede(cliente_1, "Email"));
		Assert.assertNotEquals("triple_x@hotmail.com", facade.getInfoHospede(cliente_3, "Email"));
	}

	@Test
	public void testGetInfoHospedePorDataAnoNascimento() throws HotelGotemburgoException {

		Assert.assertEquals("13/10/1978", facade.getInfoHospede(cliente_1, "Data de Nascimento"));
		Assert.assertEquals("13/10/1991", facade.getInfoHospede(cliente_2, "Data de Nascimento"));
		Assert.assertEquals("31/01/1970", facade.getInfoHospede(cliente_3, "Data de Nascimento"));
		Assert.assertEquals("12/02/1995", facade.getInfoHospede(cliente_4, "Data de Nascimento"));

		Assert.assertNotEquals("12/10/1996", facade.getInfoHospede(cliente_1, "Data de Nascimento"));
		Assert.assertNotEquals("13/02/1994", facade.getInfoHospede(cliente_4, "Data de Nascimento"));
	}

	@Test
	public void testeGetInfoHospedagemPorQuantidade() throws HotelGotemburgoException {

		Assert.assertEquals("2", facade.getInfoHospedagem(cliente_1, "Hospedagens Ativas"));
		Assert.assertEquals("1", facade.getInfoHospedagem(cliente_2, "Hospedagens Ativas"));
		Assert.assertEquals("2", facade.getInfoHospedagem(cliente_3, "Hospedagens Ativas"));
		Assert.assertEquals("1", facade.getInfoHospedagem(cliente_4, "Hospedagens Ativas"));

		Assert.assertNotEquals("3", facade.getInfoHospedagem(cliente_4, "Hospedagens Ativas"));
		Assert.assertNotEquals("2", facade.getInfoHospedagem(cliente_2, "Hospedagens Ativas"));
	}

	@Test
	public void testeGetInfoHospedagemPorQuarto() throws HotelGotemburgoException {

		Assert.assertEquals("101A,102A", facade.getInfoHospedagem(cliente_1, "Quarto"));
		Assert.assertEquals("25A,25C", facade.getInfoHospedagem(cliente_3, "Quarto"));
		Assert.assertEquals("102B", facade.getInfoHospedagem(cliente_2, "Quarto"));
		Assert.assertEquals("103C", facade.getInfoHospedagem(cliente_4, "Quarto"));

	}

	@Test
	public void testeGetInfoHospedagemPorTotal() throws HotelGotemburgoException {

		Assert.assertEquals("R$1500,00", facade.getInfoHospedagem(cliente_1, "Total"));
		Assert.assertEquals("R$500,00", facade.getInfoHospedagem(cliente_2, "Total"));
		Assert.assertEquals("R$1800,00", facade.getInfoHospedagem(cliente_3, "Total"));
		Assert.assertEquals("R$1250,00", facade.getInfoHospedagem(cliente_4, "Total"));
	}

	@Test
	public void testAtualizaCadastro() throws HotelGotemburgoException {
		facade.atualizaCadastro("mateus_mangueira@hotmail.com", "nome", "Manguezal Sertanejo");
		Assert.assertEquals("Manguezal Sertanejo", facade.getInfoHospede(cliente_5, "Nome"));
	}

	@Test
	public void testeRealizaCheckout() throws HotelGotemburgoException {

		Assert.assertEquals("2", facade.getInfoHospedagem(cliente_1, "Hospedagens Ativas"));

		facade.realizaCheckout(cliente_1, "102A");

		Assert.assertEquals("1", facade.getInfoHospedagem(cliente_1, "Hospedagens Ativas"));
		Assert.assertEquals("101A", facade.getInfoHospedagem(cliente_1, "Quarto"));
		Assert.assertEquals("R$1000,00", facade.getInfoHospedagem(cliente_1, "Total"));
		Assert.assertEquals("1", facade.consultaTransacoes("Quantidade"));
		Assert.assertEquals("R$500,00", facade.consultaTransacoes("Total"));
		Assert.assertEquals("Lo Porco Voador", facade.consultaTransacoes("Nome"));

		Assert.assertNotEquals("Mateus Mangueira", facade.consultaTransacoes("Nome"));
	}

	@Test
	public void testCriaHospedeWithException() {

		// Teste: Nome do hospede vazio/null.
		try {
			facade.cadastraHospede(" ", "InvisibleGame@loja.com", "03/04/1995");
			Assert.fail("Lancamento de Exception com Nome de Usuario vazio.");

		} catch (HotelGotemburgoException exception) {
			Assert.assertEquals("Erro no cadastro de Hospede. Nome do(a) hospede nao pode ser vazio.",
					exception.getMessage());
		}

		try {
			facade.cadastraHospede(null, "InvisibleGame@loja.com", "03/04/1995");
			Assert.fail("Lancamento de Exception com Nome de Usuario vazio.");

		} catch (HotelGotemburgoException exception) {
			Assert.assertEquals("Erro no cadastro de Hospede. Nome do(a) hospede nao pode ser vazio.",
					exception.getMessage());
		}

		// Teste: Email do hospede vazio/null.
		try {
			facade.cadastraHospede("Tiberuis Carlos Sanchez", "", "07/08/2009");
			Assert.fail("Lancamento de Exception com Login vazio.");

		} catch (HotelGotemburgoException exception) {
			Assert.assertEquals("Erro no cadastro de Hospede. Email do(a) hospede nao pode ser vazio.",
					exception.getMessage());
		}

		try {
			facade.cadastraHospede("Tiberuis Carlos Sanchez", null, "07/08/2009");
			Assert.fail("Lancamento de Exception com Login vazio.");

		} catch (HotelGotemburgoException exception) {
			Assert.assertEquals("Erro no cadastro de Hospede. Email do(a) hospede nao pode ser vazio.",
					exception.getMessage());
		}

		// Teste: Data de nascimento do hospede vazio/null.
		try {
			facade.cadastraHospede("Cleomar Santos Rocha", "cleomarsr@gmail.com", " ");
			Assert.fail("Lancamento de Exception com Login vazio.");

		} catch (HotelGotemburgoException exception) {
			Assert.assertEquals("Erro no cadastro de Hospede. Data de Nascimento do(a) hospede nao pode ser vazio.",
					exception.getMessage());
		}

		try {
			facade.cadastraHospede("Cleomar Santos Rocha", "cleomarsr@gmail.com", null);
			Assert.fail("Lancamento de Exception com Login vazio.");

		} catch (HotelGotemburgoException exception) {
			Assert.assertEquals("Erro no cadastro de Hospede. Data de Nascimento do(a) hospede nao pode ser vazio.",
					exception.getMessage());
		}
	}

	@Test
	public void testCadastraHospedeWithException() {

		// Teste: Nome do hospede vazio/null.
		try {
			facade.cadastraHospede("", "aramis_farpados@lol.com", "05/09/1999");
			Assert.fail();
		} catch (HotelGotemburgoException exception) {
			Assert.assertEquals("Erro no cadastro de Hospede. Nome do(a) hospede nao pode ser vazio.",
					exception.getMessage());
		}

		try {
			facade.cadastraHospede(null, "aramis_farpados@lol.com", "05/09/1999");
			Assert.fail();
		} catch (HotelGotemburgoException exception) {
			Assert.assertEquals("Erro no cadastro de Hospede. Nome do(a) hospede nao pode ser vazio.",
					exception.getMessage());
		}

		// Teste: Email do hospede vazio/null.
		try {
			facade.cadastraHospede("Carlos Aguiar Junior", " ", "05/06/2007");
			Assert.fail();
		} catch (HotelGotemburgoException exception) {
			Assert.assertEquals("Erro no cadastro de Hospede. Email do(a) hospede nao pode ser vazio.",
					exception.getMessage());
		}

		try {
			facade.cadastraHospede("Carlos Aguiar Junior", null, "05/06/2007");
			Assert.fail();
		} catch (HotelGotemburgoException exception) {
			Assert.assertEquals("Erro no cadastro de Hospede. Email do(a) hospede nao pode ser vazio.",
					exception.getMessage());
		}

		// Teste: Data de nascimento vazia/null.
		try {
			facade.cadastraHospede("Diego Aquino Lima", "diego_al@outlook.com", " ");
			Assert.fail();
		} catch (HotelGotemburgoException exception) {
			Assert.assertEquals("Erro no cadastro de Hospede. Data de Nascimento do(a) hospede nao pode ser vazio.",
					exception.getMessage());
		}

		try {
			facade.cadastraHospede("Diego Aquino Lima", "diego_al@outlook.com", null);
			Assert.fail();
		} catch (HotelGotemburgoException exception) {
			Assert.assertEquals("Erro no cadastro de Hospede. Data de Nascimento do(a) hospede nao pode ser vazio.",
					exception.getMessage());
		}

		try {
			facade.cadastraHospede("Nego do Borels", "negols@", "12/06/1994");
			Assert.fail();
		} catch (HotelGotemburgoException exception) {
			Assert.assertEquals("Erro no cadastro de Hospede. Email do(a) hospede esta invalido.",
					exception.getMessage());
		}
		try {
			facade.cadastraHospede("Xing Ling", "xinglings@outlook.com", "12/06");
			Assert.fail();
		} catch (HotelGotemburgoException exception) {
			Assert.assertEquals("Erro no cadastro de Hospede. Formato de data invalido.", exception.getMessage());
		}
		try {
			facade.cadastraHospede("R@ncok", "rancok@outlook.com", "12/05/1990");
			Assert.fail();
		} catch (HotelGotemburgoException exception) {
			Assert.assertEquals("Erro no cadastro de Hospede. Nome do(a) hospede esta invalido.",
					exception.getMessage());
		}
		try {
			facade.cadastraHospede("Girino Silva", "girino@outlook.com", "12/05/2007");
			Assert.fail();
		} catch (HotelGotemburgoException exception) {
			Assert.assertEquals("Erro no cadastro de Hospede. A idade do(a) hospede deve ser maior que 18 anos.",
					exception.getMessage());
		}

	}

	@Test
	public void testAtualizaHospedeWithException() {
		try {
			facade.atualizaCadastro("mateus_mangueira@hotmail.com", "data de nascimento", "09*09*2010");
			Assert.fail();
		} catch (HotelGotemburgoException exception) {
			Assert.assertEquals("Erro na atualizacao do cadastro de Hospede. Formato de data invalido.",
					exception.getMessage());
		}
	}

	@Test
	public void testRemoveHospedeWithException() {

		String emailNaoCadastrado = "rancok@outlook.com";

		try {
			facade.removeHospede("  ");
			Assert.fail();
		} catch (HotelGotemburgoException exception) {
			Assert.assertEquals("Erro na remocao do Hospede. Formato de email invalido.", exception.getMessage());
		}

		try {
			facade.removeHospede(emailNaoCadastrado);
			Assert.fail();
		} catch (HotelGotemburgoException exception) {
			Assert.assertEquals(
					"Erro na consulta de hospede. Hospede de email " + emailNaoCadastrado + " nao foi cadastrado(a).",
					exception.getMessage());
		}
	}

	public void testInfoHospedeWithException() {
		try {
			facade.getInfoHospede("girino@outlook.com", "cpf");
		} catch (HotelGotemburgoException exception) {
			Assert.assertEquals("Erro na consulta de hospede. Opcao invalida.", exception.getMessage());
		}
	}
}
