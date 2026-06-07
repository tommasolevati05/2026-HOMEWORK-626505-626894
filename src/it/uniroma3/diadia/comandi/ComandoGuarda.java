package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.Partita;

public class ComandoGuarda extends AbstractComando {

	@Override
	public void esegui(Partita partita) {
		this.getIo().mostraMessaggio(partita.getStanzaCorrente().getDescrizione());
		this.getIo().mostraMessaggio("CFU rimanenti: " + partita.getGiocatore().getCfu());
		this.getIo().mostraMessaggio(partita.getGiocatore().getBorsa().toString());
	}
}