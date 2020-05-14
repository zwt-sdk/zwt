/*
 * Copyright (c) 2018 NetSec Lab - University of Parma (Italy)
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND. IN NO EVENT
 * SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR
 * OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE,
 * ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER
 * DEALINGS IN THE SOFTWARE.
 *
 * Author(s):
 * Luca Veltri (luca.veltri@unipr.it)
 */

package it.unipr.netsec.zwt.frame;


import java.util.Vector;

import it.unipr.netsec.zwt.ZwtFrame;
import it.unipr.netsec.zwt.ZwtView;


/** Frame with command keys.
  */
public class ZwtCommandFrame extends ZwtFrame {
	
	/** List of commands (as Vector<ZwtCommand>) */
	Vector<ZwtCommand> commands=new Vector<ZwtCommand>();


	/** Creates a new frame. */
	protected ZwtCommandFrame(ZwtView view, int width, int height) {
		super(view,width,height);
	}

	/** Clears the current frame. */
	public void clear() {
		//for (int i=commands.size()-1; i>=0; i--) canvas.removeCommand((ZwtCommand)commands.elementAt(i));
		commands=new Vector<ZwtCommand>();
		//canvas.setCommandListener(null);
		super.clear();
	}

	/** Adds a command. */
	public void addCommand(ZwtCommand cmd) {
		commands.addElement(cmd);
		//canvas.addCommand(cmd);
		//canvas.setCommandListener(this);
	}

	/** Removes a command. */
	public void removeCommand(ZwtCommand cmd) {
		commands.removeElement(cmd);
		//canvas.removeCommand(cmd);
	}

	/*@Override
	public void commandAction(Command c, Displayable d) {
		ZwtCommand cmd=(ZwtCommand)c;
		ZwtCommandListener listener=cmd.getListener();
		if (listener!=null) listener.onCommand(cmd);
	}*/

}
