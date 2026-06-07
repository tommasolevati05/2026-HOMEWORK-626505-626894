package it.uniroma3.diadia;

import java.io.*;
import java.util.*;

import it.uniroma3.diadia.ambienti.Direzione;
import it.uniroma3.diadia.ambienti.Labirinto;
import it.uniroma3.diadia.ambienti.Labirinto.LabirintoBuilder;

public class CaricatoreLabirinto {

	private static final String STANZE_MARKER      = "Stanze:";
	private static final String STANZA_INIZIALE_MARKER = "Inizio:";
	private static final String STANZA_VINCENTE_MARKER = "Vincente:";
	private static final String ATTREZZI_MARKER    = "Attrezzi:";
	private static final String USCITE_MARKER      = "Uscite:";
	private static final String PERSONAGGI_MARKER  = "Personaggi:";

	private LineNumberReader reader;
	private LabirintoBuilder builder;

	public CaricatoreLabirinto(Reader reader) {
		this.reader  = new LineNumberReader(reader);
		this.builder = Labirinto.newBuilder();   // usa il factory method
	}

	public Labirinto carica() throws IOException {
		try {
			this.leggiECreaStanze();
			this.leggiInizialeEvincente();
			this.leggiECollocaAttrezzi();
			this.leggiEImpostaUscite();
			this.leggiECollocaPersonaggi();
			return this.builder.getLabirinto();
		} finally {
			try {
				reader.close();
			} catch (IOException e) {
				e.printStackTrace();
				throw new RuntimeException(e);
			}
		}
	}

	private String leggiRigaCheCominciaPer(String marker) throws IOException {
		String riga = this.reader.readLine();
		if (riga == null) {
			throw new IOException("Fine del file inaspettata a riga " +
					this.reader.getLineNumber() + ": cercato marker " + marker);
		}
		check(riga.startsWith(marker), "era attesa una riga che cominciasse per " + marker);
		return riga.substring(marker.length());
	}

	private void leggiECreaStanze() throws IOException {
		String specificheStanze = this.leggiRigaCheCominciaPer(STANZE_MARKER);
		for (String specifica : separaStringheAlleVirgole(specificheStanze)) {
			if (specifica.contains("-")) {
				String[] parti = specifica.split("-");
				String nomeStanza = parti[0];
				String tipoStanza = parti[1];
				if (tipoStanza.equalsIgnoreCase("buia")) {
					String attrezzoPerVedere = parti[2];
					this.builder.addStanzaBuia(nomeStanza, attrezzoPerVedere);
				} else if (tipoStanza.equalsIgnoreCase("bloccata")) {
					Direzione direzioneBloccata = Direzione.fromString(parti[2]);
					String attrezzoSbloccante = parti[3];
					this.builder.addStanzaBloccata(nomeStanza, direzioneBloccata, attrezzoSbloccante);
				} else if (tipoStanza.equalsIgnoreCase("magica")) {
					this.builder.addStanzaMagica(nomeStanza);
				} else {
					this.builder.addStanza(nomeStanza);
				}
			} else {
				this.builder.addStanza(specifica);
			}
		}
	}

	private List<String> separaStringheAlleVirgole(String string) {
		List<String> result = new LinkedList<>();
		Scanner scanner = new Scanner(string);
		scanner.useDelimiter(",");
		while (scanner.hasNext()) {
			result.add(scanner.next().trim());
		}
		scanner.close();
		return result;
	}

	private void leggiInizialeEvincente() throws IOException {
		String nomeStanzaIniziale = this.leggiRigaCheCominciaPer(STANZA_INIZIALE_MARKER).trim();
		this.builder.addStanzaIniziale(nomeStanzaIniziale);
		String nomeStanzaVincente = this.leggiRigaCheCominciaPer(STANZA_VINCENTE_MARKER).trim();
		this.builder.addStanzaVincente(nomeStanzaVincente);
	}

