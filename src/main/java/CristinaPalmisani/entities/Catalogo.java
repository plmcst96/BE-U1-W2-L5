package CristinaPalmisani.entities;

import java.time.LocalDate;

public class Catalogo {
    private final String codeISBN;
    private final String title;
    private final LocalDate year;
    private final int numberOfPage;

    public Catalogo(String title, LocalDate year, int numberOfPage, String codeISBN) {

        this.title = title;
        this.year = year;
        this.numberOfPage = numberOfPage;
        this.codeISBN = codeISBN;
    }

    @Override
    public String toString() {
        return
                "codeISBN='" + codeISBN + '\'' +
                ", title='" + title + '\'' +
                ", year=" + year +
                ", numberOfPage=" + numberOfPage;
    }

    public String getCodeISBN() {
        return codeISBN;
    }

    public String getTitle() {
        return title;
    }

    public LocalDate getYear() {
        return year;
    }

    public int getNumberOfPage() {
        return numberOfPage;
    }
}
