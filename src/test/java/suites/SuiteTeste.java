package suites;

import static support.DriverFactory.killDriver;

import org.junit.AfterClass;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import tests.ExemploCasseTest;

@RunWith(Suite.class)
@SuiteClasses({
	// Informar a(s) classe(s) abaixo
	ExemploCasseTest.class,
})

public class SuiteTeste {

	@AfterClass
	public static void finaliza() {
		killDriver();
	}

}
