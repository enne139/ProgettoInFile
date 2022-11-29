# ProgettoInFile

programma che consente di salvare un progetto di file di carattteri in un file e viceversa.
Comprende versioni per normali utenti ma anche per utenza scolastica

# Normale utenza

## installazione

- scaricare i file FolderInFile.jar e FileInFolder.jar
- eseguirli da terminale o con un doppio clik
	- se da terminale usare la formula java -jar nome_file [eventuali_parametri]

## possibili parametri

### FolderInFile
-i [output]	// path file di input     (default=out.txt)
-f [folder]	// path folder di output  (default=out)	
-h 			// mostra help
-g 			// mostra gui

### FileInFolder
-a [autore]	// nome dell'autore		(default=null)
-o [output]	// path file di output  (default=out.txt)
-f [folder]	// path folder di input (default=.)
-m			// mostra file nascosti (default=no)
-h 			// mostra help
-g 			// mostra gui


## avvertenzze 
in caso i programmi ci mettano troppo ad completare le operazioni interromperli e verificare i percori, perchè si potrebbe aver speficicato un percorso inesatto e quindi stia venendo creato un file non desiderato.
Esempio se in linux si mette "/" senza specificare altro non prendera la cartella corrente ma la root quindi copiera l'intero file sistem nel file, e questo non è buono.

è consigliato usare percorsi che inizaino con il "." e mettendo il programma dentro la cartella da analizare o in cui si vuole che venga creata la cartella di output

# Utenza scolastica

## installazione 

- il docende 
	- dovra scaricare FileInFolder.jar o MultiFileInFolder.jar
	- FileInFolder.jar
		- si estrarra un file singolarmente
	- MultiFileInFolder.jar
		- si crea una cartella 
		- si mette dentro tutti i file generati dagli studenti
		- si mettera il programma nella stessa cartella e lo si avviera
		- e si otterra in una cartella le cartelle estratte da ogni file in una cartella per ogni file chiamata o con il nome del fine di input o lo stesso ma con una "F" in fondo
- gli studenti 
	- dovranno scaricare il file PStudenti.jar 
	- metterlo nella cartella che si vuole salvare
	- eseguirlo con un doppio click o da terminale con java -jar PStudenti.jar
	- e rinominare il file out.txt con il proprio cognome
