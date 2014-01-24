/*
RPLogo/RPLogo.java

	
Copyright (C) 2001 Philippe Yves Poupon
	
This program is distributed under the terms of the GNU General Public Licence



This file is part of RPLogo.

RPLogo is free software; you can redistribute it and/or modify
it under the terms of the GNU General Public License as published by
the Free Software Foundation; either version 2, or (at your option)
any later version.
 
RPLogo is distributed in the hope that it will be useful, but
WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
General Public License for more details.

You should have received a copy of the GNU General Public License
along with GNU Classpath; see the file COPYING.  If not, write to the
Free Software Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA
02111-1307 USA.

As a special exception, if you link this library with other files to
produce an executable, this library does not by itself cause the
resulting executable to be covered by the GNU General Public License.
This exception does not however invalidate any other reasons why the
executable file might be covered by the GNU General Public License. */

package phipo.RPLogo;

import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JMenuBar;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JSplitPane;
import javax.swing.JTextArea;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JFileChooser;
import javax.swing.filechooser.*;
import javax.swing.JOptionPane;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JEditorPane;

import java.awt.event.*;
import java.awt.*;
import java.awt.print.*;


import java.io.*;


//import com.sun.image.codec.jpeg.*;
import phipo.RPLogo.PhiLib.Interface.*;


