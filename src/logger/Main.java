package logger;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Random;
import java.util.UUID;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import logger.models.Priority;
import logger.services.loggers.ErrorLogger;
import logger.services.loggers.InfoLogger;
import logger.services.loggers.Logger;
import logger.services.loggers.WarnLogger;
import logger.services.writers.FileLogWriter;
import logger.services.writers.LogWriter;

import static logger.models.Priority.ERROR;
import static logger.models.Priority.INFO;
import static logger.models.Priority.WARN;

public class Main {

    private static final Random RANDOM = new Random();
    private static final List<Priority> PRIORITY_LIST = List.of(INFO, WARN, ERROR);

    public static void main(String[] args) throws IOException {
        final LogWriter fileWriter = new FileLogWriter(new File("./src/logger/log.txt"));
        final Logger logger = getLogger(fileWriter);
        final ExecutorService executorService = Executors.newFixedThreadPool(10);
        final Runnable loggerTask = getLoggerTask(logger);

        for (int i = 0; i < 1_000; ++i) {
            executorService.submit(loggerTask);
        }
        executorService.shutdown();
        try {
            if (!executorService.awaitTermination(60, TimeUnit.SECONDS)) {
                executorService.shutdownNow();
            }
        } catch (InterruptedException e) {
            executorService.shutdownNow();
        }
        System.out.println("all tasks completed");
    }

    private static Logger getLogger(LogWriter writer) {
        return new InfoLogger(writer, new WarnLogger(writer, new ErrorLogger(writer, null)));
    }

    private static Runnable getLoggerTask(Logger logger) {
        return () -> {
            try {
                int r = RANDOM.nextInt(3);
                logger.log(UUID.randomUUID().toString(), PRIORITY_LIST.get(r));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        };
    }
}
