package it.uniroma3.diadia.personaggi;

import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.attrezzi.Attrezzo;

public class Cane extends AbstractPersonaggio {

	private static final String MESSAGGIO_MORSO = "GRRR! Il cane ti ha morso violentemente! Perdi 1 CFU.";

	public Cane(String nome, String presentazione) {
		super(nome, presentazione);
	}

	@Override
	public String agisci(Partita partita) {
		partita.getGiocatore().consumaCfu(); // Sottrae 1 CFU al giocatore
		return MESSAGGIO_MORSO;
	}
	
	@Override
	public String riceviRegalo(Attrezzo attrezzo, Partita partita) {
		if (attrezzo == null) return "Il cane ringhia.";
		
		// Verifica se il regalo è il suo cibo preferito
		if (attrezzo.getNome().equals("osso")) {
			
			Attrezzo attrezzoCustodito = new Attrezzo("chiave", 2);
			partita.getStanzaCorrente().addAttrezzo(attrezzoCustodito);
			
			return "WOF WOF! Il cane accetta felicemente l'osso, lo rosicchia e sputa a terra qualcosa: un(a) " + attrezzoCustodito.getNome() + "!";
		} else {
			// Per tutto il resto morde e toglie un CFU
			partita.getGiocatore().consumaCfu();
			return "RINGHIO! Al cane non piace " + attrezzo.getNome() + "! Ti morde violentemente! Perdi 1 CFU.";
		}
	}
}