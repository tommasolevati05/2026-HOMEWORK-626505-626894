 package it.uniroma3.diadia.comandi;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import it.uniroma3.diadia.IO;
import it.uniroma3.diadia.IOConsole;
import it.uniroma3.diadia.IOSimulator;
import it.uniroma3.diadia.Partita;

class AbstractComandoTest {

	private AbstractComando comandoStub;
	private IO ioTest;

	// Creiamo un'implementazione concreta minimale solo a scopo di test
	private class FintoComando extends AbstractComando {
		@Override
		public void esegui(Partita partita) {
			// corpo vuoto per il test dei setter/getter comuni
		}
	}

	@BeforeEach
	void setUp() {
		this.comandoStub = new FintoComando();
		this.ioTest = new IOSimulator();
	}

	@Test
	void testSetEGetParametro() {
		this.comandoStub.setParametro("unParametro");
		assertEquals("unParametro", this.comandoStub.getParametro());
	}

	@Test
	void testSetEGetParametroNullo() {
		this.comandoStub.setParametro(null);
		assertNull(this.comandoStub.getParametro());
	}

	@Test
	void testSetEGetIo() {
		this.comandoStub.setIo(this.ioTest);
		assertSame(this.ioTest, this.comandoStub.getIo());
	}
}