package Project;

import java.sql.Date;
import java.time.Clock;
import java.time.LocalDate;

public final class ScientificMagazine extends LibraryResource {

    private String ScientificTopic;
    private String Publisher;
    private ResourceType type;

    static int CountScientificMagazines = 0;

    public ScientificMagazine()
    {
        super();
        this.type = ResourceType.MAGAZINE;
        this.ScientificTopic = null;
        this.Publisher = null;
        CountScientificMagazines++;
    }

    public ScientificMagazine(String ResourceName, boolean available, java.sql.Date BorrowDate, String ScientificTopic, String Publisher)
    {
        super(ResourceName, available, BorrowDate);
        this.type = ResourceType.MAGAZINE;
        this.ScientificTopic = ScientificTopic;
        this.Publisher = Publisher;
        CountScientificMagazines++;
    }

    public String toString() {
        return String.format("%-30s | %10s | %5s | %10s | %-40s | %15s", this.getResourceName(), type, isAvailable(), getBorrowDate(), "Domeniu științific: "+ScientificTopic, "Editor: "+Publisher);
    }

    public String Search() {
        return this.ScientificTopic;
    }

    public String GetResourceType() {
        return ("Scientific Magazine");
    }
}
