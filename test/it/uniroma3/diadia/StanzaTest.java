package it.uniroma3.diadia;

import static org.junit.jupiter.api.Assertions.*;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.uniroma3.diadia.ambienti.Stanza;
import it.uniroma3.diadia.attrezzi.Attrezzo;

class StanzaTest {

	private Stanza stanzaVuota;
	private Stanza stanzaConAttrezzo;
	private Attrezzo spada;
	private Attrezzo osso;
	
	@BeforeEach
	public void Setup() {
	stanzaVuota=new Stanza("StanzaVuota");
	stanzaConAttrezzo=new Stanza("StanzaAdiacente");
	spada=new Attrezzo("spada",10);
	osso=new Attrezzo("osso",5);
	
	stanzaConAttrezzo.addAttrezzo(spada);
	}
	
	@Test
	public void testStanzaAdiacenteCasoBase() {
		 Stanza nord = new Stanza("nord");
		 stanzaVuota.impostaStanzaAdiacente("nord", nord);
		 assertEquals(nord, stanzaVuota.getStanzaAdiacente("nord"));
	}
	
	@Test
	public void testImpostaStanzaAdiacenteAggiornareDirezione() {
		Stanza nord1 = new Stanza("nord Vecchio");
		Stanza nord2 = new Stanza("nord NUovo");
		stanzaVuota.impostaStanzaAdiacente("nord", nord1);
		stanzaVuota.impostaStanzaAdiacente("nord", nord2);
		
		assertEquals(nord2, stanzaVuota.getStanzaAdiacente("nord"));
	}
	
	@Test
	public void testImpostaStanzaAdiacenteOltreLimite() {
		stanzaVuota.impostaStanzaAdiacente("nord", new Stanza("N"));
		stanzaVuota.impostaStanzaAdiacente("sud", new Stanza("S"));
		stanzaVuota.impostaStanzaAdiacente("est", new Stanza("E"));
		stanzaVuota.impostaStanzaAdiacente("ovest", new Stanza("O"));
		
		// Proviamo ad aggiungere una quinta direzione e speriamo ci dia errore HOPE!
		stanzaVuota.impostaStanzaAdiacente("alto", new Stanza("A"));
		
		assertNull(stanzaVuota.getStanzaAdiacente("alto"));
	}
	
	// Metodo del getStanzaAdiacente
	
	@Test
	public void testGetStanzaAdiacenteEsiste() {
		Stanza sud = new Stanza("sud");
		stanzaVuota.impostaStanzaAdiacente("sud", sud);
		assertEquals(sud, stanzaVuota.getStanzaAdiacente("sud"));
	}
	
	@Test
	public void testGetStanzaAdiacenteInesistente() {
		assertNull(stanzaVuota.getStanzaAdiacente("nord"));
	}
	
	@Test
	public void testGetStanzaAdiacenteNoDirezione() {
		assertNull(stanzaVuota.getStanzaAdiacente(null));
	}
	
	// Metodo addAttrezzo
	@Test
	public void testAddAttrezzoVuoto() {
		assertTrue(stanzaVuota.addAttrezzo(osso));
		assertEquals(osso,stanzaVuota.getAttrezzo("osso"));
	}
	
	@Test
	public void testAddAttrezzoPiena() {
		for(int i=0;i<10;i++) {
			stanzaVuota.addAttrezzo(new Attrezzo("oggetto"+i,1));
		}
		assertFalse(stanzaVuota.addAttrezzo(osso));
	}
	
	@Test
	public void testAddAttrezzoGiaAttrezzi() {
		assertTrue(stanzaConAttrezzo.addAttrezzo(osso));
		assertTrue(stanzaConAttrezzo.hasAttrezzo("spada"));
		assertTrue(stanzaConAttrezzo.hasAttrezzo("osso"));
	}
	
	// Metodo hasAttrezzo
	
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
	
	//Metodo getAttrezzo aspettandoci indietro un oggetto e non un booleano
	
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
    
    // Test sulla rimozione 
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


