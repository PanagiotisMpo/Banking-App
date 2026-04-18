package app;

import java.util.Scanner;

public class StandardConsole implements Console {
    private final Scanner scanner;

    public StandardConsole(Scanner scanner) {
        this.scanner = scanner;
    }

    @Override
    public void printMessage(String message) {
        System.out.println(message);
    }

    @Override
    public void printError(String message) {
        System.out.println(message);
    }

    @Override
    public String readLine() {
        return scanner.nextLine();
    }
}
