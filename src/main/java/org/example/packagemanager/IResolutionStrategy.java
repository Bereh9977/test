package org.example.packagemanager;

import java.util.List;

public interface IResolutionStrategy
{
    PackageId resolve(String packageName, List<PackageId> candidates) throws PackageManagerException;
}
