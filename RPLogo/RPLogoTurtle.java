/*
RPLogo/RPLogoTurtle.java

	
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
import java.math.*;
import java.awt.Graphics;
import java.awt.Color;
import java.awt.Point;

import phipo.RPLogo.PhiLib.Interface.*;

//***********************************
public class RPLogoTurtle{

	double c_x;
	double c_y;
	int c_angle;

	boolean  c_pen_pos;
	Color    c_color;
	boolean  c_fill;

	public	void setFill(boolean p_fill) { c_fill = p_fill; }

static float cs_cos[];
static float cs_sin[];



	//-------------------------------------
	static void InitTable(){
		
		int l_max = 360;

		cs_cos = new float[l_max];
		cs_sin = new float[l_max];

		double l_degre = Math.PI*2.0;
		l_degre /= 360.0;

		for( int i=0; i< l_max; i++)
			{
				cs_cos[i] = (float)Math.cos(l_degre*(double)(i));

				// ATTENTIOIN INVERSION DES SINUS POUR CONTRECARRER
				// La direction des Y sur l ecran
				cs_sin[i] = -(float)Math.sin(l_degre*(double)(i));
			}
	}
	//-------------------------------------
	public	RPLogoTurtle(){
		c_x = 100;
		c_y = 100;
		c_angle = 0;
		c_pen_pos = true;
		c_color = Color.blue;
	}
	//-------------------------------------
    public	RPLogoTurtle( int p_x, int p_y, 
			      int p_angle, 
			      boolean p_pen_pos, 
			      Color p_color){
		c_x = p_x;
		c_y = p_y;
		c_angle = p_angle;
		c_pen_pos = p_pen_pos;
		c_color = p_color;
		}	
	
	//-------------------------------------
	public	RPLogoTurtle(int p_x, int p_y){
		c_x = p_x;
		c_y = p_y;
		}	
	//----------------
	void setPen( boolean p_b ){
		c_pen_pos = p_b;
	}
	//----------------
	void setColor( Color p_c ){
		//		System.out.println( "SetColor: " + p_c );
		c_color = p_c;
	}
	//----------------
	void correctAngle(){

		while( c_angle >= 360 )
			c_angle -= 360;
		
		while( c_angle < 0 )
			c_angle += 360;		
	}
	//----------------
	public void setAngle( RPLogoManager G, double p_angle ){
		G.drawTurtle(false);
		c_angle = (int)p_angle;
		correctAngle();
		G.drawTurtle(true);
	}
	//----------------
	public void setPos( RPLogoManager G, double p_x, double p_y){
		int l_memx = (int)c_x;
		int l_memy = (int)c_y;		

		G.drawTurtle(false);
		c_x = (double)p_x;
		c_y = (double)p_y;

		if( c_pen_pos )
			{
				G.setColor( c_color );
				G.drawLine( (int)c_x, (int)c_y, l_memx, l_memy );
			}
		G.drawTurtle(true);

	}
	//----------------
	public void avance( RPLogoManager G, double p_n ){

		double  l_x = (c_x + cs_cos[c_angle]*p_n);
		double l_y = (c_y + cs_sin[c_angle]*p_n);
		setPos( G, l_x, l_y );
	}
	//-----------------
	public void rotate( RPLogoManager G , double p_angle ){
		setAngle( G, c_angle + p_angle );		
	}
	//-----------------
	public void oval( RPLogoManager G, double p_rayon, double p_rayon2 ){
		if( c_pen_pos )
			{
				G.drawTurtle(false);

				G.setColor( c_color );
				G.drawOval( (int)(c_x-p_rayon/2),
										(int)(c_y-p_rayon2/2), 
										(int)p_rayon, 
										(int)p_rayon2, c_fill );				

				G.drawTurtle(true);
			}
	}
	//----------------------------------
    final Point transform( Point p_pt ){ 
	return new Point( (int)(c_x+p_pt.x * cs_cos[c_angle] +p_pt.y * cs_sin[c_angle]), 
				   (int)(c_y+p_pt.x * cs_sin[c_angle] - p_pt.y * cs_cos[c_angle]));
	//				   (int)(c_y+p_pt.x * cs_sin[c_angle] + p_pt.y - cs_cos[c_angle]));
    }
	//----------------------------------
    final void transform( int []x, int[]y, int nb ){ 
			for( int i=0; i< nb; i++ ) {
				int lx = x[i];
				int ly = y[i];
				x[i] = (int)( c_x + lx * cs_cos[c_angle] + ly * cs_sin[c_angle]);
				y[i] = (int)( c_y + lx * cs_sin[c_angle] - ly * cs_cos[c_angle]);
			}
    }
	
	//----------------------------------
    final void line( Graphics G, Point A, Point B ){
			//	System.out.println( A.x + ":" + A.y + "->" + B.x +":" + B.y );
	G.drawLine( A.x, A.y, B.x, B.y );
    }
	//----------------------------------

	void drawTurtle( Graphics G, Color p_color, int p_sz, int p_ttype ){
		Color l_memcolor = G.getColor();

		G.setColor( p_color );
		//				G.setXORMode( p_color );
				//				G.drawOval( c_x-p_rayon/2, c_y-p_rayon/2, p_rayon, p_rayon);


		int x[]=new int[3];
		int y[]=new int[3];

		x[0] = (int)(p_sz*1.5);
		y[0] = (int)(0);

		x[1] = (int)(-p_sz);
		y[1] = (int)(p_sz);

		x[2] = (int)(-p_sz);
		y[2] = (int)(-p_sz);


		transform( x, y, 3 );
		G.fillPolygon( x, y, 3 );
		
		G.setColor( Color.white );
		G.drawRect(  (int)c_x, (int)c_y, 1, 1 );
		
		
		//		Point A = new Point( (int)(p_sz*1.5), 0 );
		//		Point B = new Point( -p_sz, p_sz );
		//		Point C = new Point( -p_sz, -p_sz );
		
		//		A=transform( A );
		//		B=transform( B );
		//		C=transform( C );
		
		
		
		//		x[0] = (int)(A.getX());
		//		x[1] = (int)(B.getX());
		//		x[2] = (int)(C.getX());
		//		y[0] = (int)(A.getY());
		//		y[1] = (int)(B.getY());
		//		y[2] = (int)(C.getY());
		
		
		//		System.out.println( "=====================================");
		//		line( G, A, B );
		//		line( G, B, C );
		//		line( G, C, A );
		/*
			{
			Point D = new Point(  p_sz, 0 );
			Point E = new Point( -p_sz/3, p_sz/3 );
			Point F = new Point( -p_sz/3, -p_sz/3 );
			
			D=transform( D );
			E=transform( E );
			F=transform( F );
			
			line( G, D, E );
			line( G, E, F );
			line( G, F, D );
			}
 */
 G.setColor( l_memcolor );
	}

}
//***********************************
