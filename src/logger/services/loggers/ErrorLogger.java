package logger.services.loggers;

import java.io.IOException;

import logger.models.Priority;
import logger.services.writers.LogWriter;

public class ErrorLogger implements Logger {
    private Logger nextLogger;
    private final LogWriter logWriter;

    public ErrorLogger(LogWriter logWriter) {
        this.logWriter = logWriter;
    }

    @Override
    public void log(String message, Priority priority) throws IOException {
        if (priority == Priority.ERROR) {
            logWriter.write("ERROR> " + message);
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
