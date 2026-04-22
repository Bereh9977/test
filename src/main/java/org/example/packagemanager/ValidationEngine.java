package org.example.packagemanager;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ValidationEngine
{
    public boolean hasCycle(DependencyGraph graph)
    {

        Set<PackageId> visited = new HashSet<>();
        Set<PackageId> recursionStack = new HashSet<>();

        for (PackageId pkg : graph.getGraph().keySet())
        {
            if (detectCycle(pkg, graph, visited, recursionStack))
            {
                return true;
            }
        }

        return false;
    }

    private boolean detectCycle(
            PackageId pkg,
            DependencyGraph graph,
            Set<PackageId> visited,
            Set<PackageId> stack)
    {

        if (stack.contains(pkg))
        {
            return true;
        }

        if (visited.contains(pkg))
        {
            return false;
        }

        visited.add(pkg);
        stack.add(pkg);

        List<PackageId> dependencies = graph.getDependencies(pkg);

        for (PackageId dep : dependencies)
        {
            if (detectCycle(dep, graph, visited, stack))
            {
                return true;
            }
        }

        stack.remove(pkg);

        return false;
    }
}
