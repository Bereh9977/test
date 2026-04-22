package org.example.packagemanager;

public class FileSystemException extends PackageManagerException
{
    public FileSystemException(String message)
    {
        super(message, -1);
    }
}
