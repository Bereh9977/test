package org.example.packagemanager;

public class VersionConflictException extends ValidationException
{
    public VersionConflictException(String message, int lineNumber)
    {
        super(message, lineNumber);
    }
}
