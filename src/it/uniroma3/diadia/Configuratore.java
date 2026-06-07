package it.uniroma3.diadia;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Configuratore {
	private static final String FILE_PROPERTIES = "diadia.properties";
	private static Properties properties = null;

	private static void caricaProperties() {
		if (properties == null) {
			properties = new Properties();
			try (InputStream input = ClassLoader.getSystemResourceAsStream(FILE_PROPERTIES)) {
				if (input == null) {
					System.err.println("Attenzione: impossibile trovare il file " + FILE_PROPERTIES + ". Verranno usati i valori di default.");
					return;
				}
				properties.load(input);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public static int getCFUIniziali() {
		caricaProperties();
		String cfu = properties.getProperty("cfu_iniziali");
		return Integer.parseInt(cfu);
	}

	public static int getPesoMassimoBorsa() {
		caricaProperties();
		String peso = properties.getProperty("peso_massimo_borsa");
		return Integer.parseInt(peso);
	}

	public static int getSogliaMagica() {
		caricaProperties();
		String soglia = properties.getProperty("soglia_magica");
		return Integer.parseInt(soglia);
	}
}