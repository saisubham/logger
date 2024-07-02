package logger.services.loggers;

import java.io.IOException;

import logger.models.Priority;

public class Logger {

    private final Logger next;

    public Logger(Logger next) {
        this.next = next;
    }

    public void log(String message, Priority priority) throws IOException {
        if (next != null) {
            next.log(message, priority);
        }
    }
}
