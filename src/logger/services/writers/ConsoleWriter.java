package logger.services.writers;

import java.io.IOException;
import java.time.LocalDateTime;

public class ConsoleWriter implements LogWriter {
    @Override
    public void write(String message) throws IOException {
        System.out.println(LocalDateTime.now() + ": " + message);
    }
}
