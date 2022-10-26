package Project;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.sql.*;
import java.sql.Date;
import java.text.ParseException;
import java.time.Clock;
import java.time.LocalDate;
import java.util.*;

import Project.Book;
import java.time.Clock;
import java.text.SimpleDateFormat;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;

public class Library {

    private ArrayList<LibraryResource> resources;

    Library() {
        resources = new ArrayList<LibraryResource>();
    }

    private String url = "jdbc:mysql://localhost:3306/library";
    public Connection con;
    public Statement instr;

    public void Connect() throws SQLException
    {
        con = DriverManager.getConnection(url, "root",
                "root");
        instr = con.createStatement();
        System.out.println("Conectare reusita!");
    }

    public void LoadData() throws SQLException{
        String sql = "SELECT * FROM library";
        ResultSet rs = instr.executeQuery(sql);
        while (rs.next()) {
            String ResourceType = rs.getString("ResourceType");

            if (ResourceType.equals("Book")) {
                String ResourceName = rs.getString("Name");
                boolean available = (Boolean) rs.getObject("available");
                java.sql.Date BorrowDate = rs.getDate("BorrowDate");
                String author = rs.getString("Author");
                String subject = rs.getString("Subject");
                LibraryResource.ResourceType type = LibraryResource.ResourceType.BOOK;

                Book CurrentBook = new Book(ResourceName, available, BorrowDate, author, subject);
                resources.add(CurrentBook);
            }

            if (ResourceType.equals("Dictionary")) {
                String ResourceName = rs.getString("Name");
                boolean available = (Boolean) rs.getObject("available");
                java.sql.Date BorrowDate = rs.getDate("BorrowDate");
                String DictionaryType = rs.getString("DictionaryType");
                int NumberPages = rs.getInt("NumberPages");
                LibraryResource.ResourceType type = LibraryResource.ResourceType.DICTIONARY;

                Dictionary CurrentDictionary = new Dictionary(ResourceName, available, BorrowDate, DictionaryType, NumberPages);
                resources.add(CurrentDictionary);
            }

            if (ResourceType.equals("Magazine")) {
                String ResourceName = rs.getString("Name");
                boolean available = (Boolean) rs.getObject("available");
                java.sql.Date BorrowDate = rs.getDate("BorrowDate");
                String ScientificTopic = rs.getString("ScientificTopic");
                String Publisher = rs.getString("Publisher");
                LibraryResource.ResourceType type = LibraryResource.ResourceType.DICTIONARY;

                ScientificMagazine CurrentDictionary = new ScientificMagazine(ResourceName, available, BorrowDate, ScientificTopic, Publisher);
                resources.add(CurrentDictionary);
            }

        }
    }

    public void ReloadData() throws SQLException{
        resources.clear();
        LibraryResource.CountResources=0;
        Book.CountBooks=0;
        ScientificMagazine.CountScientificMagazines=0;
        Dictionary.CountDictionaries=0;
        this.LoadData();
    }

    public void DisplayData() throws SQLException{
        for (int i = 0; i < resources.size(); i++)
            System.out.println(resources.get(i));
    }

    public void AddBook() throws SQLException, ExceptieNumeResursaLung, ExceptieNumeAutorLung, ExceptieSubiectLung{
        Scanner keyboard = new Scanner(System.in);
        System.out.println("Introduceti numele cartii:");
        String name = keyboard.nextLine();
        if(name.length()>50) {
            throw new ExceptieNumeResursaLung();
        }
        System.out.println("Introduceti autorul cartii:");
        String author = keyboard.nextLine();
        if(author.length()>50) {
            throw new ExceptieNumeAutorLung();
        }
        System.out.println("Introduceti subiectul cartii:");
        String subiect = keyboard.nextLine();
        if(subiect.length()>50) {
            throw new ExceptieSubiectLung();
        }
        String ResourceType = "Book";
        boolean available = true;
        java.sql.Date BorrowDate = null;
        Book NewBook = new Book(name, available, BorrowDate, author, subiect);
        String sql = "INSERT INTO library (Name, available, Author, Subject, ResourceType) VALUES (\'" + name + "\',\'" + 1 + "\',\'" + author + "\',\'" + subiect + "\' ,\'"+ ResourceType + "\')";
        boolean rs = instr.execute(sql);
    }

