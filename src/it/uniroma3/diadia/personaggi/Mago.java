package it.uniroma3.diadia.personaggi;

import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.attrezzi.Attrezzo;

public class Mago extends AbstractPersonaggio {

	private static final String MESSAGGIO_DONO = "Sei un vero simpaticone, con una mia magica azione, troverai un nuovo oggetto per il tuo borsone!";
	private static final String MESSAGGIO_SCUSE = "Mi spiace, ma non ho piu' nulla...";
	private Attrezzo attrezzo;

	public Mago(String nome, String presentazione, Attrezzo attrezzo) {
		super(nome, presentazione);
		this.attrezzo = attrezzo;
	}

	@Override
	public String agisci(Partita partita) {
		if (this.attrezzo != null) {
			partita.getStanzaCorrente().addAttrezzo(this.attrezzo);
			this.attrezzo = null; // Il mago si priva del suo unico attrezzo
			return MESSAGGIO_DONO;
		}
		return MESSAGGIO_SCUSE;
	}
	
	@Override
	public String riceviRegalo(Attrezzo attrezzo, Partita partita) {
		if (attrezzo == null) return "Non hai specificato nessun attrezzo valido.";
		
		int nuovoPeso = attrezzo.getPeso() / 2;
		if (nuovoPeso == 0) {
			nuovoPeso = 1; 
		}
		attrezzo.setPeso(nuovoPeso);
		
		// Lo lascia cadere nella stanza
		partita.getStanzaCorrente().addAttrezzo(attrezzo);
		
		return "Abra kadabra! Grazie per il regalo! Ho dimezzato il peso di " + attrezzo.getNome() + 
			   " (ora pesa " + nuovoPeso + ") e l'ho lasciato cadere nella stanza.";
	}
}