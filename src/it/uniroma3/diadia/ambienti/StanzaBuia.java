
package it.uniroma3.diadia.ambienti;


public class StanzaBuia extends Stanza {

	private String AttrezzoPerVedere;
	public StanzaBuia(String nome, String AttrezzoPerVedere) {
		super(nome);
		this.AttrezzoPerVedere=AttrezzoPerVedere;
	}
	
	@Override
	public String getDescrizione() {
		if(this.hasAttrezzo(AttrezzoPerVedere)) {
			return super.getDescrizione();
		} else {
			return "Qui c'é un buio pesto";
		}
	}

}
