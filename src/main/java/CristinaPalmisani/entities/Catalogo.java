package CristinaPalmisani.entities;

import java.time.LocalDate;

public class Catalogo {
    private final String codeISBN;
    private final String title;
    private final String year;
    private final int pages;

    public Catalogo(String title, String year, int numberOfPage, String codeISBN) {

        this.title = title;
        this.year = year;
        this.pages = numberOfPage;
        this.codeISBN = codeISBN;
    }

    @Override
    public String toString() {
        return
                "codeISBN='" + codeISBN + '\'' +
                ", title='" + title + '\'' +
                ", year=" + year +
                ", numberOfPage=" + pages;
    }

    public String getCodeISBN() {
        return codeISBN;
    }

    public String getTitle() {
        return title;
    }

    public String getYear() {
        return year;
    }

    public int getNumberOfPage() {
        return pages;
    }
}
