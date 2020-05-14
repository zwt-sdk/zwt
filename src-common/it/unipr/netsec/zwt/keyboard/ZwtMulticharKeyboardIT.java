package it.unipr.netsec.zwt.keyboard;


import it.unipr.netsec.zwt.ZwtKeyboard;


/** Keyboard with multiple-character keys.
 * There can be multiple characters associated to the same key.
 * When using this type of keyboard, the user has to press multiple times the same key for selecting the target character.
 */
public class ZwtMulticharKeyboardIT extends ZwtKeyboard {

	/** Singletone */
	private static ZwtMulticharKeyboardIT INSTANCE=null;


	/** Gets the keyboard. */
	public static ZwtMulticharKeyboardIT getInstance() {
		if (INSTANCE==null) INSTANCE=new ZwtMulticharKeyboardIT();
		return INSTANCE;
	}

	
	// Chars mapped on QWERTY keyboard
	static final char[] CHARS_KEY_0x5C={'\\', '|'};
	static final char[] CHARS_KEY_1={'1', '!'};
	static final char[] CHARS_KEY_2={'2', '"'};
	static final char[] CHARS_KEY_3={'3', '£'};
	static final char[] CHARS_KEY_4={'4', '$'};
	static final char[] CHARS_KEY_5={'5', '%'};
	static final char[] CHARS_KEY_6={'6', '&'};
	static final char[] CHARS_KEY_7={'7', '/'};
	static final char[] CHARS_KEY_8={'8', '('};
	static final char[] CHARS_KEY_9={'9', ')'};
	static final char[] CHARS_KEY_0={'0', '='};
	static final char[] CHARS_KEY_0x5B={'\'', '?'};
	static final char[] CHARS_KEY_0x5D={'ì', '^'};
	static final char[] CHARS_KEY_Q={'q', 'Q'};
	static final char[] CHARS_KEY_W={'w', 'W'};
	static final char[] CHARS_KEY_E={'e', 'E', 'è', 'é'};
	static final char[] CHARS_KEY_R={'r', 'R'};
	static final char[] CHARS_KEY_T={'t', 'T'};
	static final char[] CHARS_KEY_Y={'y', 'Y'};
	static final char[] CHARS_KEY_U={'u', 'U'};
	static final char[] CHARS_KEY_I={'i', 'I', 'ì'};
	static final char[] CHARS_KEY_O={'o', 'O', 'ò'};
	static final char[] CHARS_KEY_P={'p', 'P'};
	static final char[] CHARS_KEY_0x3B={'è', 'é', '['};
	static final char[] CHARS_KEY_0x3D={'+', '*', ']'};
	static final char[] CHARS_KEY_A={'a', 'A', 'à'};
	static final char[] CHARS_KEY_S={'s', 'S'};
	static final char[] CHARS_KEY_D={'d', 'D'};
	static final char[] CHARS_KEY_F={'f', 'F'};
	static final char[] CHARS_KEY_G={'g', 'G'};
	static final char[] CHARS_KEY_H={'h', 'H'};
	static final char[] CHARS_KEY_J={'j', 'J'};
	static final char[] CHARS_KEY_K={'k', 'K'};
	static final char[] CHARS_KEY_L={'l', 'L'};
	static final char[] CHARS_KEY_0xC0={'ò', 'ç', '@'};
	static final char[] CHARS_KEY_0xDE={'à', '°', '#'};
	static final char[] CHARS_KEY_0x2F={'ù', '§'};
	static final char[] CHARS_KEY_Z={'z', 'Z'};
	static final char[] CHARS_KEY_X={'x', 'X'};
	static final char[] CHARS_KEY_C={'c', 'C'};
	static final char[] CHARS_KEY_V={'v', 'V'};
	static final char[] CHARS_KEY_B={'b', 'B'};
	static final char[] CHARS_KEY_N={'n', 'N'};
	static final char[] CHARS_KEY_M={'m', 'M'};
	static final char[] CHARS_KEY_COMMA={',', ';', '<'};
	static final char[] CHARS_KEY_DOT={'.', ':', '>'};
	static final char[] CHARS_KEY_MINUS={'-', '_'};
	static final char[] CHARS_KEY_SLASH={'/', ')'};
	//static final char[] CHARS_KEY_LESS={'<', '>'};
	static final char[] CHARS_KEY_SPACE={' '};

	
	@Override
	public char[] getChars(int keyCode) {
		switch (keyCode) {
			case 0x5C : return CHARS_KEY_0x5C;
			case '1' : return CHARS_KEY_1;
			case '2' : return CHARS_KEY_2;
			case '3' : return CHARS_KEY_3;
			case '4' : return CHARS_KEY_4;
			case '5' : return CHARS_KEY_5;
			case '6' : return CHARS_KEY_6;
			case '7' : return CHARS_KEY_7;
			case '8' : return CHARS_KEY_8;
			case '9' : return CHARS_KEY_9;
			case '0' : return CHARS_KEY_0;
			case 0x5B : return CHARS_KEY_0x5B;
			case 0x5D : return CHARS_KEY_0x5D;

			case 'q' : return CHARS_KEY_Q;
			case 'Q' : return CHARS_KEY_Q;
			case 'w' : return CHARS_KEY_W;
			case 'W' : return CHARS_KEY_W;
			case 'e' : return CHARS_KEY_E;
			case 'E' : return CHARS_KEY_E;
			case 'r' : return CHARS_KEY_R;
			case 'R' : return CHARS_KEY_R;
			case 't' : return CHARS_KEY_T;
			case 'T' : return CHARS_KEY_T;
			case 'y' : return CHARS_KEY_Y;
			case 'Y' : return CHARS_KEY_Y;
			case 'u' : return CHARS_KEY_U;
			case 'U' : return CHARS_KEY_U;
			case 'i' : return CHARS_KEY_I;
			case 'I' : return CHARS_KEY_I;
			case 'o' : return CHARS_KEY_O;
			case 'O' : return CHARS_KEY_O;
			case 'p' : return CHARS_KEY_P;
			case 'P' : return CHARS_KEY_P;
			case 0x3B : return CHARS_KEY_0x3B;
			case 0x3D : return CHARS_KEY_0x3D;
			case 'a' : return CHARS_KEY_A;
			case 'A' : return CHARS_KEY_A;
			case 's' : return CHARS_KEY_S;
			case 'S' : return CHARS_KEY_S;
			case 'd' : return CHARS_KEY_D;
			case 'D' : return CHARS_KEY_D;
			case 'f' : return CHARS_KEY_F;
			case 'F' : return CHARS_KEY_F;
			case 'g' : return CHARS_KEY_G;
			case 'G' : return CHARS_KEY_G;
			case 'h' : return CHARS_KEY_H;
			case 'H' : return CHARS_KEY_H;
			case 'j' : return CHARS_KEY_J;
			case 'J' : return CHARS_KEY_J;
			case 'k' : return CHARS_KEY_K;
			case 'K' : return CHARS_KEY_K;
			case 'l' : return CHARS_KEY_L;
			case 'L' : return CHARS_KEY_L;
			case 0xC0 : return CHARS_KEY_0xC0;
			case 0xDE : return CHARS_KEY_0xDE;
			case 0x2F : return CHARS_KEY_0x2F;
			case 'z' : return CHARS_KEY_Z;
			case 'Z' : return CHARS_KEY_Z;
			case 'x' : return CHARS_KEY_X;
			case 'X' : return CHARS_KEY_X;
			case 'c' : return CHARS_KEY_C;
			case 'C' : return CHARS_KEY_C;
			case 'v' : return CHARS_KEY_V;
			case 'V' : return CHARS_KEY_V;
			case 'b' : return CHARS_KEY_B;
			case 'B' : return CHARS_KEY_B;
			case 'n' : return CHARS_KEY_N;
			case 'N' : return CHARS_KEY_N;
			case 'm' : return CHARS_KEY_M;
			case 'M' : return CHARS_KEY_M;
			
			case ',' : return CHARS_KEY_COMMA;
			case '.' : return CHARS_KEY_DOT;
			case '-' : return CHARS_KEY_MINUS;
			case ' ' : return CHARS_KEY_SPACE;
		}
		return null;
	}

}
