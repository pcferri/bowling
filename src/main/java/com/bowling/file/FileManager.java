package com.bowling.file;

import java.io.IOException;
import java.util.List;

/**
 * Interface for manage files
 *
 * @author Pedro Ferri
 */
public interface FileManager {

    /**
     * Load all file content into a List<String>
     *
     * @param pathname path of the file
     * @return List<String> with all lines of the file
     * @throws IOException If an error occurs when trying to upload the file
     */
    List<String> loadFileContent(String pathname) throws IOException;
}
