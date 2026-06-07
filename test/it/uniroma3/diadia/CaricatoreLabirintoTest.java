package it.uniroma3.diadia;

import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;
import java.io.StringReader;

import org.junit.jupiter.api.Test;

import it.uniroma3.diadia.ambienti.Labirinto;
import it.uniroma3.diadia.ambienti.Stanza;

class CaricatoreLabirintoTest {

	private static final String LABIRINTO_COMPLETO = 
			"Stanze: Atrio, Segreta-bloccata-nord-chiave, Biblioteca\n" +
			"Inizio: Atrio\n" +
			"Vincente: Biblioteca\n" +
			"Attrezzi: chiave 1 Atrio\n" +
			"Uscite: Atrio nord Segreta, Segreta nord Biblioteca\n" +
			"Personaggi:";

	@Test
	void testCaricaLabirintoValiditaStanze() throws IOException {
		StringReader sr = new StringReader(LABIRINTO_COMPLETO);
		CaricatoreLabirinto caricatore = new CaricatoreLabirinto(sr);
		Labirinto labirinto = caricatore.carica();
		
		assertNotNull(labirinto);
		assertEquals("Atrio", labirinto.getStanzaDiIngresso().getNome());
		assertEquals("Biblioteca", labirinto.getStanzaVincente().getNome());
	}

	@Test
	void testCaricaLabirintoVerificaAttrezzo() throws IOException {
		StringReader sr = new StringReader(LABIRINTO_COMPLETO);
		CaricatoreLabirinto caricatore = new CaricatoreLabirinto(sr);
		Labirinto labirinto = caricatore.carica();
		
		Stanza atrio = labirinto.getStanzaDiIngresso();
		assertTrue(atrio.hasAttrezzo("chiave"));
	}
}