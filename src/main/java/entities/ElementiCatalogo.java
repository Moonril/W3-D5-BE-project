package entities;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class ElementiCatalogo {
    @Id
    @GeneratedValue
    private String ISBN;
    private String titolo;
    private int anno; // cambiare in Year
    private int numeroPagine;

    public ElementiCatalogo(String ISBN, String titolo, int anno, int numeroPagine) {
        this.ISBN = ISBN;
        this.titolo = titolo;
        this.anno = anno;
        this.numeroPagine = numeroPagine;
    }

    @Override
    public String toString() {
        return "Elemento nel catalogo[" +
                "ISBN=" + ISBN +
                ", titolo='" + titolo + '\'' +
                ", anno=" + anno +
                ", numeroPagine=" + numeroPagine +
                ", ";
    }

    public String getISBN() {
        return ISBN;
    }

    public String getTitolo() {
        return titolo;
    }

    public int getAnno() {
        return anno;
    }

    public int getNumeroPagine() {
        return numeroPagine;
    }

    //equals


    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        ElementiCatalogo that = (ElementiCatalogo) o;
        return Objects.equals(ISBN, that.ISBN);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(ISBN);
    }
}
