package org.example.packagemanager;

public class ValidationException extends PackageManagerException
{
    public ValidationException(String message, int lineNumber)
    {
        super(message, lineNumber);
    }
}
