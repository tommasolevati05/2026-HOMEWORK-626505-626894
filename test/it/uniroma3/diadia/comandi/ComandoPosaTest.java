package it.uniroma3.diadia.comandi;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import it.uniroma3.diadia.IOSimulator;
import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.ambienti.Labirinto;
import it.uniroma3.diadia.attrezzi.Attrezzo;

class ComandoPosaTest {

	private ComandoPosa comando;
    private Partita partita;
    private Attrezzo attrezzo;
    private Labirinto labirinto;
	@BeforeEach
	void setUp() throws Exception {
		comando = new ComandoPosa();
		labirinto = Labirinto.newBuilder().addStanzaIniziale("Atrio").getLabirinto();
		partita = new Partita(labirinto);
		comando.setIo(new IOSimulator());
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
	

}
