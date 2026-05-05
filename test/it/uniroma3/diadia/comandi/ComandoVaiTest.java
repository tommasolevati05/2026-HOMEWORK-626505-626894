package it.uniroma3.diadia.comandi;

import static org.junit.jupiter.api.Assertions.*;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.uniroma3.diadia.IOConsole;
import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.ambienti.Stanza;

class ComandoVaiTest {

	private ComandoVai comando;
	private Partita partita;
	private Stanza StanzaPartenza;
	private Stanza StanzaDestinazione;
	private int cfuIniziali;
	@BeforeEach
	void setUp() throws Exception {
		comando = new ComandoVai();
		partita = new Partita();
		comando.setIo(new IOConsole());
		cfuIniziali = partita.getGiocatore().getCfu();
		
		StanzaDestinazione = new Stanza("Biblioteca");
		StanzaPartenza = new Stanza("Atrio");
		
		StanzaPartenza.impostaStanzaAdiacente("nord", StanzaDestinazione);
		
		partita.setStanzaCorrente(StanzaPartenza);
	}

	@Test
	void testVaiDirezioneValida() {
		comando.setParametro("nord");
		
		comando.esegui(partita);
		
		assertEquals(StanzaDestinazione,partita.getStanzaCorrente());
		assertEquals(cfuIniziali-1, partita.getGiocatore().getCfu());
	}
	
	@Test
	void testVaiDirezioneInesistente() {
		comando.setParametro("sud");
		
		comando.esegui(partita);
		
		assertEquals(StanzaPartenza,partita.getStanzaCorrente());
		assertEquals(cfuIniziali,partita.getGiocatore().getCfu());
	}
	
	@Test
	void testVaiParametroNullo() {
		comando.setParametro(null);
		
		comando.esegui(partita);
		
		assertEquals(StanzaPartenza,partita.getStanzaCorrente());
		assertEquals(cfuIniziali,partita.getGiocatore().getCfu());
	}

}
