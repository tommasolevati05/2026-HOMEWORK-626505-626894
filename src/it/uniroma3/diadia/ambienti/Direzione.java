package it.uniroma3.diadia.ambienti;

/**
 * Enumerativo delle quattro direzioni cardinali possibili nel labirinto.
 * Sostituisce le stringhe "nord", "sud", "est", "ovest" sparse nel codice,
 * garantendo type-safety a compile-time.
 *
 * Ogni costante conosce il proprio nome in minuscolo (usato per display
 * e per la lettura dal file di configurazione).
 */
public enum Direzione {

    NORD("nord"),
    SUD("sud"),
    EST("est"),
    OVEST("ovest");

    private final String nome;

    Direzione(String nome) {
        this.nome = nome;
    }

    /** Restituisce il nome in minuscolo della direzione (es. "nord"). */
    public String getNome() {
        return nome;
    }

    /**
     * Restituisce il {@code Direzione} corrispondente alla stringa fornita
     * (confronto case-insensitive).
     *
     * @param s la stringa da convertire (es. "Nord", "NORD", "nord")
     * @return il valore enum corrispondente
     * @throws IllegalArgumentException se la stringa non corrisponde a nessuna direzione
     */
    public static Direzione fromString(String s) {
        for (Direzione d : values()) {
            if (d.nome.equalsIgnoreCase(s.trim())) {
                return d;
            }
        }
        throw new IllegalArgumentException("Direzione non riconosciuta: \"" + s + "\"");
    }

    @Override
    public String toString() {
        return nome;
    }
}
