package logger.services.writers;

import java.io.IOException;

public interface LogWriter {
    void write(String message) throws IOException;
}
