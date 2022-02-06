package pages;

import static org.junit.Assert.assertEquals;

import org.openqa.selenium.By;

import support.BasePage;

public class ExemploClassePage extends BasePage {

	public void exemploEscrever(String texto) {
		escreverCampo(By.id("mapear"), texto);
	}

	public void exemploClicarBtn() {
		clicar(By.xpath("mapear"));
	}

	public void exemploValidarTelaCampo() {
		assertEquals("valor a ser validado", obterValorCampo(By.xpath("mapear")));
	}

}
