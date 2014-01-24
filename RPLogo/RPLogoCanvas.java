/*
RPLogo/RPLogoCanvas.java

	
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

import java.awt.event.*;
import java.awt.*;
import java.awt.image.*;

import javax.swing.*;
import javax.swing.Timer;

//package RPLogo.;

import phipo.RPLogo.PhiLib.Interface.*;

//***********************************
final public class RPLogoCanvas extends PPCanvas 
	implements MouseListener, ActionListener{


	RPLogoManager  c_mng_pplogo;

	Dimension     c_size;


	//--------------------------
	public RPLogoCanvas(RPLogoManager p_mng_pplogo) { 

		super();
		c_mng_pplogo = p_mng_pplogo;

		c_size    = getSize();
 		addMouseListener( this );		
	}


	public void paint( Graphics g ){
		super.paintComponent(g);

		Dimension l_size = getSize();
		c_mng_pplogo.repaint( g, this );
	}
	//-------------------------- 
	public	void update( Graphics g ){
		//		System.out.println( "Update" );
	}
	//-------------------------- 
	public void actionPerformed( ActionEvent p_ev ){		
		repaint();
	}
	//-------------------------- 
	public void mousePressed( MouseEvent p_e ) {
		//		System.out.println( "mouse pressed" );
	} 
	
	public void mouseReleased( MouseEvent p_e ) {
		//		System.out.println( "mouse released" );
	}
	
	public void mouseEntered( MouseEvent p_e ) {
		//		System.out.println( "mouse entered" );
	}

	public void mouseExited( MouseEvent p_e ) {
		//		System.out.println( "mouse exited" );
	}
	
	public void mouseClicked( MouseEvent p_e ) {
	}
}

//***********************************

