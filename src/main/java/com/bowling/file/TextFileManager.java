package com.bowling.file;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;

/**
 * Text files manager
 *
 * @author Pedro Ferri
 */
public class TextFileManager implements FileManager {

    /**
     * Load all text file content into a List<String>
     *
     * @param pathname path of the text file
     * @return List<String> with all lines of the text file
     * @throws IOException If an error occurs when trying to upload the text file
     */
    @Override
    public List<String> loadFileContent(String pathname) throws IOException {
        return Files.readAllLines(new File(pathname).toPath());
    }
}
