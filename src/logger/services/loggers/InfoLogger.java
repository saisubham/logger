package logger.services.loggers;

import java.io.IOException;

import logger.models.Priority;
import logger.services.writers.LogWriter;

public class InfoLogger extends Logger {
    private final LogWriter logWriter;

    public InfoLogger(LogWriter logWriter, Logger next) {
        super(next);
        this.logWriter = logWriter;
    }

    @Override
    public void log(String message, Priority priority) throws IOException {
        if (priority == Priority.INFO) {
            logWriter.write("INFO> " + message);
        } else {
            super.log(message, priority);
        }
    }
}
