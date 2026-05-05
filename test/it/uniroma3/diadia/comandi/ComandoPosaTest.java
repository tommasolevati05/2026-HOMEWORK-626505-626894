package it.uniroma3.diadia.comandi;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import it.uniroma3.diadia.IOConsole;
import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.attrezzi.Attrezzo;

class ComandoPosaTest {

	private ComandoPosa comando;
    private Partita partita;
    private Attrezzo attrezzo;
	@BeforeEach
	void setUp() throws Exception {
		comando = new ComandoPosa();
		partita = new Partita();
		comando.setIo(new IOConsole());
		attrezzo = new Attrezzo("martello",3);
	}

	@Test
	void testPosaAttrezzoConSuccesso() {
		partita.getGiocatore().getBorsa().addAttrezzo(attrezzo);
		comando.setParametro("martello");
		comando.esegui(partita);
		
		assertFalse(partita.getGiocatore().getBorsa().hasAttrezzo("martello"),"L'attrezzo dovrebbe essere stato rimosso dalla borsa");
		
		assertTrue(partita.getStanzaCorrente().hasAttrezzo("martello"),"L'attrezzo dovrebbe essere presente nella stanza");
	}
	
	@Test
    public void testPosaAttrezzoParametroNullo() {
        partita.getGiocatore().getBorsa().addAttrezzo(attrezzo);
        comando.setParametro(null);
        
        comando.esegui(partita);
        
        assertTrue(partita.getGiocatore().getBorsa().hasAttrezzo("martello"),"L'attrezzo non deve essere rimosso se il parametro è nullo");
    }
	
	@Test
    public void testPosaAttrezzoMaStanzaPiena() {
        partita.getGiocatore().getBorsa().addAttrezzo(attrezzo);
        comando.setParametro("martello");
        
        for (int i = 0; i < 10; i++) {
            partita.getStanzaCorrente().addAttrezzo(new Attrezzo("finto" + i, 1));
        }
        
        comando.esegui(partita);
        
        assertTrue(partita.getGiocatore().getBorsa().hasAttrezzo("martello"),"L'attrezzo deve tornare in borsa se la stanza è piena");
    }

}
