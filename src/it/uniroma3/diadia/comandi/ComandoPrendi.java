package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.IO;
import it.uniroma3.diadia.IOConsole;
import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.attrezzi.Attrezzo;

public class ComandoPrendi implements Comando{
	private IO io;
	private String nomeAttrezzo;

	@Override
	public void esegui(Partita partita) {
		if(nomeAttrezzo==null) {
			io.mostraMessaggio("Che attrezzo vuoi prendere?");
		}
		boolean attrezzoTrovato;
		attrezzoTrovato=false;
		if(partita.getStanzaCorrente().hasAttrezzo(nomeAttrezzo)) {
			attrezzoTrovato=true;
		}
		if(attrezzoTrovato) {
			int pesoMax=partita.getGiocatore().getBorsa().getPesoMax();
			int pesoAttuale=partita.getGiocatore().getBorsa().getPeso();
			int pesoAttrezzo=partita.getStanzaCorrente().getAttrezzo(nomeAttrezzo).getPeso();
			
			if((pesoMax-pesoAttuale)>pesoAttrezzo) {
			Attrezzo attrezzoDaRimuovere=partita.getStanzaCorrente().getAttrezzo(nomeAttrezzo);
			partita.getStanzaCorrente().removeAttrezzo(attrezzoDaRimuovere);
			
			partita.getGiocatore().getBorsa().addAttrezzo(attrezzoDaRimuovere);
			io.mostraMessaggio(nomeAttrezzo +" è stato aggiunto alla borsa con successo");
			}
			else {
				io.mostraMessaggio("Capienza borsa non sufficiente");
			}
		}
		else {
			io.mostraMessaggio("Non c'è " + nomeAttrezzo + " quindi non puoi prenderlo");
		}	
	}

	@Override
	public void setParametro(String parametro) {
		this.nomeAttrezzo=parametro;	
	}

	@Override
	public void setIo(IO io) {
		this.io=io;	
	}

}
