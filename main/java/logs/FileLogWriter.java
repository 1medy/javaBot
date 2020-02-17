package logs;

import java.io.File;


public class FileLogWriter extends AbstractLogWriter {

    private final File file;

    public FileLogWriter(File path, boolean save) {
        super(save);
        file = path;
    }

    @Override
    public void writeLogs(String... args) {
        throw new UnsupportedOperationException("Not supported yet");
    }
}