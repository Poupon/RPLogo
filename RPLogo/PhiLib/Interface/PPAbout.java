/*
phipo.RPLogo/PhiLib/Interface/PPAbout.java

	
Copyright (C) 2001 Philippe Yves Poupon
	
This program is distributed under the terms of the GNU General Public Licence



This file is part of phipo.RPLogo/PhiLib/Interface.

PPLogo is free software; you can redistribute it and/or modify
it under the terms of the GNU General Public License as published by
the Free Software Foundation; either version 2, or (at your option)
any later version.
 
PPLogo is distributed in the hope that it will be useful, but
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


import javax.swing.JOptionPane;
import javax.swing.JFrame;


//***********************************

public class PPAbout{

	public PPAbout( String p_progname, 
									String p_version,
									String p_copyright,
									String p_licence,
									String p_mail ){

			JOptionPane.showMessageDialog( PPAppli.ThePPAppli,
																		 p_progname + " "+  p_version +"\n"
																		 + ( p_copyright != null ? p_copyright+"\n" : "" )
																		 + ( p_licence   != null ? p_licence+ "\n" : "")
																		 + ( p_mail      != null ? p_mail : ""),
																		 "About" + p_progname, 
																						 JOptionPane.INFORMATION_MESSAGE);
			
		}
}
//***********************************