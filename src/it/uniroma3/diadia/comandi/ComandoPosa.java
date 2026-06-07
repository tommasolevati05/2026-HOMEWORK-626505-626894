package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.ambienti.Stanza;
import it.uniroma3.diadia.attrezzi.Attrezzo;
import it.uniroma3.diadia.giocatore.Borsa;

public class ComandoPosa extends AbstractComando {

	@Override
	public void esegui(Partita partita) {
		String nomeAttrezzo = this.getParametro();
		
		if (nomeAttrezzo == null) {
			this.getIo().mostraMessaggio("Che attrezzo vuoi posare?");
			return;
		}
		
		Borsa borsaGiocatore = partita.getGiocatore().getBorsa();
		Stanza stanzaCorrente = partita.getStanzaCorrente();
		
		if (borsaGiocatore.hasAttrezzo(nomeAttrezzo)) {
			Attrezzo attrezzoDaPosare = borsaGiocatore.removeAttrezzo(nomeAttrezzo);
			boolean attrezzoPosato = stanzaCorrente.addAttrezzo(attrezzoDaPosare);
			
			if (attrezzoPosato) {
				this.getIo().mostraMessaggio("Hai posato con successo " + nomeAttrezzo + " nella stanza.");
			} else {
				// Se la stanza è piena, rimetti l'attrezzo in borsa
				borsaGiocatore.addAttrezzo(attrezzoDaPosare);
				this.getIo().mostraMessaggio("Impossibile posare l'attrezzo: la stanza è piena!");
			}
		} else {
			this.getIo().mostraMessaggio("Non hai questo attrezzo nella borsa.");
		}
	}
}