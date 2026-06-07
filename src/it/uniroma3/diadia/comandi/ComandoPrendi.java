package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.attrezzi.Attrezzo;

public class ComandoPrendi extends AbstractComando {

	@Override
	public void esegui(Partita partita) {
		String nomeAttrezzo = this.getParametro();
		
		if (nomeAttrezzo == null) {
			this.getIo().mostraMessaggio("Che attrezzo vuoi prendere?");
			return;
		}
		
		if (partita.getStanzaCorrente().hasAttrezzo(nomeAttrezzo)) {
			Attrezzo attrezzoDaPrendere = partita.getStanzaCorrente().getAttrezzo(nomeAttrezzo);
			
			// Verifica se c'è spazio nella borsa
			if (partita.getGiocatore().getBorsa().getPeso() + attrezzoDaPrendere.getPeso() <= partita.getGiocatore().getBorsa().getPesoMax()) {
				partita.getStanzaCorrente().removeAttrezzo(attrezzoDaPrendere);
				partita.getGiocatore().getBorsa().addAttrezzo(attrezzoDaPrendere);
				this.getIo().mostraMessaggio("Hai preso con successo: " + nomeAttrezzo);
			} else {
				this.getIo().mostraMessaggio("Troppo pesante! Non c'è abbastanza spazio nella borsa.");
			}
		} else {
			this.getIo().mostraMessaggio("L'attrezzo '" + nomeAttrezzo + "' non è presente in questa stanza.");
		}
	}
}