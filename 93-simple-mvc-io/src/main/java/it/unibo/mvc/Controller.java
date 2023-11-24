package it.unibo.mvc;

import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;

/**
 * Application controller. Performs the I/O.
 */
public class Controller {
    private static final String SEP = File.separator;
    private static final String DEFAULT_PATH = System.getProperty("user.home") + SEP + "output.txt";

    private File file;

    public Controller() {
        this.file = new File(DEFAULT_PATH);
    }

    public File getFile() {
        return file;
    }

    public void setFile(File file) {
        this.file = file;
    }

    public String getFilePath() {
        return this.file.getPath();
    }

    public void saveString(final String string) throws IOException {
        try (final PrintStream ps = new PrintStream(file, StandardCharsets.UTF_8)) {
            ps.println(string);
        }
    }

}
