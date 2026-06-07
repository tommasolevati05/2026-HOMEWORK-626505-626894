package it.uniroma3.diadia;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.uniroma3.diadia.giocatore.Giocatore;

class GiocatoreTest {
	private Giocatore giocatore;
	
	@BeforeEach
	void setUp() {
		this.giocatore=new Giocatore();
	}
	
	
	@Test
	public void testGetCfuCostante() {
		assertEquals(20, giocatore.getCfu(), "i cfu non sono 20");
	}
	
	@Test
	public void testConsumaCfuUguali() {
		giocatore.consumaCfu();
		assertEquals(19, giocatore.getCfu(), "non consuma i cfu");
	}
	
	@Test
	public void testGetBorsaNonNulla() {
		assertNotNull(giocatore.getBorsa(), " la borsa non esiste");
		
	}
	

}
