package it.uniroma3.diadia;

import java.util.Scanner;


public class IOConsole implements IO {

	private final Scanner scanner;

	/**
	 * Crea una IOConsole che usa lo scanner fornito.
	 * La chiusura dello scanner rimane responsabilità del chiamante.
	 *
	 * @param scanner scanner già aperto su System.in
	 */
	public IOConsole(Scanner scanner) {
		this.scanner = scanner;
	}

	@Override
	public void mostraMessaggio(String msg) {
		System.out.println(msg);
	}

	/**
	 * Legge una riga dalla console.
	 * Non chiude lo scanner: ci penserà il try-with-resource nel main.
	 */
	@Override
	public String leggiRiga() {
		return this.scanner.nextLine();
	}
}
