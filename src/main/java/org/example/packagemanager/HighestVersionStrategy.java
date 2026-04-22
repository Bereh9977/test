package org.example.packagemanager;

import java.util.List;

public class HighestVersionStrategy implements IResolutionStrategy
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

        PackageId highest = candidates.get(0);

        for (PackageId candidate : candidates)
        {
            if (compareVersions(
                    candidate.getVersion(),
                    highest.getVersion()
            ) > 0)
            {
                highest = candidate;
            }
        }

        return highest;
    }

    private int compareVersions(String firstVersion, String secondVersion)
    {
        String[] firstParts = firstVersion.substring(1).split("\\.");
        String[] secondParts = secondVersion.substring(1).split("\\.");

        int firstMajor = Integer.parseInt(firstParts[0]);
        int firstMinor = Integer.parseInt(firstParts[1]);

        int secondMajor = Integer.parseInt(secondParts[0]);
        int secondMinor = Integer.parseInt(secondParts[1]);

        if (firstMajor != secondMajor)
        {
            return Integer.compare(firstMajor, secondMajor);
        }

        return Integer.compare(firstMinor, secondMinor);
    }
}
