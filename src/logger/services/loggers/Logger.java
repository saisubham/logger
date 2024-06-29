package logger.services.loggers;

import java.io.IOException;

import logger.models.Priority;

public interface Logger {
    void log(String message, Priority priority) throws IOException;

    void setNextLogger(Logger nextLogger);
}
