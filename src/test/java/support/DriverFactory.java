package support;

import static java.lang.System.getProperty;

import java.io.FileInputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

public class DriverFactory {

	public static ThreadLocal<WebDriver> threadDriver = new ThreadLocal<WebDriver>() {
		@Override
		protected synchronized WebDriver initialValue() {
			return initDriver();
		}
	};

	private DriverFactory() {
	}

	public static WebDriver getDriver() {
		return threadDriver.get();
	}

	public static WebDriver initDriver() {
		WebDriver driver = null;
		try {
			if (getProp().getProperty("prop.tipoExecucao").equalsIgnoreCase("Local")) {
				if (getProp().getProperty("prop.browser").equalsIgnoreCase("FireFox")) {
					try {
						System.setProperty("webdriver.gecko.driver", getProp().getProperty("prop.driver"));
					} catch (IOException e) {
						e.printStackTrace();
					}
					driver = new FirefoxDriver();
				}
				if (getProp().getProperty("prop.browser").equalsIgnoreCase("Chrome")) {
					try {
						System.setProperty("webdriver.chrome.driver", getProp().getProperty("prop.driver"));
					} catch (IOException e) {
						e.printStackTrace();
					}
					driver = new ChromeDriver();
				}
			}
		} catch (IOException e1) {
			e1.printStackTrace();
		}

		try {
			if (getProp().getProperty("prop.tipoExecucao").equalsIgnoreCase("Grid")) {
				DesiredCapabilities cap = null;
				if (getProp().getProperty("prop.browser").equalsIgnoreCase("FireFox")) {
					cap = DesiredCapabilities.firefox();
				}
				if (getProp().getProperty("prop.browser").equalsIgnoreCase("Chrome")) {
					cap = DesiredCapabilities.chrome();
				}
				try {
					driver = new RemoteWebDriver(new URL("http://192.168.0.104:40238/wd/hub"), cap);
					driver = new RemoteWebDriver(new URL("http://192.168.0.104:5556/wd/hub"), cap);
				} catch (MalformedURLException e) {
					System.err.println("Falha na conexão com o GRID");
					e.printStackTrace();
				}
			}
		} catch (IOException e1) {
			e1.printStackTrace();
		}

		try {
			if (getProp().getProperty("prop.tipoExecucao").equalsIgnoreCase("Nuvem")) {
				DesiredCapabilities cap = null;
				if (getProp().getProperty("prop.browser").equalsIgnoreCase("FireFox")) {
					cap = DesiredCapabilities.firefox();
				}
				if (getProp().getProperty("prop.browser").equalsIgnoreCase("Chrome")) {
					cap = DesiredCapabilities.chrome();
				}
				try {
					driver = new RemoteWebDriver(new URL(
							"https://Niltinho:37559398-d9cf-4ed4-ad5b-e29977d410c0@ondemand.saucelabs.com:443/wd/hub"),
							cap);
				} catch (MalformedURLException e) {
					e.printStackTrace();
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		return driver;
	}

	public static Properties getProp() throws IOException {
		Properties props = new Properties();
		FileInputStream file = new FileInputStream(
				getProperty("user.dir") + "/src/test/resources/properties/config.properties");
		props.load(file);
		return props;
	}

	public static void killDriver() {
		WebDriver driver = getDriver();
		if (driver != null) {
			driver.quit();
			driver = null;
		}
		if (threadDriver != null) {
			threadDriver.remove();
		}
	}

}