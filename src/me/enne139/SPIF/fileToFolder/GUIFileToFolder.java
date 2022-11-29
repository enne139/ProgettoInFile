package me.enne139.SPIF.fileToFolder;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class GUIFileToFolder extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String pathFileIn;
	private String pathFolderOut;
	
	
	// grafica
	
	private JPanel jp_pannello;

	private JLabel jl_pathFileIn;
	private JTextField jtf_pathFileIn;
	
	private JLabel jl_pathFolderOut;
	private JTextField jtf_pathFolderOut;
	
	private JTextArea jta_commento;
	
	private JButton jb_esegui;

	public GUIFileToFolder(String pathFileIn, String pathFolderOut) {
		super();
		this.pathFileIn = pathFileIn;
		this.pathFolderOut = pathFolderOut;
		
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
		// pathFileIn
		
		c.gridy = 1;
		
		jl_pathFileIn = new JLabel("file input");
		c.gridx = 0;
		c.weightx = 0.3;
		jp_pannello.add(jl_pathFileIn, c);
		
		jtf_pathFileIn = new JTextField(pathFileIn);
		c.gridx = 1; 
		c.weightx = 0.7;
		jp_pannello.add(jtf_pathFileIn, c);
		
		//------------------------------------------------------
		// pathFolderOut
		
		c.gridy = 3;
		
		jl_pathFolderOut = new JLabel("folder output");
		c.gridx = 0;
		c.weightx = 0.3;
		jp_pannello.add(jl_pathFolderOut, c);
		
		jtf_pathFolderOut = new JTextField(pathFolderOut);
		c.gridx = 1; 
		c.weightx = 0.7;
		jp_pannello.add(jtf_pathFolderOut, c);
		

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
			
			this.pathFileIn = jtf_pathFileIn.getText().trim();
			this.pathFolderOut = jtf_pathFolderOut.getText().trim();
			
			if ( this.pathFileIn.length() == 0 ) {
				JOptionPane.showMessageDialog(this, "inseriere un file di input");
				return;
			}
			if ( this.pathFolderOut.length() == 0 ) {
				JOptionPane.showMessageDialog(this, "inseriere una folder di output");
				return;
			}
			
			String msg = new FileToFolder(this.pathFileIn, this.pathFolderOut).esegui();
			
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

	public GUIFileToFolder() {
		this("out/example.txt", "out/prova");
	}
	
	public static void main(String[] args) {
		new GUIFileToFolder();
	}

	
}
