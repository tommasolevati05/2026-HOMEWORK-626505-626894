package it.uniroma3.diadia.comandi;

import java.io.File;
import java.net.URL;
import java.util.TreeSet;
import java.util.Set;
import it.uniroma3.diadia.IO;
import it.uniroma3.diadia.Partita;

public abstract class AbstractComando implements Comando {

	protected static final Set<String> COMANDI_DISPONIBILI = new TreeSet<>();

	static {
		String packageRelPath = AbstractComando.class.getPackage().getName().replace('.', File.separatorChar);

		// Metodo primario: URL reale del .class (funziona anche con Eclipse launcher JAR)
		try {
			URL url = AbstractComando.class.getResource("");
			if (url != null && "file".equals(url.getProtocol()))
				scanDir(new File(url.toURI()));
		} catch (Exception ignored) {}

		// Fallback: scansione delle entry di java.class.path
		for (String entry : System.getProperty("java.class.path", "").split(File.pathSeparator)) {
			File dir = new File(entry, packageRelPath);
			if (dir.isDirectory())
				scanDir(dir);
		}
	}

	private static void scanDir(File dir) {
		File[] files = dir.listFiles();
		if (files == null)
			return;
		for (File file : files) {
			String fileName = file.getName();
			boolean isClass = fileName.endsWith(".class");
			boolean isSource = fileName.endsWith(".java");
			if (!fileName.startsWith("Comando") || (!isClass && !isSource) || fileName.contains("$"))
				continue;
			String className = isClass ? fileName.replace(".class", "") : fileName.replace(".java", "");
			if (className.equals("ComandoNonValido") || className.endsWith("Test"))
				continue;
			String nome = className.substring("Comando".length()).toLowerCase();
			if (!nome.isEmpty())
				COMANDI_DISPONIBILI.add(nome);
		}
	}

	private String parametro;
	private IO io;

	public AbstractComando() {}

	@Override
	public abstract void esegui(Partita partita);

	@Override
	public void setParametro(String parametro) {
		this.parametro = parametro;
	}

	@Override
	public void setIo(IO io) {
		this.io = io;
	}

	public String getParametro() {
		return this.parametro;
	}

	public IO getIo() {
		return this.io;
	}
}