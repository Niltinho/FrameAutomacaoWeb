package support;

import java.util.List;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BasePage {

	public void escreverCampo(By by, String texto) {
//		limparCampo(by);
		DriverFactory.getDriver().findElement(by).sendKeys(texto);
	}

	public void limparCampo(By by) {
		DriverFactory.getDriver().findElement(by).clear();
	}

	public void confirmarCampo(By by) {
		DriverFactory.getDriver().findElement(by).sendKeys(Keys.ENTER);
		;
	}

	public String obterValorCampo(By by) {
		return DriverFactory.getDriver().findElement(by).getText();
	}

	public String validarCampo(By by) {
		return DriverFactory.getDriver().findElement(by).getText();
	}

	public boolean validarCampoHabilitado(String validaCampo) {
		return DriverFactory.getDriver().findElement(By.xpath(validaCampo)).isEnabled();
	}

	public void escreverCampoAction(By by, String valor) {
		Actions act = new Actions(DriverFactory.getDriver());
		WebElement escreverCampo = DriverFactory.getDriver().findElement(by);
		act.sendKeys(escreverCampo, valor).perform();
	}

	public void apagarCampoAction(By by) {
		Actions act = new Actions(DriverFactory.getDriver());
		WebElement apagarCampo = DriverFactory.getDriver().findElement(by);
		act.sendKeys(apagarCampo, "").perform();
	}

	public void confirmarCampoAction(By by) {
		Actions act = new Actions(DriverFactory.getDriver());
		WebElement escreverCampo = DriverFactory.getDriver().findElement(by);
		act.sendKeys(escreverCampo, Keys.ENTER).perform();
	}

	public boolean validarRadio(By by) {
		return DriverFactory.getDriver().findElement(by).isSelected();
	}

	public void selecionarRadio(By by) {
		DriverFactory.getDriver().findElement(by).click();
	}

	public boolean validarCheckbox(By by) {
		return DriverFactory.getDriver().findElement(by).isSelected();
	}

	public void selecionarCheckbox(By by) {
		DriverFactory.getDriver().findElement(by).click();
	}

	public void clicarBotao(By by) {
		DriverFactory.getDriver().findElement(by).click();
	}

	public void clicarBotao(String id_botao) {
		clicarBotao(By.id(id_botao));
		;
	}

	public void clicarBotaoAction(By by) {
		Actions act = new Actions(DriverFactory.getDriver());
		WebElement clique = DriverFactory.getDriver().findElement(by);
		act.click(clique).perform();
	}

	public void clicarLink(By by) {
		DriverFactory.getDriver().findElement(by).click();
	}

	public void clicarMenu(By by) {
		DriverFactory.getDriver().findElement(by).click();
	}

	public void trocarJanela(By by) {
		WebElement fr = DriverFactory.getDriver().findElement(by);
		DriverFactory.getDriver().switchTo().frame(fr);
	}

	public void trocarJanela2(By by) {
		WebElement fr = DriverFactory.getDriver().findElement(by);
		DriverFactory.getDriver().switchTo().frame(fr);
	}

	public void descerJanela() {
		JavascriptExecutor jse = (JavascriptExecutor) DriverFactory.getDriver();
		jse.executeScript("scrollBy(0,500)", "");
	}

	public void esperarElementoAparecer(By by) {
		WebDriverWait wait = new WebDriverWait(DriverFactory.getDriver(), 30);
		wait.until(ExpectedConditions.visibilityOfElementLocated(by));
	}

	public void esperarElementoDesaparecer(By by) {
		WebDriverWait wait = new WebDriverWait(DriverFactory.getDriver(), 30);
		wait.until(ExpectedConditions.invisibilityOfElementLocated(by));
	}

	public void selecionarCombo(By by, String valor) {
		WebElement element = DriverFactory.getDriver().findElement(by);
		Select combo = new Select(element);
		combo.selectByVisibleText(valor);
	}

	public void obterValorCombo(By by) {
		WebElement element = DriverFactory.getDriver().findElement(by);
		Select combo = new Select(element);
		combo.getFirstSelectedOption().getText();
	}

	public WebElement obterCelula(String colunaBusca, String valor, String colunaBotao, String idTabela) {
		// procurar coluna do registro
		WebElement tabela = DriverFactory.getDriver().findElement(By.xpath("//*[@id='" + idTabela + "']"));
		int idColuna = obterIndiceColuna(colunaBusca, tabela);

		// encontrar a linha do registro
		int idLinha = obterIndiceLinha(valor, tabela, idColuna);

		// procurar coluna do botao
		int idColunaBotao = obterIndiceColuna(colunaBotao, tabela);

		// clicar no botao da celula encontrada
		WebElement celula = tabela.findElement(By.xpath(".//tr[" + idLinha + "]/td[" + idColunaBotao + "]"));
		return celula;
	}

	public void clicarBotaoTabela(String colunaBusca, String valor, String colunaBotao, String idTabela) {
		WebElement celula = obterCelula(colunaBusca, valor, colunaBotao, idTabela);
		celula.findElement(By.xpath(".//input")).click();

	}

	protected int obterIndiceLinha(String valor, WebElement tabela, int idColuna) {
		List<WebElement> linhas = tabela.findElements(By.xpath("./tbody/tr/td[" + idColuna + "]"));
		int idLinha = -1;
		for (int i = 0; i < linhas.size(); i++) {
			if (linhas.get(i).getText().equals(valor)) {
				idLinha = i + 1;
				break;
			}
		}
		return idLinha;
	}

	protected int obterIndiceColuna(String coluna, WebElement tabela) {
		List<WebElement> colunas = tabela.findElements(By.xpath(".//th"));
		int idColuna = -1;
		for (int i = 0; i < colunas.size(); i++) {
			if (colunas.get(i).getText().equals(coluna)) {
				idColuna = i + 1;
				break;
			}
		}
		return idColuna;
	}

	public void alertaAceitar() {
		Alert alert = DriverFactory.getDriver().switchTo().alert();
		alert.accept();
	}

	public String alertaObterTexto() {
		Alert alert = DriverFactory.getDriver().switchTo().alert();
		return alert.getText();
	}

	public String alertaObterTextoEAceitar() {
		Alert alert = DriverFactory.getDriver().switchTo().alert();
		String valor = alert.getText();
		alert.accept();
		return valor;

	}

}
