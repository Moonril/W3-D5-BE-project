package entities;

import jakarta.persistence.*;

import java.time.LocalDate;
//utente e elemento prestato vanno settati in prestito, perchè lato proprietario
@Entity
@Table(name = "prestiti")
//ritornare una lista di elementi in prestito. lista di elementi prestati.
@NamedQuery(name = "Prestito.findByNumeroTessera", query = "select p from Prestito p where p.utente.numeroTessera= :numeroTessera and p.dataRestituzioneEffettiva is null")
@NamedQuery(name = "Prestito.findScaduti", query = "select p from Prestito p where p.dataRestituzionePrevista< CURRENT_DATE and p.dataRestituzioneEffettiva is null")
public class Prestito {
    @Id
    @GeneratedValue
    private int id;

    @ManyToOne
    @JoinColumn(name = "utente_numero_tessera")
    private Utente utente;

    @Column(name = "elemento_prestato")
    @ManyToOne
    @JoinColumn(name = "elemento_isbn")
    private ElementiCatalogo elementoPrestato;

    @Column(name = "data_inizio_prestito")
    private LocalDate dataInizioPrestito;

    @Column(name = "data_restituzione_prevista")
    private LocalDate dataRestituzionePrevista; // 30 giorni dalla data di inizio

    @Column(name = "data_restituzione_effettiva")
    private LocalDate dataRestituzioneEffettiva;

    //costruttori

    public Prestito(Utente utente, ElementiCatalogo elementoPrestato, LocalDate dataInizioPrestito) {
        this.utente = utente;
        this.elementoPrestato = elementoPrestato;
        this.dataInizioPrestito = dataInizioPrestito;
        this.dataRestituzionePrevista = dataInizioPrestito.plusDays(30);
    }

    public Prestito() {
    }

    //tostring

    @Override
    public String toString() {
        return "Prestito{" +
                "id=" + id +
                ", utente=" + utente +
                ", elementoPrestato=" + elementoPrestato +
                ", dataInizioPrestito=" + dataInizioPrestito +
                ", dataRestituzionePrevista=" + dataRestituzionePrevista +
                ", dataRestituzioneEffettiva=" + dataRestituzioneEffettiva +
                '}';
    }

    // get set

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Utente getUtente() {
        return utente;
    }

    public void setUtente(Utente utente) {
        this.utente = utente;
    }

    public ElementiCatalogo getElementoPrestato() {
        return elementoPrestato;
    }

    public void setElementoPrestato(ElementiCatalogo elementoPrestato) {
        this.elementoPrestato = elementoPrestato;
    }

    public LocalDate getDataInizioPrestito() {
        return dataInizioPrestito;
    }

    public void setDataInizioPrestito(LocalDate dataInizioPrestito) {
        this.dataInizioPrestito = dataInizioPrestito;
    }

    public LocalDate getDataRestituzionePrevista() {
        return dataRestituzionePrevista;
    }

    public void setDataRestituzionePrevista(LocalDate dataRestituzionePrevista) {
        this.dataRestituzionePrevista = dataRestituzionePrevista;
    }

    public LocalDate getDataRestituzioneEffettiva() {
        return dataRestituzioneEffettiva;
    }

    public void setDataRestituzioneEffettiva(LocalDate dataRestituzioneEffettiva) {
        this.dataRestituzioneEffettiva = dataRestituzioneEffettiva;
    }
}
