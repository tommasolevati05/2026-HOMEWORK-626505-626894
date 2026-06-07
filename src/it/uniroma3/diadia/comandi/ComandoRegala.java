package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.attrezzi.Attrezzo;
import it.uniroma3.diadia.personaggi.AbstractPersonaggio;

public class ComandoRegala extends AbstractComando {

	@Override
	public void esegui(Partita partita) {
		String nomeAttrezzo = this.getParametro();
		
		if (nomeAttrezzo == null) {
			this.getIo().mostraMessaggio("Quale attrezzo vorresti regalare?");
			return;
		}
		
		AbstractPersonaggio personaggio = partita.getStanzaCorrente().getPersonaggio();
		if (personaggio == null) {
			this.getIo().mostraMessaggio("Non c'è nessuno in questa stanza a cui poter fare un regalo!");
			return;
		}
		
		if (!partita.getGiocatore().getBorsa().hasAttrezzo(nomeAttrezzo)) {
			this.getIo().mostraMessaggio("Non possiedi l'attrezzo '" + nomeAttrezzo + "' nella tua borsa.");
			return;
		}
		
		Attrezzo regalo = partita.getGiocatore().getBorsa().removeAttrezzo(nomeAttrezzo);
		
		String feedbackRegalo = personaggio.riceviRegalo(regalo, partita);
		this.getIo().mostraMessaggio(feedbackRegalo);
	}
}