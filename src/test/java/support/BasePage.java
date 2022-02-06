package support;

import static org.openqa.selenium.support.ui.ExpectedConditions.invisibilityOfElementLocated;
import static org.openqa.selenium.support.ui.ExpectedConditions.visibilityOfElementLocated;
import static support.DriverFactory.getDriver;
import static utils.Log.info;

import java.util.List;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BasePage {

	/**
	 * @author Nilton L. Correia 
	 * Método que escreve no campo de texto
	 */
	public void escreverCampo(By by, String texto) {
		getDriver().findElement(by).sendKeys(texto);
		info(texto + " escrito com sucesso");
	}

	/**
	 * @author Nilton L. Correia 
	 * Método que limpa o campo de texto
	 */
	public void limparCampo(By by) {
		getDriver().findElement(by).clear();
		info("Campo limpo com sucesso");
	}

	/**
	 * @author Nilton L. Correia 
	 * Método que escreve e confirma no campo de texto
	 */
	public void confirmarCampo(By by) {
		getDriver().findElement(by).sendKeys(Keys.ENTER);
		info("Campo escrito com sucesso");
	}

	/**
	 * @author Nilton L. Correia 
	 * Método que obtém o texto do elemento
	 */
	public String obterValorCampo(By by) {
		return getDriver().findElement(by).getText();
	}

	/**
	 * @author Nilton L. Correia 
	 * Método que verifica se o campo está habilitado
	 */
	public boolean validarCampoHabilitado(String valor) {
		return getDriver().findElement(By.xpath(valor)).isEnabled();
	}

	/**
	 * @author Nilton L. Correia 
	 * Método que escreve num campo via Action
	 */
	public void escreverCampoAction(By by, String texto) {
		Actions act = new Actions(getDriver());
		WebElement escreverCampo = getDriver().findElement(by);
		act.sendKeys(escreverCampo, texto).perform();
		info(texto + " escrito com sucesso");
	}

	/**
	 * @author Nilton L. Correia 
	 * Método que limpa o campo de texto via Action
	 */
	public void apagarCampoAction(By by) {
		Actions act = new Actions(getDriver());
		WebElement apagarCampo = getDriver().findElement(by);
		act.sendKeys(apagarCampo, "").perform();
		info("Campo limpo com sucesso");

	}

	/**
	 * @author Nilton L. Correia 
	 * Método que escreve e confirma num campo de texto,
	 *         via Action
	 */
	public void confirmarCampoAction(By by) {
		Actions act = new Actions(getDriver());
		WebElement escreverCampo = getDriver().findElement(by);
		act.sendKeys(escreverCampo, Keys.ENTER).perform();
		info("Campo escrito com sucesso");
	}

	/**
	 * @author Nilton L. Correia 
	 * Método que verifica se o elemento está selecionado
	 */
	public boolean validarElementoSelecionado(By by) {
		return getDriver().findElement(by).isSelected();
	}

	/**
	 * @author Nilton L. Correia 
	 * Método que clica no elemento
	 */
	public void clicar(By by) {
		getDriver().findElement(by).click();
		info("Clique no elemento com sucesso");
	}

	/**
	 * @author Nilton L. Correia 
	 * Método que clica no elemento, via Action
	 */
	public void clicarAction(By by) {
		Actions act = new Actions(getDriver());
		WebElement clique = getDriver().findElement(by);
		act.click(clique).perform();
		info("Clique no elemento com sucesso");
	}

	/**
	 * @author Nilton L. Correia 
	 * Método que troca a janela do browser
	 */
	public void trocarJanela(By by) {
		WebElement fr = getDriver().findElement(by);
		getDriver().switchTo().frame(fr);
		info("Troca de janela do browser executado com sucesso");
	}

	/**
	 * @author Nilton L. Correia 
	 * Método que faz o scroll na tela
	 */
	public void descerJanela() {
		JavascriptExecutor jse = (JavascriptExecutor) getDriver();
		jse.executeScript("scrollBy(0,500)", "");
		info("Realizado scroll com sucesso");
	}

	/**
	 * @author Nilton L. Correia 
	 * Método que aguarda o elemento aparecer
	 */
	public void esperarElementoAparecer(By by) {
		WebDriverWait wait = new WebDriverWait(getDriver(), 30);
		wait.until(visibilityOfElementLocated(by));
		info("Elemento apareceu desapareceu com sucesso");
	}

	/**
	 * @author Nilton L. Correia 
	 * Método que aguarda o elemento desaparecer
	 */
	public void esperarElementoDesaparecer(By by) {
		WebDriverWait wait = new WebDriverWait(getDriver(), 30);
		wait.until(invisibilityOfElementLocated(by));
		info("Elemento desapareceu desapareceu com sucesso");
	}

	/**
	 * @author Nilton L. Correia 
	 * Método que clica no elemento do combo
	 */
	public void selecionarCombo(By by, String valor) {
		WebElement element = getDriver().findElement(by);
		Select combo = new Select(element);
		combo.selectByVisibleText(valor);
		info(valor + " selecionado com sucesso");
	}

	/**
	 * @author Nilton L. Correia 
	 * Método que obtém o elemento do combo
	 */
	public void obterValorCombo(By by) {
		WebElement element = getDriver().findElement(by);
		Select combo = new Select(element);
		combo.getFirstSelectedOption().getText();
		info("Elemento obtido com sucesso " + combo.getFirstSelectedOption().getText());
	}

	/**
	 * @author Nilton L. Correia 
	 * Método que obtém o elementos de uma celula
	 */
	public WebElement obterCelula(String colunaBusca, String valor, String colunaBotao, String idTabela) {
		// procurar coluna do registro
		WebElement tabela = getDriver().findElement(By.xpath("//*[@id='" + idTabela + "']"));
		int idColuna = obterIndiceColuna(colunaBusca, tabela);

		// encontrar a linha do registro
		int idLinha = obterIndiceLinha(valor, tabela, idColuna);

		// procurar coluna do botao
		int idColunaBotao = obterIndiceColuna(colunaBotao, tabela);

		// clicar no botao da celula encontrada
		WebElement celula = tabela.findElement(By.xpath(".//tr[" + idLinha + "]/td[" + idColunaBotao + "]"));
		return celula;
	}

	/**
	 * @author Nilton L. Correia 
	 * Método que clica no elemento da tabela
	 */
	public void clicarBotaoTabela(String colunaBusca, String valor, String colunaBotao, String idTabela) {
		WebElement celula = obterCelula(colunaBusca, valor, colunaBotao, idTabela);
		celula.findElement(By.xpath(".//input")).click();
		info("Clique executado com sucesso");
	}

	/**
	 * @author Nilton L. Correia 
	 * Método que obtém o índice da linha da tabela
	 */
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

	/**
	 * @author Nilton L. Correia 
	 * Método que obtém o índice da coluna da tabela
	 */
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

	/**
	 * @author Nilton L. Correia 
	 * Método que confirma o alerta na tela
	 */
	public void alertaAceitar() {
		Alert alert = getDriver().switchTo().alert();
		alert.accept();
		info("Alerta aceito com sucesso");
	}

	/**
	 * @author Nilton L. Correia 
	 * Método que obtém o texto que apresenta no alerta
	 */
	public String alertaObterTexto() {
		Alert alert = getDriver().switchTo().alert();
		return alert.getText();
	}

	/**
	 * @author Nilton L. Correia 
	 * Método que obtém o texto que apresenta no alerta e confirma
	 */
	public String alertaObterTextoEAceitar() {
		Alert alert = getDriver().switchTo().alert();
		String valor = alert.getText();
		alert.accept();
		return valor;
	}

}
