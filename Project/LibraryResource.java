package Project;

import java.sql.Date;
import java.time.Clock;
import java.time.LocalDate;

public abstract class LibraryResource implements Comparable<LibraryResource> {
    //implements Comparable<LibraryResource>

    protected enum ResourceType{BOOK, DICTIONARY, MAGAZINE};

    private String ResourceName;
    private boolean available;
    java.sql.Date BorrowDate;

    static int CountResources = 0;

    public LibraryResource() {
        this.ResourceName = null;
        this.available = true;
        this.BorrowDate = null;
        CountResources++;
    }

    public LibraryResource(String ResourceName, boolean available, java.sql.Date BorrowDate) {
        this.ResourceName = ResourceName;
        this.available = available;
        this.BorrowDate = BorrowDate;
        CountResources++;
    }

    public String getResourceName() {
        return ResourceName;
    }

    public Date getBorrowDate() {
        return BorrowDate;
    }

    public boolean isAvailable() {
        return available;
    }

    public boolean SearchDate(Date SearchDate) {
        if(this.BorrowDate == null) return false;
        if (this.BorrowDate.equals(SearchDate)) return true;
            else return false;
    }

    public int compareTo(LibraryResource n) {
        int nameCmp = ResourceName.compareTo(n.ResourceName);
        return nameCmp;
    }

    public abstract String toString();

    public abstract String GetResourceType();

    public abstract String Search();
}
