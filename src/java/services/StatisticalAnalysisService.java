package services;

import crypto.Alphabet;
import io.FileHandler;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;

public class StatisticalAnalysisService {
    private final FileHandler fileHandler = new FileHandler();

    public int autoDecrypt(String inputPath, String outputPath) throws IOException {
        Path in = Path.of(inputPath);
        if (!Files.exists(in)) throw new FileNotFoundException("Файл не найден");

        int[] charCounts = new int[Alphabet.getSize()];
        try (BufferedReader reader = Files.newBufferedReader(in)) {
            int charCode;
            while ((charCode = reader.read()) != -1) {
                int index = Alphabet.indexOf((char) charCode);
                if (index != -1) {
                    charCounts[index]++;
                }
            }
        }

        int maxCountIndex = 0;
        for (int i = 1; i < charCounts.length; i++) {
            if (charCounts[i] > charCounts[maxCountIndex]) {
                maxCountIndex = i;
            }
        }

        int spaceIndex = Alphabet.indexOf(' ');
        int estimatedKey = maxCountIndex - spaceIndex;
        int actualKey = ((estimatedKey % Alphabet.getSize()) + Alphabet.getSize()) % Alphabet.getSize();

        fileHandler.processFile(inputPath, outputPath, actualKey, false);
        return actualKey;
    }
}
