package logger.services.writers;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class FileLogWriter implements LogWriter {

    private final BufferedWriter bufferedWriter;
    private final ReentrantReadWriteLock lock;

    public FileLogWriter(File file) throws IOException {
        this.bufferedWriter = new BufferedWriter(new FileWriter(file));
        lock = new ReentrantReadWriteLock(true);
    }

    @Override
    public void write(String message) throws IOException {
        lock.writeLock().lock();
        try {
            bufferedWriter.append(Thread.currentThread().getName())
                    .append(": ")
                    .append(String.valueOf(LocalDateTime.now()))
                    .append(": ")
                    .append(message);
            bufferedWriter.newLine();
            bufferedWriter.flush();
        } finally {
            lock.writeLock().unlock();
        }
    }
}
