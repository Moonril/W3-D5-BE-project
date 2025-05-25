package entities;

import jakarta.persistence.*;

import java.util.List;
import java.util.Objects;

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@NamedQuery(name = "ElementoCatalogo.findByAnno", query = "select e from ElementoCatalogo e WHERE e.anno = :anno")
@NamedQuery(name = "ElementoCatalogo.findByIsbn", query = "select e from ElementoCatalogo e WHERE e.isbn = :isbn")
public abstract class ElementoCatalogo {
    @Id
    @GeneratedValue
    private int isbn;
    private String titolo;
    private int anno; // cambiare in Year
    @Column(name = "numero_pagine")
    private int numeroPagine;
    @OneToMany
    private List<Prestito> prestiti;

    public ElementoCatalogo(String titolo, int anno, int numeroPagine) {
        this.isbn = isbn;
        this.titolo = titolo;
        this.anno = anno;
        this.numeroPagine = numeroPagine;
    }

    public ElementoCatalogo(){}

    @Override
    public String toString() {
        return "ElementiCatalogo{" +
                "ISBN='" + isbn + '\'' +
                ", titolo='" + titolo + '\'' +
                ", anno=" + anno +
                ", numeroPagine=" + numeroPagine +
                ", prestiti=" + prestiti +
                '}';
    }

    public int getIsbn() {
        return isbn;
    }

    public void setIsbn(int isbn) {
        this.isbn = isbn;
    }

    public String getTitolo() {
        return titolo;
    }

    public void setTitolo(String titolo) {
        this.titolo = titolo;
    }

    public int getAnno() {
        return anno;
    }

    public void setAnno(int anno) {
        this.anno = anno;
    }

    public int getNumeroPagine() {
        return numeroPagine;
    }

    public void setNumeroPagine(int numeroPagine) {
        this.numeroPagine = numeroPagine;
    }

    public List<Prestito> getPrestiti() {
        return prestiti;
    }

    public void setPrestiti(List<Prestito> prestiti) {
        this.prestiti = prestiti;
    }


    //equals


    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        ElementoCatalogo that = (ElementoCatalogo) o;
        return Objects.equals(isbn, that.isbn);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(isbn);
    }
}
