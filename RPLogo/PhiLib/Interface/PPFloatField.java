/*
phipo.RPLogo/PhiLib/Interface/PPFloatField.java

	
Copyright (C) 2001 Philippe Yves Poupon
	
This program is distributed under the terms of the GNU General Public Licence



This file is part of phipo.RPLogo/PhiLib/Interface.

PPLab is free software; you can redistribute it and/or modify
it under the terms of the GNU General Public License as published by
the Free Software Foundation; either version 2, or (at your option)
any later version.
 
PPLab is distributed in the hope that it will be useful, but
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

package phipo.RPLogo.PhiLib.Interface;
import  phipo.RPLogo.PhiLib.Interface.*;

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



//*****************************
public class PPFloatField extends PPField{

	//----------------
	public	PPFloatField( String p_str, float p_val, int p_sens ){
		super( p_str, Float.toString( p_val ), p_sens );
	}	
	//----------------
	public	float getFloat(){
		return  Float.parseFloat( c_text.getText() );
	}
	//----------------
	public	void setFloat(float p_val){
		c_text.setText( Float.toString( p_val ) );
	}
}
//*****************************
