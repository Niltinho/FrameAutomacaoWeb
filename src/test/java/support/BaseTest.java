package support;

import java.io.File;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.rules.TestName;

import pages.LoginPage;
import utils.Generator;
import utils.Screnshot;

public class BaseTest {

	private LoginPage page = new LoginPage();

	@Rule
	public TestName testName = new TestName();

	@Before
	public void inicializar() {
		page.acessarTelaInicial();

		page.setEmail("nilton@teste.com.br");
		page.setPassword("080114");
		page.clicarBotaoEntrar();
	}

	@After
	public void finaliza() {
		String arquivoPrint = "target" + File.separator + "print" + File.separator + testName.getMethodName()
				+ Generator.dataHoraParaArquivo() + ".png";
		Screnshot.tirar(DriverFactory.getDriver(), arquivoPrint);

		if (Propriedades.FECHAR_BROWSER) {
			DriverFactory.killDriver();
		}

	}

}
