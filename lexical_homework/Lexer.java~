package lexer;
import java.io.*; import java.util.*;
public class Lexer {
	public int line = 1;
	private char peek = ' ';
	private Hashtable words = new Hashtable();
	void reserve(Word t) { words.put(t.lexeme, t); }
	public Lexer() {
		reserve(new Word(Tag.TRUE, "true"));
		reserve(new Word(Tag.FALSE, "false"));
		reserve(new Word(Tag.LESS, "<"));
		reserve(new Word(Tag.LE, "<="));
		reserve(new Word(Tag.EQUAL, "=="));
		reserve(new Word(Tag.NE, "!="));
		reserve(new Word(Tag.LAE, ">="));
		reserve(new Word(Tag.LARGER, ">"));
	}
	public Token scan() throws IOException {
		int slash_tag = 0;
		for( ; ; peek = (char)System.in.read() ) {
			if (peek == ' ' || peek == '\t' ) continue;
			else if (peek == '\n') line = line + 1;
			else if (peek == '/' && slash_tag == 0) {
				slash_tag = 1;
			} 
			else if (peek == '/' && slash_tag == 1) {
				for (; ;peek = (char)System.in.read())
				{
					if (peek == '\n') {
						line += 1;
						break;
					}
				}
			}
			else if (peek == '*' && slash_tag == 1) {
					int asterisk_tag = 0;
					for (; ;peek = (char)System.in.read()) {
						if (peek == '*') {
							asterisk_tag = 1;
						} else if (peek != '/' && asterisk_tag == 1) {
							asterisk_tag = 0;
							break;
						} else
							break;
						}
					}
			else break;
		}
			
		if (Character.isDigit(peek)) {
			int v = 0;
			int before_dot = 0;
			int after_dot = 0;
			int is_float = 0;
			do {
				if (peek == '.') {
					before_dot = v;
					is_float = 1;
					v = 0;
				}
				v = 10*v + Character.digit(peek, 10);
				peek = (char)System.in.read();
			} while ( Character.isDigit(peek) );
			if (is_float == 0)
				return new Num(v);
			else {
				after_dot = v;
				return new Float(before_dot, after_dot);
			}
		}
		if (Character.isLetter(peek)) {
			StringBuffer b = new StringBuffer();
			do {
				b.append(peek);
				peek = (char)System.in.read();
			} while( Character.isLetterOrDigit(peek) );
			String s = b.toString();
			Word w = (Word)words.get(s);
			if ( w != null) return w;
			w = new Word(Tag.ID, s);
			words.put(s, w);
			return w;
		}
		Token t = new Token(peek);
		peek = ' ';
		return t;
		}
}


