package logger.services.loggers;

import java.io.IOException;

import logger.models.Priority;
import logger.services.writers.LogWriter;

public class WarnLogger implements Logger {
    private Logger nextLogger;
    private final LogWriter logWriter;

    public WarnLogger(LogWriter logWriter) {
        this.logWriter = logWriter;
    }

    @Override
    public void log(String message, Priority priority) throws IOException {
        if (priority == Priority.WARN) {
            logWriter.write("WARN> " + message);
            return;
        }
        if (nextLogger != null) {
            nextLogger.log(message, priority);
            return;
        }
        throw new RuntimeException("cannot handle");
    }

    @Override
    public void setNextLogger(Logger nextLogger) {
        this.nextLogger = nextLogger;
    }
}
