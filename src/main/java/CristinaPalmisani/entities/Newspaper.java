package CristinaPalmisani.entities;

public class Newspaper extends Catalogo {
    private periodicity periodicity;

    public Newspaper(String title, String year, int numberOfPage, String codeISBN, periodicity periodicity) {
        super(title, year, numberOfPage, codeISBN);
        this.periodicity = periodicity;
    }

    @Override
    public String toString() {
        return "Newspaper{" +
                "periodicity=" + periodicity +
                '}';
    }

    public CristinaPalmisani.entities.periodicity getPeriodicity() {
        return periodicity;
    }

    public void setPeriodicity(CristinaPalmisani.entities.periodicity periodicity) {
        this.periodicity = periodicity;
    }
}
