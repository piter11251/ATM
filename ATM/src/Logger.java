import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Logger {
    public static void log(String message){
        DateTimeFormatter format = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        String date_ = now.format(format);
        LogEntry log = new LogEntry(date_, message);

        System.out.println(log.getTimestamp()+" "+log.getMessage());
    }

}
