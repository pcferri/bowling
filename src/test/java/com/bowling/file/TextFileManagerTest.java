package com.bowling.file;

import org.junit.Test;

import java.io.IOException;
import java.net.URL;
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
        URL url = classLoader.getResource("perfect-score.txt");
        if(url == null){
            throw new IOException();
        }
        List<String> content = file.loadFileContent(url.getPath());

        assertEquals(12, content.size());
    }
}