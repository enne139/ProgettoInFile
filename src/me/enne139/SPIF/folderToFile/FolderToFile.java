package me.enne139.SPIF.folderToFile;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;

import me.enne139.SPIF.comune.Standard;

public class FolderToFile {
	
	public static String JARNAME = "FolderInFile.jar";
	
	private String autore;  // autore
	private String pathFolderIn;  // path folder da salvare
	private String pathFileOut; // path file di output
	
	private Boolean evitaFileNascosti; // se si vogliamo evitare i file nascosti
	
	private PrintWriter pwOut;

	private String pathFileOutAbsolute; 
	private String pathJarFileAbsolute;
	
	public FolderToFile(String autore, 
						  String pathFolderIn, 
						  String pathFileOut, 
						  Boolean evitaFileNascosti) {
		this.autore = autore;
		this.pathFolderIn = pathFolderIn;
		this.pathFileOut =  pathFileOut;
		this.evitaFileNascosti = evitaFileNascosti;

		
	}
	
	public String esegui() {
		
		File fileOut = new File(pathFileOut);
		
		try { // controllo se il file di output esiste già
			if (fileOut.exists()) { // se esiste
				fileOut.delete(); // lo cancella
			}
			fileOut.createNewFile(); // lo crea
		} catch (Exception e) {
			e.printStackTrace();
			return e.getMessage();
		}
		
		try {
			pathFileOutAbsolute = new File(pathFileOut).getCanonicalPath(); // path del file di output
			pathJarFileAbsolute = new File(JARNAME).getCanonicalPath(); // path del file di Jar
		} catch (Exception e) {
			e.printStackTrace();
			return e.getMessage();
		}
		
		try { // apertura del file di output
			pwOut = new PrintWriter( fileOut );
		} catch (Exception e) {
			e.printStackTrace();
			return e.getMessage();
		}

		try { // intestazione file
			pwOut.println(Standard.VER + " " + Standard.NUM); // mette informazioni versione
			pwOut.println(Standard.BAR);
			
			if (autore!=null) { // se c'è un autore lo scrive nel file
				pwOut.println(Standard.COM + " autore : " + autore);
				pwOut.println(Standard.BAR);
			}
			
			stampaStrutturaCartellaInFileOut(new File(pathFolderIn), ""); // salva la struttura della cartella di input nel file di output
			
			
		} catch (Exception e) {
			e.printStackTrace();
			return e.getMessage();
		}
		
		try {
			stampaContenutoFileInFolderInFileOut(new File(pathFolderIn)); // stampa il contenuto dei file in folderIn nel file di output
		} catch (Exception e) {
			e.printStackTrace();
			return e.getMessage();
		}
		
		try {
			pwOut.close(); // chiude stream file output
		} catch (Exception e) {
			e.printStackTrace();
			return e.getMessage();
		}
		
		return null;
	}
	
	private void stampaStrutturaCartellaInFileOut(File path, String spazzi) throws IOException {
		// funzione ricorsiva
		
		File tmp; // file in analizzato
		
		for (String file : path.list()) { // scorre tutti i "figli" cartelle della cartella
			if ( evitaFileNascosti && file.charAt(0)=='.' ) continue; // se è attiva, salta i file nascosti
			tmp = new File(path + Standard.SEP + file); // ottiene il file da analizzare
			if (tmp.isDirectory()) { // se è una cartella
				pwOut.println(Standard.STR + " " + spazzi+file + Standard.SEP); // stampa la cartella nella struttura
				stampaStrutturaCartellaInFileOut(tmp, spazzi+"    "); // richiama la funzione analizzano la sotto cartella e aumentando gli spazzi di tabulazione
			}
			
		} 
		
		for (String file : path.list()) { // scorre tutti i "figli" file della cartella
			if ( evitaFileNascosti && file.charAt(0)=='.' ) continue; // se è attiva, salta i file nascosti
			tmp = new File(path + Standard.SEP + file); // ottiene il file da analizzare
			if ( tmp.getCanonicalPath().equals(pathFileOutAbsolute) ) continue; // se il file analizzato è quello di output lo salta
			if ( tmp.getCanonicalPath().equals(pathJarFileAbsolute) ) continue; // se il file analizzato è quello di jar lo salta
			if (tmp.isFile()) { // se è un file
				pwOut.println(Standard.STR + " " + spazzi + file); // stampa il file nella struttura
			}
			
		} 
		
	}

	private void stampaContenutoFileInFolderInFileOut(File path) throws IOException { 
		// funzione che stampa il contenuti di tutti i file di una cartella in file di output
		
		File tmp;
		
		for (String file : path.list()) {
			if ( file.charAt(0)=='.' ) continue;
			tmp = new File(path + Standard.SEP + file); // ottiene il file da analizzare
			if (tmp.isDirectory()) { // se è una cartella
				stampaContenutoFileInFolderInFileOut(tmp); // richiama la funzione sulla sotto cartella
			}
			
		} 
		
		for (String file : path.list()) {
			if ( file.charAt(0)=='.' ) continue;
			tmp = new File(path + Standard.SEP + file); // ottiene il file da analizzare
			if ( tmp.getCanonicalPath().equals(pathFileOutAbsolute) ) continue; // se il file analizzato è quello di output lo salta
			if ( tmp.getCanonicalPath().equals(pathJarFileAbsolute) ) continue; // se il file analizzato è quello di jar lo salta
			if (tmp.isFile()) { // se è un file
				aggiungiAlFileOut(tmp); // aggiunge il contenuto del file tmp al file di output
			} 
			
		} 
		
	}
	
	private void aggiungiAlFileOut(File fileIn) {
		// funzione che aggiunge al file di output il contenuto del file input
		
		String str;
		
		try {
			BufferedReader brFileIn = new BufferedReader( new FileReader(fileIn)); // apre lo stream per la lettura dal file da salvare
			
			pwOut.println(Standard.BAR);
			pwOut.println(Standard.PAT + " "+ fileIn.getAbsolutePath().replace( new File(pathFolderIn).getAbsolutePath() + Standard.SEP, "")); // stampa path file di input senza il percorso assoluto della folder di input
			pwOut.println(Standard.BAR);
			pwOut.println(Standard.INI);
			while ( (str=brFileIn.readLine())!=null ) { 
				pwOut.println(str); // stampa ogni riga fino alla fine del file
			}

			pwOut.println(Standard.FIN);
			
			brFileIn.close(); // chiude lo stream per la lettura dal file di input
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
}
