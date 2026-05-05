package it.uniroma3.diadia.comandi;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.uniroma3.diadia.IOConsole;
import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.attrezzi.Attrezzo;

class ComandoPrendiTest {

	private ComandoPrendi comando;
	private Partita partita;
	private Attrezzo attrezzo;
	@BeforeEach
	void setUp() throws Exception {
		comando = new ComandoPrendi();
		partita = new Partita();
		comando.setIo(new IOConsole());
		attrezzo = new Attrezzo("martello",3);
	}

	@Test
	void testPrendiAttrezzoConSuccesso() {
		partita.getGiocatore().getBorsa().addAttrezzo(attrezzo);
		comando.setParametro("martello");
		comando.esegui(partita);
		
		assertTrue(partita.getGiocatore().getBorsa().hasAttrezzo("martello"),"L'attrezzo dovrebbe essere presente nella borsa");
		assertFalse(partita.getStanzaCorrente().hasAttrezzo(null));
	}
	
	@Test
	public void testPrendiAttrezzoNonPresenteInStanza() {
        comando.setParametro("martello");
        
        comando.esegui(partita);
        
        assertFalse(partita.getGiocatore().getBorsa().hasAttrezzo("martello"),"L'attrezzo non deve essere preso se il parametro è nullo");
    }
	
	@Test
    public void testPrendiAttrezzoParametroNullo() {
        partita.getStanzaCorrente().addAttrezzo(attrezzo);
        comando.setParametro(null); 
        
        comando.esegui(partita);
        
        assertTrue(partita.getStanzaCorrente().hasAttrezzo("martello"),"L'attrezzo non deve essere rimosso se il parametro è nullo");
    }

    @Test
    public void testPrendiAttrezzoMaBorsaTroppoPesante() {
    	attrezzo = new Attrezzo("incudine",50);
        partita.getStanzaCorrente().addAttrezzo(attrezzo);
        comando.setParametro("incudine");
        
        comando.esegui(partita);
        
        assertTrue(partita.getStanzaCorrente().hasAttrezzo("incudine"),"L'attrezzo pesante deve rimanere nella stanza");
                   
        assertFalse(partita.getGiocatore().getBorsa().hasAttrezzo("incudine"),"L'attrezzo pesante non deve entrare nella borsa");
    }

}