//**********************************
final public class RPLogo extends  PPAppli
	implements ActionListener, ItemListener{
	
	JSplitPane     c_vert_panel;
	JSplitPane     c_horz_panel;
	JCheckBoxMenuItem c_item_step_by_step;
  JCheckBoxMenuItem c_item_trace;

	RPLogoCanvas       c_pplogo_canvas;
	PPTerm         c_pplogo_console;

	JTextArea      c_pplogo_editor;

	RPLogoManager  c_mng_pplogo;

	RPLogoDialogPref c_dialog_pref=null;

	File          c_file_edit;

	//-------------------------------------
	public RPLogo(int p_width, int p_height, String p_file) {
		super("RPLogo", false);

		createMenuBar();

		//---- Quit this app when the big window closes. 
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});
		
		c_mng_pplogo = new RPLogoManager();

		setSize(p_width,p_height);


		//============
		c_pplogo_canvas   = new RPLogoCanvas( c_mng_pplogo ); //(c_mplanet = new RPLogoManager()) );

		JScrollPane l_scrollcanvas = new JScrollPane( c_pplogo_canvas,
																								JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,	
																								JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED );
	
		c_pplogo_editor   = new JTextArea();
		JScrollPane l_scrolleditor = new JScrollPane( c_pplogo_editor,
																							JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,	
																									JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED );
		c_pplogo_console  = new PPTerm();
		//============
		c_horz_panel = new JSplitPane( JSplitPane.HORIZONTAL_SPLIT, l_scrolleditor, l_scrollcanvas);		
		c_vert_panel = new JSplitPane( JSplitPane.VERTICAL_SPLIT,   c_horz_panel,    c_pplogo_console );
		//============

	
		Dimension l_minimum_size = new Dimension( 100, 50 );
		c_pplogo_canvas.setMinimumSize( l_minimum_size );
		c_pplogo_editor.setMinimumSize( l_minimum_size );
		c_pplogo_console.setMinimumSize( l_minimum_size );

		//		c_pplogo_editor.append( "COL bleue \nFTN toto [ 5 AV   90 DR  5  AV  90 DR  5 AV 90 DR 5 AV 90 DR ]\n 10 AV \n10 REP [ COL bleue  10 DR 10  AV  COL rouge  toto  ]  30 AV");

		if( p_file != null)
			{
				File l_file= new File( p_file );
				String l_str = openFile( l_file );
				if( l_str != null )
					{
						c_pplogo_editor.setText( l_str );
						c_file_edit = l_file;
					}
			}

		c_pplogo_console.addActionListener( c_mng_pplogo  );
		c_mng_pplogo.setConsole(c_pplogo_console );
		c_mng_pplogo.setEditor( c_pplogo_editor );
		c_mng_pplogo.setCanvas( c_pplogo_canvas );

		getContentPane().setLayout( new BorderLayout() );
		getContentPane().add( c_vert_panel );
		setVisible(true);
		double l_d=0.5;
		c_horz_panel.setDividerLocation( l_d );		
	 l_d=0.9;
		c_vert_panel.setDividerLocation( l_d );
	}
	//-------------------------------------
	protected JMenuBar createMenuBar() {
		
		//========
		JMenu l_menu_file = new JMenu("Fichier");
		addItem( l_menu_file, "Nouveau" );
		addItem( l_menu_file, "Ouvrir" );
		addItem( l_menu_file, "Sauver" );
		addItem( l_menu_file, "Sauver ..." );
		l_menu_file.addSeparator();
		addItem( l_menu_file, "Imprimer" );
		l_menu_file.addSeparator();
		addItem( l_menu_file, "Sauver l'image en JPEG" );
		l_menu_file.addSeparator();
		addItem( l_menu_file, "Quitter" );
				

		c_menubar.add(l_menu_file);
		//========
		JMenu l_menu_edit = new JMenu("Edition");
		addItem( l_menu_edit, "Couper" );
		addItem( l_menu_edit, "Copier" );
		addItem( l_menu_edit, "Coller" );
		addItem( l_menu_edit, "Tout selectionner");
		l_menu_edit.addSeparator();
		addItem( l_menu_edit, "Preferences" );
				

		c_menubar.add(l_menu_edit);		
		//========
		JMenu l_menu_action = new JMenu("Actions");
		l_menu_action.setMnemonic(KeyEvent.VK_D);

		addItem( l_menu_action, "Executer" );
		addItem( l_menu_action, "Stop" );

		l_menu_action.addSeparator();
		addItem( l_menu_action, "Redessiner" );
		addItem( l_menu_action, "Effacer" );

		c_menubar.add(l_menu_action);
		//========
		JMenu l_menu_debug = new JMenu("Debug");
		l_menu_debug.add( (c_item_step_by_step = new JCheckBoxMenuItem( "Pas a pas")));
		c_item_step_by_step.addItemListener( this );
		addItem( l_menu_debug, "Prochain mouvement" );		
		l_menu_debug.add( (c_item_trace = new JCheckBoxMenuItem( "Trace")));
		c_item_trace.addItemListener( this );
		c_menubar.add(l_menu_debug);
		//========
		JMenu l_menu_help = new JMenu("Help");
		addAbout( l_menu_help );
		c_menubar.add(l_menu_help);
		//========

		return c_menubar;
	}
	//---------------------
	public void itemStateChanged(ItemEvent p_e ){

		//		System.out.println( "itemStateChanged" );

		Object l_source = p_e.getItemSelectable();

		if( l_source == c_item_step_by_step )
			{
				if( p_e.getStateChange() == ItemEvent.DESELECTED )
					{
						c_mng_pplogo.c_turtle_step_by_step = false;
					}
				else
					{
						c_mng_pplogo.c_turtle_step_by_step = true;				
					}
			}
		else
		if( l_source == c_item_trace )
			{
				if( p_e.getStateChange() == ItemEvent.DESELECTED )
					{
						c_mng_pplogo.c_turtle_trace_flag = false;
					}
				else
					{
						c_mng_pplogo.c_turtle_trace_flag = true;				
					}
			}
	}
	//---------------------
	public void actionPerformed( ActionEvent p_e ){
		
		if(  p_e.getActionCommand().equals( "Nouveau")  )
			{
				c_pplogo_editor.selectAll();
				c_pplogo_editor.cut();
				c_file_edit = null;
			}
		else if( p_e.getActionCommand().equals("Executer"))
			{
				c_mng_pplogo.process( c_pplogo_editor.getText() );
			}
		else if( p_e.getActionCommand().equals("Effacer"))
			{
				c_mng_pplogo.clear( );
			}
		else if( p_e.getActionCommand().equals("Redessiner"))
			{
				c_pplogo_canvas.repaint( new Rectangle( 0, 0, 2000, 2000 ));
			}
		else if( p_e.getActionCommand().equals("Stop"))
			{
				c_mng_pplogo.stopTurtle();
			}
		else if( p_e.getActionCommand().equals("Preferences"))
			{
				if( c_dialog_pref == null )
					c_dialog_pref = new RPLogoDialogPref( c_mng_pplogo );
				c_dialog_pref.setVisible(true);
			}
		else if( p_e.getActionCommand().equals("Quitter"))
			System.exit(0);		
		else if( p_e.getActionCommand().equals( "Ouvrir") )
			{				
				openDiagFile();				
			}				
		else if( p_e.getActionCommand().equals( "Sauver ...")
						 || p_e.getActionCommand().equals( "Sauver") && c_file_edit == null )
			{							 
				saveDiagFile();
			}
		else if(  p_e.getActionCommand().equals( "Sauver") && c_file_edit != null )
			{
				String l_str = c_pplogo_editor.getText();
				saveFile( c_file_edit, l_str );
			}
		else if(  p_e.getActionCommand().equals( "Imprimer") )
			{
				PPrint l_print = new PPrint( c_mng_pplogo );
				PrinterJob l_printjob = PrinterJob.getPrinterJob();

				l_printjob.setPrintable( l_print );
				
				try{
					l_printjob.print();
				}
				catch( Exception PrintException ){
				}

				l_print = null;
			}
		else if(  p_e.getActionCommand().equals( "Prochain mouvement")  )
			{
				c_mng_pplogo.c_turtle_step_by_step_flag = false;
			}
		else if(  p_e.getActionCommand().equals( "Couper")  )
			{
				c_pplogo_editor.cut();
			}
		else if(  p_e.getActionCommand().equals( "Copier")  )
			{
				c_pplogo_editor.copy();
			}
		else if(  p_e.getActionCommand().equals( "Coller")  )
			{
				c_pplogo_editor.paste();								
			}
		else if(  p_e.getActionCommand().equals( "Tout selectionner")  )
			{
				c_pplogo_editor.selectAll();								
			}
		else if(  p_e.getActionCommand().equals( "Sauver l'image en JPEG"  ))
			{
				saveJPEG();
			}
		else		if(  p_e.getActionCommand().equals( "About")){
			new PPAbout( "RPLogo", 
									 "1.0.0", 
									 "Copyright (C) 2001 Philippe Yves Poupon",
									 "",
									 "ppoupon@free.fr" );
			/*
			try{
				JFrame l_frame = new JFrame();
				JEditorPane l_ed = new JEditorPane( "file:/home/team02/phipo/DEV/Solaris2.5/macro/src/Site/prog/doc_helios/help.html" );
				l_frame.getContentPane().add( l_ed );
				l_frame.setVisible(true);

			}catch( Exception e ) {
				System.out.println( e );
			*/
			}
		/*
			else if(  p_e.getActionCommand().equals( "")  )
			{
			}
		*/
	}
	//-------------------------------------
	
	//-------------------------------------
	void openDiagFile(){
		File l_current = new File( "." );
		JFileChooser l_fc = new JFileChooser(l_current);

	  PPFileFilter l_filter = new PPFileFilter();
		l_filter.addExtension( "logo" );
		l_filter.setDescription( "fichiers logo");
		l_fc.setFileFilter( l_filter );

		int l_ret = l_fc.showOpenDialog( this);
		
		if( l_ret == JFileChooser.APPROVE_OPTION){
			File l_file = l_fc.getSelectedFile();
			
			String l_str = openFile( l_file );
			if( l_str != null ){				
				c_pplogo_editor.setText( l_str );
				c_file_edit = l_file;	
			}
		}
	}
	//-------------------------------------
	String openFile( File p_file){
		
		try{
			FileReader l_fread= new FileReader( p_file );
			
			StringBuffer l_sbuf = new StringBuffer();

			int rc;
			while( l_fread.ready() && (rc=l_fread.read()) != -1 ){
				l_sbuf.append( (char)rc );
			}
			l_fread.close();

			return l_sbuf.toString();
				
		}catch( IOException ffe ){
				JOptionPane.showMessageDialog( this,
																			 "Erreur de lecture",
																			 "Erreur",
																			 JOptionPane.ERROR_MESSAGE );
		}
		return null;
	}
	//-------------------------------------
	void saveDiagFile(){
		File l_current = new File( "." );

		final JFileChooser l_fc = new JFileChooser(l_current);
		int l_ret = l_fc.showSaveDialog( this);

		if( l_ret == JFileChooser.APPROVE_OPTION){
			{
				File l_file = l_fc.getSelectedFile();
				String l_str = c_pplogo_editor.getText();

				if( saveFile( l_file, l_str))
					c_file_edit = l_file;
			}
		}				
	}
	//-------------------------------------
		boolean saveFile( File p_file, String p_str ){
			try{
				FileWriter l_fwrite = new FileWriter( p_file );
				
				l_fwrite.write( p_str );
				
				l_fwrite.close();

				}catch( IOException ffe ){
					// faire une dialog d erreur!
					JOptionPane.showMessageDialog( this,
																				 "Erreur de lecture",
																				 "Erreur",
																				 JOptionPane.ERROR_MESSAGE );
					return false;
				}

			return true;
		}

	//-------------------------------------
	void saveJPEG( ){
		File l_current = new File( "." );
		
		final JFileChooser l_fc = new JFileChooser(l_current);


		int l_ret = l_fc.showSaveDialog( this);
		
		if( l_ret == JFileChooser.APPROVE_OPTION){
			{
				File l_file = l_fc.getSelectedFile();

				try {
					DataOutputStream l_out = new DataOutputStream( new FileOutputStream( l_file ) );
					/*					JPEGImageEncoder l_encoder = JPEGCodec.createJPEGEncoder( l_out );
						JPEGEncodeParam  l_param = l_encoder.getDefaultJPEGEncodeParam( c_mng_pplogo.getBufferedImage() );
					
					l_param.setQuality( 1.0f, false );
					l_encoder.setJPEGEncodeParam( l_param );
					l_encoder.encode( c_mng_pplogo.getBufferedImage() );
					*/
					l_out.close();
				}
				catch( IOException err){
					JOptionPane.showMessageDialog( this,
																				 "Erreur d'ecriture de " + l_file.getName(),
																				 "Erreur",
																				 JOptionPane.ERROR_MESSAGE );
				}
			}
		}
	}
	
	//-------------------------------------
	public static void main(String[] args ) {
		
		
		int l_width=800;
		int l_height=600;

		Integer l_int;
		if( ( l_int = PParam.GetInt( args, "-w" )) != null)			
					l_width = l_int.intValue();			
		
		if( (l_int = PParam.GetInt( args, "-h" )) != null)
				l_height = l_int.intValue();
		
		String l_file;
		l_file = PParam.GetString( args, "-f" );
		
	

		RPLogo pplogo_frame  = new RPLogo(l_width,l_height, l_file);
		pplogo_frame.setVisible(true);
		
	}
}
	//***********************************
