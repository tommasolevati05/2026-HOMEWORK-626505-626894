package it.uniroma3.diadia;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import java.util.Scanner;

class IOConsoleTest {

    @Test
    void testLeggiRigaSingola() {
        Scanner scanner = new Scanner("ciao\n");
        IOConsole io = new IOConsole(scanner);
        assertEquals("ciao", io.leggiRiga());
    }

    @Test
    void testLeggiRigaMultiple() {
        Scanner scanner = new Scanner("vai nord\nguarda\nfine\n");
        IOConsole io = new IOConsole(scanner);
        assertEquals("vai nord", io.leggiRiga());
        assertEquals("guarda", io.leggiRiga());
        assertEquals("fine", io.leggiRiga());
    }

    @Test
    void testLeggiRigaConSpazi() {
        Scanner scanner = new Scanner("  prendi   spada  \n");
        IOConsole io = new IOConsole(scanner);
        assertEquals("  prendi   spada  ", io.leggiRiga());
    }

    @Test
    void testMostraMessaggioNonLanciaEccezioni() {
        Scanner scanner = new Scanner("");
        IOConsole io = new IOConsole(scanner);
        io.mostraMessaggio("Messaggio di test");
    }

    @Test
    void testScannerNonChiusoTraLetture() {
        Scanner scanner = new Scanner("prima\nseconda\n");
        IOConsole io = new IOConsole(scanner);
        io.leggiRiga();
        assertEquals("seconda", io.leggiRiga());
    }
}
