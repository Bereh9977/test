package org.example.packagemanager;

import java.util.Objects;

public class PackageId
{
    private String name;
    private String version;

    public PackageId(String name, String version)
    {
        this.name = name;
        this.version = version;
    }

    public String getName()
    {
        return name;
    }

    public String getVersion()
    {
        return version;
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (!(o instanceof PackageId)) return false;
        PackageId that = (PackageId) o;
        return name.equals(that.name) && version.equals(that.version);
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(name, version);
    }
}
