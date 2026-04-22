package org.example.packagemanager;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ResolverEngineTest
{
    @Test
    void shouldReturnCorrectInstallationOrder() throws PackageManagerException
    {
        InputParser parser = new InputParser();

        List<String> lines = List.of(
                "package: app, v1.0",
                "requires: core, v1.0",
                "package: core, v1.0"
        );

        DependencyGraph graph = parser.parse(lines);

        ResolverEngine resolver = new ResolverEngine();
        List<PackageId> order = resolver.topologicalSort(graph);

        assertEquals(2, order.size());
        assertEquals("core", order.get(0).getName());
        assertEquals("app", order.get(1).getName());
    }
}
