/*
RPLogo/RPLogoLangCmd.java

	
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
import java.io.*;
import java.util.*;

import java.lang.Integer;
import java.lang.*;

import java.awt.Graphics;
import java.awt.Color;

import phipo.RPLogo.PhiLib.Lang.*;


//***********************************
public class RPLogoLangCmd extends RPVMCmd {

	RPLogoLangCmd( String p_str ){
		super( p_str );
	}
	//-------------------
	static void Init( RPVM p_lang ){

		RPVMCmdBase.Init( p_lang );
		
		p_lang.addCmd( new RPLogoLangCmdPosition("Position" ));
		p_lang.addCmd( new RPLogoLangCmdPosition("POS" ));
		p_lang.addCmd( new RPLogoLangCmdPosition("FPOS" ));
		p_lang.addCmd( new RPLogoLangCmdPosition("FixeXY" ));


		p_lang.addCmd( new RPLogoLangCmdAvance(  "Avance", true ));
		p_lang.addCmd( new RPLogoLangCmdAvance(  "AV", true ));


		p_lang.addCmd( new RPLogoLangCmdAvance( "Recule", false ));
		p_lang.addCmd( new RPLogoLangCmdAvance( "RE",  false ));


		p_lang.addCmd( new RPLogoLangCmdAngle(   "Angle" ));
		p_lang.addCmd( new RPLogoLangCmdAngle(   "FixeCap" ));
		p_lang.addCmd( new RPLogoLangCmdAngle(   "FCAP" ));


		p_lang.addCmd( new RPLogoLangCmdRotate(  "Droite", false ));
		p_lang.addCmd( new RPLogoLangCmdRotate(  "DR", false ));
		p_lang.addCmd( new RPLogoLangCmdRotate(  "TD", false ));

		p_lang.addCmd( new RPLogoLangCmdRotate(  "Gauche", true));
		p_lang.addCmd( new RPLogoLangCmdRotate(  "GA", true));
		p_lang.addCmd( new RPLogoLangCmdRotate(  "TG", true));


		p_lang.addCmd( new RPLogoLangCmdDemiTour("DemiTour" ));
		p_lang.addCmd( new RPLogoLangCmdDemiTour("Demi_Tour" ));
		p_lang.addCmd( new RPLogoLangCmdDemiTour("DT" ));



		p_lang.addCmd( new RPLogoLangCmdSeeTurtle(   "MONTRETORTUE", true ));
		p_lang.addCmd( new RPLogoLangCmdSeeTurtle(   "MONTRE_TORTUE", true ));
		p_lang.addCmd( new RPLogoLangCmdSeeTurtle(   "MT", true ));
		p_lang.addCmd( new RPLogoLangCmdSeeTurtle(   "CACHE_TORTUE", false ));
		p_lang.addCmd( new RPLogoLangCmdSeeTurtle(   "CACHETORTUE", false ));
		p_lang.addCmd( new RPLogoLangCmdSeeTurtle(   "CT", false ));



		
		p_lang.addCmd( new RPLogoLangCmdPen( "LeveCrayon", false ));
		p_lang.addCmd( new RPLogoLangCmdPen( "Leve_Crayon", false ));
		p_lang.addCmd( new RPLogoLangCmdPen( "LP", false ));


		p_lang.addCmd( new RPLogoLangCmdPen( "BaisseCrayon", true ));
		p_lang.addCmd( new RPLogoLangCmdPen( "Baisse_Crayon", true ));
		p_lang.addCmd( new RPLogoLangCmdPen( "BP", true ));
		


		p_lang.addCmd( new RPLogoLangCmdColor(   "Couleur" ));
		p_lang.addCmd( new RPLogoLangCmdColor(   "Couleur_Crayon" ));
		p_lang.addCmd( new RPLogoLangCmdColor(   "FCC" ));
		p_lang.addCmd( new RPLogoLangCmdColor(   "COL" ));


		p_lang.addCmd( new RPLogoLangCmdCircle(  "Cercle" ));
		p_lang.addCmd( new RPLogoLangCmdOval(    "Ovale" ));



		p_lang.addCmd( new RPLogoLangCmdCls(   "Nettoie" ));
		p_lang.addCmd( new RPLogoLangCmdCls(   "CLS" ));
		p_lang.addCmd( new RPLogoLangCmdCls(   "VE" ));


		p_lang.addCmd( new RPLogoLangCmdFill(   "Plein", true ));
		p_lang.addCmd( new RPLogoLangCmdFill(   "Vide", false ));
	}
}
//***********************************
class   RPLogoLangCmdCls extends RPLogoLangCmd {
		RPLogoLangCmdCls( String p_str ){
		super( p_str );	
		
	}
	//-------------------
	public boolean exec(  RPVM p_lang, Object  p_userdata,
						 RPVMManager p_mng, PPLangTokenizer p_tokenizer ){

		((RPLogoManager)p_mng).clear( );

		return true;
	}
}
//***********************************
class RPLogoLangCmdPosition  extends RPLogoLangCmd {
		RPLogoLangCmdPosition( String p_str ){
		super( p_str );				
	}
	//-------------------
	public boolean exec(  RPVM p_lang, Object  p_userdata,
						 RPVMManager p_mng, PPLangTokenizer p_tokenizer ){


		double l_y = p_lang.popPile();
		double l_x = p_lang.popPile();
		
		if( ((RPLogoManager)p_mng).getTraceFlag() ) trace( p_mng );
		((RPLogoTurtle)p_userdata).setPos( (RPLogoManager)p_mng, l_x, l_y );
		return true;
	}
}
//***********************************
class RPLogoLangCmdCircle  extends RPLogoLangCmd {
		RPLogoLangCmdCircle( String p_str ){
		super( p_str );				
	}
	//-------------------
	public boolean exec(  RPVM p_lang, Object  p_userdata,
						 RPVMManager p_mng, PPLangTokenizer p_tokenizer ){

		double l_x = p_lang.popPile();

		if( p_mng.getTraceFlag() ) trace( p_mng );

		((RPLogoTurtle)p_userdata).oval( (RPLogoManager)p_mng, l_x, l_x );
		return true;
	}
}
//***********************************
class RPLogoLangCmdOval  extends RPLogoLangCmd {
		RPLogoLangCmdOval( String p_str ){
		super( p_str );				
	}
	//-------------------
	public boolean exec(  RPVM p_lang, Object  p_userdata,
						 RPVMManager p_mng, PPLangTokenizer p_tokenizer ){

		double l_y = p_lang.popPile();
		double l_x = p_lang.popPile();

		if( p_mng.getTraceFlag() ) trace( p_mng );

		((RPLogoTurtle)p_userdata).oval( (RPLogoManager)p_mng, l_x, l_y );
		return true;
	}
}
//***********************************
class RPLogoLangCmdAvance  extends RPLogoLangCmd {

	boolean c_sens;

		RPLogoLangCmdAvance( String p_str, boolean p_sens ){
		super( p_str );	
			
		c_sens = p_sens;
	}
	//-------------------
	public boolean exec(  RPVM p_lang, Object  p_userdata,
						 RPVMManager p_mng, PPLangTokenizer p_tokenizer ){

		double l_x = p_lang.popPile();

		if( p_mng.getTraceFlag() ) trace( p_mng );

		if( c_sens )
			((RPLogoTurtle)p_userdata).avance( (RPLogoManager)p_mng, l_x );
		else
			((RPLogoTurtle)p_userdata).avance( (RPLogoManager)p_mng, -l_x );

		return true;
	}
}
//***********************************
class RPLogoLangCmdAngle  extends RPLogoLangCmd {

		RPLogoLangCmdAngle( String p_str ){
		super( p_str );				
	}
		
	public boolean exec( RPVM p_lang, Object  p_userdata,
						 RPVMManager p_mng, PPLangTokenizer p_tokenizer ){
		
			double l_x = p_lang.popPile();
		if( p_mng.getTraceFlag() ) trace( p_mng );

		((RPLogoTurtle)p_userdata).setAngle( (RPLogoManager)p_mng, l_x );
		return true;
	}		
}
//***********************************
class RPLogoLangCmdFill extends RPLogoLangCmd {
	boolean c_fill;

		RPLogoLangCmdFill( String p_str, boolean p_fill ){
		super( p_str );				
		c_fill = p_fill;
	}
		
	public boolean exec( RPVM p_lang, Object  p_userdata,
								RPVMManager p_mng, PPLangTokenizer p_tokenizer ){

		if( p_mng.getTraceFlag() ) trace( p_mng );

		((RPLogoTurtle)p_userdata).setFill( c_fill );
		return true;
	}		
}
//***********************************
class RPLogoLangCmdPen extends RPLogoLangCmd {

	boolean c_pen;

		RPLogoLangCmdPen( String p_str, boolean p_b ){
		super( p_str );				
		c_pen = p_b;
	}
		
	public boolean exec( RPVM p_lang, Object  p_userdata,
								RPVMManager p_mng, PPLangTokenizer p_tokenizer ){

		if( p_mng.getTraceFlag() ) trace( p_mng );

		((RPLogoTurtle)p_userdata).setPen( c_pen );
		return true;
	}		
}
//***********************************
class RPLogoLangCmdSeeTurtle extends RPLogoLangCmd {
	
	boolean c_see_turtle;
	
		RPLogoLangCmdSeeTurtle( String p_str, boolean p_b ){
		super( p_str );				
		c_see_turtle = p_b;
	}
		
	public boolean exec( RPVM p_lang, Object  p_userdata,
								RPVMManager p_mng, PPLangTokenizer p_tokenizer ){

		if( p_mng.getTraceFlag() ) trace( p_mng );
		((RPLogoManager)p_mng).c_turtle_pref.c_see_turtle = c_see_turtle;
		((RPLogoManager)p_mng).drawTurtle( c_see_turtle );

		return true;
	}		
}
//***********************************
class RPLogoLangCmdRotate  extends RPLogoLangCmd {

	boolean c_sens;
	
		RPLogoLangCmdRotate( String p_str, boolean p_sens ){
		super( p_str );				
		c_sens = p_sens;
	}
		
	public boolean exec( RPVM p_lang, Object  p_userdata,
						 RPVMManager p_mng, PPLangTokenizer p_tokenizer ){

		if( p_mng.getTraceFlag() ) trace( p_mng );

		double l_x = p_lang.popPile();

		if( c_sens )
			((RPLogoTurtle)p_userdata).rotate( (RPLogoManager)p_mng, l_x );
		else
			((RPLogoTurtle)p_userdata).rotate( (RPLogoManager)p_mng, -l_x );
			
		return true;
	}		
}
//***********************************
class RPLogoLangCmdRight  extends RPLogoLangCmd {

		RPLogoLangCmdRight( String p_str ){
		super( p_str );				
	}
		
	public boolean exec( RPVM p_lang, Object  p_userdata,
						 RPVMManager p_mng, PPLangTokenizer p_tokenizer ){

		if( p_mng.getTraceFlag() ) trace( p_mng );

		((RPLogoTurtle)p_userdata).rotate( (RPLogoManager)p_mng, -90 );
		return true;
	}		
}
//***********************************
class RPLogoLangCmdLeft  extends RPLogoLangCmd {

		RPLogoLangCmdLeft( String p_str ){
		super( p_str );				
	}
		
	public boolean exec( RPVM p_lang, Object  p_userdata,
						 RPVMManager p_mng, PPLangTokenizer p_tokenizer ){

		if( p_mng.getTraceFlag() ) trace( p_mng );

		((RPLogoTurtle)p_userdata).rotate( (RPLogoManager)p_mng, 90 );
		return true;
	}		
}
//***********************************
class RPLogoLangCmdDemiTour  extends RPLogoLangCmd {
		RPLogoLangCmdDemiTour( String p_str ){
		super( p_str );				
	}		
	public boolean exec( RPVM p_lang, Object  p_userdata,
						 RPVMManager p_mng, PPLangTokenizer p_tokenizer ){

		if( p_mng.getTraceFlag() ) trace( p_mng );

		((RPLogoTurtle)p_userdata).rotate( (RPLogoManager)p_mng, 180 );
		return true;
	}		
}
//***********************************
class RPLogoLangCmdColor  extends RPLogoLangCmd {
		RPLogoLangCmdColor( String p_str ){
		super( p_str );				
	}		
	public boolean exec( RPVM p_lang, Object  p_userdata,
						 RPVMManager p_mng, PPLangTokenizer p_tokenizer ){

		//		StringBuffer l_str= p_tokenizer.readToken(" \t\n", " \t\n;" );
		PPLangToken l_cmd = p_tokenizer.readToken( );
		String l_str = l_cmd.c_val;
		//System.out.println( "Color:"  + l_strbuf + ":" );
		Color l_c = Color.black;

		if( l_str.compareToIgnoreCase( "rouge" )==0 )
			{
				 l_c = Color.red;
			}
		else if( l_str.compareToIgnoreCase( "rose" )==0 )
			{
				 l_c = Color.pink;
			}
		else if( l_str.compareToIgnoreCase( "bleue" )==0 )
			{
				 l_c = Color.blue;
			}
		else if( l_str.compareToIgnoreCase( "vert" )==0 )
			{
				 l_c = Color.green;
			}
		else if( l_str.compareToIgnoreCase( "jaune" )==0 )
			{
				 l_c = Color.yellow;
			}
		else if( l_str.compareToIgnoreCase( "noir" )==0 )
			{
				 l_c = Color.black;
			}
		else if( l_str.compareToIgnoreCase( "blanc" )==0 )
			{
				 l_c = Color.white;
			}
		else if( l_str.compareToIgnoreCase( "gris" )==0 )
			{
				 l_c = Color.lightGray;
			}

		if( p_mng.getTraceFlag() ) trace( p_mng );

		((RPLogoTurtle)p_userdata).setColor( l_c );
		return true;
	}		
}

