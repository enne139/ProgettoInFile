package me.enne139.SPIF.fileToFolder;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

import me.enne139.SPIF.comune.Standard;

public class FileToFolder {

	private String pathFileIn; // path file input
	private String pathFolderOut; // path folder dove estrarre i file

	private BufferedReader brIn; 
	
	public FileToFolder(String pathFileIn, String pathFolderOut) {
		this.pathFileIn = pathFileIn;
		this.pathFolderOut = pathFolderOut;
	}

	public String esegui() {
		
		try { // crea lo stream per la lettura dal fine di input
			brIn = new BufferedReader( new FileReader(pathFileIn));
		} catch (Exception e) {
			e.printStackTrace();
			return e.getMessage();
		}
		
		
		String str;
		String s;
		StringTokenizer stringTokenizer;
		
		File out;
		PrintWriter pwOut; // stream file di output
		
		try {
			str=brIn.readLine(); // legge prima riga file di input
			stringTokenizer = new StringTokenizer(str); 
			s = stringTokenizer.nextToken(); // ottiene il prima parte della stringa divisa da " "
			if ( !s.equals(Standard.VER) || !stringTokenizer.nextToken().equals("0.5")) { // se la prima riga non è Standard.VER o se la versione del formato non è quella giusta
				return "versione sbaggliata"; // ritorna un errore
			}
			
			while ( (str=brIn.readLine())!=null ) { // legge ogni riga del file di input
				
				stringTokenizer = new StringTokenizer(str);  // ottiene la tokenizer della riga appena letta
				s = stringTokenizer.nextToken(); // si salva la prima parte
				if ( s.equals(Standard.PAT) ) { // se è uguale al delimitatore di inizio
					s = stringTokenizer.nextToken(); // ottiene la seconda parte che sarebbe il path del file
					out = new File(pathFolderOut + Standard.SEP + s); // calcola il path del file da estrarre
					
					try { // controlla l'esistenza e creazione file
						if (out.exists()) { // se esiste
							out.delete(); // elimina il file
						}
						out.getParentFile().mkdirs(); // crea le cartelle in cui è contenuto il file
						out.createNewFile(); // crea il file
					} catch (Exception e) {
						e.printStackTrace();
						return e.getMessage();
					}
					
					pwOut = new PrintWriter( out ); // ottiene lo stream del file da scrivere
					
					brIn.readLine(); // salta le due righe delimita barra 
					brIn.readLine(); // delimitatore inizio file
					
					while ( ((str=brIn.readLine())!=null) && !str.equals(Standard.FIN) ) { // legge dal file di input e scrive nel file di output fino alla fine del file di input o fino a quando trova il delimitare di fine file
						
						pwOut.println(str); // scrive la linea appena letta sul file di output
						
					}
					
					pwOut.close(); // chiude lo stream del file di output
					
				}
				
			}
		} catch (Exception e) {
			e.printStackTrace();
			return e.getMessage();
		}
		
		return null;
	}

}
