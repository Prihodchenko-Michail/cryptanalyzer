package io;

import crypto.CaesarCipher;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;

public class FileHandler {
    private final CaesarCipher cipher = new CaesarCipher();

    public void processFile(String inputPath, String outputPath, int key, boolean isEncrypt) throws IOException {
        Path in = Path.of(inputPath);
        Path out = Path.of(outputPath);

        if (!Files.exists(in)) {
            throw new FileNotFoundException("Файл не найден: " + inputPath);
        }

        try (BufferedReader reader = Files.newBufferedReader(in);
             BufferedWriter writer = Files.newBufferedWriter(out)) {

            int charCode;
            while ((charCode = reader.read()) != -1) {
                char current = (char) charCode;
                char processed = isEncrypt ? cipher.encryptChar(current, key) : cipher.decryptChar(current, key);
                writer.write(processed);
            }
        }
    }
}