	private void leggiECollocaAttrezzi() throws IOException {
		String specificheAttrezzi = this.leggiRigaCheCominciaPer(ATTREZZI_MARKER);
		if (specificheAttrezzi.trim().isEmpty()) return;

		for (String specificaAttrezzo : separaStringheAlleVirgole(specificheAttrezzi)) {
			String nomeAttrezzo = null, pesoAttrezzo = null, nomeStanza = null;
			try (Scanner scannerLinea = new Scanner(specificaAttrezzo)) {
				if (!scannerLinea.hasNext()) continue;
				nomeAttrezzo  = scannerLinea.next();
				nomeCongiuntoCheck(scannerLinea, "il peso dell'attrezzo " + nomeAttrezzo);
				pesoAttrezzo  = scannerLinea.next();
				nomeCongiuntoCheck(scannerLinea, "la stanza dell'attrezzo " + nomeAttrezzo);
				nomeStanza    = scannerLinea.next();
			}
			posaAttrezzo(nomeAttrezzo, pesoAttrezzo, nomeStanza);
		}
	}

	private void posaAttrezzo(String nomeAttrezzo, String pesoAttrezzo, String nomeStanza)
			throws IOException {
		try {
			int peso = Integer.parseInt(pesoAttrezzo);
			this.builder.addAttrezzoInStanza(nomeAttrezzo, peso, nomeStanza);
		} catch (NumberFormatException e) {
			check(false, "Peso attrezzo " + nomeAttrezzo + " non valido");
		}
	}

	private void leggiEImpostaUscite() throws IOException {
		String specificheUscite = this.leggiRigaCheCominciaPer(USCITE_MARKER);
		try (Scanner scannerDiLinea = new Scanner(specificheUscite)) {
			while (scannerDiLinea.hasNext()) {
				String stanzaPartenza = scannerDiLinea.next();
				nomeCongiuntoCheck(scannerDiLinea, "la direzione di uscita da " + stanzaPartenza);
				String dirStr = scannerDiLinea.next();
				nomeCongiuntoCheck(scannerDiLinea, "la destinazione da " + stanzaPartenza + " verso " + dirStr);
				String stanzaDestinazione = scannerDiLinea.next();
				Direzione dir = Direzione.fromString(dirStr);
				this.builder.addAdiacenza(stanzaPartenza, stanzaDestinazione, dir);
			}
		}
	}

	private void leggiECollocaPersonaggi() throws IOException {
		String specifichePersonaggi = this.leggiRigaCheCominciaPer(PERSONAGGI_MARKER);
		if (specifichePersonaggi.trim().isEmpty()) return;

		for (String specificaPersonaggio : separaStringheAlleVirgole(specifichePersonaggi)) {
			try (Scanner scannerLinea = new Scanner(specificaPersonaggio)) {
				if (!scannerLinea.hasNext()) continue;
				String tipo        = scannerLinea.next();
				nomeCongiuntoCheck(scannerLinea, "il nome del personaggio");
				String nome        = scannerLinea.next();
				nomeCongiuntoCheck(scannerLinea, "la stanza del personaggio " + nome);
				String stanza      = scannerLinea.next();
				nomeCongiuntoCheck(scannerLinea, "la presentazione di " + nome);
				String presentazione = scannerLinea.next();
				String attrezzoRegalo = scannerLinea.hasNext() ? scannerLinea.next() : null;

				if (tipo.equalsIgnoreCase("Mago")) {
					this.builder.addMago(nome, presentazione, stanza, attrezzoRegalo);
				} else if (tipo.equalsIgnoreCase("Strega")) {
					this.builder.addStrega(nome, presentazione, stanza);
				} else if (tipo.equalsIgnoreCase("Cane")) {
					this.builder.addCane(nome, presentazione, stanza);
				}
			}
		}
	}

	private void nomeCongiuntoCheck(Scanner scanner, String msg) throws IOException {
		check(scanner.hasNext(), "Terminazione precoce del file prima di leggere " + msg);
	}

	private void check(boolean condizione, String messaggioErrore) throws IOException {
		if (!condizione) {
			throw new IOException("Formato file non valido [Riga " +
					this.reader.getLineNumber() + "] " + messaggioErrore);
		}
	}
}


