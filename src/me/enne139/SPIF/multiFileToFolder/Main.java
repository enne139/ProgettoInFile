package me.enne139.SPIF.multiFileToFolder;

import java.util.ArrayList;

public class Main {

	public static void main(String[] args) {
		// -i output	// path folder di input
		// -f folder	// path folder di output
		// -h 			// mostra help
		// -g 			// mostra gui
		
		String pathFileIn = ".";
		String pathFolderOut = "out";

		ArrayList<String> lista = new ArrayList<>( );
		
		if ( System.console()==null ) { // se non esiste la console (avviato con un doppio click)
			new GUIMultiFileToFolder(pathFileIn, pathFolderOut);
			return;
		} 
		
		for ( int i=0; i<args.length; i++ ) { // trasforma l'array in un arraylist
			lista.add( args[i] );
		}
		
		if ( lista.indexOf("-h") != -1 ) { // se c'è -h mostra guida
			System.out.println("");
			System.out.println(" -i [input]	// path folder di input (default=.) ");
			System.out.println(" -f [folder]	// path folder di output (default=out) ");
			System.out.println(" -h 	    	// mostra help");
			System.out.println(" -g 	    	// mostra gui");
			return;
		}
		
		
		if ( lista.indexOf("-i") != -1 ) { // se è presente un -o
			pathFileIn = lista.get(lista.indexOf("-i")+1); // ottiene il parametro dopo il -o il parametro
		}
		
		if ( lista.indexOf("-f") != -1 ) {
			pathFolderOut = lista.get(lista.indexOf("-f")+1);
		}
		
		if ( lista.indexOf("-g") != -1 ) { // se è presente un -g
			new GUIMultiFileToFolder(pathFileIn, pathFolderOut); // avvia la versione gui
			return;
		} 
		
		// se non c'è la modalita grafica ca di modalita console
		
		System.out.println("se ci mette troppo, (se non compare il messaggio in poco tempo, max 2 min) teminare il teminale o premere (control + c) e verificare i dati forniti e se sono stati creati file troppo pesanti");
		
		String msg = new MultiFileToFolder(pathFileIn, pathFolderOut).esegui();
		
		if ( msg == null ) System.out.println("operazione riuscita");
		else System.out.println("operazione non riuscita, errore : ");
		
		return;
	}
	
	
}
