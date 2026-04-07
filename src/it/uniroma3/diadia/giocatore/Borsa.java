package it.uniroma3.diadia.giocatore;

import it.uniroma3.diadia.attrezzi.Attrezzo;

/**
 * una classe che modella la borsa del giocatore.
 * la borsa ha una serie di attrezzi ed ha un peso massimo.
 * @author manuel
 *
 */
public class Borsa {
	
	public final static int DEFAULT_PESO_MAX_BORSA = 10;
	private Attrezzo[] attrezzi;
	private int numeroAttrezzi;
	private int pesoMax;
	
	
	public Borsa() {
	this(DEFAULT_PESO_MAX_BORSA);
	}
	
	/**
	 * crea una borsa.
	 * @param pesoMax il peso massimo della borsa
	 *
	 */
	public Borsa(int pesoMax) {
	this.pesoMax = pesoMax;
	this.attrezzi = new Attrezzo[10]; // speriamo bastino...
	this.numeroAttrezzi = 0;
	}
	
	/**
	 * aggiunta di un attrezzo
	 * @param attrezzo l'attrezzo da inserire
	 * @return true se rientra nei limiti imposti
	 */
	public boolean addAttrezzo(Attrezzo attrezzo) {
	if (this.getPeso() + attrezzo.getPeso() > this.getPesoMax())
	return false;
	if (this.numeroAttrezzi==10)
	return false;
	this.attrezzi[this.numeroAttrezzi] = attrezzo;
	this.numeroAttrezzi++;
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
	 * @param nomeAttrezzo il nome dell'attrezzo che sto cercando
	 * @return l'attrezzo che sto cercando
	 */
	public Attrezzo getAttrezzo(String nomeAttrezzo) {
	Attrezzo a = null;
	for (int i= 0; i<this.numeroAttrezzi; i++)
	if (this.attrezzi[i].getNome().equals(nomeAttrezzo))
	a = attrezzi[i];
	return a;
	}
	
	/**
	 * ricevo il peso attuale della borsa
	 * @return il peso della borsa
	 */
	public int getPeso() {
		int peso = 0;
		for (int i= 0; i<this.numeroAttrezzi; i++)
		peso += this.attrezzi[i].getPeso();
		return peso;
		}
	
	public boolean isEmpty() {
		return this.numeroAttrezzi == 0;
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
	 * @return true se l'attrezzo e' stato rimosso, false altrimenti
	 */
	public Attrezzo removeAttrezzo(String nomeAttrezzo) {
	    Attrezzo a = null;
	    int indiceDaRimuovere = -1;
	    
	    for (int i = 0; i < this.numeroAttrezzi; i++) {
	        if (this.attrezzi[i].getNome().equals(nomeAttrezzo)) {
	            indiceDaRimuovere = i;
	            a = this.attrezzi[i];
	            break; 
	        }
	    }
	    if (indiceDaRimuovere != -1) {
	        int indiceUltimoElemento = this.numeroAttrezzi - 1;
	        this.attrezzi[indiceDaRimuovere] = this.attrezzi[indiceUltimoElemento];
	        this.numeroAttrezzi--;
	    }
	    
	    return a;
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
		for (int i= 0; i<this.numeroAttrezzi; i++)
		s.append(attrezzi[i].toString()+" ");
		}
		else
		s.append("Borsa vuota");
		return s.toString();
		}
	
}
