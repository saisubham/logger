package logger.services.loggers;

import java.io.IOException;

import logger.models.Priority;
import logger.services.writers.LogWriter;

public class WarnLogger extends Logger {
    private final LogWriter logWriter;

    public WarnLogger(LogWriter logWriter, Logger next) {
        super(next);
        this.logWriter = logWriter;
    }

    @Override
    public void log(String message, Priority priority) throws IOException {
        if (priority == Priority.WARN) {
            logWriter.write("WARN> " + message);
        } else {
            super.log(message, priority);
        }
    }
}
