package org.example.packagemanager;

import org.junit.jupiter.api.Test;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class InputParserTest
{
    @Test
    void shouldParseValidInput() throws PackageManagerException
    {
        InputParser parser = new InputParser();

        List<String> lines = List.of(
                "package: app, v1.0",
                "requires: core, v1.0",
                "package: core, v1.0"
        );

        DependencyGraph graph = parser.parse(lines);

        assertNotNull(graph);
        assertEquals(2, graph.getGraph().size());
    }

    @Test
    void shouldThrowInvalidVersionExceptionForWrongVersion()
    {
        InputParser parser = new InputParser();

        List<String> lines = List.of(
                "package: app, v1.x"
        );

        InvalidVersionException exception = assertThrows(
                InvalidVersionException.class,
                () -> parser.parse(lines)
        );

        assertEquals(1, exception.getLineNumber());
    }

    @Test
    void shouldThrowInvalidFormatExceptionForWrongPackageFormat()
    {
        InputParser parser = new InputParser();

        List<String> lines = List.of(
                "package: app"
        );

        InvalidFormatException exception = assertThrows(
                InvalidFormatException.class,
                () -> parser.parse(lines)
        );

        assertEquals(1, exception.getLineNumber());
    }

    @Test
    void shouldThrowInvalidFormatExceptionWhenRequiresComesFirst()
    {
        InputParser parser = new InputParser();

        List<String> lines = List.of(
                "requires: core, v1.0",
                "package: app, v1.0"
        );

        InvalidFormatException exception = assertThrows(
                InvalidFormatException.class,
                () -> parser.parse(lines)
        );

        assertEquals(1, exception.getLineNumber());
    }

    @Test
    void shouldThrowMissingDependencyExceptionForUnknownDependency()
    {
        InputParser parser = new InputParser();

        List<String> lines = List.of(
                "package: app, v1.0",
                "requires: core, v1.0"
        );

        MissingDependencyException exception = assertThrows(
                MissingDependencyException.class,
                () -> parser.parse(lines)
        );

        assertEquals(2, exception.getLineNumber());
    }
}
