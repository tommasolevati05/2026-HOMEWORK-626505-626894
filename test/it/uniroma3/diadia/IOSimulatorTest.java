package it.uniroma3.diadia;

import static org.junit.Assert.assertTrue;
import org.junit.jupiter.api.Test;

class IOSimulatorTest {

	@Test
    public void testAccettazionePartitaVinta() {
        IOSimulator simulatore = new IOSimulator("prendi osso", "vai sud", "posa osso", "vai nord", "vai nord");
        DiaDia gioco = new DiaDia(simulatore); 
        gioco.gioca();
        assertTrue(simulatore.hasMessaggio("Hai vinto"));
    }
}
