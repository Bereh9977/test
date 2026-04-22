package org.example.packagemanager;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class DependencyGraph
{
    private Map<PackageId, List<PackageId>> graph;

    public DependencyGraph()
    {
        graph = new LinkedHashMap<>();
    }

    public void addPackage(PackageId pkg)
    {
        graph.putIfAbsent(pkg, new ArrayList<>());
    }

    public void addDependency(PackageId from, PackageId to)
    {
        graph.putIfAbsent(from, new ArrayList<>());
        graph.get(from).add(to);
    }

    public boolean containsPackage(PackageId pkg)
    {
        return graph.containsKey(pkg);
    }

    public List<PackageId> getDependencies(PackageId pkg)
    {
        return graph.getOrDefault(pkg, new ArrayList<>());
    }

    public Map<PackageId, List<PackageId>> getGraph()
    {
        return graph;
    }
}
