package it.uniroma3.diadia.personaggi;

import java.util.Collection;
import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.ambienti.Stanza;
import it.uniroma3.diadia.attrezzi.Attrezzo;

public class Strega extends AbstractPersonaggio {

	private static final String MESSAGGIO_SALUTATA = "Ahahah! Visto che sei stato educato ti manderò in un posto ricco di tesori!";
	private static final String MESSAGGIO_NON_SALUTATA = "Che maleducato! Non mi hai nemmeno salutata! Sparisci in questa stanza desolata!";

	public Strega(String nome, String presentazione) {
		super(nome, presentazione);
	}

	@Override
	public String agisci(Partita partita) {
		Stanza stanzaCorrente = partita.getStanzaCorrente();
		// Supponiamo che stanzaCorrente.getStanzeAdiacenti() ritorni la Collection delle stanze collegate
		Collection<Stanza> adiacenti = stanzaCorrente.getStanzeAdiacenti();
		
		if (adiacenti.isEmpty()) {
			return "Non ci sono stanze adiacenti in cui teletrasportarti!";
		}

		Stanza destinazione = null;

		if (this.haSalutato()) {
			// Cerca la stanza adiacente con PIÙ attrezzi
			for (Stanza s : adiacenti) {
				if (destinazione == null || s.getAttrezzi().size() > destinazione.getAttrezzi().size()) {
					destinazione = s;
				}
			}
			partita.setStanzaCorrente(destinazione);
			return MESSAGGIO_SALUTATA;
		} else {
			// Cerca la stanza adiacente con MENO attrezzi
			for (Stanza s : adiacenti) {
				if (destinazione == null || s.getAttrezzi().size() < destinazione.getAttrezzi().size()) {
					destinazione = s;
				}
			}
			partita.setStanzaCorrente(destinazione);
			return MESSAGGIO_NON_SALUTATA;
		}
		
		
	}

	@Override
	public String riceviRegalo(Attrezzo attrezzo, Partita partita) {
		if (attrezzo == null) return "Non hai regalato nulla...";
		
		// La strega trattiene semplicemente l'oggetto
		return "HIHIHIHI! Grazie per il regalo, " + attrezzo.getNome() + " sarà mio per sempre! *scoppia a ridere sguaiatamente*";
	}
}