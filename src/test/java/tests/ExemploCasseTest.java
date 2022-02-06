package tests;

import org.junit.Test;

import pages.ExemploClassePage;
import support.BaseTest;

public class ExemploCasseTest extends BaseTest {

	private ExemploClassePage exemploClassePage = new ExemploClassePage();

	@Test
	public void exemploMetodoTeste() {
		exemploClassePage.exemploEscrever("Escrever texto");
		exemploClassePage.exemploClicarBtn();
		exemploClassePage.exemploValidarTelaCampo();
	}

}
