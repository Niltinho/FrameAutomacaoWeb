package utils;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import support.DriverFactory;

import java.io.File;

public class Screnshot {
	public static void tirar(WebDriver geDriver, String arquivo) {
		File screenshot = ((TakesScreenshot) DriverFactory.getDriver()).getScreenshotAs(OutputType.FILE);
		try {
			FileUtils.copyFile(screenshot, new File(arquivo));
		} catch (Exception e) {
			System.out.println("Houveram problemas ao copiar o arquivo para a pasta");
		}

	}
}
