package tests;

import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import pages.ContaPage;
import pages.MenuPage;
import support.BaseTest;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ContaTest extends BaseTest {

	private MenuPage menuPage = new MenuPage();
	private ContaPage contaPage = new ContaPage();

	@Test
	public void test1_InserirConta() {
		menuPage.acessarTelaAdicionarConta();

		contaPage.setConta("NiltonTeste");
		contaPage.clicarBotaoSalvar();
		Assert.assertEquals("Conta adicionada com sucesso!", contaPage.obterMensagemSucesso());
	}

	@Test
	public void test2_AlterarConta() {
		menuPage.acessarTelaListarConta();

		contaPage.clicarBotaoAlterar("NiltonTeste");
		contaPage.setConta("NiltonTesteAlterado");
		contaPage.clicarBotaoSalvar();
		Assert.assertEquals("Conta alterada com sucesso!", contaPage.obterMensagemSucesso());

	}

	@Test
	public void test3_InserirContaMesmoNome() {
		menuPage.acessarTelaAdicionarConta();

		contaPage.setConta("NiltonTesteAlterado");
		contaPage.clicarBotaoSalvar();
		Assert.assertEquals("Já existe uma conta com esse nome!", contaPage.obterMensagemErro());
	}

}
