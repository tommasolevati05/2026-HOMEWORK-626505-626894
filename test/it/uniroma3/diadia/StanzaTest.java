package it.uniroma3.diadia;

import static org.junit.jupiter.api.Assertions.*;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.uniroma3.diadia.ambienti.Direzione;
import it.uniroma3.diadia.ambienti.Stanza;
import it.uniroma3.diadia.attrezzi.Attrezzo;

class StanzaTest {

	private Stanza stanzaVuota;
	private Stanza stanzaConAttrezzo;
	private Attrezzo spada;
	private Attrezzo osso;
	
	@BeforeEach
	public void setUp() {
		stanzaVuota = new Stanza("StanzaVuota");
		stanzaConAttrezzo = new Stanza("StanzaAdiacente");
		spada = new Attrezzo("spada", 10);
		osso = new Attrezzo("osso", 5);
		stanzaConAttrezzo.addAttrezzo(spada);
	}
	

	@Test
	public void testStanzaAdiacenteCasoBase() {
		 Stanza nord = new Stanza("nord");
		 stanzaVuota.impostaStanzaAdiacente(Direzione.NORD, nord);
		 assertEquals(nord, stanzaVuota.getStanzaAdiacente(Direzione.NORD));
	}

	@Test
	public void testImpostaStanzaAdiacenteAggiornareDirezione() {
		Stanza nord1 = new Stanza("nord Vecchio");
		Stanza nord2 = new Stanza("nord NUovo");
		stanzaVuota.impostaStanzaAdiacente(Direzione.NORD, nord1);
		stanzaVuota.impostaStanzaAdiacente(Direzione.NORD, nord2);

		assertEquals(nord2, stanzaVuota.getStanzaAdiacente(Direzione.NORD));
	}


	@Test
	public void testGetStanzaAdiacenteEsiste() {
		Stanza sud = new Stanza("sud");
		stanzaVuota.impostaStanzaAdiacente(Direzione.SUD, sud);
		assertEquals(sud, stanzaVuota.getStanzaAdiacente(Direzione.SUD));
	}

	@Test
	public void testGetStanzaAdiacenteInesistente() {
		assertNull(stanzaVuota.getStanzaAdiacente(Direzione.NORD));
	}

	@Test
	public void testGetStanzaAdiacenteNoDirezione() {
		assertNull(stanzaVuota.getStanzaAdiacente((Direzione) null));
	}
	
	@Test
	public void testAddAttrezzoVuoto() {
		assertTrue(stanzaVuota.addAttrezzo(osso));
		assertEquals(osso,stanzaVuota.getAttrezzo("osso"));
	}
	
	
	@Test
	public void testAddAttrezzoGiaAttrezzi() {
		assertTrue(stanzaConAttrezzo.addAttrezzo(osso));
		assertTrue(stanzaConAttrezzo.hasAttrezzo("spada"));
		assertTrue(stanzaConAttrezzo.hasAttrezzo("osso"));
	}
	

	@Test
	public void testHasAttrezzoPresente() {
		assertTrue(stanzaConAttrezzo.hasAttrezzo("spada"));
	}
	
	@Test
	public void testHasAttrezzoNonPresente() {
		assertFalse(stanzaConAttrezzo.hasAttrezzo("osso"));
	}
	
	@Test
	public void testHasAttrezzoVuota() {
		assertFalse(stanzaVuota.hasAttrezzo("osso"));
	}
	

	@Test
    public void testGetAttrezzoAttrezzoPresente() {
        assertEquals(spada, stanzaConAttrezzo.getAttrezzo("spada"));
    }

    @Test
    public void testGetAttrezzoAttrezzoNonPresente() {
        assertNull(stanzaConAttrezzo.getAttrezzo("chiave"));
    }

    @Test
    public void testGetAttrezzoInStanzaVuota() {
        assertNull(stanzaVuota.getAttrezzo("osso"));
    }
    

   @Test
   public void testRemoveAttrezzoBase() {
	   assertTrue(stanzaConAttrezzo.removeAttrezzo(spada));
   }
   
   @Test
   public void testRemoveAttrezzoNull() {
	   stanzaConAttrezzo.removeAttrezzo(spada);
	   assertFalse(stanzaConAttrezzo.removeAttrezzo(null));
   }
   
   @Test
   public void testRemoveAttrezzoNonPresente() {
	   assertFalse(stanzaConAttrezzo.removeAttrezzo(osso));
   }
   
  
}


