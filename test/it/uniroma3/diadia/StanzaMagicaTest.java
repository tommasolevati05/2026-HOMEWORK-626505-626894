package it.uniroma3.diadia;


import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.uniroma3.diadia.ambienti.StanzaMagica;
import it.uniroma3.diadia.attrezzi.Attrezzo;

public class StanzaMagicaTest {

	private StanzaMagica stanzaMagica;
	private Attrezzo a1;
	private Attrezzo a2;
	private Attrezzo a3;
	private Attrezzo a4;

	@BeforeEach
	public void setUp() {
		stanzaMagica = new StanzaMagica("Laboratorio");
		a1 = new Attrezzo("osso", 2);
		a2 = new Attrezzo("scudo", 4);
		a3 = new Attrezzo("spada", 5);
		a4 = new Attrezzo("chiave", 1); // Questo sarà il quarto attrezzo inserito
	}

	@Test
	public void testAddAttrezzoSottoSoglia() {
		assertTrue(stanzaMagica.addAttrezzo(a1));
		assertTrue(stanzaMagica.addAttrezzo(a2));
		assertTrue(stanzaMagica.addAttrezzo(a3));
		assertNotNull(stanzaMagica.getAttrezzo("spada"));
		assertEquals(5, stanzaMagica.getAttrezzo("spada").getPeso());
	}

	@Test
	public void testAddAttrezzoOltreSogliaModificaAttrezzo() {
		stanzaMagica.addAttrezzo(a1);
		stanzaMagica.addAttrezzo(a2);
		stanzaMagica.addAttrezzo(a3);
		stanzaMagica.addAttrezzo(a4);
		assertFalse(stanzaMagica.hasAttrezzo("chiave"));
		assertTrue(stanzaMagica.hasAttrezzo("eyaihca"));
		assertEquals(2, stanzaMagica.getAttrezzo("eyaihca").getPeso());
	}
}
