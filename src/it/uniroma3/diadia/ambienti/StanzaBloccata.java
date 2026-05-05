package it.uniroma3.diadia.ambienti;

public class StanzaBloccata extends Stanza{
	 private String direzioneBloccata;
	    private String nomeAttrezzoSbloccante;

	    public StanzaBloccata(String nome, String direzioneBloccata, String nomeAttrezzoSbloccante) {
	        super(nome);
	        this.direzioneBloccata=direzioneBloccata;
	        this.nomeAttrezzoSbloccante=nomeAttrezzoSbloccante;
	    }

	    @Override
	    public Stanza getStanzaAdiacente(String dir) {
	        if(this.direzioneBloccata.equals(dir) && !hasAttrezzo(nomeAttrezzoSbloccante)) {
	            return this;
	        }
	        return super.getStanzaAdiacente(dir);
	    }

	    @Override
	    public String getDescrizione() {
	        String descrizioneBlocco = "\nAttenzione: la direzione " + direzioneBloccata + 
	                                   " E' bloccata. Ti serve un " + nomeAttrezzoSbloccante + " per passare.";

	        if (!this.hasAttrezzo(nomeAttrezzoSbloccante)) {
	            return super.getDescrizione() + descrizioneBlocco;
	        } else {
	            return super.getDescrizione() + "\nGrazie alla " + nomeAttrezzoSbloccante + " la via per " + direzioneBloccata + " e' aperta!";
	        }
	    }
}
