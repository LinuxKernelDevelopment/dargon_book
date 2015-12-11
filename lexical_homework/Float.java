package lexer;
import java.lang.String;
public class Float extends Token {
	public final float value;
	public Float(int before_dot, int after_dot) {
		super(Tag.FLOAT);
		int DIV = 1;
		int number = String.valueOf(after_dot).length();
		for (int i = 0; i < number; i++) {
			DIV *= 10;
		}
		value = before_dot + after_dot / DIV;
	}
}
