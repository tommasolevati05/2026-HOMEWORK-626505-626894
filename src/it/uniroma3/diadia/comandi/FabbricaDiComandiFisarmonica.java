package it.uniroma3.diadia.comandi;

import java.util.Scanner;

import it.uniroma3.diadia.IO;
import it.uniroma3.diadia.IOConsole;

public class FabbricaDiComandiFisarmonica implements FabbricaDiComandi{

	private IO io; // L'istanza unica di IO Console

    // Il costruttore riceve l'IO da DiaDia
    public FabbricaDiComandiFisarmonica(IO console) {
        this.io = console;
    }
	@Override
	public Comando costruisciComando(String istruzione) {
		
		Scanner scannerDiParole = new Scanner(istruzione);
		String nomeComando = null;
		String parametro = null;
		Comando comando = null;
		if (scannerDiParole.hasNext()) {
		nomeComando = scannerDiParole.next();// prima parola: nome del comando
		}
		if (scannerDiParole.hasNext()) {
		parametro = scannerDiParole.next(); // seconda parola: eventuale param.
		}
		if (nomeComando == null) {
			comando = new ComandoNonValido();
		}
			else if (nomeComando.equals("vai")) {
			comando = new ComandoVai();
			}
			else if (nomeComando.equals("prendi")) {
			comando = new ComandoPrendi();
			}
			else if (nomeComando.equals("posa")) {
			comando = new ComandoPosa();
			}
			else if (nomeComando.equals("aiuto")) {
			comando = new ComandoAiuto();
			}
			else if (nomeComando.equals("fine")) {
			comando = new ComandoFine();
			}
			else if (nomeComando.equals("guarda")) {
			comando = new ComandoGuarda();
			}
			else comando = new ComandoNonValido();
		
			comando.setParametro(parametro);
			comando.setIo(io);
			return comando;
		}
			

		
		

}
