package org.example.packagemanager;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ConfigReaderTest
{
    @Test
    void shouldReadHighestPolicyFromConfigFile()
            throws IOException
    {
        Path tempFile = Files.createTempFile("config", ".json");
        Files.writeString(
                tempFile,
                "{ \"resolution_policy\": \"highest\" }"
        );

        ConfigReader reader = new ConfigReader();
        ResolutionConfig config = reader.readConfig(tempFile.toString());

        assertEquals("highest", config.getResolutionPolicy());

        Files.deleteIfExists(tempFile);
    }

    @Test
    void shouldUseStrictPolicyWhenFileIsMissing()
    {
        ConfigReader reader = new ConfigReader();
        ResolutionConfig config = reader.readConfig("missing-config.json");

        assertEquals("strict", config.getResolutionPolicy());
    }
}
