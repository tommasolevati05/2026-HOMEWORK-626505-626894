package it.uniroma3.diadia;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import it.uniroma3.diadia.ambienti.Direzione;


class DirezioneTest {

    @Test
    void testFromStringNordMinuscolo() {
        assertEquals(Direzione.NORD, Direzione.fromString("nord"));
    }

    @Test
    void testFromStringNordMaiuscolo() {
        assertEquals(Direzione.NORD, Direzione.fromString("NORD"));
    }

    @Test
    void testFromStringSudConSpazi() {
        assertEquals(Direzione.SUD, Direzione.fromString("  sud  "));
    }

    @Test
    void testFromStringEst() {
        assertEquals(Direzione.EST, Direzione.fromString("Est"));
    }

    @Test
    void testFromStringOvest() {
        assertEquals(Direzione.OVEST, Direzione.fromString("ovest"));
    }

    @Test
    void testFromStringNonValida() {
        assertThrows(IllegalArgumentException.class, () -> Direzione.fromString("diagonale"));
    }

    @Test
    void testFromStringStringaVuota() {
        assertThrows(IllegalArgumentException.class, () -> Direzione.fromString(""));
    }

    @Test
    void testToStringNord() {
        assertEquals("nord", Direzione.NORD.toString());
    }

    @Test
    void testToStringOvest() {
        assertEquals("ovest", Direzione.OVEST.toString());
    }

    @Test
    void testEsattamenteQuattroDirezioni() {
        assertEquals(4, Direzione.values().length);
    }
}
