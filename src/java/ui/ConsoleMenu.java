package ui;

import io.FileHandler;
import services.BruteForceService;
import services.StatisticalAnalysisService;
import java.io.IOException;
import java.util.Scanner;

public class ConsoleMenu {
    private final FileHandler fileHandler = new FileHandler();
    private final BruteForceService bruteForceService = new BruteForceService();
    private final StatisticalAnalysisService statService = new StatisticalAnalysisService();
    private final Scanner scanner = new Scanner(System.in);

    public void run() {
        System.out.println("=== КРИПТОАНАЛИЗАТОР ===");
        System.out.println("1. Зашифровать файл");
        System.out.println("2. Расшифровать файл с известным ключом");
        System.out.println("3. Взлом методом Brute Force");
        System.out.println("4. Взлом Статистическим анализом");
        System.out.println("0. Выход");
        System.out.print("Выберите действие: ");

        int choice = scanner.nextInt();
        scanner.nextLine();

        if (choice == 0) return;

        try {
            System.out.print("Введите путь к исходному файлу: ");
            String input = scanner.nextLine();

            String output = "";
            if (choice != 3) {
                System.out.print("Введите путь для сохранения результата: ");
                output = scanner.nextLine();
            }

            switch (choice) {
                case 1 -> {
                    System.out.print("Введите ключ: ");
                    int key = scanner.nextInt();
                    fileHandler.processFile(input, output, key, true);
                    System.out.println("Файл успешно зашифрован!");
                }
                case 2 -> {
                    System.out.print("Введите ключ: ");
                    int key = scanner.nextInt();
                    fileHandler.processFile(input, output, key, false);
                    System.out.println("Файл успешно расшифрован!");
                }
                case 3 -> bruteForceService.bruteForceToConsole(input);
                case 4 -> {
                    int key = statService.autoDecrypt(input, output);
                    System.out.println("Файл взломан! Подобранный ключ: " + key);
                }
                default -> System.out.println("Неверный выбор.");
            }
        } catch (IOException e) {
            System.err.println("Ошибка ввода-вывода: " + e.getMessage());
        }
    }
}
