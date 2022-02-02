package suites;

import org.junit.AfterClass;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import support.DriverFactory;
import tests.ContaTest;

@RunWith(Suite.class)
@SuiteClasses({
	ContaTest.class,
})

public class SuiteTeste {
	
	/*@BeforeClass
	public static void inicializar() {
		page.acessarTelaInicial();
		
		page.setEmail("nilton@teste.com.br");
		page.setPassword("080114");
		page.clicarBotaoEntrar();
		
		DriverFactory.killDriver();
	}*/
	
	@AfterClass
	public static void finaliza() {
		DriverFactory.killDriver();
	}
	
}
