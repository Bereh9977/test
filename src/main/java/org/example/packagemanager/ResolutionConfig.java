package org.example.packagemanager;

public class ResolutionConfig
{
    private String resolutionPolicy;

    public ResolutionConfig()
    {
        this.resolutionPolicy = "strict";
    }

    public String getResolutionPolicy()
    {
        return resolutionPolicy;
    }

    public void setResolutionPolicy(String resolutionPolicy)
    {
        this.resolutionPolicy = resolutionPolicy;
    }
}
