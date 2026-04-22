package org.example.packagemanager;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class ConfigReader
{
    public ResolutionConfig readConfig(String path)
    {
        ResolutionConfig config = new ResolutionConfig();

        try
        {
            String content = Files.readString(Path.of(path));

            if (content.contains("\"resolution_policy\""))
            {
                if (content.contains("\"strict\""))
                {
                    config.setResolutionPolicy("strict");
                }
                else if (content.contains("\"highest\""))
                {
                    config.setResolutionPolicy("highest");
                }
                else if (content.contains("\"first\""))
                {
                    config.setResolutionPolicy("first");
                }
            }
        }
        catch (IOException e)
        {
            return config;
        }

        return config;
    }
}
