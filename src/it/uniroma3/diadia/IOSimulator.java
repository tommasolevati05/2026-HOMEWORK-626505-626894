package it.uniroma3.diadia;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class IOSimulator implements IO {
	
	private List<String> comandiDaLeggere; 
	private int indice;
	private List<String> messaggiStampati; 

	public IOSimulator(String... comandi) { 
		
		this.comandiDaLeggere = new ArrayList<>(Arrays.asList(comandi));
		this.indice = 0;
		this.messaggiStampati = new ArrayList<>();
	}

	@Override
	public void mostraMessaggio(String msg) {
		this.messaggiStampati.add(msg);
		System.out.println(msg); 
	}

	@Override
	public String leggiRiga() {
		if (this.indice < this.comandiDaLeggere.size()) {
			String riga = this.comandiDaLeggere.get(this.indice);
			this.indice++;
			return riga;
		} else {
			return "fine"; 
		}
	}

	public boolean hasMessaggio(String messaggioAtteso) {
		for (String msg : this.messaggiStampati) {
			if (msg != null && msg.contains(messaggioAtteso)) {
				return true;
			}
		}
		return false;
	}
}