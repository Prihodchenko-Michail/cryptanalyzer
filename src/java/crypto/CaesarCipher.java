
    package crypto;

    public class CaesarCipher {

        public char encryptChar(char c, int key) {
            int alphabetSize = Alphabet.getSize();
            int idx = Alphabet.indexOf(c);
            if (idx != -1) {
                int newIdx = ((idx + key) % alphabetSize + alphabetSize) % alphabetSize;
                return Alphabet.get(newIdx);
            }
            return c; // Возвращаем без изменений, если символа нет в алфавите
        }

        public char decryptChar(char c, int key) {
            return encryptChar(c, -key);
        }
    }


