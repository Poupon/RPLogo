/*
RPLogo/RPLogoDialogPref.java

	
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


import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;

import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JFrame;
import javax.swing.JCheckBox;


import phipo.RPLogo.PhiLib.Interface.*;

//***********************************
final public class RPLogoDialogPref  extends JFrame 	
	implements ItemListener, ActionListener, ChangeListener{
	
	RPLogoManager c_mng_pplogo;


	JTabbedPane c_tabpane;
	
	JPanel c_pane_turtle;

	//-------------------------------------
	public 	RPLogoDialogPref( RPLogoManager p_mng_pplogo ){
		super("Preferences RPLogo");
		
		c_mng_pplogo = p_mng_pplogo;
		c_tabpane = new JTabbedPane();
		getContentPane().setLayout( new BorderLayout() );

		//===============
		c_pane_turtle = p_mng_pplogo.c_turtle_pref.initPanel();		

		//===============
		ImageIcon l_turtleimg= new ImageIcon( "images/turtle.gif" );
		c_tabpane.addTab( "Tortue", l_turtleimg, c_pane_turtle,
											"Preferences pour la tortue" );

		getContentPane().add( c_tabpane, BorderLayout.CENTER );


		//===============
		JPanel l_panel_south = new JPanel();
		l_panel_south.setLayout( new GridLayout( 1, 0 ));
		l_panel_south.setBorder( BorderFactory.createEmptyBorder( 10, 10, 10, 10 ));

		JButton  c_button_ok = new JButton( "Ok" );
		c_button_ok.setActionCommand( "ok");
		c_button_ok.addActionListener( this );

		JButton  c_button_cancel = new JButton( "Cancel" );
		c_button_cancel.setActionCommand( "cancel");
		c_button_cancel.addActionListener( this );
		l_panel_south.add( c_button_ok );
		l_panel_south.add( c_button_cancel);
		//===============

		getContentPane().add( l_panel_south, BorderLayout.SOUTH );

		//		setSize(200, 400);
		pack();
	}
	
	//-------------------------------------
	 JSlider makeSlider( int p_min, int p_max, int p_current ){
		 
		JSlider l_slider = new JSlider( JSlider.HORIZONTAL, p_min, p_max, p_current );		
		l_slider.addChangeListener( this ); 
		l_slider.setMajorTickSpacing(10);
		l_slider.setMinorTickSpacing(1);
		l_slider.setPaintTicks(false);
		l_slider.setPaintLabels(false);
		l_slider.setBorder( BorderFactory.createEmptyBorder(0, 0, 10, 0));
		return l_slider;
	}	

	//---------------------
	public void itemStateChanged(ItemEvent p_e ){
		Object l_source = p_e.getItemSelectable();
		
	}
	//---------------------
	public void actionPerformed( ActionEvent p_e ){

		if( p_e.getActionCommand().equals("ok"))
			{
				
				// faire la mise a jour de pref !
				c_mng_pplogo.c_turtle_pref.setPref();
				setVisible(false);

				//				c_xpos 
		
				c_mng_pplogo.c_pplogo_canvas.repaint();
			}
		else if( p_e.getActionCommand().equals("cancel"))
			{
				setVisible(false);
			}
	}
	//---------------------
	public void stateChanged( ChangeEvent p_e ){
	}
}
//***********************************
