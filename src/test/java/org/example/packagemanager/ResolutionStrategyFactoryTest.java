package org.example.packagemanager;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertInstanceOf;

public class ResolutionStrategyFactoryTest
{
    @Test
    void shouldCreateStrictStrategy()
    {
        ResolutionConfig config = new ResolutionConfig();
        config.setResolutionPolicy("strict");

        ResolutionStrategyFactory factory = new ResolutionStrategyFactory();
        IResolutionStrategy strategy = factory.createStrategy(config);

        assertInstanceOf(StrictStrategy.class, strategy);
    }

    @Test
    void shouldCreateHighestVersionStrategy()
    {
        ResolutionConfig config = new ResolutionConfig();
        config.setResolutionPolicy("highest");

        ResolutionStrategyFactory factory = new ResolutionStrategyFactory();
        IResolutionStrategy strategy = factory.createStrategy(config);

        assertInstanceOf(HighestVersionStrategy.class, strategy);
    }

    @Test
    void shouldCreateFirstEncounteredStrategy()
    {
        ResolutionConfig config = new ResolutionConfig();
        config.setResolutionPolicy("first");

        ResolutionStrategyFactory factory = new ResolutionStrategyFactory();
        IResolutionStrategy strategy = factory.createStrategy(config);

        assertInstanceOf(FirstEncounteredStrategy.class, strategy);
    }

    @Test
    void shouldFallbackToStrictStrategyForUnknownPolicy()
    {
        ResolutionConfig config = new ResolutionConfig();
        config.setResolutionPolicy("unknown");

        ResolutionStrategyFactory factory = new ResolutionStrategyFactory();
        IResolutionStrategy strategy = factory.createStrategy(config);

        assertInstanceOf(StrictStrategy.class, strategy);
    }
}
