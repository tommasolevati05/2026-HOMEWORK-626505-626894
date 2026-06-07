package it.uniroma3.diadia.comandi;

import java.util.Scanner;

import it.uniroma3.diadia.IO;
import it.uniroma3.diadia.IOConsole;


public class FabbricaDiComandiRiflessiva implements FabbricaDiComandi {

	private IO io; 

	public FabbricaDiComandiRiflessiva(IO io) {
		this.io = io;
	}

	@Override
	public Comando costruisciComando(String istruzione) throws Exception {

		Scanner scannerDiParole = new Scanner(istruzione); // es. 'vai sud'
		String nomeComando = null; // es. 'vai'
		String parametro = null; // es. 'sud'
		Comando comando = null;

		if (scannerDiParole.hasNext()) {
			nomeComando = scannerDiParole.next(); // prima parola: nome del comando
		}
		if (scannerDiParole.hasNext()) {
			parametro = scannerDiParole.next(); // seconda parola: eventuale parametro
		}
		scannerDiParole.close();

		try {
			// Costruzione dinamica del nome completo della classe
			StringBuilder nomeClasse = new StringBuilder("it.uniroma3.diadia.comandi.Comando");

			// Prima lettera in Maiuscola (es. 'v')
			nomeClasse.append(Character.toUpperCase(nomeComando.charAt(0)));
			// Resto della stringa (es. 'ai') -> Diventa 'ComandoVai'
			nomeClasse.append(nomeComando.substring(1));

			// Caricamento dell'oggetto Class e istanziazione dinamica tramite Riflessione
			// Nota: getDeclaredConstructor().newInstance() sostituisce il vecchio .newInstance() deprecato in Java moderno
			comando = (Comando) Class.forName(nomeClasse.toString()).getDeclaredConstructor().newInstance();

			// Assegnazione del parametro estratto (es. 'sud')
			comando.setParametro(parametro);

			
			comando.setIo(this.io);

		} catch (Exception e) {
			// Se l'utente scrive un comando inesistente (es. "sfoglia") Class.forName solleverà un'eccezione.
			// La intercettiamo e restituiamo un comando non valido a cui iniettiamo comunque la console.
			comando = new ComandoNonValido();
			comando.setIo(this.io);
		}

		return comando;
	}
}







