package it.uniroma3.diadia.ambienti;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import it.uniroma3.diadia.CaricatoreLabirinto;
import it.uniroma3.diadia.attrezzi.Attrezzo;
import it.uniroma3.diadia.personaggi.Cane;
import it.uniroma3.diadia.personaggi.Mago;
import it.uniroma3.diadia.personaggi.Strega;

/**
 * Modella il labirinto del gioco DiaDia.
 */
public class Labirinto {

	private Stanza stanzaDiIngresso;
	private Stanza stanzaVincente;

	/** Costruttore privato: si usa solo tramite LabirintoBuilder. */
	private Labirinto() {
	}

	
	/**
	 * Restituisce un nuovo {@link LabirintoBuilder} per costruire un labirinto
	 * in modo fluido.
	 */
	public static LabirintoBuilder newBuilder() {
		return new LabirintoBuilder();
	}

	/**
	 * Carica un labirinto da file.
	 *
	 * @param nomeFile path del file di configurazione
	 * @return il labirinto caricato
	 */
	public static Labirinto caricaDaFile(String nomeFile)
			throws FileNotFoundException, IOException {
		try (FileReader fileReader = new FileReader(nomeFile)) {
			CaricatoreLabirinto caricatore = new CaricatoreLabirinto(fileReader);
			return caricatore.carica();
		}
	}

	

	public Stanza getStanzaDiIngresso() {
		return stanzaDiIngresso;
	}

	void setStanzaDiIngresso(Stanza stanzaDiIngresso) {
		this.stanzaDiIngresso = stanzaDiIngresso;
	}

	public Stanza getStanzaVincente() {
		return stanzaVincente;
	}

	void setStanzaVincente(Stanza stanzaVincente) {
		this.stanzaVincente = stanzaVincente;
	}


	public static class LabirintoBuilder {

		private Labirinto labirinto;
		private Map<String, Stanza> nome2stanza;
		private Stanza ultimaStanzaAggiunta;

		/** Costruttore privato: si accede tramite {@link Labirinto#newBuilder()}. */
		private LabirintoBuilder() {
			this.labirinto = new Labirinto();   // può usare il costruttore privato: stessa top-level class
			this.nome2stanza = new HashMap<>();
		}

		/** Restituisce il labirinto completamente assemblato. */
		public Labirinto getLabirinto() {
			return this.labirinto;
		}

		/** Imposta la stanza iniziale. Se non esiste, la crea. */
		public LabirintoBuilder addStanzaIniziale(String nomeStanza) {
			Stanza iniziale = this.creaSeNonPresente(nomeStanza);
			this.labirinto.setStanzaDiIngresso(iniziale);
			this.ultimaStanzaAggiunta = iniziale;
			return this;
		}

		/** Imposta la stanza vincente. Se non esiste, la crea. */
		public LabirintoBuilder addStanzaVincente(String nomeStanza) {
			Stanza vincente = this.creaSeNonPresente(nomeStanza);
			this.labirinto.setStanzaVincente(vincente);
			this.ultimaStanzaAggiunta = vincente;
			return this;
		}

		/** Aggiunge una stanza standard. */
		public LabirintoBuilder addStanza(String nomeStanza) {
			Stanza stanza = new Stanza(nomeStanza);
			this.registraStanza(stanza);
			return this;
		}

		/** Aggiunge una StanzaBuia. */
		public LabirintoBuilder addStanzaBuia(String nomeStanza, String attrezzoPerVedere) {
			Stanza stanzaBuia = new StanzaBuia(nomeStanza, attrezzoPerVedere);
			this.registraStanza(stanzaBuia);
			return this;
		}

		/**
		 * Aggiunge una StanzaBloccata.
		 * La direzione e' specificata come {@link Direzione}.
		 */
		public LabirintoBuilder addStanzaBloccata(String nomeStanza,
				Direzione direzioneBloccata, String nomeAttrezzoSbloccante) {
			Stanza stanzaBloccata = new StanzaBloccata(nomeStanza, direzioneBloccata, nomeAttrezzoSbloccante);
			this.registraStanza(stanzaBloccata);
			return this;
		}

		/** Aggiunge una StanzaMagica. */
		public LabirintoBuilder addStanzaMagica(String nomeStanza) {
			Stanza stanzaMagica = new StanzaMagica(nomeStanza);
			this.registraStanza(stanzaMagica);
			return this;
		}

		/** Inserisce un attrezzo in una stanza specifica. */
		public LabirintoBuilder addAttrezzoInStanza(String nomeAttrezzo, int peso, String nomeStanza) {
			Stanza stanza = this.creaSeNonPresente(nomeStanza);
			Attrezzo attrezzo = new Attrezzo(nomeAttrezzo, peso);
			stanza.addAttrezzo(attrezzo);
			return this;
		}

		/** Inserisce un attrezzo nell'ultima stanza modificata. */
		public LabirintoBuilder addAttrezzo(String nomeAttrezzo, int peso) {
			if (this.ultimaStanzaAggiunta != null) {
				this.addAttrezzoInStanza(nomeAttrezzo, peso, this.ultimaStanzaAggiunta.getNome());
			}
			return this;
		}

		/**
		 * Crea un collegamento tra due stanze.
		 * @param direzione la direzione come {@link Direzione}
		 */
		public LabirintoBuilder addAdiacenza(String stanzaPartenza,
				String stanzaDestinazione, Direzione direzione) {
			Stanza partenza = this.creaSeNonPresente(stanzaPartenza);
			Stanza destinazione = this.creaSeNonPresente(stanzaDestinazione);
			partenza.impostaStanzaAdiacente(direzione, destinazione);
			return this;
		}

		/** Aggiunge un Mago nella stanza indicata. */
		public LabirintoBuilder addMago(String nome, String presentazione,
				String nomeStanza, String nomeAttrezzoRegalo) {
			Stanza stanza = this.creaSeNonPresente(nomeStanza);
			Attrezzo regalo = (nomeAttrezzoRegalo != null) ? new Attrezzo(nomeAttrezzoRegalo, 1) : null;
			Mago mago = new Mago(nome, presentazione, regalo);
			stanza.setPersonaggio(mago);
			return this;
		}

		/** Aggiunge una Strega nella stanza indicata. */
		public LabirintoBuilder addStrega(String nome, String presentazione, String nomeStanza) {
			Stanza stanza = this.creaSeNonPresente(nomeStanza);
			Strega strega = new Strega(nome, presentazione);
			stanza.setPersonaggio(strega);
			return this;
		}

		/** Aggiunge un Cane nella stanza indicata. */
		public LabirintoBuilder addCane(String nome, String presentazione, String nomeStanza) {
			Stanza stanza = this.creaSeNonPresente(nomeStanza);
			Cane cane = new Cane(nome, presentazione);
			stanza.setPersonaggio(cane);
			return this;
		}

		// --- utility interne ---

		private void registraStanza(Stanza stanza) {
			this.nome2stanza.put(stanza.getNome(), stanza);
			this.ultimaStanzaAggiunta = stanza;
		}

		private Stanza creaSeNonPresente(String nomeStanza) {
			if (!this.nome2stanza.containsKey(nomeStanza)) {
				this.addStanza(nomeStanza);
			}
			return this.nome2stanza.get(nomeStanza);
		}

		public Map<String, Stanza> getListaStanze() {
			return this.nome2stanza;
		}
	}
}
