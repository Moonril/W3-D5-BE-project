package entities;

import enums.Periodicita;
import jakarta.persistence.Entity;

@Entity
public class Riviste extends ElementiCatalogo {
    //variabili
    private Periodicita periodicita;

    //costruttore

    public Riviste(String ISBN, String titolo, int anno, int numeroPagine, Periodicita periodicita) {
        super(ISBN, titolo, anno, numeroPagine);
        this.periodicita = periodicita;
    }

    public Riviste(){}

    //tostring

    @Override
    public String toString() {
        return super.toString() + " " +
                "periodicita=" + periodicita +
                ']';
    }


    // get set

    public Periodicita getPeriodicita() {
        return periodicita;
    }

    public void setPeriodicita(Periodicita periodicita) {
        this.periodicita = periodicita;
    }
}
