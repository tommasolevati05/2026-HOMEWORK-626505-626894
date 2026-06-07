package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.personaggi.AbstractPersonaggio;

public class ComandoSaluta extends AbstractComando {

	private static final String MESSAGGIO_NESSUNO = "Non c'è nessuno qui da salutare...";

	@Override
	public void esegui(Partita partita) {
		AbstractPersonaggio personaggio = partita.getStanzaCorrente().getPersonaggio();
		if (personaggio != null) {
			String rispostaSaluto = personaggio.saluta();
			this.getIo().mostraMessaggio(rispostaSaluto);
		} else {
			this.getIo().mostraMessaggio(MESSAGGIO_NESSUNO);
		}
	}
}