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



/** Nokia compact keyboard.
  */
public class NokiaCompactKeyboard extends ZwtKeyboard {
	
	/** Singletone */
	private static NokiaCompactKeyboard INSTANCE=null;
 
	
	/** Gets the keyboard. */
	public static NokiaCompactKeyboard getInstance() {
		if (INSTANCE==null) INSTANCE=new NokiaCompactKeyboard();
		return INSTANCE;
	}


	/** Chars mapped on KEY 0 */
	static final char[] CHARS_KEY_NUM0={ ' ', '0' };

	/** Chars mapped on KEY 1 */
	static final char[] CHARS_KEY_NUM1={ '.', ',', '?', '!', '1', '@', '\'', '-', '_', '(', ')', ':', ';', '&', '/', '%', '*', '#', '+', '<', '=', '>', '"', '&' };

	/** Chars mapped on KEY 2 */
	static final char[] CHARS_KEY_NUM2={ 'a', 'b', 'c', '2', 'A', 'B', 'C' };

	/** Chars mapped on KEY 3 */
	static final char[] CHARS_KEY_NUM3={ 'd', 'e', 'f', '3', 'D', 'E', 'F' };

	/** Chars mapped on KEY 4 */
	static final char[] CHARS_KEY_NUM4={ 'g', 'h', 'i', '4', 'G', 'H', 'I' };

	/** Chars mapped on KEY 5 */
	static final char[] CHARS_KEY_NUM5={ 'j', 'k', 'l', '5', 'J', 'K', 'L' };

	/** Chars mapped on KEY 6 */
	static final char[] CHARS_KEY_NUM6={ 'm', 'n', 'o', '6', 'M', 'N', 'O' };

	/** Chars mapped on KEY 7 */
	static final char[] CHARS_KEY_NUM7={ 'p', 'q', 'r', 's', '7', 'P', 'Q', 'R', 'S' };

	/** Chars mapped on KEY 8 */
	static final char[] CHARS_KEY_NUM8={ 't', 'u', 'v', '8', 'T', 'U', 'V' };

	/** Chars mapped on KEY 9 */
	static final char[] CHARS_KEY_NUM9={ 'w', 'x', 'y', 'z', '9', 'W', 'X', 'Y', 'Z' };

	/** Chars mapped on KEY * */
	static final char[] CHARS_KEY_STAR={ '*', '@', '+', '-' };

	/** Chars mapped on KEY # */
	static final char[] CHARS_KEY_POUND={ '#', '/' };


	/** From Keyboard. Gets array of chars associated to a given key code. */
	public char[] getChars(int keyCode) {
		switch (keyCode) {
			case ZwtKeyboard.KEY_NUM0 : return CHARS_KEY_NUM0;
			case ZwtKeyboard.KEY_NUM1 : return CHARS_KEY_NUM1;
			case ZwtKeyboard.KEY_NUM2 : return CHARS_KEY_NUM2;
			case ZwtKeyboard.KEY_NUM3 : return CHARS_KEY_NUM3;
			case ZwtKeyboard.KEY_NUM4 : return CHARS_KEY_NUM4;
			case ZwtKeyboard.KEY_NUM5 : return CHARS_KEY_NUM5;
			case ZwtKeyboard.KEY_NUM6 : return CHARS_KEY_NUM6;
			case ZwtKeyboard.KEY_NUM7 : return CHARS_KEY_NUM7;
			case ZwtKeyboard.KEY_NUM8 : return CHARS_KEY_NUM8;
			case ZwtKeyboard.KEY_NUM9 : return CHARS_KEY_NUM9;
			case ZwtKeyboard.KEY_STAR : return CHARS_KEY_STAR;
			case ZwtKeyboard.KEY_POUND : return CHARS_KEY_POUND;
		}
		return null;
	}


	/** Gets the numeric value associated to a given key code. */
	public int getKeyNum(int keyCode) {
		return keyCode;
	}

}
