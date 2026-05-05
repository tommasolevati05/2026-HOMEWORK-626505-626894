package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.IO;
import it.uniroma3.diadia.IOConsole;
import it.uniroma3.diadia.Partita;

public class ComandoGuarda implements Comando{
	private IO io;

	@Override
	public void esegui(Partita partita) {
        this.io.mostraMessaggio(partita.getStanzaCorrente().getDescrizione());
        
        this.io.mostraMessaggio("CFU rimanenti: " + partita.getGiocatore().getCfu());
        this.io.mostraMessaggio(partita.getGiocatore().getBorsa().toString());
    }
		
	

	@Override
	public void setParametro(String parametro) {
	}

	@Override
	public void setIo(IO io) {
		this.io=io;
		
	}

}
