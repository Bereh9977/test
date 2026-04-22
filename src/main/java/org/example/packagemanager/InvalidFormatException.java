package org.example.packagemanager;

public class InvalidFormatException extends ParserException
{
    public InvalidFormatException(String message, int lineNumber)
    {
        super(message, lineNumber);
    }
}
