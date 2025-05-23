package entities;

import jakarta.persistence.Entity;

@Entity
public class Libri extends ElementiCatalogo{
    // variabili
    private String autore;
    private String genere;

    //costruttore
    public Libri(String ISBN, String titolo, int anno, int numeroPagine, String autore, String genere) {
        super(ISBN, titolo, anno, numeroPagine);
        this.autore = autore;
        this.genere = genere;
    }

    public Libri(){}

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
