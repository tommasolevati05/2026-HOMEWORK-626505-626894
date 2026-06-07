package it.uniroma3.diadia;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.uniroma3.diadia.ambienti.StanzaBuia;
import it.uniroma3.diadia.attrezzi.Attrezzo;

class StanzaBuiaTest {
	private StanzaBuia stanzaBuia;
	private Attrezzo lanterna;
	private Attrezzo spada;
	
	@BeforeEach
	void setUp() throws Exception {
	stanzaBuia=new StanzaBuia("stanzaBuia","lanterna");
	lanterna=new Attrezzo("lanterna",3);
	spada=new Attrezzo("spada",5);
	}
	

	@Test
	void testValido() {
		stanzaBuia.addAttrezzo(lanterna);
		String descrizione = stanzaBuia.getDescrizione();
		assertTrue(descrizione.contains("lanterna"));
	}
	
	@Test
	void testNonValido() {
		assertEquals("Qui c'é un buio pesto",stanzaBuia.getDescrizione());
	}
	
	@Test
	void TestAttrezzoSbagliato() {
		stanzaBuia.addAttrezzo(spada);
		
		assertEquals("Qui c'é un buio pesto",stanzaBuia.getDescrizione());
	}

}
