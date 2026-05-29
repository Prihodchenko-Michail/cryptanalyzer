package services;

import crypto.Alphabet;
import crypto.CaesarCipher;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;

public class BruteForceService {
    private final CaesarCipher cipher = new CaesarCipher();

    public void bruteForceToConsole(String inputPath) throws IOException {
        Path in = Path.of(inputPath);
        if (!Files.exists(in)) throw new FileNotFoundException("Файл не найден");

        try (BufferedReader reader = Files.newBufferedReader(in)) {
            char[] buffer = new char[500];
            int read = reader.read(buffer);
            if (read == -1) return;

            String sample = new String(buffer, 0, read);
            System.out.println("\n=== ВАРИАНТЫ ДЕШИФРОВКИ ===");

            for (int key = 1; key < Alphabet.getSize(); key++) {
                StringBuilder sb = new StringBuilder();
                for (char c : sample.toCharArray()) {
                    sb.append(cipher.decryptChar(c, key));
                }
                String preview = sb.toString().replace("\n", " ").trim();
                System.out.printf("Ключ %d: %s...\n", key, preview.substring(0, Math.min(60, preview.length())));
            }
        }
    }
}
