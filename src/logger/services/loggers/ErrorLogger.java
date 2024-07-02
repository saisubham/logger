package logger.services.loggers;

import java.io.IOException;

import logger.models.Priority;
import logger.services.writers.LogWriter;

public class ErrorLogger extends Logger {
    private final LogWriter logWriter;

    public ErrorLogger(LogWriter logWriter, Logger next) {
        super(next);
        this.logWriter = logWriter;
    }

    @Override
    public void log(String message, Priority priority) throws IOException {
        if (priority == Priority.ERROR) {
            logWriter.write("ERROR> " + message);
        } else {
            super.log(message, priority);
        }
    }

}
