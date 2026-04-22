package org.example.packagemanager;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class InputReader
{
    public List<String> readFile(String path) throws FileSystemException
    {
        try
        {
            return Files.readAllLines(Path.of(path));
        }
        catch (IOException e)
        {
            throw new FileSystemException("Cannot read file: " + path);
        }
    }
}
