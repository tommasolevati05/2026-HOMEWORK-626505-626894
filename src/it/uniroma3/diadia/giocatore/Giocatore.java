package it.uniroma3.diadia.giocatore;

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
	static final private int CFU_INIZIALI = 20;
	
	public Giocatore() {
		this.borsa=new Borsa();
		this.cfu=CFU_INIZIALI;
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


