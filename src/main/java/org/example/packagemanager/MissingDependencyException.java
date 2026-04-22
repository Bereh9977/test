package org.example.packagemanager;

public class MissingDependencyException extends ValidationException
{
    public MissingDependencyException(String message, int lineNumber)
    {
        super(message, lineNumber);
    }
}
