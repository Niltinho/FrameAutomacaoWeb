package support;

import static support.DriverFactory.getDriver;
import static support.DriverFactory.getProp;
import static support.DriverFactory.killDriver;
import static utils.Generator.dataHoraParaArquivo;
import static utils.Screnshot.tirar;

import java.io.File;
import java.io.IOException;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.rules.TestName;

public class BaseTest {

	@Rule
	public TestName testName = new TestName();

	@Before
	public void inicializar() {

	}

	@After
	public void finaliza() {
		String arquivoPrint = "target" + File.separator + "print" + File.separator + testName.getMethodName()
				+ dataHoraParaArquivo() + ".png";
		tirar(getDriver(), arquivoPrint);
		try {
			if (getProp().getProperty("prop.fecharBrowser").equalsIgnoreCase("sim")) {
				killDriver();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
