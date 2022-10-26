package Project;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

public class File {
    java.io.File fisier;
    String denumire;

    public File(String den)
    {
        denumire = den;
    }

    public void open()
    {
        fisier = new java.io.File(denumire);
    }

    public FileOutputStream getOS() throws FileNotFoundException
    {
        return new FileOutputStream(fisier);
    }
}
