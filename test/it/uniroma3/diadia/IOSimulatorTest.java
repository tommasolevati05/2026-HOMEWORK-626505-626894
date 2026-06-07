package it.uniroma3.diadia;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import it.uniroma3.diadia.ambienti.Direzione;
import it.uniroma3.diadia.ambienti.Labirinto;


class IOSimulatorTest {

	@Test
	public void testPartitaMonolocaleVintaSubito() {
		Labirinto labirinto = Labirinto.newBuilder()
				.addStanzaIniziale("Salotto")
				.addStanzaVincente("Salotto")
				.getLabirinto();
		
		
		IOSimulator simulatore = new IOSimulator("aiuto");
		DiaDia gioco = new DiaDia(labirinto, simulatore);
		gioco.gioca();
		assertTrue(simulatore.hasMessaggio("Hai vinto"));
	}

	@Test
	public void testPartitaBilocaleSpostamentoEVittoria() {
	    Labirinto labirinto = Labirinto.newBuilder()
	            .addStanzaIniziale("Ingresso")
	            .addStanzaVincente("Uscita")
	            .addAdiacenza("Ingresso", "Uscita", Direzione.NORD)
	            .getLabirinto();
	    
		IOSimulator simulatore = new IOSimulator("vai nord");
		DiaDia gioco = new DiaDia(labirinto, simulatore);
		gioco.gioca();
		assertTrue(simulatore.hasMessaggio("Hai vinto"));
	}

	@Test
	public void testPartitaUscitaVolontariaConComandoFine() {
		Labirinto labirinto = Labirinto.newBuilder()
				.addStanzaIniziale("Atrio")
				.addStanzaVincente("Biblioteca")
				.getLabirinto();
		
		IOSimulator simulatore = new IOSimulator("fine");
		DiaDia gioco = new DiaDia(labirinto, simulatore);
		gioco.gioca();
		
		assertTrue(simulatore.hasMessaggio("Grazie di aver giocato!"));
		assertFalse(simulatore.hasMessaggio("Hai vinto!"));
	}
}