package it.uniroma3.diadia.giocatore;

import it.uniroma3.diadia.Configuratore;

/**
 * una classe che modella il giocatore della partita.
 * Gestisce i cfu del giocatore e gli attrezzi che possiede.
 * @author manuel
 * @version 1.0
 *
 */
public class Giocatore {
	
	private int cfu;
	private Borsa borsa;
	
	public Giocatore() {
		this.borsa=new Borsa();
		this.cfu = Configuratore.getCFUIniziali();
	}
	
	public int getCfu() {
		return this.cfu;
	}

	public int consumaCfu() {
		return this.cfu--;
	}
	
	public Borsa getBorsa() {
		return this.borsa;
	}

}


