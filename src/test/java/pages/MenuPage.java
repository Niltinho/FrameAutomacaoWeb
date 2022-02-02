package pages;

import org.openqa.selenium.By;

import support.BasePage;

public class MenuPage extends BasePage {

	public void acessarTelaAdicionarConta() {
		clicarLink(By.id("Contas"));
		clicarLink(By.id("Adicionar"));
	}

	public void acessarTelaListarConta() {
		clicarLink(By.id("Contas"));
		clicarLink(By.id("Listar"));
	}

	public void acessarTelaInserirMovimentacao() {
		clicarLink(By.id("Criar Movimentação"));
	}

	public void acessarTelaResumoMensal() {
		clicarLink(By.id("Resumo Mensal"));
	}

	public void acessarTelaPrincipal() {
		clicarLink(By.id("Home"));
	}
}
