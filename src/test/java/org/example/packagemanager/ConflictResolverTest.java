package org.example.packagemanager;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ConflictResolverTest
{
    @Test
    void shouldResolveGraphUsingHighestVersionStrategy()
            throws PackageManagerException
    {
        InputParser parser = new InputParser();

        List<String> lines = List.of(
                "package: app, v1.0",
                "requires: logger, v1.0",
                "package: admin, v1.0",
                "requires: logger, v2.0",
                "package: logger, v1.0",
                "package: logger, v2.0"
        );

        DependencyGraph originalGraph = parser.parse(lines);

        ConflictResolver resolver = new ConflictResolver(
                new HighestVersionStrategy()
        );

        DependencyGraph resolvedGraph = resolver.resolveConflicts(originalGraph);
        ResolverEngine engine = new ResolverEngine();
        List<PackageId> order = engine.topologicalSort(resolvedGraph);

        long loggerCount = order.stream()
                .filter(pkg -> "logger".equals(pkg.getName()))
                .count();

        PackageId loggerPackage = order.stream()
                .filter(pkg -> "logger".equals(pkg.getName()))
                .findFirst()
                .orElseThrow();

        assertEquals(1, loggerCount);
        assertEquals("v2.0", loggerPackage.getVersion());
    }

    @Test
    void shouldResolveGraphUsingFirstEncounteredStrategy()
            throws PackageManagerException
    {
        InputParser parser = new InputParser();

        List<String> lines = List.of(
                "package: app, v1.0",
                "requires: logger, v1.0",
                "package: admin, v1.0",
                "requires: logger, v2.0",
                "package: logger, v1.0",
                "package: logger, v2.0"
        );

        DependencyGraph originalGraph = parser.parse(lines);

        ConflictResolver resolver = new ConflictResolver(
                new FirstEncounteredStrategy()
        );

        DependencyGraph resolvedGraph = resolver.resolveConflicts(originalGraph);
        ResolverEngine engine = new ResolverEngine();
        List<PackageId> order = engine.topologicalSort(resolvedGraph);

        long loggerCount = order.stream()
                .filter(pkg -> "logger".equals(pkg.getName()))
                .count();

        PackageId loggerPackage = order.stream()
                .filter(pkg -> "logger".equals(pkg.getName()))
                .findFirst()
                .orElseThrow();

        assertEquals(1, loggerCount);
        assertEquals("v1.0", loggerPackage.getVersion());
    }

    @Test
    void shouldThrowVersionConflictExceptionInStrictMode()
            throws PackageManagerException
    {
        InputParser parser = new InputParser();

        List<String> lines = List.of(
                "package: app, v1.0",
                "requires: logger, v1.0",
                "package: admin, v1.0",
                "requires: logger, v2.0",
                "package: logger, v1.0",
                "package: logger, v2.0"
        );

        DependencyGraph originalGraph = parser.parse(lines);

        ConflictResolver resolver = new ConflictResolver(
                new StrictStrategy()
        );

        assertThrows(
                VersionConflictException.class,
                () -> resolver.resolveConflicts(originalGraph)
        );
    }
}
