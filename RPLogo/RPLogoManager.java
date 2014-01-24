/*
RPLogo/RPLogoManager.java

	
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
import javax.swing.JTextArea;
import javax.swing.JMenuBar;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JSplitPane;
import javax.swing.JTextArea;
import javax.swing.JLabel;

import java.awt.event.*;
import java.awt.*;
import java.awt.image.*;

import phipo.RPLogo.PhiLib.Interface.*;
import phipo.RPLogo.PhiLib.Lang.*;


//***********************************
public class RPLogoManager extends PPManager 
	implements ActionListener, RPVMManager{

	final int MAX_XY=2000;

	JTextArea      c_pplogo_editor;
	PPTerm         c_pplogo_console;
	PPCanvas       c_pplogo_canvas;

	RPVM           c_logolang;
	
	Graphics       c_cG; 
	BufferedImage  c_bufferimg;
	Graphics       c_bG; // buffer


	RPLogoTurtle      c_turtle;
	RPLogoTurtlePref  c_turtle_pref;
	boolean           c_turtle_in_use=false;

	boolean           c_turtle_step_by_step=false;
	boolean           c_turtle_step_by_step_flag;
	boolean           c_turtle_trace_flag=false;

	long              c_out_sz_y=0;

	//-----------------	
	public boolean getTraceFlag() { return c_turtle_trace_flag; }
	public boolean setTraceFlag(boolean p_flag) { return c_turtle_trace_flag; }

	//-----------------	
	BufferedImage getBufferedImage() { return c_bufferimg; }

	//------------
	void setConsole( PPTerm p_pplogo_console){
		c_pplogo_console = p_pplogo_console;
	}
	//------------
	void setEditor( JTextArea p_pplogo_editor ){
		c_pplogo_editor = p_pplogo_editor;
	}
	//------------
	void setCanvas( PPCanvas p_canvas ){
		c_pplogo_canvas = p_canvas;

		c_bufferimg = new BufferedImage( MAX_XY, MAX_XY,  
																		 //															BufferedImage.TYPE_INT_ARGB );
		 															BufferedImage.TYPE_BYTE_INDEXED );
		c_bG = c_bufferimg.createGraphics();
		c_bG.setColor( Color.white );
		c_bG.fillRect(0, 0, MAX_XY, MAX_XY );
		
	}
	//-------------------------------------
	RPLogoManager(){
		c_turtle_pref = new RPLogoTurtlePref( this );

		RPLogoTurtle.InitTable();
		c_turtle   = new RPLogoTurtle( c_turtle_pref.c_beginx,
																	 c_turtle_pref.c_beginy,
																	 0, true,
																	 Color.red );
		c_logolang = new RPVM();
		RPLogoLangCmd.Init(c_logolang); 
	}
	//----------------------
	public void actionPerformed(ActionEvent p_ev ){
		//		System.out.println( "Last cmd:" + c_pplogo_console.getLastCmd() );
		try{
			process( c_pplogo_console.getLastCmd() );
		}
		catch( Exception e ){
		}
		c_logolang.setStop( false );
	}
	//----------------------
	public void process( String p_str ){
		try{
			if( c_turtle_in_use == true )
				{
					processError( "Tortue deja en activite !");
					return;
				}
			
			c_cG = c_pplogo_canvas.getGraphics();
			
			c_logolang.setStop( false );
			
			// create a local object to process 
			RPVM l_lang = new RPVM();
			RPLogoLangCmd.Init(l_lang); 

			l_lang.setStop( false );
			
			l_lang.c_debug=false;
			//		l_lang.processString( c_turtle, this, p_str);
			StringReader l_reader = new StringReader(p_str);
			c_turtle_step_by_step_flag = false;
			
			// permer de lancer la tortue dans un thread
			RPLogoProcess l_lp = new RPLogoProcess( l_lang, c_turtle, this, l_reader );
			l_lp.start();
			//l_lang.processStream( c_turtle, this, l_reader);    
			
			
			c_cG.finalize();
		}
		catch(  Exception e ){
		}
		c_logolang.setStop( false );
	}
	//----------------------
	public void stopTurtle(){
		c_logolang.setStop( true );
	}
	//--------------------------------------
	void clear(){

		c_bG.setColor( Color.white );
		c_bG.fillRect(0, 0, MAX_XY, MAX_XY );	
 
		c_cG = c_pplogo_canvas.getGraphics();
		c_cG.setColor( Color.white );
		c_cG.fillRect(0, 0, MAX_XY, MAX_XY );	

		if( c_turtle_pref.c_see_turtle == true )			
			c_turtle.drawTurtle( c_cG,  c_turtle_pref.c_color, c_turtle_pref.c_sz, c_turtle_pref.c_type  );		 

	}
	//-------------------
	void drawOval( int p_ax, int p_ay, int p_bx, int p_by, boolean p_fill ){
		
		if( p_fill ) {
			c_cG.fillOval( p_ax, p_ay, p_bx, p_by );
			c_bG.fillOval( p_ax, p_ay, p_bx, p_by );
		}
		else
			{
				c_cG.drawOval( p_ax, p_ay, p_bx, p_by );
				c_bG.drawOval( p_ax, p_ay, p_bx, p_by );
			}
	}
	//-------------------
	void drawLine( int p_ax, int p_ay, int p_bx, int p_by ){

		c_cG.drawLine( p_ax, p_ay, p_bx, p_by );
		c_bG.drawLine( p_ax, p_ay, p_bx, p_by );
	}
	//--------------------
	void setColor( Color p_color ){

		c_cG.setColor( p_color );
		c_bG.setColor( p_color );		
	}
	//--------------------------------------
	void drawTurtle( boolean p_state){

		if( c_turtle_pref.c_see_turtle == true )
			{
				if( p_state == true )
					{
						c_turtle.drawTurtle( c_cG, 
																 c_turtle_pref.c_color, 
																 c_turtle_pref.c_sz, 
																 c_turtle_pref.c_type  );
					}
				else
					{						
						int l_sz = (int)(c_turtle_pref.c_sz*1.5);
						int l_x  = (int)(c_turtle.c_x - l_sz);
						int l_y  = (int)(c_turtle.c_y - l_sz);
						l_sz *=2;

						c_cG.drawImage( c_bufferimg, l_x, l_y, l_x+l_sz, l_y+l_sz, l_x, l_y, l_x+l_sz, l_y+l_sz, c_pplogo_canvas );
						//						l_G.drawRect( l_x, l_y, l_sz, l_sz );
					}

			}

		if( p_state == true )
			{
				if( c_turtle_step_by_step )
					{
						while( c_turtle_step_by_step_flag )
							{
								try{
									Thread.sleep( 100 );
								}
								catch(InterruptedException e){}
							}
						
						c_turtle_step_by_step_flag = true;					
					}
				else
					if( c_turtle_pref.c_pause!= 0 )
						{
							try{
								Thread.sleep( c_turtle_pref.c_pause );
							}
							catch(InterruptedException e){}
						}
			}
			
	}
	//-----------------------------------------------------
	public boolean print( Graphics G, Component p_comp )		{
		drawBuffer( G, p_comp );
		return true;
	}
	//-----------------------------------------------------
	boolean drawBuffer(  Graphics G, Component p_comp ){
		//		System.out.println( "drawBuffer =="+ c_bufferimg );

		if( c_bufferimg == null )
			return false;

		G.drawImage( c_bufferimg, 0, 0, p_comp );				

		return true;
	}
	//-----------------------------------------------------
	void repaint( Graphics G, ImageObserver p_io ){

		//		Dimension l_dim = c_pplogo_canvas.getSize();
		//		c_out_sz_y = l_dim.getY();

		if( c_bufferimg != null )
			{
				//				System.out.println( "repaint ==" + c_bufferimg);

				G.drawImage( c_bufferimg, 0, 0, p_io );			
				//	drawTurtle( true );

				if( c_turtle_pref.c_see_turtle == true )			
					c_turtle.drawTurtle( G,  c_turtle_pref.c_color, c_turtle_pref.c_sz, c_turtle_pref.c_type  );
			}
	}
	//-----------------------------------------------------
	public void processError( RPVMException p_ex ){
		processError( p_ex.getMessage() );
	}
	//------------------
	public void processError( String p_str ){
		c_pplogo_console.append( p_str );
	}
	//------------------
	public void processTrace( String p_str ){
		c_pplogo_console.append( p_str );
	}
}
//***********************************

