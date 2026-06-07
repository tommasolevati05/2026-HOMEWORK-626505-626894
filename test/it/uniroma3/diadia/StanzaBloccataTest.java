package it.uniroma3.diadia;

import static org.junit.jupiter.api.Assertions.*;


import org.junit.jupiter.api.BeforeEach;

import it.uniroma3.diadia.ambienti.Direzione;
import it.uniroma3.diadia.ambienti.Stanza;
import it.uniroma3.diadia.ambienti.StanzaBloccata;
import it.uniroma3.diadia.attrezzi.Attrezzo;
import org.junit.jupiter.api.Test;

public class StanzaBloccataTest {
	private StanzaBloccata stanzaA;
	private Stanza stanzaB;
	private Attrezzo chiave;

	@BeforeEach
	public void setUp() {
		stanzaA = new StanzaBloccata("Stanza A", Direzione.NORD, "chiave");
		stanzaB = new Stanza("Stanza B");
		chiave = new Attrezzo("chiave", 1);

		stanzaA.impostaStanzaAdiacente(Direzione.NORD, stanzaB);
	}

	@Test
	public void testGetStanzaAdiacente_DirezioneBloccataSenzaAttrezzo() {
		assertEquals(stanzaA, stanzaA.getStanzaAdiacente(Direzione.NORD));
	}

	@Test
	public void testGetStanzaAdiacente_DirezioneBloccataConAttrezzo() {
		stanzaA.addAttrezzo(chiave);
		assertEquals(stanzaB, stanzaA.getStanzaAdiacente(Direzione.NORD));
	}

	@Test
	public void testGetStanzaAdiacente_DirezioneNonBloccata() {
		Stanza stanzaC = new Stanza("Stanza C");
		stanzaA.impostaStanzaAdiacente(Direzione.SUD, stanzaC);

		assertEquals(stanzaC, stanzaA.getStanzaAdiacente(Direzione.SUD));
	}

	@Test
	public void testGetDescrizione_NonNulla() {
		assertNotNull(stanzaA.getDescrizione());
	}
}
