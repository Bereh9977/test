package org.example.packagemanager;

import java.util.List;

public class ReportGenerator
{
    public void printInstallOrder(List<PackageId> order)
    {
        System.out.println("Installation order:");

        for (PackageId pkg : order)
        {
            System.out.println(pkg.getName() + " " + pkg.getVersion());
        }
    }

    public void printCycleError()
    {
        System.out.println("Error: cyclic dependency detected.");
    }
}
