package it.uniroma3.diadia;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.uniroma3.diadia.attrezzi.Attrezzo;
import it.uniroma3.diadia.giocatore.Borsa;

class BorsaTest {
	private Borsa borsa;
	private Attrezzo spada;
	private Attrezzo osso;
	private Attrezzo scudo;
	
	@BeforeEach
	public void setUp(){
		this.borsa=new Borsa();
		spada=new Attrezzo("spada", 10);
		osso=new Attrezzo("osso", 8);
		scudo=new Attrezzo("scudo", 2);
		
		
	}
	
	//metodo addAttrezzo
	@Test
	public void testAddAttrezzoVieneInserito() {
		assertTrue(borsa.addAttrezzo(osso), "l'attrezzo non viene inserito"); 	
	}
	@Test
	public void testAddAttrezzoBorsaPiena() {
		borsa.addAttrezzo(osso);
		borsa.addAttrezzo(scudo);
		assertFalse(borsa.addAttrezzo(spada), "l'attrezzo viene inserito anche se la borsa è piena");
	}
	@Test
	public void testAddAttrezzoPesoOltreIlLimite() {
		borsa.addAttrezzo(scudo);
		assertFalse(borsa.addAttrezzo(spada), "l'attrezzo viene inserito e la borsa supera il peso massimo");
	}
	
	//metodo getPesoMax
	@Test
	public void testGetPesoMaxCostante() {
		assertEquals(10, borsa.getPesoMax(), "il peso massimo non è 10");
	}
	
	//metodo getAttrezzo
	@Test
	public void testGetAttrezzoNoAttrezzi() {
		assertNull(borsa.getAttrezzo("osso"), "non mi da null anche se la borsa è vuota");
	}
	@Test
	public void testGetAttrezzoUguale() {
		borsa.addAttrezzo(scudo);
		assertEquals(scudo, borsa.getAttrezzo("scudo"), "non viene restituito l'attrezzo giusto");
	}
	@Test
	public void testGetAttrezzoDiverso() {
		borsa.addAttrezzo(scudo);
		assertNotEquals(scudo, borsa.getAttrezzo("spada"), "viene restituito l'attrezzo sbagliato");
	}
	
	//metodo getPeso
	@Test
	public void testGetPesoUguale() {
		borsa.addAttrezzo(scudo);
		assertEquals(2, borsa.getPeso(), "il peso è diverso");
	}
	@Test
	public void testGetPesoDiverso() {
		assertNotEquals(10, borsa.getPeso(), "il peso è sbagliato");
	}
	
	
	//metodo isEmpty
	@Test
	public void testIsEmptyBorsaVuota() {
		assertTrue(borsa.isEmpty(),"la borsa non è vuota");
	}
	@Test
	public void testIsEmptyBorsaNonVuota() {
		borsa.addAttrezzo(scudo);
		assertFalse(borsa.isEmpty()," la borsa è vuota anche se non dovrebbe esserlo");
	}
	
	//metodo hasAttrezzo
	@Test
	public void testHasAttrezzoBorsaVuota() {
		assertFalse(borsa.hasAttrezzo("scudo"), "l'attrezzo viene trovato anche se la borsa è vuota");
	}
	@Test
	public void testHasAttrezzoPresente() {
		borsa.addAttrezzo(scudo);
		assertTrue(borsa.hasAttrezzo("scudo"),"l'attrezzo non viene trovato");
	}
	@Test
	public void testHasAttrezzoNonPresente() {
		borsa.addAttrezzo(scudo);
		assertFalse(borsa.hasAttrezzo("spada"),"l'attrezzo viene trovato anche se non c'è");
	}
	
	//metodo remove attrezzo 
	@Test
	public void testRemoveAttrezzoBase() {
		borsa.addAttrezzo(scudo);
		assertEquals(scudo,borsa.removeAttrezzo("scudo"),"l'attrezzo non viene rimosso");
	}
	@Test
	public void testRemoveAttrezzoNull() {
		assertNull(borsa.removeAttrezzo("scudo"),"l'attrezzo viene rimosso anche se non c'è");
	}
	@Test
	public void testRemoveAttrezzoNonPresente() {
		borsa.addAttrezzo(osso);
		assertNotEquals(osso,borsa.removeAttrezzo("spada"),"viene rimosso l'attrezzo sbagliato");
	}
		
	

}
