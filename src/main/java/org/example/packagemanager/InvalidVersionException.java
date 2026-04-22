package org.example.packagemanager;

public class InvalidVersionException extends ParserException
{
    public InvalidVersionException(String message, int lineNumber)
    {
        super(message, lineNumber);
    }
}
