package it.uniroma3.diadia.comandi;

import static org.junit.jupiter.api.Assertions.*;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.uniroma3.diadia.IOSimulator;
import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.ambienti.Labirinto;
import it.uniroma3.diadia.ambienti.Direzione;
import it.uniroma3.diadia.ambienti.Stanza;

class ComandoVaiTest {

	private ComandoVai comando;
	private Partita partita;
	private Stanza stanzaPartenza;
	private Stanza stanzaDestinazione;
	private int cfuIniziali;
	private Labirinto labirinto;

	@BeforeEach
	void setUp() throws Exception {
		comando = new ComandoVai();
		labirinto = Labirinto.newBuilder().addStanzaIniziale("Atrio").getLabirinto();
		partita = new Partita(labirinto);
		comando.setIo(new IOSimulator());
		cfuIniziali = partita.getGiocatore().getCfu();
		stanzaDestinazione = new Stanza("Biblioteca");
		stanzaPartenza = new Stanza("Atrio");
		stanzaPartenza.impostaStanzaAdiacente(Direzione.NORD, stanzaDestinazione);
		partita.setStanzaCorrente(stanzaPartenza);
	}

	@Test
	void testVaiDirezioneValida() {
		comando.setParametro("nord");
		comando.esegui(partita);
		assertEquals(stanzaDestinazione, partita.getStanzaCorrente());
		assertEquals(cfuIniziali - 1, partita.getGiocatore().getCfu());
	}

	@Test
	void testVaiDirezioneInesistente() {
		comando.setParametro("sud");
		comando.esegui(partita);
		assertEquals(stanzaPartenza, partita.getStanzaCorrente());
		assertEquals(cfuIniziali, partita.getGiocatore().getCfu());
	}

	@Test
	void testVaiParametroNullo() {
		comando.setParametro(null);
		comando.esegui(partita);
		assertEquals(stanzaPartenza, partita.getStanzaCorrente());
		assertEquals(cfuIniziali, partita.getGiocatore().getCfu());
	}

}
