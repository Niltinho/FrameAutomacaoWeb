package utils;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;

public class Generator {

	/**
	 * @author Nilton L. Correia 
	 * Método para gerar a data e hora no formato
	 *         "dd-MM-yyyy-hhmmss"
	 */
	public static String dataHoraParaArquivo() {
		Timestamp ts = new Timestamp(System.currentTimeMillis());
		return new SimpleDateFormat("dd-MM-yyyy-hhmmss").format(ts);
	}
}
