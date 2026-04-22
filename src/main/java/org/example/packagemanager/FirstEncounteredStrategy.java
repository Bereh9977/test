package org.example.packagemanager;

import java.util.List;

public class FirstEncounteredStrategy implements IResolutionStrategy
{
    @Override
    public PackageId resolve(
            String packageName,
            List<PackageId> candidates
    ) throws PackageManagerException
    {
        if (candidates.isEmpty())
        {
            throw new VersionConflictException("No candidates found for package: " + packageName, -1);
        }

        return candidates.get(0);
    }
}
