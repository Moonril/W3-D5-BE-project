package entities;

import enums.Periodicita;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Table;

@Entity
@Table(name = "riviste")
public class Rivista extends ElementoCatalogo {
    //variabili
    @Enumerated(EnumType.STRING)
    private Periodicita periodicita;

    //costruttore

    public Rivista(String titolo, int anno, int numeroPagine, Periodicita periodicita) {
        super(titolo, anno, numeroPagine);
        this.periodicita = periodicita;
    }

    public Rivista(){}

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
