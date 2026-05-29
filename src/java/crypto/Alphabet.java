package crypto;

import java.util.ArrayList;
import java.util.List;

public class Alphabet {
    private static final String RUSSIAN = "абвгдеёжзийклмнопрстуфхцчшщъыьэюя";
    private static final String SYMBOLS = ".,\"'-!? ";
    public static final List<Character> CHARS = new ArrayList<>();

    static {
        for (char c : RUSSIAN.toCharArray()) CHARS.add(c);
        for (char c : RUSSIAN.toUpperCase().toCharArray()) CHARS.add(c);
        for (char c : SYMBOLS.toCharArray()) CHARS.add(c);
    }

    public static int getSize() {
        return CHARS.size();
    }

    public static int indexOf(char c) {
        return CHARS.indexOf(c);
    }

    public static char get(int index) {
        return CHARS.get(index);
    }
}