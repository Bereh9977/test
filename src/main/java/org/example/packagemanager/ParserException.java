package org.example.packagemanager;

public class ParserException extends PackageManagerException
{
    public ParserException(String message, int lineNumber)
    {
        super(message, lineNumber);
    }
}
