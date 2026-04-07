package it.uniroma3.diadia;



import it.uniroma3.diadia.ambienti.Stanza;
import it.uniroma3.diadia.attrezzi.Attrezzo;
import it.uniroma3.diadia.giocatore.Borsa;

/**
 * Classe principale di diadia, un semplice gioco di ruolo ambientato al dia.
 * Per giocare crea un'istanza di questa classe e invoca il letodo gioca
 *
 * Questa e' la classe principale crea e istanzia tutte le altre
 *
 * @author  docente di POO 
 *         (da un'idea di Michael Kolling and David J. Barnes) 
 *          
 * @version base
 */

public class DiaDia {

	static final private String MESSAGGIO_BENVENUTO = ""+
			"Ti trovi nell'Universita', ma oggi e' diversa dal solito...\n" +
			"Meglio andare al piu' presto in biblioteca a studiare. Ma dov'e'?\n"+
			"I locali sono popolati da strani personaggi, " +
			"alcuni amici, altri... chissa!\n"+
			"Ci sono attrezzi che potrebbero servirti nell'impresa:\n"+
			"puoi raccoglierli, usarli, posarli quando ti sembrano inutili\n" +
			"o regalarli se pensi che possano ingraziarti qualcuno.\n\n"+
			"Per conoscere le istruzioni usa il comando 'aiuto'.";
	
	static final private String[] elencoComandi = {"vai", "aiuto", "fine", "prendi", "posa"};

	private Partita partita;
	private IOConsole console;

	public DiaDia(IOConsole consoleRicevuta) {
		this.partita = new Partita();
		this.console=consoleRicevuta;
	}

	public void gioca() {
		String istruzione; 

		console.mostraMessaggio(MESSAGGIO_BENVENUTO);		
		do		
			istruzione =console.leggiRiga();
		while (!processaIstruzione(istruzione));
	}   


	/**
	 * Processa una istruzione 
	 *
	 * @return true se l'istruzione e' eseguita e il gioco continua, false altrimenti
	 */
	private boolean processaIstruzione(String istruzione) {
		Comando comandoDaEseguire = new Comando(istruzione);

		if (comandoDaEseguire.getNome().equals("fine")) {
			this.fine(); 
			return true;
		} else if (comandoDaEseguire.getNome().equals("vai"))
			this.vai(comandoDaEseguire.getParametro());
		else if (comandoDaEseguire.getNome().equals("aiuto"))
			this.aiuto();
		else if (comandoDaEseguire.getNome().equals("prendi"))
			this.prendi(comandoDaEseguire.getParametro());
		else if (comandoDaEseguire.getNome().equals("posa"))
			this.posa(comandoDaEseguire.getParametro());
		else
			console.mostraMessaggio("Comando sconosciuto");
		if (this.partita.vinta()) {
			console.mostraMessaggio("Hai vinto!");
			return true;
		} else
			return false;
	}   

	// implementazioni dei comandi dell'utente:

	/**
	 * Stampa informazioni di aiuto.
	 */
	private void aiuto() {
		for(int i=0; i< elencoComandi.length; i++) 
			console.mostraMessaggio(elencoComandi[i]+ " ");
	}

	/**
	 * Cerca di andare in una direzione. Se c'e' una stanza ci entra 
	 * e ne stampa il nome, altrimenti stampa un messaggio di errore
	 */
	private void vai(String direzione) {
		if(direzione==null)
			console.mostraMessaggio("Dove vuoi andare");
		Stanza prossimaStanza = null;
		prossimaStanza = this.partita.getStanzaCorrente().getStanzaAdiacente(direzione);
		if (prossimaStanza == null)
			console.mostraMessaggio("Direzione inesistente");
		else {
			this.partita.setStanzaCorrente(prossimaStanza);
			this.partita.getGiocatore().consumaCfu(); //HO CREATO UN METODO CINSUMA CFU NELLA CLASSE PARTITA
			
		}
		//RICHIAMO IL TO STRING CREATO NELLA CLASSE PARTITA
		console.mostraMessaggio(partita.getStatoPartita());
	}
	
	/**
	 * cerca di prendere un attrezzo della stanza in cui si trova il
	 * giocatore. se c'è abbastanza spazio nella borsa, lo prende,
	 * altrimenti stampa un messaggio di errore.
	 */
	private void prendi(String nomeAttrezzo) {
		if(nomeAttrezzo==null) {
			console.mostraMessaggio("Che attrezzo vuoi prendere?");
		}
		boolean attrezzoTrovato;
		attrezzoTrovato=false;
		if(partita.getStanzaCorrente().hasAttrezzo(nomeAttrezzo)) {
			attrezzoTrovato=true;
		}
		if(attrezzoTrovato) {
			int pesoMax=partita.getGiocatore().getBorsa().getPesoMax();
			int pesoAttuale=partita.getGiocatore().getBorsa().getPeso();
			int pesoAttrezzo=partita.getStanzaCorrente().getAttrezzo(nomeAttrezzo).getPeso();
			
			if((pesoMax-pesoAttuale)>pesoAttrezzo) {
			Attrezzo attrezzoDaRimuovere=partita.getStanzaCorrente().getAttrezzo(nomeAttrezzo);
			partita.getStanzaCorrente().removeAttrezzo(attrezzoDaRimuovere);
			
			partita.getGiocatore().getBorsa().addAttrezzo(attrezzoDaRimuovere);
			console.mostraMessaggio(nomeAttrezzo +" è stato aggiunto alla borsa con successo");
			}
			else {
				console.mostraMessaggio("Capienza borsa non sufficiente");
			}
		}
		else {
			console.mostraMessaggio("Non c'è " + nomeAttrezzo + " quindi non puoi prenderlo");
		}
	}
	
	
	/**
	 * cerca di posare un attrezzo della borsa che possiede il
	 * giocatore. se c'è abbastanza spazio nella stanza, lo posa,
	 * altrimenti stampa un messaggio di errore.
	 */	
	private void posa(String nomeAttrezzo){
	       if(nomeAttrezzo==null) {
	           console.mostraMessaggio("Che attrezzo vuoi posare?");
	           return;
	       }
	       Borsa borsaGiocatore = this.partita.getGiocatore().getBorsa();
	       Stanza stanzaCorrente = this.partita.getStanzaCorrente();
	       if(borsaGiocatore.hasAttrezzo(nomeAttrezzo)) {
	           Attrezzo attrezzoDaPosare = borsaGiocatore.removeAttrezzo(nomeAttrezzo);
	           boolean attrezzoPosato = stanzaCorrente.addAttrezzo(attrezzoDaPosare);
	           if(attrezzoPosato) {
	               console.mostraMessaggio("Hai posato con successo "+ nomeAttrezzo +" nella stanza");
	           }
	           else {
	               borsaGiocatore.addAttrezzo(attrezzoDaPosare);
	               console.mostraMessaggio("Non ci sta spazio nella stanza per posarlo");
	           }
	       }
	       else {
	           console.mostraMessaggio("Non hai "+ nomeAttrezzo +", quindi non puoi lasciarlo");
	       }
	   }
		
		
	

	/**
	 * Comando "Fine".
	 */
	private void fine() {
		console.mostraMessaggio("Grazie di aver giocato!");  // si desidera smettere
	}

	public static void main(String[] argc) {
		IOConsole console=new IOConsole();
		DiaDia gioco = new DiaDia(console);
		gioco.gioca();
	}
}
