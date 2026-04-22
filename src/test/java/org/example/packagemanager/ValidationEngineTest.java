package org.example.packagemanager;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ValidationEngineTest
{
    @Test
    void shouldDetectNoCycle() throws PackageManagerException
    {
        InputParser parser = new InputParser();

        List<String> lines = List.of(
                "package: app, v1.0",
                "requires: core, v1.0",
                "package: core, v1.0"
        );

        DependencyGraph graph = parser.parse(lines);

        ValidationEngine validator = new ValidationEngine();

        assertFalse(validator.hasCycle(graph));
    }

    @Test
    void shouldDetectCycle() throws PackageManagerException
    {
        InputParser parser = new InputParser();

        List<String> lines = List.of(
                "package: app, v1.0",
                "requires: core, v1.0",
                "package: core, v1.0",
                "requires: app, v1.0"
        );

        DependencyGraph graph = parser.parse(lines);

        ValidationEngine validator = new ValidationEngine();

        assertTrue(validator.hasCycle(graph));
    }
}
