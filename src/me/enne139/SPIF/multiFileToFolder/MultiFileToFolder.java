package me.enne139.SPIF.multiFileToFolder;

import java.io.File;

import me.enne139.SPIF.comune.Standard;
import me.enne139.SPIF.fileToFolder.FileToFolder;

public class MultiFileToFolder {

	public static String JARNAME = "MultiFileInFolder.jar";
	
	private String pathFolderIn;  // path folder file da estrarre
	private String pathFolderOut; // path folder in cui estrarre i file
	
	private String pathJarFileAbsolute; // path assoluto del file jar
	
	public MultiFileToFolder(String pathFolderIn, String pathFolderOut) {
		super();
		this.pathFolderIn = pathFolderIn;
		this.pathFolderOut = pathFolderOut;
	}
	
	public String esegui() {
		
		File folderIn = new File(pathFolderIn);

		File tmp; // file temporaneo;
		String nome; // nome cartella output
		String out; 
		String err = "";
		try {
			
			pathJarFileAbsolute = new File(JARNAME).getCanonicalPath(); // path del file di Jar
			
			for (String pathFile : folderIn.list() ) {
				if ( pathFile.charAt(0)=='.' ) continue; // se è attiva, salta i file nascosti
				tmp = new File(pathFolderIn + Standard.SEP + pathFile);
				if ( tmp.getCanonicalPath().equals(pathJarFileAbsolute) ) continue; // se il file analizzato è quello di jar lo salta
				if (tmp.isFile()) { // se è un file
					nome = pathFile.indexOf(".")!=-1 ? pathFile.substring(0, pathFile.lastIndexOf(".")) : pathFile + "F"; // ottiene il nome del file senza estensione e in caso non ci sia l'estensione aggiunge una F
					out = new FileToFolder( pathFolderIn + Standard.SEP + pathFile, pathFolderOut + Standard.SEP + nome).esegui();
					if (out!=null) err += pathFile + " : "+  out + "\n";
				}
				
			}
		} catch (Exception e) {
			e.printStackTrace();
			return e.getMessage();
		}
		
		return err.equals("") ? null : err;
	}
	
	
}
