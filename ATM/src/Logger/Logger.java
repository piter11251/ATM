package Logger;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Logger {
    public static void log(String message) throws IOException {
        DateTimeFormatter format = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        String date_ = now.format(format);
        LogEntry log = new LogEntry(date_, message);

        try (PrintWriter logFile = new PrintWriter(new FileWriter("logs.txt", true))) {
            logFile.println(log.getTimestamp() + " " + log.getMessage());
        } catch (IOException e) {
            System.err.println("Błąd podczas zapisu logów do pliku: " + e.getMessage());
        }
    }
}
