package com.bowling.file;

import org.junit.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * Cover the main actions of the TextFileManager class
 *
 * @author Pedro Ferri
 */
public class TextFileManagerTest {

    private final FileManager file = new TextFileManager();

    @Test(expected = IOException.class)
    public void whenLoadAWrongFile_thenExceptThrow() throws IOException {
        file.loadFileContent("test.txt");
    }

    @Test()
    public void whenLoadAFileTest_thenCheckSize() throws IOException {
        ClassLoader classLoader = getClass().getClassLoader();
        List<String> content = file.loadFileContent(classLoader.getResource("perfect-score.txt").getPath());

        assertEquals(12, content.size());
    }
}