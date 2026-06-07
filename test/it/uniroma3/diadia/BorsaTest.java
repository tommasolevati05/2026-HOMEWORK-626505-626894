package it.uniroma3.diadia;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.SortedSet;

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

	@Test
	public void testGetPesoMaxCostante() {
		assertEquals(10, borsa.getPesoMax(), "il peso massimo non è 10");
	}

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
		assertNull( borsa.getAttrezzo("spada"), "viene restituito l'attrezzo sbagliato");
	}

	@Test
	public void testGetPesoUguale() {
		borsa.addAttrezzo(scudo);
		assertEquals(2, borsa.getPeso(), "il peso è diverso");
	}
	@Test
	public void testGetPesoDiverso() {
		assertNotEquals(10, borsa.getPeso(), "il peso è sbagliato");
	}


	@Test
	public void testIsEmptyBorsaVuota() {
		assertTrue(borsa.isEmpty(),"la borsa non è vuota");
	}
	@Test
	public void testIsEmptyBorsaNonVuota() {
		borsa.addAttrezzo(scudo);
		assertFalse(borsa.isEmpty()," la borsa è vuota anche se non dovrebbe esserlo");
	}

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
		assertNull(borsa.removeAttrezzo("spada"),"viene rimosso l'attrezzo sbagliato");
	}

	@Test
	public void testGetContenutoOrdinatoPerPeso_Vuota() {
		assertEquals(Collections.emptyList(), borsa.getContenutoOrdinatoPerPeso());

	}

	@Test
	public void testGetContenutoOrdinatoPerPeso_nonVuota() {
		borsa.addAttrezzo(osso);
		assertEquals(Collections.singletonList(osso), borsa.getContenutoOrdinatoPerPeso());

	}

	@Test
	public void testGetContenutoRaggruppatoPerPeso_vuota() {
		assertEquals(Collections.emptyMap(), borsa.getContenutoRaggruppatoPerPeso());
	}

	@Test
	public void testGetContenutoRaggruppatoPerPeso_singoletto() {
		borsa.addAttrezzo(osso);
		assertEquals(Collections.singletonMap(8, Collections.singleton(this.osso)), borsa.getContenutoRaggruppatoPerPeso());
	}

	@Test
	public void testGetContenutoRaggruppatoPerPeso_doppiettoPesiDiversi() {
		borsa.addAttrezzo(this.osso);
		borsa.addAttrezzo(this.scudo);

		Map<Integer, Set<Attrezzo>> mappaAttesa = new HashMap<>();
		mappaAttesa.put(8, Collections.singleton(this.osso));
		mappaAttesa.put(2, Collections.singleton(this.scudo));

		assertEquals(mappaAttesa, borsa.getContenutoRaggruppatoPerPeso());
	}


	@Test
	public void testGetContenutoRaggruppatoPerPeso_doppiettoPesiUguali() {
		Attrezzo sasso = new Attrezzo("sasso", 8);
		borsa.addAttrezzo(this.osso);  // peso 8
		borsa.addAttrezzo(sasso);      // peso 8

		Map<Integer, Set<Attrezzo>> mappaAttesa = new HashMap<>();
		Set<Attrezzo> insiemeAttrezzi = new HashSet<>();
		insiemeAttrezzi.add(this.osso);
		insiemeAttrezzi.add(sasso);
		mappaAttesa.put(8, insiemeAttrezzi);

		assertEquals(mappaAttesa, borsa.getContenutoRaggruppatoPerPeso());
	}


	@Test
	public void testGetSortedSetOrdinatoPerPeso_Vuota() {
		assertTrue(borsa.getSortedSetOrdinatoPerPeso().isEmpty());
	}

	@Test
	public void testGetSortedSetOrdinatoPerPeso_PesiDiversi() {
		borsa.addAttrezzo(this.osso);   // 8
		borsa.addAttrezzo(this.scudo);  // 2

		SortedSet<Attrezzo> risultato = borsa.getSortedSetOrdinatoPerPeso();
		assertEquals(2, risultato.size());
		assertEquals(this.scudo, risultato.first());
		assertEquals(this.osso, risultato.last());
	}

	@Test
	public void testGetSortedSetOrdinatoPerPeso_StessoPesoNomeDiversoRimangonoDistinti() {
		Attrezzo sasso = new Attrezzo("sasso", 8);
		borsa.addAttrezzo(this.osso);
		borsa.addAttrezzo(sasso);

		SortedSet<Attrezzo> risultato = borsa.getSortedSetOrdinatoPerPeso();

		assertEquals(2, risultato.size(), "Due attrezzi con stesso peso ma nome diverso non devono essere fusi!");
		assertEquals(this.osso, risultato.first());
		assertEquals(sasso, risultato.last());
	}











}
