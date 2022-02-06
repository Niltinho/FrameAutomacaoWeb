package utils;

import static org.apache.commons.io.FileUtils.copyFile;
import static support.DriverFactory.getDriver;

import java.io.File;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

public class Screnshot {

	/**
	 * @author Nilton L. Correia 
	 * Método para tirar evidência
	 */
	public static void tirar(WebDriver geDriver, String arquivo) {
		File screenshot = ((TakesScreenshot) getDriver()).getScreenshotAs(OutputType.FILE);
		try {
			copyFile(screenshot, new File(arquivo));
		} catch (Exception e) {
			System.out.println("Houveram problemas ao copiar o arquivo para a pasta");
		}

	}
}
