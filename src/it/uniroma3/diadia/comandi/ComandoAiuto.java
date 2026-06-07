package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.Partita;

public class ComandoAiuto extends AbstractComando {

	@Override
	public void esegui(Partita partita) {
		StringBuilder sb = new StringBuilder("Comandi disponibili: ");
		
		// Iteriamo sul Set statico ereditato da AbstractComando
		for (String comando : COMANDI_DISPONIBILI) {
			sb.append(comando).append(" ");
		}
		
		this.getIo().mostraMessaggio(sb.toString().trim());
	}
}