package Project;

import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Hashtable;
import java.sql.Date;

public final class Book extends LibraryResource{

    private String author;
    private String subject;
    private ResourceType type;
    static int CountBooks = 0;

    public Book()
    {
        super();
        this.type = ResourceType.BOOK;
        this.author = null;
        this.subject = null;
        CountBooks++;
    }

    public Book(String ResourceName, boolean available, java.sql.Date BorrowDate, String author, String subject) {
        super(ResourceName, available, BorrowDate);
        this.author = author;
        this.subject = subject;
        this.type = ResourceType.BOOK;
        CountBooks++;
    }

    public String toString(){
        return String.format("%-30s | %10s | %5s | %10s | %-25s | %15s", this.getResourceName(), type, isAvailable(), getBorrowDate(), "Autor: "+author, "Subiect: "+subject);
    }

    public String GetResourceType() {
        return ("Book");
    }

    public String Search() {
        return this.subject;
    }
}
