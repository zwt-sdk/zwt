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

package it.unipr.netsec.zwt.keyboard;



import it.unipr.netsec.zwt.ZwtKeyboard;



/** Nokia QWERTY keyboard.
  */
public class NokiaQwertyKeyboard extends ZwtKeyboard {
	
	/** Singletone */
	protected static NokiaQwertyKeyboard INSTANCE=null;
 
	
	/** Gets the keyboard. */
	public static NokiaQwertyKeyboard getInstance() {
		if (INSTANCE==null) INSTANCE=new NokiaQwertyKeyboard();
		return INSTANCE;
	}
	

	// Chars mapped on QWERTY keyboard 
	static final char[] CHARS_KEY_Q={ 'q', 'Q', '!' };
	static final char[] CHARS_KEY_W={ 'w', 'W', '"' };
	static final char[] CHARS_KEY_E={ 'e', 'E', '-' };
	static final char[] CHARS_KEY_R={ 'r', 'R', '1' };
	static final char[] CHARS_KEY_T={ 't', 'T', '2' };
	static final char[] CHARS_KEY_Y={ 'y', 'Y', '3' };
	static final char[] CHARS_KEY_U={ 'u', 'U', '*' };
	static final char[] CHARS_KEY_I={ 'i', 'I', 'ì' };
	static final char[] CHARS_KEY_O={ 'o', 'O', 'è' };
	static final char[] CHARS_KEY_P={ 'p', 'P', '?' };
	static final char[] CHARS_KEY_A={ 'a', 'A', '£' };
	static final char[] CHARS_KEY_S={ 's', 'S', '=' };
	static final char[] CHARS_KEY_D={ 'd', 'D', '+' };
	static final char[] CHARS_KEY_F={ 'f', 'F', '4' };
	static final char[] CHARS_KEY_G={ 'g', 'G', '5' };
	static final char[] CHARS_KEY_H={ 'h', 'H', '6' };
	static final char[] CHARS_KEY_J={ 'j', 'J', '#' };
	static final char[] CHARS_KEY_K={ 'k', 'K', 'é' };
	static final char[] CHARS_KEY_L={ 'l', 'L', 'ù' };
	static final char[] CHARS_KEY_Z={ 'z', 'Z' };
	static final char[] CHARS_KEY_X={ 'x', 'X' };
	static final char[] CHARS_KEY_C={ 'c', 'C' };
	static final char[] CHARS_KEY_V={ 'v', 'V', '7' };
	static final char[] CHARS_KEY_B={ 'b', 'B', '8' };
	static final char[] CHARS_KEY_N={ 'n', 'N', '9' };
	static final char[] CHARS_KEY_M={ 'm', 'M', '0' };
	static final char[] CHARS_KEY_COMMA={ ',', ';', 'ò' };
	static final char[] CHARS_KEY_DOT={ '.', ':', 'à' };
	static final char[] CHARS_KEY_AND={ '&', '(' };
	static final char[] CHARS_KEY_SLASH={ '/', ')' };
	static final char[] CHARS_KEY_SPACE={ ' ' };
	static final char[] CHARS_KEY_APOS={ '\'', '@' };


	/** From Keyboard. Gets array of chars associated to a given key code. */
	public char[] getChars(int keyCode) {
		switch (keyCode) {
			case 'q' : return CHARS_KEY_Q;
			case 'w' : return CHARS_KEY_W;
			case 'e' : return CHARS_KEY_E;
			case 'r' : return CHARS_KEY_R;
			case 't' : return CHARS_KEY_T;
			case 'y' : return CHARS_KEY_Y;
			case 'u' : return CHARS_KEY_U;
			case 'i' : return CHARS_KEY_I;
			case 'o' : return CHARS_KEY_O;
			case 'p' : return CHARS_KEY_P;
			case 'a' : return CHARS_KEY_A;
			case 's' : return CHARS_KEY_S;
			case 'd' : return CHARS_KEY_D;
			case 'f' : return CHARS_KEY_F;
			case 'g' : return CHARS_KEY_G;
			case 'h' : return CHARS_KEY_H;
			case 'j' : return CHARS_KEY_J;
			case 'k' : return CHARS_KEY_K;
			case 'l' : return CHARS_KEY_L;
			case 'z' : return CHARS_KEY_Z;
			case 'x' : return CHARS_KEY_X;
			case 'c' : return CHARS_KEY_C;
			case 'v' : return CHARS_KEY_V;
			case 'b' : return CHARS_KEY_B;
			case 'n' : return CHARS_KEY_N;
			case 'm' : return CHARS_KEY_M;
			case ';' : return CHARS_KEY_COMMA;
			case '\'': return CHARS_KEY_DOT;
			case ',' : return CHARS_KEY_AND;
			case '.' : return CHARS_KEY_SLASH;
			case ' ' : return CHARS_KEY_SPACE;
			case '/' : return CHARS_KEY_APOS;

			case '1' : return CHARS_KEY_R;
			case '2' : return CHARS_KEY_T;
			case '3' : return CHARS_KEY_Y;
			case '4' : return CHARS_KEY_F;
			case '5' : return CHARS_KEY_G;
			case '6' : return CHARS_KEY_H;
			case '7' : return CHARS_KEY_V;
			case '8' : return CHARS_KEY_B;
			case '9' : return CHARS_KEY_N;
			case '0' : return CHARS_KEY_M;
			case '*' : return CHARS_KEY_U;
			case '#' : return CHARS_KEY_J;
		}
		return null;
	}


	/** Gets the numeric value associated to a given key code. */
	public int getKeyNum(int keyCode) {
		
		if ((keyCode>=ZwtKeyboard.KEY_NUM0 && keyCode<=ZwtKeyboard.KEY_NUM9) || keyCode==ZwtKeyboard.KEY_STAR || keyCode==ZwtKeyboard.KEY_POUND) {
			return keyCode;
		}
		else {
			switch (keyCode) {
				case 'r' : return ZwtKeyboard.KEY_NUM1;
				case 't' : return ZwtKeyboard.KEY_NUM2;
				case 'y' : return ZwtKeyboard.KEY_NUM3;
				case 'f' : return ZwtKeyboard.KEY_NUM4;
				case 'g' : return ZwtKeyboard.KEY_NUM5;
				case 'h' : return ZwtKeyboard.KEY_NUM6;
				case 'v' : return ZwtKeyboard.KEY_NUM7;
				case 'b' : return ZwtKeyboard.KEY_NUM8;
				case 'n' : return ZwtKeyboard.KEY_NUM9;
				case 'm' : return ZwtKeyboard.KEY_NUM0;
				case 'u' : return ZwtKeyboard.KEY_STAR;
				case 'j' : return ZwtKeyboard.KEY_POUND;
			}
			return keyCode;
		}
	}

}
