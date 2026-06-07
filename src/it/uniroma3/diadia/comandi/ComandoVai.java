package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.ambienti.Direzione;
import it.uniroma3.diadia.ambienti.Stanza;

public class ComandoVai extends AbstractComando {

	@Override
	public void esegui(Partita partita) {
		Stanza stanzaCorrente = partita.getStanzaCorrente();
		String parametro = this.getParametro();

		if (parametro == null) {
			this.getIo().mostraMessaggio("Dove vuoi andare? Specificare una direzione.");
			return;
		}

		Direzione direzione;
		try {
			direzione = Direzione.fromString(parametro);
		} catch (IllegalArgumentException e) {
			this.getIo().mostraMessaggio("Direzione \"" + parametro + "\" non riconosciuta. " +
					"Usa: nord, sud, est, ovest.");
			return;
		}

		Stanza prossimaStanza = stanzaCorrente.getStanzaAdiacente(direzione);
		if (prossimaStanza == null) {
			this.getIo().mostraMessaggio("Direzione inesistente");
			return;
		}

		if (prossimaStanza == stanzaCorrente) {
			this.getIo().mostraMessaggio("Direzione bloccata! Hai bisogno di qualcosa per passare.");
			return;
		}

		partita.setStanzaCorrente(prossimaStanza);
		this.getIo().mostraMessaggio(partita.getStanzaCorrente().getNome());
		partita.getGiocatore().consumaCfu();
	}
}
