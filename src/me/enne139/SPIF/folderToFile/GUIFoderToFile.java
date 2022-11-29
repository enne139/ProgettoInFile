package me.enne139.SPIF.folderToFile;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class GUIFoderToFile extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String autore;
	private String pathFileOut;
	private String pathFolderIn;
	
	private Boolean evitaFileNascosti;
	
	// grafica
	
	private JPanel jp_pannello;
	
	private JLabel jl_autore;
	private JTextField jtf_autore;
	
	private JLabel jl_pathFileOut;
	private JTextField jtf_pathFileOut;
	
	private JLabel jl_pathFolderIn;
	private JTextField jtf_pathFolderIn;
	
	private JLabel jl_evitaFileNascosti;
	private JCheckBox jcb_evitaFileNascosti;
	
	private JTextArea jta_commento;
	
	private JButton jb_esegui;

	public GUIFoderToFile(String autore, String pathFileOut, String pathFolderIn, Boolean evitaFileNascosti) {
		super();
		this.autore = autore;
		this.pathFileOut = pathFileOut;
		this.pathFolderIn = pathFolderIn;
		this.evitaFileNascosti = evitaFileNascosti;
		
		addWindowListener( new WindowListener() { // imposta operazione chiusura finestra
			
			@Override
			public void windowOpened(WindowEvent e) {}
			
			@Override
			public void windowIconified(WindowEvent e) {}
			
			@Override
			public void windowDeiconified(WindowEvent e) {}
			
			@Override
			public void windowDeactivated(WindowEvent e) {}
			
			@Override
			public void windowClosing(WindowEvent e) {
				System.exit(0); // termina programma
			}
			
			@Override
			public void windowClosed(WindowEvent e) {
				System.exit(0); // termina programma
			}
			
			@Override
			public void windowActivated(WindowEvent e) {}
		});
	
		//------------------------------------------------------
		// panello
		jp_pannello = new JPanel( new GridBagLayout() );
		setContentPane(jp_pannello);
		
		GridBagConstraints c = new GridBagConstraints();
		c.weighty = 1;
		c.fill = GridBagConstraints.HORIZONTAL;
		
		//------------------------------------------------------
		// autore
		
		c.gridy = 0;
		
		jl_autore = new JLabel("autore");
		c.gridx = 0;
		c.weightx = 0.3;
		jp_pannello.add(jl_autore, c);
		
		jtf_autore = new JTextField(autore);
		c.gridx = 1; 
		c.weightx = 0.7;
		jp_pannello.add(jtf_autore, c);
		
		//------------------------------------------------------
		// pathFileOut
		
		c.gridy = 1;
		
		jl_pathFileOut = new JLabel("file di output");
		c.gridx = 0;
		c.weightx = 0.3;
		jp_pannello.add(jl_pathFileOut, c);
		
		jtf_pathFileOut = new JTextField(pathFileOut);
		c.gridx = 1; 
		c.weightx = 0.7;
		jp_pannello.add(jtf_pathFileOut, c);
		
		//------------------------------------------------------
		// pathFolderIn
		
		c.gridy = 3;
		
		jl_pathFolderIn = new JLabel("folder input");
		c.gridx = 0;
		c.weightx = 0.3;
		jp_pannello.add(jl_pathFolderIn, c);
		
		jtf_pathFolderIn = new JTextField(pathFolderIn);
		c.gridx = 1; 
		c.weightx = 0.7;
		jp_pannello.add(jtf_pathFolderIn, c);
		
		//------------------------------------------------------
		// evitaFileNascosti
		
		c.gridy = 4;
		
		jl_evitaFileNascosti = new JLabel("nascondi file nascosti");
		c.gridx = 0;
		c.weightx = 0.3;
		jp_pannello.add(jl_evitaFileNascosti, c);
		
		jcb_evitaFileNascosti = new JCheckBox();
		jcb_evitaFileNascosti.setSelected(evitaFileNascosti);
		c.gridx = 1; 
		c.weightx = 0.7;
		jp_pannello.add(jcb_evitaFileNascosti, c);
		
		//------------------------------------------------------
		// commento
		
		c.gridy = 5;
		
		jta_commento = new JTextArea("se ci mette troppo, (se non compare il messaggio in poco tempo, max 2 min)\n chiudere la finestra e verificare i dati forniti e se sono stati creati file troppo pesanti");
		jta_commento.setEditable(false);
		jta_commento.setWrapStyleWord(true);
		c.gridx = 0;
		c.weightx = 1;
		c.gridwidth = 2;
		jp_pannello.add(jta_commento, c);
		
		//------------------------------------------------------
		// commento
		
		c.gridy = 6;
		
		jb_esegui = new JButton("esegui");
		jb_esegui.addActionListener(e -> {
			
			this.autore = jtf_autore.getText().trim();
			this.pathFileOut = jtf_pathFileOut.getText().trim();
			this.pathFolderIn = jtf_pathFolderIn.getText().trim();
			this.evitaFileNascosti = jcb_evitaFileNascosti.isSelected();
			
			if ( this.autore.length() == 0 ) this.autore = null;
			if ( this.pathFileOut.length() == 0 ) {
				JOptionPane.showMessageDialog(this, "inseriere un file di output");
				return;
			}
			if ( this.pathFolderIn.length() == 0 ) {
				JOptionPane.showMessageDialog(this, "inseriere una folder di input");
				return;
			}
			
			String msg = new FolderToFile(this.autore, this.pathFolderIn, this.pathFileOut, this.evitaFileNascosti).esegui();
			
			if ( msg == null ) JOptionPane.showMessageDialog(this, "operazione riuscita"); // se non ci sono errori
			else JOptionPane.showMessageDialog(this, "errore : " + msg); // se no mostra l'errore
			
		});
		c.gridx = 0;
		c.weightx = 1;
		c.gridwidth = 2;
		jp_pannello.add(jb_esegui, c);
		
		
		setSize(500,700);
		setLocationRelativeTo(null);
		setVisible(true);
	}

	public GUIFoderToFile() {
		this( null, "out.txt", ".", true);
	}

	
}
