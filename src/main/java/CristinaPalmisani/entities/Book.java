package CristinaPalmisani.entities;

import java.time.LocalDate;
import java.util.Random;

public class Book extends Catalogo{

    private final String author;
    private final String genre;

    public Book(String title, LocalDate year, int numberOfPage, String codeISBN, String author, String genre) {
        super(title, year, numberOfPage, codeISBN);
        this.author = author;
        this.genre = genre;
    }

    @Override
    public String toString() {
        return "Book{" +
                "author='" + author + '\'' +
                ", genre='" + genre + '\'' +
                '}';
    }

    public String getAuthor() {
        return author;
    }

    public String getGenre() {
        return genre;
    }
}
