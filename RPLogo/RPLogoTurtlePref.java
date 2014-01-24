/*
RPLogo/RPLogoTurtlePref.java

	
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


import java.util.*;
import java.lang.*;
import java.awt.Color;


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
public class RPLogoTurtlePref{

	Color          c_color = Color.green;

	int            c_sz    = 10;
	int            c_type;
	int            c_beginx =200;
	int            c_beginy =200;

	int            c_pause  =0;

	boolean        c_see_turtle = true;

  RPLogoManager  c_mng_pplogo;

	//-------------------------------------

	JPanel      c_pane_turtle;
	JPanel      c_pane_lang;
	
	JCheckBox   c_checkbox_turtle;

	JTextField  c_xpos, c_ypos;
	
	PPIntField c_pause_field;
	PPIntField c_sz_field;

	PPIntField c_xpos_field;
	PPIntField c_ypos_field;

	//-------------------------------------
	RPLogoTurtlePref(RPLogoManager p_mng_pplogo){

		c_mng_pplogo = p_mng_pplogo;
	}
	//------------------------------------- 
	JPanel initPanel(){

		//===============
		c_pane_turtle = new JPanel();
		c_pane_turtle.setLayout( new GridLayout( 0, 1 ));

		
		c_pane_turtle.setBorder( BorderFactory.createEmptyBorder( 10, 10, 10, 10 ));
		c_checkbox_turtle  = new JCheckBox( "Voir la Tortue",  c_see_turtle);
		c_pane_turtle.add( c_checkbox_turtle);
		
				
		c_sz_field = new PPIntField( "taille", c_sz, PPField.HORIZONTAL );
		c_pane_turtle.add( c_sz_field );

		c_xpos_field = new PPIntField( "Position x", c_beginx, PPField.HORIZONTAL );
		c_pane_turtle.add( c_xpos_field );

		c_ypos_field = new PPIntField( "Position y", c_beginy, PPField.HORIZONTAL );
		c_pane_turtle.add( c_ypos_field );
		
		c_pause_field = new PPIntField( "Pause ", c_pause, PPField.HORIZONTAL );
		c_pane_turtle.add( c_pause_field);
		//===============



		return c_pane_turtle;
	}
	//-------------------------------------
	void setPref(){

		Object l_obj[]= c_checkbox_turtle.getSelectedObjects();

		if( l_obj == null )
			c_see_turtle = false;
		else
			c_see_turtle = true;

		 c_sz = c_sz_field.getInt();

		c_beginx = c_xpos_field.getInt();

		c_beginy= c_ypos_field.getInt();
 
		c_pause = c_pause_field.getInt();
	}
	//-------------------------------------
	void init( String[] args ){

		for (int i=0; i< args.length; i++) {
			
	    String arg = args[i];			
		}
	}
}
//***********************************
