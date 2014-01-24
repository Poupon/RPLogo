/*
RPLogo/RPLogoProcess.java

	
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


import java.io.*;
import java.util.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;

import javax.swing.*;
import javax.swing.Timer;

import phipo.RPLogo.PhiLib.Lang.*;

// Permet de lancer l execution du logo dans un thread 
// separe, ce qui permet notament a l interface de continuer 
// a marcher normalement 

//********************************************************
class RPLogoProcess extends Thread{

	RPVM          c_lang;
	RPLogoTurtle  c_turtle;
	RPLogoManager c_mng;
	Reader        c_reader;

	//----------------------------
	RPLogoProcess( RPVM p_lang,
								 RPLogoTurtle  p_turtle,
								 RPLogoManager p_mng,
								 Reader        p_reader){
		c_lang   = p_lang;
		c_turtle = p_turtle;
		c_mng    = p_mng;
		c_reader = p_reader;		
	}
	//----------------------------
	public void run(){
		System.out.println(">>run");
		c_mng.c_cG = c_mng.c_pplogo_canvas.getGraphics();
		
		try{
		c_mng.c_turtle_in_use=true;
		c_lang.processStream( (Object)c_turtle, (RPLogoManager)c_mng, c_reader);  
		}
		catch(Exception e)
			{ System.out.println( "RPLogoProcess Run : Exception :" + e );}
		// ATTENTION AU CATCH 
		c_mng.c_turtle_in_use=false;
		c_lang.setStop( false );
	}
}
//********************************************************
