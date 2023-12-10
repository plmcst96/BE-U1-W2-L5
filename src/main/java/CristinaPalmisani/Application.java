package CristinaPalmisani;

import CristinaPalmisani.entities.*;
import com.github.javafaker.Faker;

import java.util.List;
import java.util.Locale;
import java.util.Random;
import java.util.Scanner;

public class Application {
    public static Library library = new Library();
    static  Scanner input = new Scanner(System.in);
    public static void main(String[] args) {
            faker();
            menuLibrary();
            input.close();
    }

    public static void menuLibrary(){
        String query;
        List<Catalogo> result;
        int choice = -1;

        while (choice != 0){
            System.out.println();
            System.out.println("Fake library system");
            System.out.println("Make your choice");
            System.out.println("1 - Add an item to the library");
            System.out.println("2 - Remove an item from the library");
            System.out.println("3 - Find item by ISBN");
            System.out.println("4 - Find item by year");
            System.out.println("5 - Find item by author");
            System.out.println("6 - Save on disk");
            System.out.println("7 - Load on disk");
            System.out.println("8 - Print all library catalogue");
            System.out.println("0 - Exit");

            try {
                choice = Integer.parseInt(input.nextLine());
            } catch (NumberFormatException ex){
                System.err.println("not a number");
            }

            switch (choice){
                case 1:
                    addItem();
                    break;
                case 2:
                    remItem();
                    break;
                case 3:
                    System.out.println("Search by ISBN");
                    query = input.nextLine();
                    result = library.findIsbn(query);
                    System.out.println("Result: ");
                    result.forEach(item -> System.out.println(item.toString()));
                    System.out.println("press enter to continue");
                    input.nextLine();
                    break;
                case 4:
                    System.out.println("Search by year");
                    query = input.nextLine();
                    result = library.findYear(query);
                    System.out.println("Result: ");
                    result.forEach(item -> System.out.println(item.toString()));
                    System.out.println("press enter to continue");
                    input.nextLine();
                    break;
                case 5:
                    System.out.println("Search by year");
                    query = input.nextLine().toLowerCase();
                    result = library.findAuthor(query);
                    System.out.println("Result: ");
                    result.forEach(item -> System.out.println(item.toString()));
                    System.out.println("press enter to continue");
                    input.nextLine();
                    break;
                case 6:
                    library.save();
                    break;
                case 7:
                    library.load();
                    System.out.println(library.toString());
                    System.out.println("press enter to continue");
                    input.nextLine();
                    break;
                case 8:
                    System.out.println(library.toString());
                    System.out.println("press enter to continue");
                    input.nextLine();
                    break;
                case 0:
                    System.out.println("0");
                    break;
            }
        }
    }

    private static void remItem(){
        int choice = -1;
        String isbn;

        while (choice != 0){
            System.out.println("1 - Remove an ISBN");
            System.out.println("2 - Remove from a numbered list");
            System.out.println("0 - Exit");

            try {
                choice = Integer.parseInt(input.nextLine());
            } catch (NumberFormatException ex){
                System.err.println("not a number");
            }

            if (choice == 1){
                System.out.println(library.toString());
                System.out.println("Enter an ISBN (10 number)");
                try {
                    isbn = input.nextLine();
                    library.removeElement(isbn);
                    System.out.println(library.toString());
                } catch (Exception ex){
                    System.err.println("Wrong search filter!");
                }
            }
            if (choice == 2){
                choice = -1;
                while (choice != 0){
                    System.err.println("Chose an item to remove from catalogue - 0 to exit");
                    library.printIndex();
                    try {
                        choice = Integer.parseInt(input.nextLine());
                        if (choice > 0) library.removeElement(String.valueOf(choice - 1));
                    } catch (NumberFormatException ex){
                        System.err.println("Not a number");
                    }
                }
            }
        }
    }

    private static void addItem() {
        String isbn;
        String title;
        String year;
        int pages;
        periodicity[] periodicities = periodicity.values();
        periodicity periodicity = null;
        int choice = -1;

        while (choice != 0) {
            System.out.println("1 - Add a book");
            System.out.println("2 - Add a newspaper");
            System.out.println("0 - Back");
            try {
                choice = Integer.parseInt(input.nextLine());
            } catch (NumberFormatException ex) {
                System.err.println("Not a number");
            }

            switch (choice) {
                case 1:
                    System.out.println("ISBN: ");
                    isbn = input.nextLine();
                    System.out.println("Title: ");
                    title = input.nextLine();
                    System.out.println("Year: ");
                    year = input.nextLine();
                    System.out.println("Pages: ");
                    try {
                        pages = Integer.parseInt(input.nextLine());
                    } catch (NumberFormatException ex) {
                        System.err.println("Not a number");
                        break;
                    }
                    System.out.println("Author: ");
                    String author = input.nextLine();
                    System.out.println("Genre: ");
                    String genre = input.nextLine();
                    library.addElement(new Book(title, year, pages, isbn, author, genre));
                    System.out.println(library.toString());
                    System.out.println("Press enter to continue...");
                    input.nextLine();
                    choice = 0;
                    break;
                case 2:
                    System.out.println("ISBN: ");
                    isbn = input.nextLine();
                    System.out.println("Title: ");
                    title = input.nextLine();
                    System.out.println("Year: ");
                    year = input.nextLine();
                    System.out.println("Pages: ");
                    try {
                        pages = Integer.parseInt(input.nextLine());
                    } catch (NumberFormatException ex) {
                        System.err.println("Not a number");
                        break;
                    }
                    int periodicityChoice = -1;
                    while (periodicityChoice != 0) {
                        System.out.println("Periodicity: ");
                        for (int i = 0; i < periodicities.length; i++) {
                            System.out.println((i + 1) + " - " + periodicities[i]);
                        }
                        try {
                            periodicityChoice = Integer.parseInt(input.nextLine());
                        } catch (NumberFormatException ex) {
                            System.err.println("Not a number");
                        }
                        periodicity = periodicities[periodicityChoice - 1];
                        periodicityChoice = 0;
                    }
                    library.addElement(new Newspaper(title, year, pages, isbn, periodicity));
                    System.out.println(library.toString());
                    System.out.println("Press enter to continue");
                    input.nextLine();
                    choice = 0;
                    break;
                case 0:
                    System.out.println("Orevoire");
                    break;
            }
        }
    }

    public static void faker(){
        Faker faker = new Faker(Locale.ITALY);
        Random rndm = new Random();

        for (int i = 0; i < 4; i++) {
            periodicity[] periodicities = periodicity.values();
            if (rndm.nextBoolean()){
                library.addElement(new Book(faker.book().title(), faker.date().birthday().toString(),
                        faker.number().numberBetween(35, 600), faker.code().isbn10(),
                        faker.book().author(), faker.book().genre()));
            } else {
                library.addElement(new Newspaper(faker.book().title(), faker.date().birthday().toString(),
                        faker.number().numberBetween(35, 600), faker.code().isbn10(),
                        periodicities[rndm.nextInt(periodicities.length)]));
            }

        }
    }
}
