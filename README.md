# ProgettoInFile

programma che consente di salvare un progetto di file di caratteri in un file e viceversa.
Comprende versioni per normali utenti ma anche per utenza scolastica

# Normale utenza

## installazione

- scaricare i file FolderInFile.jar e FileInFolder.jar
- eseguirli da terminale o con un doppio click
	- se da terminale usare la formula ```java -jar nome_file [eventuali_parametri]```

## possibili parametri

### FileInFolder 
| indicatore | parametro | default           | descrizione           |
| :--------- | :-------- | :---------------- | :-------------------- |
| -i         | [input]   | (default=out.txt) | path file di input    |
| -f         | [folder]  | (default=out)     | path folder di output |
| -h         |           |                   | mostra help           |
| -g         |           |                   | mostra gui            |

### FolderInFile

| indicatore | parametro | default           | descrizione                       |
| :--------- | :-------- | :---------------- | :-------------------------------- |
| -a         | [autore]  | (default=null)    | nome dell'autore                  |
| -o         | [output]  | (default=out.txt) | path file di output               |
| -f         | [folder]  | (default=.)       | path folder di input              |
| -m         |           |                   | mostra file nascosti (default=no) |
| -h         |           |                   | mostra help                       |
| -g         |           |                   | mostra gui                        |


## avvertenze  
in caso i programmi ci mettano troppo a completare le operazioni, interromperli e verificare i percorsi, perché si potrebbe aver specificato un percorso inesatto e quindi stia venendo creato un file non desiderato.
Esempio se in linux si mette "/" senza specificare altro non prenderà la cartella corrente ma la root quindi copierà l'intero file system nel file, e questo non è buono.

è consigliato usare percorsi che iniziano con il "." e mettendo il programma dentro la cartella da analizzare o in cui si vuole che venga creata la cartella di output

# Utenza scolastica

## installazione 

- il docente 
	- dovrà scaricare FileInFolder.jar o MultiFileInFolder.jar
	- FileInFolder.jar
		- si estrarrà un file singolarmente
	- MultiFileInFolder.jar
		- si crea una cartella 
		- si mette dentro tutti i file generati dagli studenti
		- si metterà il programma nella stessa cartella e lo si avvierà
		- e si otterranno le cartelle estratte da ogni file, una cartella per ogni file chiamata o con il nome del fine di input o lo stesso ma con una "F" in fondo
- gli studenti 
	- dovranno scaricare il file PStudenti.jar 
	- metterlo nella cartella che si vuole converitire in file (src)
	- eseguirlo con un doppio click (solo su windows) o da terminale con ```java -jar PStudenti.jar```
	- e rinominare il file out.txt con il proprio cognome

## possibili parametri

### MultiFileInFolder
| indicatore | parametro | default           | descrizione           |
| :--------- | :-------- | :---------------- | :-------------------- |
| -i         | [input]   | (default=.) 		 | path folder di input  |
| -f         | [folder]  | (default=out)     | path folder di output |
| -h         |           |                   | mostra help           |
| -g         |           |                   | mostra gui            |

### PStudenti

| indicatore | parametro | default           | descrizione                       |
| :--------- | :-------- | :---------------- | :-------------------------------- |
