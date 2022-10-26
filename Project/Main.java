package Project;

import java.sql.SQLException;
import java.text.ParseException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws ClassNotFoundException, SQLException, ParseException, ExceptieNumeResursaLung, ExceptieNumeAutorLung, ExceptieSubiectLung{

        int choice;
        boolean stop = true;
        Scanner keyboard = new Scanner(System.in);

        Library myLibrary = new Library();

        while (stop) {
            System.out.println("\n");
            System.out.print("1.) Conectare la baza de date a bibliotecii \n");
            System.out.print("2.) Reincarca datele bibliotecii din baza de date\n");
            System.out.print("3.) Afisarea tuturor resurselor din cadrul bibliotecii\n");
            System.out.print("4.) Adaugare carte noua\n");
            System.out.print("5.) Afisare carti dintr-un anumit subiect\n");
            System.out.print("6.) Afisare dictionare de un anumit tip\n");
            System.out.print("7.) Afisare reviste stiintifice pe un anumit subiect\n");
            System.out.print("8.) Imprumut nou\n");
            System.out.print("9.) Indexare carti imprumutate la o anumita data\n");
            System.out.print("10.) Generare date statistice despre resursele bibliotecii\n");
            System.out.print("11.) Ordoneaza si afiseaza resursele bibliotecii in ordine alfabetica\n");
            System.out.print("12.) Exit e-biblioteca\n");
            System.out.print("\nIntroduceti comanda dorita: ");

            choice = Integer.parseInt(keyboard.nextLine());

            switch (choice) {

                case 1:
                    myLibrary.Connect();
                    myLibrary.LoadData();
                    break;

                case 2:
                    myLibrary.ReloadData();
                    break;

                case 3:
                    System.out.println();
                    myLibrary.DisplayData();
                    break;

                case 4:
                    myLibrary.AddBook();
                    myLibrary.ReloadData();
                    break;

                case 5:
                    System.out.println();
                    myLibrary.SearchBookSubject();
                    break;

                case 6:
                    System.out.println();
                    myLibrary.SearchDictionaryType();
                    break;

                case 7:
                    System.out.println();
                    myLibrary.SearchScientificMagazineTopic();
                    break;

                case 8:
                    System.out.println();
                    myLibrary.Borrow();
                    myLibrary.ReloadData();
                    break;

                case 9:
                    System.out.println();
                    myLibrary.BorrowAtDate();
                    break;

                case 10:
                    System.out.println();
                    myLibrary.GetLibraryStatistics();
                    break;

                case 11:
                    System.out.println();
                    myLibrary.OrderAlphabetical();
                    myLibrary.DisplayData();
                    break;

                case 12:
                    System.out.println("Exiting Program...");
                    stop = false;
                    break;

                default:
                    System.out.println("Aceasta optiune nu face parte din meniu! Va rog selectati alta");
                    break;
            }
        }
    }
}
