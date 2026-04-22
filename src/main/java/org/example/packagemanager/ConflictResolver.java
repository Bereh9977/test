package org.example.packagemanager;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ConflictResolver
{
    private final IResolutionStrategy strategy;

    public ConflictResolver(IResolutionStrategy strategy)
    {
        this.strategy = strategy;
    }

    public DependencyGraph resolveConflicts(DependencyGraph originalGraph) throws PackageManagerException
    {
        Map<String, List<PackageId>> groupedPackages = new HashMap<>();
        Map<String, PackageId> resolvedPackages = new HashMap<>();

        for (PackageId pkg : originalGraph.getGraph().keySet())
        {
            groupedPackages.putIfAbsent(pkg.getName(), new ArrayList<>());
            groupedPackages.get(pkg.getName()).add(pkg);
        }

        for (Map.Entry<String, List<PackageId>> entry : groupedPackages.entrySet())
        {
            PackageId resolved = strategy.resolve(entry.getKey(), entry.getValue());

            resolvedPackages.put(entry.getKey(), resolved);
        }

        DependencyGraph normalizedGraph = new DependencyGraph();

        for (PackageId originalPkg : originalGraph.getGraph().keySet())
        {
            PackageId resolvedPkg = resolvedPackages.get(originalPkg.getName());
            normalizedGraph.addPackage(resolvedPkg);
        }

        for (PackageId originalPkg : originalGraph.getGraph().keySet())
        {
            PackageId resolvedFrom = resolvedPackages.get(originalPkg.getName());

            for (PackageId dependency : originalGraph.getDependencies(originalPkg))
            {
                PackageId resolvedTo = resolvedPackages.get(dependency.getName());
                normalizedGraph.addDependency(resolvedFrom, resolvedTo);
            }
        }

        return normalizedGraph;
    }
}
