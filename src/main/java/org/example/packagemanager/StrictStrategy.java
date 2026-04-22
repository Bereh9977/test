package org.example.packagemanager;

import java.util.List;

public class StrictStrategy implements IResolutionStrategy
{
    @Override
    public PackageId resolve(
            String packageName,
            List<PackageId> candidates
    ) throws PackageManagerException
    {
        if (candidates.isEmpty())
        {
            throw new VersionConflictException(
                    "No candidates found for package: " + packageName,
                    -1
            );
        }

        if (candidates.size() > 1)
        {
            throw new VersionConflictException(
                    "Version conflict for package: " + packageName,
                    -1
            );
        }

        return candidates.get(0);
    }
}
