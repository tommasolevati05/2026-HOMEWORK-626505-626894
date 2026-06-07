package it.uniroma3.diadia;

import java.util.Scanner;

import it.uniroma3.diadia.ambienti.Direzione;
import it.uniroma3.diadia.ambienti.Labirinto;
import it.uniroma3.diadia.comandi.Comando;
import it.uniroma3.diadia.comandi.FabbricaDiComandi;
import it.uniroma3.diadia.comandi.FabbricaDiComandiRiflessiva;

/**
 * Classe principale di DiaDia, un semplice gioco di ruolo ambientato al DIA.
 *
 * <h3>Es. 20 – try-with-resource per lo Scanner</h3>
 * Lo Scanner su {@code System.in} viene creato UNA SOLA VOLTA nel {@code main}
 * (o nel costruttore della sessione) e passato a {@link IOConsole}.  In questo
 * modo il ciclo di vita dello scanner copre l'intera partita e viene chiuso
 * automaticamente alla fine, senza mai chiudere prematuramente {@code System.in}.
 */
public class DiaDia {

	static final private String MESSAGGIO_BENVENUTO = "" +
			"Ti trovi nell'Universita', ma oggi e' diversa dal solito...\n" +
			"Meglio andare al piu' presto in biblioteca a studiare. Ma dov'e'?\n" +
			"I locali sono popolati da strani personaggi, " +
			"alcuni amici, altri... chissa'!\n" +
			"Ci sono attrezzi che potrebbero servirti nell'impresa:\n" +
			"puoi raccoglierli, usarli, posarli quando ti sembrano inutili\n" +
			"o regalarli se pensi che possano ingraziarti qualcuno.\n\n" +
			"Per conoscere le istruzioni usa il comando 'aiuto'.";

	private Partita partita;
	private IO console;
	private FabbricaDiComandi factory;

	public DiaDia(Labirinto labirinto, IO io) {
		this.partita = new Partita(labirinto);
		this.console = io;
		this.factory = new FabbricaDiComandiRiflessiva(this.console);
	}

	public void gioca() {
		console.mostraMessaggio(MESSAGGIO_BENVENUTO);
		String istruzione;
		do {
			istruzione = console.leggiRiga();
		} while (!processaIstruzione(istruzione));

		if (this.partita.vinta()) {
			console.mostraMessaggio("Hai vinto! Sei arrivato in biblioteca. Ora puoi studiare!");
		} else if (!this.partita.giocatoreIsVivo()) {
			console.mostraMessaggio("Hai esaurito i CFU. Hai perso!");
		}
	}

	private boolean processaIstruzione(String istruzione) {
		Comando comandoDaEseguire;
		try {
			comandoDaEseguire = this.factory.costruisciComando(istruzione);
		} catch (Exception e) {
			console.mostraMessaggio("Errore fatale nella creazione del comando.");
			return false;
		}
		comandoDaEseguire.esegui(this.partita);
		return this.partita.isFinita();
	}

	/**
	 * Punto d'ingresso dell'applicazione.
	 *
	 * Lo {@link Scanner} su {@code System.in} viene creato qui, nel metodo che
	 * gestisce l'intero ciclo di vita di una partita, e viene chiuso
	 * automaticamente dal try-with-resource al termine.
	 * Viene passato a {@link IOConsole} che lo usa senza possederne la
	 * responsabilità di chiusura: in questo modo {@code System.in} non viene
	 * mai chiuso prematuramente tra una lettura e l'altra.
	 */
	public static void main(String[] argc) {
	    try (Scanner scanner = new Scanner(System.in)) {
	        IO io = new IOConsole(scanner);
	        int livello = 1;
	        boolean continuaAGiocare = true;

	        while (continuaAGiocare) {
	            Labirinto labirinto;
	            try {
	            	labirinto = Labirinto.caricaDaFile("labirinto" + livello + ".txt");
	            } catch (Exception e) {
	                e.printStackTrace(); // stampa l'errore completo nella console Eclipse
	                io.mostraMessaggio("Errore: " + e.getMessage());
	                break;
	            }

	            DiaDia gioco = new DiaDia(labirinto, io);
	            gioco.gioca();

	            if (gioco.partita.vinta()) {
	                livello++;  // livello successivo
	                // se non esiste un prossimo file, il try-catch sopra lo gestisce
	            } else {
	                // partita persa: si ricomincia dal livello 1
	                livello = 1;
	                io.mostraMessaggio("Ricominciamo dal livello 1...");
	            }

	            // se vuoi che il gioco finisca dopo aver perso, metti:
	            // continuaAGiocare = false;
	        }
	    }
	}
}
