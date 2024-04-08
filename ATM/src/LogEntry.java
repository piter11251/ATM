import lombok.Getter;

import java.time.LocalDateTime;

public class LogEntry {
    @Getter
    String timestamp;
    @Getter
    String message;

    public LogEntry(String timestamp, String message){
        this.timestamp = timestamp;
        this.message = message;
    }
}
