package app;

public interface Console {
    void printMessage(String message);

    void printError(String message);

    String readLine();
}
