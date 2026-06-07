package it.uniroma3.diadia;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import it.uniroma3.diadia.ambienti.Direzione;
import it.uniroma3.diadia.ambienti.Labirinto;
import it.uniroma3.diadia.ambienti.Labirinto.LabirintoBuilder;
import it.uniroma3.diadia.ambienti.Stanza;


class LabirintoBuilderTest {

    @Test
    void testNewBuilderRestituisceBuilder() {
        LabirintoBuilder builder = Labirinto.newBuilder();
        assertNotNull(builder);
    }

    @Test
    void testCostruttoreLabirintoPrivatoTramiteReflection() throws Exception {
        java.lang.reflect.Constructor<Labirinto> c =
                Labirinto.class.getDeclaredConstructor();
        assertTrue(java.lang.reflect.Modifier.isPrivate(c.getModifiers()),
                "Il costruttore di Labirinto deve essere privato");
    }

    @Test
    void testBuilderEClasseStaticaNidificata() {
        Class<?> enclosing = LabirintoBuilder.class.getEnclosingClass();
        assertEquals(Labirinto.class, enclosing,
                "LabirintoBuilder deve essere nidificata dentro Labirinto");
        assertTrue(java.lang.reflect.Modifier.isStatic(LabirintoBuilder.class.getModifiers()),
                "LabirintoBuilder deve essere statica");
        assertTrue(java.lang.reflect.Modifier.isPublic(LabirintoBuilder.class.getModifiers()),
                "LabirintoBuilder deve essere pubblica");
    }

    @Test
    void testStanzaInizialeImpostata() {
        Labirinto l = Labirinto.newBuilder()
                .addStanzaIniziale("Atrio")
                .getLabirinto();
        assertNotNull(l.getStanzaDiIngresso());
        assertEquals("Atrio", l.getStanzaDiIngresso().getNome());
    }

    @Test
    void testStanzaVincenteImpostata() {
        Labirinto l = Labirinto.newBuilder()
                .addStanzaVincente("Biblioteca")
                .getLabirinto();
        assertNotNull(l.getStanzaVincente());
        assertEquals("Biblioteca", l.getStanzaVincente().getNome());
    }

    @Test
    void testAdiacenzaConDirezioneEnum() {
        Labirinto l = Labirinto.newBuilder()
                .addStanzaIniziale("Corridoio")
                .addStanza("Aula")
                .addAdiacenza("Corridoio", "Aula", Direzione.NORD)
                .getLabirinto();

        Stanza corridoio = l.getStanzaDiIngresso();
        Stanza aula = corridoio.getStanzaAdiacente(Direzione.NORD);
        assertNotNull(aula, "Deve esserci una stanza a nord");
        assertEquals("Aula", aula.getNome());
    }

    @Test
    void testAdiacenzaInDirezioneOppostaENull() {
        Labirinto l = Labirinto.newBuilder()
                .addStanzaIniziale("Corridoio")
                .addStanza("Aula")
                .addAdiacenza("Corridoio", "Aula", Direzione.NORD)
                .getLabirinto();

        Stanza corridoio = l.getStanzaDiIngresso();
        assertNull(corridoio.getStanzaAdiacente(Direzione.SUD), "A sud non deve esserci nulla");
    }

    @Test
    void testAttrezzoAggiunto() {
        Labirinto l = Labirinto.newBuilder()
                .addStanzaIniziale("Laboratorio")
                .addAttrezzo("spada", 3)
                .getLabirinto();

        assertTrue(l.getStanzaDiIngresso().hasAttrezzo("spada"));
    }

    @Test
    void testBuilderFluentePiuPassaggi() {
        Labirinto l = Labirinto.newBuilder()
                .addStanzaIniziale("Ingresso")
                .addStanzaVincente("Uscita")
                .addStanza("Corridoio")
                .addAdiacenza("Ingresso", "Corridoio", Direzione.EST)
                .addAdiacenza("Corridoio", "Uscita", Direzione.EST)
                .getLabirinto();

        Stanza ingresso = l.getStanzaDiIngresso();
        Stanza corridoio = ingresso.getStanzaAdiacente(Direzione.EST);
        Stanza uscita = corridoio.getStanzaAdiacente(Direzione.EST);

        assertEquals("Uscita", uscita.getNome());
        assertEquals(l.getStanzaVincente(), uscita);
    }
}
