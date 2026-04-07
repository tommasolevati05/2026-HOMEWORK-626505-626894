package it.uniroma3.diadia;

import static org.junit.jupiter.api.Assertions.*;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.uniroma3.diadia.ambienti.Labirinto;

class LabirintoTest {
	private Labirinto labirinto;
	
	@BeforeEach
	public void setUp() {
		this.labirinto=new Labirinto();
	}
	
	@Test
	public void testGetStanzaVincenteNonNulla() {
		assertNotNull(labirinto.getStanzaVincente(), "la stanza vincente non esiste");
	}
	@Test
	public void testGetStanzaVincenteUguale() {
		assertEquals("Biblioteca", labirinto.getStanzaVincente().getNome(), "la stanza vincente è sbagliata");	
	}
	@Test
	public void testGetStanzaVincenteDiversa() {
		assertNotEquals("aulaN11", labirinto.getStanzaVincente().getNome(), "la stanza vincente è sbagliata");
	}
	
	
	@Test
	public void testGetStanzaDiIngressoNonNulla() {
		assertNotNull(labirinto.getStanzaDiIngresso(), "la stanza di ingresso non esiste");
	}
	@Test
	public void testGetStanzaDiIngressoUguale() {
		assertEquals("Atrio", labirinto.getStanzaDiIngresso().getNome(), " la stanza di ingresso è sbagliata");
	}
	@Test
	public void testGetStanzaDiIngressoDiversa() {
		assertNotEquals("aulaN10", labirinto.getStanzaDiIngresso().getNome(), "la stanza di ingresso è sbagliata");
	}
	
	
	

}
