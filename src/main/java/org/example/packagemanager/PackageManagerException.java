package org.example.packagemanager;

public class PackageManagerException extends Exception
{
    private int lineNumber;

    public PackageManagerException(String message, int lineNumber)
    {
        super(message);
        this.lineNumber = lineNumber;
    }

    public int getLineNumber()
    {
        return lineNumber;
    }
}
