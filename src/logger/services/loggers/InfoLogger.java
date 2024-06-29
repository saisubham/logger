package logger.services.loggers;

import java.io.IOException;

import logger.models.Priority;
import logger.services.writers.LogWriter;

public class InfoLogger implements Logger {
    private Logger nextLogger;
    private final LogWriter logWriter;

    public InfoLogger(LogWriter logWriter) {
        this.logWriter = logWriter;
    }

    @Override
    public void log(String message, Priority priority) throws IOException {
        if (priority == Priority.INFO) {
            logWriter.write("INFO> " + message);
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
