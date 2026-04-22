package org.example.packagemanager;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ResolutionStrategyTest
{
    @Test
    void shouldThrowVersionConflictExceptionInStrictStrategy()
    {
        IResolutionStrategy strategy = new StrictStrategy();

        List<PackageId> candidates = List.of(
                new PackageId("logger", "v1.0"),
                new PackageId("logger", "v2.0")
        );

        assertThrows(
                VersionConflictException.class,
                () -> strategy.resolve("logger", candidates)
        );
    }

    @Test
    void shouldReturnHighestVersionInHighestVersionStrategy()
            throws PackageManagerException
    {
        IResolutionStrategy strategy = new HighestVersionStrategy();

        List<PackageId> candidates = List.of(
                new PackageId("logger", "v1.0"),
                new PackageId("logger", "v2.0"),
                new PackageId("logger", "v1.5")
        );

        PackageId result = strategy.resolve("logger", candidates);

        assertEquals("logger", result.getName());
        assertEquals("v2.0", result.getVersion());
    }

    @Test
    void shouldReturnFirstVersionInFirstEncounteredStrategy()
            throws PackageManagerException
    {
        IResolutionStrategy strategy = new FirstEncounteredStrategy();

        List<PackageId> candidates = List.of(
                new PackageId("logger", "v1.0"),
                new PackageId("logger", "v2.0")
        );

        PackageId result = strategy.resolve("logger", candidates);

        assertEquals("logger", result.getName());
        assertEquals("v1.0", result.getVersion());
    }
}
