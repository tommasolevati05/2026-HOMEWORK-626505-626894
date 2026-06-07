package it.uniroma3.diadia.giocatore;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;

import it.uniroma3.diadia.Configuratore;
import it.uniroma3.diadia.attrezzi.Attrezzo;

/**
 * una classe che modella la borsa del giocatore.
 * la borsa ha una serie di attrezzi ed ha un peso massimo.
 * @author manuel
 *
 */
public class Borsa {

	private List<Attrezzo> attrezzi;
	private int pesoMax;


	public Borsa() {
		this(Configuratore.getPesoMassimoBorsa());
	}

	/**
	 * crea una borsa.
	 * @param pesoMax il peso massimo della borsa
	 *
	 */
	public Borsa(int pesoMax) {
		this.pesoMax = pesoMax;
		this.attrezzi = new ArrayList<>();
	}

	/**
	 * aggiunta di un attrezzo
	 * @param attrezzo l'attrezzo da inserire
	 * @return true se rientra nei limiti imposti
	 */
	public boolean addAttrezzo(Attrezzo attrezzo) {
		if (this.getPeso() + attrezzo.getPeso() > this.getPesoMax())
			return false;
		this.attrezzi.add(attrezzo);
		return true;
	}

	/**
	 * ricevo il peso massimo della borsa
	 * @return ul peso massimo che puo avere la borsa
	 */
	public int getPesoMax() {
		return pesoMax;
	}

	/**
	 * se è presente nella borsa, ricevo l'attrezzo che voglio 
	 * @param wanted il nome dell'attrezzo che sto cercando
	 * @return l'attrezzo che sto cercando
	 */
	public Attrezzo getAttrezzo(String wanted) {
		for (Attrezzo a:this.attrezzi)
			if (a.getNome().equals(wanted))
				return a;
		return null;
	}

	/**
	 * ricevo il peso attuale della borsa
	 * @return il peso della borsa
	 */
	public int getPeso() {
		int peso = 0;
		for (Attrezzo a:this.attrezzi)
			peso += a.getPeso();
		return peso;
	}

	public boolean isEmpty() {
		return this.attrezzi.isEmpty();
	}
	/**
	 * controllo se nella borsa è presente un attrezzo che sto cercando
	 * @param nomeAttrezzo il nome dell'attrezzo che sto cercando
	 * @return true o false
	 */
	public boolean hasAttrezzo(String nomeAttrezzo) {
		return this.getAttrezzo(nomeAttrezzo)!=null;
	}

	/**
	 * Rimuove un attrezzo dalla borsa (ricerca in base al nome).
	 * @param nomeAttrezzo il nome dell'attrezzo da rimuovere
	 * @return il riferimento all'attrezzo appena rimosso
	 */
	public Attrezzo removeAttrezzo(String nomeAttrezzo) {
		int index=this.attrezzi.indexOf(new Attrezzo(nomeAttrezzo,0));
		if(index!=-1) { //trovato
			return this.attrezzi.remove(index);

		}
		return null;

	}

	public List<Attrezzo> getContenutoOrdinatoPerPeso(){ //esterno
		ComparatorePesoNome perPeso=new ComparatorePesoNome();
		final List<Attrezzo> inOrdine=new ArrayList<>(this.attrezzi);
		Collections.sort(inOrdine,perPeso); //FARE QUESTO ALL'ESAME 
		return inOrdine;
	}

	public SortedSet<Attrezzo> getContenutoOrdinatoPerNome(){
		final TreeSet<Attrezzo> inOrdine = new TreeSet<>(); //o cosi oppure return new TreeSet<>(this.attrezzi)
		inOrdine.addAll(this.attrezzi);
		return inOrdine;
	}

	public Map<Integer, Set<Attrezzo>> getContenutoRaggruppatoPerPeso(){
		//QUESTI ESERCIZI IN QUESTA FORMA SI RISOLVONO IN UN UNICA SCANSIONE
		final Map<Integer, Set<Attrezzo>> peso2attrezzi =new HashMap<>();
		for(Attrezzo corrente: this.attrezzi) {
			if(peso2attrezzi.containsKey(corrente.getPeso())) {
				//corrente ha un peso gia visto prima 
				Set<Attrezzo> vecchioInsiemePerAttrezziDiPesoGiaVistiPrima = peso2attrezzi.get(corrente.getPeso());
				vecchioInsiemePerAttrezziDiPesoGiaVistiPrima.add(corrente);
			}
			else {
				//corrente ha un peso mai visto prima
				final Set<Attrezzo> nuovoInsiemeAttrezziMaiVistoPrima=new HashSet<>();
				nuovoInsiemeAttrezziMaiVistoPrima.add(corrente);
				peso2attrezzi.put(corrente.getPeso(), nuovoInsiemeAttrezziMaiVistoPrima);
			}
		}
		return peso2attrezzi;
	}
	
	
	public SortedSet<Attrezzo> getSortedSetOrdinatoPerPeso() {
		SortedSet<Attrezzo> inOrdine = new TreeSet<>(new ComparatorePesoNome());
		inOrdine.addAll(this.attrezzi);
		return inOrdine;
	}
	
	/**
	 * ricevo una descrizione della borsa.
	 * @return la descrizione della borsa
	 */
	public String getDescrizioneBorsa() {
		return this.toString();

	}



	/**
	 * restituisce una rappresentazione stringa della borsa
	 * stampandone il peso rimanente e gli eventuali attrezzi contenuti.
	 */
	public String toString() {
		StringBuilder s = new StringBuilder();
		if (!this.isEmpty()) {
			s.append("Contenuto borsa ("+this.getPeso()+"kg/"+this.getPesoMax()+"kg): ");
			Iterator<Attrezzo> it=this.attrezzi.iterator(); //CASO CON ITERATORE POTEVO USARE ANCHE FOR EACH
			while(it.hasNext())
				s.append(it.next()+" ");
		}
		else
			s.append("Borsa vuota");
		return s.toString();
	}
	//CONTROL A CONTROL I INDENTA DA SOLO

}
