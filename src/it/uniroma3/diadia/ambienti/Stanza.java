package it.uniroma3.diadia.ambienti;

import java.util.Collection;
import java.util.EnumMap;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import it.uniroma3.diadia.attrezzi.Attrezzo;
import it.uniroma3.diadia.personaggi.AbstractPersonaggio;

/**
 * Classe Stanza - una stanza in un gioco di ruolo.
 * Una stanza e' un luogo fisico nel gioco.
 * E' collegata ad altre stanze attraverso delle uscite.
 * Ogni uscita e' associata ad una direzione (tipo {@link Direzione}).
 *
 * @author Tommaso
 * @see Attrezzo
 * @version es18 - enum Direzione
 */
public class Stanza {

	private String nome;

	// Chiave: nome dell'attrezzo, Valore: l'oggetto Attrezzo
	private Map<String, Attrezzo> attrezzi;
	// Chiave: direzione cardinale (enum), Valore: la Stanza adiacente
	private Map<Direzione, Stanza> stanzeAdiacenti;
	private AbstractPersonaggio personaggio;

	/**
	 * Crea una stanza. Non ci sono stanze adiacenti, non ci sono attrezzi.
	 * @param nome il nome della stanza
	 */
	public Stanza(String nome) {
		this.nome = nome;
		this.stanzeAdiacenti = new EnumMap<>(Direzione.class);
		this.attrezzi = new HashMap<>();
	}

	/**
	 * Imposta una stanza adiacente.
	 *
	 * @param direzione la direzione cardinale verso cui si trova la stanza adiacente
	 * @param stanza    la stanza adiacente
	 */
	public void impostaStanzaAdiacente(Direzione direzione, Stanza stanza) {
		this.stanzeAdiacenti.put(direzione, stanza);
	}

	/**
	 * Restituisce la stanza adiacente nella direzione specificata.
	 * @param direzione la direzione
	 * @return la stanza adiacente, o null se non esiste
	 */
	public Stanza getStanzaAdiacente(Direzione direzione) {
		return this.stanzeAdiacenti.get(direzione);
	}

	/** @return il nome della stanza */
	public String getNome() {
		return this.nome;
	}

	/** @return la descrizione della stanza */
	public String getDescrizione() {
		return "Ti trovi in: " + this.toString();
	}

	/** @return la collezione di attrezzi presenti nella stanza */
	public Collection<Attrezzo> getAttrezzi() {
		return this.attrezzi.values();
	}

	/**
	 * Mette un attrezzo nella stanza.
	 * @param attrezzo l'attrezzo da mettere nella stanza
	 * @return true se riesce ad aggiungere l'attrezzo, false altrimenti
	 */
	public boolean addAttrezzo(Attrezzo attrezzo) {
		if (attrezzo != null) {
			this.attrezzi.put(attrezzo.getNome(), attrezzo);
			return true;
		}
		return false;
	}

	@Override
	public String toString() {
		StringBuilder risultato = new StringBuilder();
		risultato.append(this.nome);

		risultato.append("\nUscite: ");
		for (Direzione direzione : this.stanzeAdiacenti.keySet()) {
			risultato.append(" ").append(direzione.getNome());
		}

		risultato.append("\nAttrezzi nella stanza: ");
		for (Attrezzo attrezzo : this.attrezzi.values()) {
			risultato.append(attrezzo.toString()).append(" ");
		}
		return risultato.toString();
	}

	/** @return true se l'attrezzo esiste nella stanza */
	public boolean hasAttrezzo(String nomeAttrezzo) {
		return this.attrezzi.containsKey(nomeAttrezzo);
	}

	/**
	 * Restituisce l'attrezzo nomeAttrezzo se presente nella stanza.
	 * @return l'attrezzo, o null se non presente
	 */
	public Attrezzo getAttrezzo(String nomeAttrezzo) {
		return this.attrezzi.get(nomeAttrezzo);
	}

	/**
	 * Rimuove un attrezzo dalla stanza.
	 * @return true se rimosso, false altrimenti
	 */
	public boolean removeAttrezzo(Attrezzo attrezzo) {
		if (attrezzo != null && this.attrezzi.containsKey(attrezzo.getNome())) {
			this.attrezzi.remove(attrezzo.getNome());
			return true;
		}
		return false;
	}

	/** @return le direzioni con uscite valide */
	public Direzione[] getDirezioni() {
		Set<Direzione> keys = this.stanzeAdiacenti.keySet();
		return keys.toArray(new Direzione[0]);
	}

	/** @return le stanze adiacenti a quella corrente */
	public Collection<Stanza> getStanzeAdiacenti() {
		return this.stanzeAdiacenti.values();
	}

	public void setPersonaggio(AbstractPersonaggio personaggio) {
		this.personaggio = personaggio;
	}

	public AbstractPersonaggio getPersonaggio() {
		return this.personaggio;
	}
}
