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


import it.unipr.netsec.zwt.ZwtView;


/** Frame with two command keys.
  */
public class TwoCommandsFrame extends ZwtCommandFrame {
	
	/** Command1 */
	ZwtCommand cmd1=null;

	/** Command2 */
	ZwtCommand cmd2=null;



	/** Creates a new TwoCommandsFrame. */
	public TwoCommandsFrame(ZwtView view, int width, int height) {
		super(view,width,height);
	}

	/*@Override
	public void setFullScreenMode(boolean mode) {
		// do nothing
	}*/

	/** Sets command1. */
	public void setCommand1(ZwtCommand cmd) {
		if (cmd1!=null) super.removeCommand(cmd1);
		cmd1=cmd;
		if (cmd1!=null) super.addCommand(cmd1);
	}

	/** Sets command2. */
	public void setCommand2(ZwtCommand cmd) {
		if (cmd2!=null) super.removeCommand(cmd2);
		cmd2=cmd;
		if (cmd2!=null) super.addCommand(cmd2);
	}

	@Override
	public void addCommand(ZwtCommand cmd) {
		// do nothing
	}

}
