package it.uniroma3.diadia.ambienti;

import it.uniroma3.diadia.attrezzi.Attrezzo;

/**
 * questa classe modella il labirinto.
 * il labirinto ha una entrata (stanza di ingresso) 
 * e un uscita (stanza vincente).
 * @author manuel
 * @see Stanza
 * @version 1.0
 */

public class Labirinto {
	private Stanza stanzaDiIngresso;
	private Stanza stanzaVincente;
	
	/** crea il labirinto.
	 */
	public Labirinto() {
		this.init(); 
	}
	
	/** crea tutte le stanze e le porte di collegamento
	 */
	private void init() {
		/* crea stanze del labirinto */
		Stanza atrio = new Stanza("Atrio");
		Stanza aulaN11 = new StanzaBloccata("Aula N11","est", "chiave");
		Stanza aulaN10 = new StanzaBuia("Aula N10","lanterna");
		Stanza laboratorio = new StanzaMagica("Laboratorio Campus");
		Stanza biblioteca = new Stanza("Biblioteca");
		
		/* crea gli attrezzi */
		Attrezzo lanterna = new Attrezzo("lanterna",3);
		Attrezzo osso = new Attrezzo("osso",1);
		Attrezzo spada = new Attrezzo("spada",5);
		Attrezzo chiave = new Attrezzo("chiave",1);
		
			
		/* pone gli attrezzi nelle stanze */
		aulaN10.addAttrezzo(spada);
		atrio.addAttrezzo(osso);
		laboratorio.addAttrezzo(chiave);
		aulaN11.addAttrezzo(lanterna);
		
		
		
		/* collega le stanze */
		atrio.impostaStanzaAdiacente("nord", biblioteca);
		atrio.impostaStanzaAdiacente("est", aulaN11);
		atrio.impostaStanzaAdiacente("sud", aulaN10);
		atrio.impostaStanzaAdiacente("ovest", laboratorio);
		aulaN11.impostaStanzaAdiacente("est", laboratorio);
		aulaN11.impostaStanzaAdiacente("ovest", atrio);
		aulaN10.impostaStanzaAdiacente("nord", atrio);
		aulaN10.impostaStanzaAdiacente("est", aulaN11);
		aulaN10.impostaStanzaAdiacente("ovest", laboratorio);
		laboratorio.impostaStanzaAdiacente("est", atrio);
		laboratorio.impostaStanzaAdiacente("ovest", aulaN11);
		biblioteca.impostaStanzaAdiacente("sud", atrio);
		
		
		// il gioco comincia nell'atrio
        this.stanzaDiIngresso = atrio;  
		this.stanzaVincente = biblioteca;
	}
	
	public Stanza getStanzaVincente() {
		return stanzaVincente;
	}

	public Stanza getStanzaDiIngresso() {
		return this.stanzaDiIngresso;
	}
}
