package Project;

public final class Dictionary extends LibraryResource{

    private String DictionaryType;
    private int PagesNumber;
    private ResourceType type;

    static int CountDictionaries = 0;

    public Dictionary()
    {
        super();
        this.type = ResourceType.DICTIONARY;
        this.DictionaryType = null;
        this.PagesNumber = 0;
        CountDictionaries++;
    }

    public Dictionary(String ResourceName, boolean available, java.sql.Date BorrowDate, String dictionaryType, int pagesNumber) {
        super(ResourceName, available, BorrowDate);
        this.DictionaryType = dictionaryType;
        this.PagesNumber = pagesNumber;
        this.type = ResourceType.DICTIONARY;
        CountDictionaries++;
    }

    public String toString(){
        return String.format("%-30s | %10s | %5s | %10s | %-25s | %15s", this.getResourceName(), type, isAvailable(), getBorrowDate(), "Tip dictionar: "+DictionaryType, "No pagini: "+PagesNumber);
    }

    public String Search() {
        return this.DictionaryType;
    }

    public String GetResourceType() {
        return ("Dictionary");
    }
}
