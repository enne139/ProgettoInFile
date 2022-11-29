package me.enne139.SPIF.folderToFile;

import java.util.ArrayList;

public class Main {

	public static void main(String[] args) {
		// -a autore	// nome dell'autore		(default=null)
		// -o output	// path file di output	(default=out.txt)
		// -f folder	// path folder di input	(default=.)
		// -m			// mostra file nascosti (default=no)
		// -h 			// mostra help
		// -g 			// mostra gui
		
		String autore = "";
		String pathFileOut = "out.txt";
		String pathFolderIn = ".";
		
		Boolean evitaFileNascosti = true;

		ArrayList<String> lista = new ArrayList<>( );
		
		if ( System.console()==null ) { // se non esiste la console (avviato con un doppio click)
			new GUIFoderToFile(autore, pathFileOut, pathFolderIn, evitaFileNascosti); // avvia la versione gui
			return;
		} 
		
		for ( int i=0; i<args.length; i++ ) { // trasforma l'array in un arraylist
			lista.add( args[i] );
		}
		
		if ( lista.indexOf("-h") != -1 ) { // se c'è -h mostra guida
			System.out.println("");
			System.out.println(" -a [autore]	// nome dell'autore (default=null) ");
			System.out.println(" -o [output]	// path file di output (default=out.txt) ");
			System.out.println(" -f [folder]	// path folder di input (default=.) ");
			System.out.println(" -m				// mostra file nascosti (default=no) ");
			System.out.println(" -h 	    	// mostra help");
			System.out.println(" -g 	    	// mostra gui");
			return;
		}
		
		if ( lista.indexOf("-a") != -1 ) { // se è presente un -a
			autore = lista.get(lista.indexOf("-a")+1); // ottiene il parametro dopo il -a il parametro
		}
		
		if ( lista.indexOf("-o") != -1 ) { // se è presente un -o
			pathFileOut = lista.get(lista.indexOf("-o")+1); // ottiene il parametro dopo il -o il parametro
		}
		
		if ( lista.indexOf("-f") != -1 ) {
			pathFolderIn = lista.get(lista.indexOf("-f")+1);
		}
		
		if ( lista.indexOf("-m") != -1 ) { // se è presente un -m
			evitaFileNascosti = false; // abilita il salvataggio dei file nascosti
		}
		
		if ( lista.indexOf("-g") != -1 ) { // se è presente un -g
			new GUIFoderToFile(autore, pathFileOut, pathFolderIn, evitaFileNascosti); // avvia la versione gui
			return;
		} 
		
		// se non c'è la modalita grafica ca di modalita console
		
		System.out.println("se ci mette troppo, (se non compare il messaggio in poco tempo, max 2 min) teminare il teminale o premere (control + c) e verificare i dati forniti e se sono stati creati file troppo pesanti");
		
		String msg = new FolderToFile(autore, pathFolderIn, pathFileOut, evitaFileNascosti).esegui();
		
		if ( msg == null ) System.out.println("operazione riuscita");
		else System.out.println("operazione non riuscita, errore : ");
		
		return;
	}
	
	
}
