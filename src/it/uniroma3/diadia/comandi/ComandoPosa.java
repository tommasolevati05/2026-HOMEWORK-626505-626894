package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.IO;
import it.uniroma3.diadia.IOConsole;
import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.ambienti.Stanza;
import it.uniroma3.diadia.attrezzi.Attrezzo;
import it.uniroma3.diadia.giocatore.Borsa;

public class ComandoPosa implements Comando{
	private IO io;
	private String nomeAttrezzo;

	@Override
	public void esegui(Partita partita) {
		if(nomeAttrezzo==null) {
	           io.mostraMessaggio("Che attrezzo vuoi posare?");
	           return;
	       }
	       Borsa borsaGiocatore =partita.getGiocatore().getBorsa();
	       Stanza stanzaCorrente =partita.getStanzaCorrente();
	       if(borsaGiocatore.hasAttrezzo(nomeAttrezzo)) {
	           Attrezzo attrezzoDaPosare = borsaGiocatore.removeAttrezzo(nomeAttrezzo);
	           boolean attrezzoPosato = stanzaCorrente.addAttrezzo(attrezzoDaPosare);
	           if(attrezzoPosato) {
	               io.mostraMessaggio("Hai posato con successo "+ nomeAttrezzo +" nella stanza");
	           }
	           else {
	               borsaGiocatore.addAttrezzo(attrezzoDaPosare);
	               io.mostraMessaggio("Non ci sta spazio nella stanza per posarlo");
	           }
	       }
	       else {
	           io.mostraMessaggio("Non hai "+ nomeAttrezzo +", quindi non puoi lasciarlo");
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
