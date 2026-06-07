package it.uniroma3.diadia;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.uniroma3.diadia.ambienti.Labirinto;
import it.uniroma3.diadia.ambienti.Stanza;

class PartitaTest {
	private Partita partita;
	private Labirinto labirinto;
	
	@BeforeEach
	public void setUp() {
		labirinto = Labirinto.newBuilder()
				.addStanzaIniziale("Atrio")
				.addStanzaVincente("Biblioteca")
				.getLabirinto();
		partita = new Partita(labirinto);
	}
	
	@Test
	public void testStanzaCorrenteIniziale() {
		assertNotNull(partita.getStanzaCorrente(), "La stanza corrente non deve essere nulla");
		assertEquals("Atrio", partita.getStanzaCorrente().getNome());
	}

	@Test
	public void testIsVintaInizio() {
		assertFalse(partita.vinta());
	}
	
	@Test
	public void testIsVintaFine() {
		Stanza vincente =partita.getLabirinto().getStanzaVincente();
		partita.setStanzaCorrente(vincente);
		assertTrue(partita.vinta(),"Ora la partita dovrebbe rrestituirci vero ossia che è stata vinta");
	}
	
	@Test
	public void testIsFinitaInizio() {
		assertFalse(partita.isFinita());
	}
	
	@Test
	public void testIsFinitaConComandoSet() {
		partita.setFinita();
		assertTrue(partita.isFinita(),"Ora la partita dovrebbe essere finita");
	}
	
	@Test
	public void testIsFinitaQuandoVinta() {
		Stanza vincente=partita.getLabirinto().getStanzaVincente();
		partita.setStanzaCorrente(vincente);
		
		assertTrue(partita.isFinita());
	}
	
	@Test
	public void testIsFinitaZeroCFU() {
		int cfuIniziali = 20;
		for (int i = 0; i < cfuIniziali; i++) {
			partita.getGiocatore().consumaCfu();
		}
		assertTrue(partita.isFinita(),"La partita deve finire quando finisco i CFU");
		assertFalse(partita.vinta(),"La partita però non è vinta anche se finisco i CFU");
	}
	

}

