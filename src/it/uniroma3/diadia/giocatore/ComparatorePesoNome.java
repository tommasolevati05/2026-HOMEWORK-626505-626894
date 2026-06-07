package it.uniroma3.diadia.giocatore;

import java.util.Comparator;

import it.uniroma3.diadia.attrezzi.Attrezzo;

public class ComparatorePesoNome implements Comparator<Attrezzo>{

	@Override
	public int compare(Attrezzo o1, Attrezzo o2) {
		int cmp = o1.getPeso()-o2.getPeso();
		if(cmp==0) { //in caso di pari e patta confronto i nomi
			cmp=o1.getNome().compareTo(o2.getNome());
		}
		//if(cmp==0) {
			//cmp=o1.getCognome().compareTo(o2.getCognome()) possiamo fare questa catena di ordinamenti esterni
		//}
		return cmp; //Confronta i pesi
	}

}
