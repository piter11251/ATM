package RestClasses;

import lombok.Getter;


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
