package org.example.packagemanager;

public class ResolutionStrategyFactory
{
    public IResolutionStrategy createStrategy(ResolutionConfig config)
    {
        String policy = config.getResolutionPolicy();

        if ("highest".equalsIgnoreCase(policy))
        {
            return new HighestVersionStrategy();
        }

        if ("first".equalsIgnoreCase(policy))
        {
            return new FirstEncounteredStrategy();
        }

        return new StrictStrategy();
    }
}
