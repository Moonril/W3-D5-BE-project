package entities;

import jakarta.persistence.Entity;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.Table;

@Entity
@Table(name = "libri")
@NamedQuery(name = "Libro.findByAutore", query = "SELECT e FROM Libro e WHERE e.autore = :autore")
public class Libro extends ElementoCatalogo {
    // variabili
    private String autore;
    private String genere;

    //costruttore

    public Libro(String titolo, int anno, int numeroPagine, String autore, String genere) {
        super(titolo, anno, numeroPagine);
        this.autore = autore;
        this.genere = genere;
    }


    public Libro(){}

    //tostring

    @Override
    public String toString() {
        return super.toString() + " " +
                "autore='" + autore + '\'' +
                ", genere='" + genere + '\'' +
                ']';
    }


    //get set

    public String getAutore() {
        return autore;
    }

    public String getGenere() {
        return genere;
    }

    public void setAutore(String autore) {
        this.autore = autore;
    }

    public void setGenere(String genere) {
        this.genere = genere;
    }
}