    public void SearchBookSubject(){
        Scanner keyboard = new Scanner(System.in);
        System.out.println("Introduceti denumirea subiectului cautat in cadrul cartilor:");
        String subiect = keyboard.nextLine();

        for (int i = 0; i < resources.size(); i++)
            if(resources.get(i).GetResourceType().equals("Book") && resources.get(i).Search().equals(subiect))
                System.out.println(resources.get(i));
    }

    public void SearchDictionaryType(){
        Scanner keyboard = new Scanner(System.in);
        System.out.println("Introduceti tipul de dictionare cautat:");
        String tip = keyboard.nextLine();

        for (int i = 0; i < resources.size(); i++)
            if(resources.get(i).GetResourceType().equals("Dictionary") && resources.get(i).Search().equals(tip))
                System.out.println(resources.get(i));
    }

    public void SearchScientificMagazineTopic(){
        Scanner keyboard = new Scanner(System.in);
        System.out.println("Introduceti subiectul stiintific cautat in cadrul revistelor stiintifice:");
        String tip = keyboard.nextLine();

        for (int i = 0; i < resources.size(); i++)
            if(resources.get(i).GetResourceType().equals("Scientific Magazine") && resources.get(i).Search().equals(tip))
                System.out.println(resources.get(i));
    }

    public void Borrow() throws SQLException {
        Scanner keyboard = new Scanner(System.in);
        System.out.println("Introduceti numele resursei pe care doriti sa o imprumutati:");
        String name = keyboard.nextLine();

        Clock cl = Clock.systemUTC();
        cl = Clock.systemUTC();
        Date date = Date.valueOf(LocalDate.now(cl));

        String sql = "UPDATE library SET available = 0, BorrowDate = \'" +  date + "\' WHERE Name = \'" +  name + "\'";
        boolean rs = instr.execute(sql);
        System.out.println(name + " a fost imprumutat");
    }

    public void BorrowAtDate() throws SQLException, ParseException {
        Scanner keyboard = new Scanner(System.in);
        System.out.println("Afisati toate resursele imprumutate la date de [yyyy-mm-zz]:");
        String StringData = keyboard.nextLine();
        Date sqlDate = Date.valueOf(StringData);//converting string into sql date

        Hashtable<Integer, ArrayList<String>> result = new Hashtable<Integer, ArrayList<String>>();
        int j = 1;
        for (int i = 0; i < resources.size(); i++)
            if(resources.get(i).SearchDate(sqlDate)) {
                result.put(j, new ArrayList<String>());
                result.get(j).add(resources.get(i).getResourceName());
                result.get(j++).add(resources.get(i).GetResourceType());
            }

        result.forEach((key, value) ->
            System.out.println(key + "\t\t" + result.get(key)));

    }

    public void OrderAlphabetical() {
        Collections.sort(resources);
    }

    public void GetLibraryStatistics() {

        File mf = new File("LibraryStatistics.txt");

        try
        {
            mf.open();
            FileOutputStream fos = mf.getOS();
            String text = "Total number of Resources in this library: " + String.valueOf(LibraryResource.CountResources) + "\r\n"
                    + "Number of Books in this library: " + String.valueOf(ScientificMagazine.CountScientificMagazines) + "\r\n"
                    + "Number of Scientific Magazines in this library: " + String.valueOf(Book.CountBooks) + "\r\n"
                    + "Number of Dictionaries in this library: " + String.valueOf(Dictionary.CountDictionaries);
            byte[] mybytes = text.getBytes();
            fos.write(mybytes);
            fos.close();
        }

        catch (Exception ex)
        {
            ex.printStackTrace();
        }
    }
}

