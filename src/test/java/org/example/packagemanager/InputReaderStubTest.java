package org.example.packagemanager;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class InputReaderStubTest
{
    @Test
    void shouldUseStubReaderInsteadOfRealFile() throws PackageManagerException
    {
        InputReader reader = new StubInputReader();
        InputParser parser = new InputParser();

        List<String> lines = reader.readFile("ignored.txt");
        DependencyGraph graph = parser.parse(lines);

        assertEquals(2, graph.getGraph().size());
    }

    private static class StubInputReader extends InputReader
    {
        @Override
        public List<String> readFile(String path)
        {
            return List.of(
                    "package: app, v1.0",
                    "requires: core, v1.0",
                    "package: core, v1.0"
            );
        }
    }
}
