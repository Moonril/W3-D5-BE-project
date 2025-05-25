import entities.Archivio;
import entities.ElementoCatalogo;
import entities.Libro;
import entities.Rivista;
import enums.Periodicita;
import exceptions.DuplicatoException;
import exceptions.ElementoNonTrovatoException;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);


        EntityManagerFactory emf = Persistence.createEntityManagerFactory("postgres");
        EntityManager em = emf.createEntityManager();

        Archivio archivio = new Archivio(em);

        int scelta = -1;
        while (scelta != 0){

            try {


                System.out.println("Menu Libreria:");
                System.out.println("1 - Aggiungi un libro");
                System.out.println("2 - Aggiungi una rivista");
                System.out.println("3 - Rimuovi un elemento");
                System.out.println("4 - Cerca per codice ISBN");
                System.out.println("5 - Cerca per autore");
                System.out.println("6 - Cerca per anno di pubblicazione");
                System.out.println("7 - Ricerca elementi attualmente in prestito per tessera");
                System.out.println("8 - Ricerca elementi scaduti per tessera");
                System.out.println("9 - Ricerca per titolo");
                System.out.println("0 - Esci");

                scelta = Integer.parseInt(scanner.nextLine());

            } catch (NumberFormatException e) {
                System.out.println("Inserisci un numero valido.");
            }

            switch (scelta){
                case 1->{
                    try {

                        System.out.println("Titolo: ");
                        String titolo = scanner.nextLine();

                        System.out.println("Anno di pubblicazione: ");
                        int anno = scanner.nextInt();
                        scanner.nextLine();

                        System.out.println("Numero di pagine: ");
                        int pagine = scanner.nextInt();
                        scanner.nextLine();
                        // libri
                        System.out.println("Inserisci l'autore: ");
                        String autore = scanner.nextLine();

                        System.out.println("Genere: ");
                        String genere = scanner.nextLine();

                        Libro libro = new Libro(titolo, anno, pagine, autore, genere);
                        archivio.aggiungiElemento(libro);
                        System.out.println(titolo + " aggiunto!");
                    }  catch (DuplicatoException e) {
                        System.out.println("Errore: " + e.getMessage());
                    } catch (InputMismatchException e) {
                        System.out.println("Errore: Inserisci un numero");
                    } catch (Exception e) {
                        System.out.println("Errore generico");
                        e.printStackTrace();
                    }
                }
                case 2 ->{

                    try{

                        System.out.println("Titolo: ");
                        String titolor = scanner.nextLine();

                        System.out.println("Anno di pubblicazione: ");
                        int annor = scanner.nextInt();
                        scanner.nextLine();

                        System.out.println("Numero di pagine: ");
                        int paginer = scanner.nextInt();
                        scanner.nextLine();

                        System.out.println("Periodicità della rivista (SETTIMANALE, MENSILE, SEMESTRALE): ");
                        String input = scanner.nextLine().toUpperCase();
                        Periodicita periodicita = Periodicita.valueOf(input);

                        Rivista rivista = new Rivista(titolor, annor, paginer, periodicita);
                        archivio.aggiungiElemento(rivista);
                        System.out.println(titolor + " aggiunto!");
                    } catch (IllegalArgumentException e) {
                        System.out.println("Errore: periodicità non valida o input numerico errato.");
                    } catch (DuplicatoException e) {
                        System.out.println("Errore: " + e.getMessage());
                    } catch (Exception e) {
                        System.out.println("Errore generico");
                    }

                }
                case 3 -> {
                    System.out.println("Inserisci il codice ISBN dell'elemento che vuoi eliminare: ");

                    String isbn = scanner.nextLine();
                    archivio.rimuoviElemento(isbn);
                    System.out.println("Elemento rimosso");
                }
                case 4 -> {
                    try {

                        System.out.println("Ricerca per codice ISBN: ");
                        String isbn = scanner.nextLine();
                        System.out.println(archivio.cercaPerIsbn(isbn));
                    } catch (ElementoNonTrovatoException e) {
                        System.out.println("Errore: " + e.getMessage());
                    }
                }
                case 5->{
                    try {

                        System.out.println("Ricerca per autore: ");
                        String autore = scanner.nextLine();
                        System.out.println(archivio.cercaPerAutore(autore));
                    } catch (ElementoNonTrovatoException e){
                        System.out.println("Errore: " + e.getMessage());
                    }

                }
                case 6->{
                    try {
                        System.out.println("Ricerca per anno: ");
                        int anno = scanner.nextInt();
                        scanner.nextLine();
                        System.out.println(archivio.cercaPerAnno(anno));
                    } catch (ElementoNonTrovatoException e){
                        System.out.println("Errore: " + e.getMessage());
                    }
                }
                case 7->{
                    try {
                        System.out.println("Inserisci numero tessera utente: ");
                        int tessera = scanner.nextInt();
                        scanner.nextLine();


                        List<ElementoCatalogo> prestitiAttivi = archivio.getPrestitiAttiviPerUtente(tessera);
                        if (prestitiAttivi.isEmpty()) {
                            System.out.println("Nessun elemento attualmente in prestito per la tessera " + tessera);
                        } else {
                            System.out.println("Elementi attualmente in prestito per la tessera " + tessera + ":");
                            prestitiAttivi.forEach(e -> System.out.println("- " + e.getTitolo() + " (ISBN: " + e.getIsbn() + ")"));
                        }
                    } catch (ElementoNonTrovatoException e) {
                        System.out.println("Errore: " + e.getMessage());
                    }
                }
                case 8-> {
                    try {
                        List<ElementoCatalogo> prestitiScaduti = archivio.getPrestitiScadutiNonRestituiti();
                        if (prestitiScaduti.isEmpty()) {
                            System.out.println("Non ci sono prestiti scaduti non ancora restituiti.");
                        } else {
                            System.out.println("Elementi con prestiti scaduti e non restituiti:");
                            prestitiScaduti.forEach(e -> System.out.println("- " + e.getTitolo() + " (ISBN: " + e.getIsbn() + ")"));
                        }
                    } catch (ElementoNonTrovatoException e) {
                        System.out.println("Errore: " + e.getMessage());
                    }
                }
                case 9-> {
                    try {
                        System.out.println("Inserisci il titolo o parte del titolo da cercare: ");
                        String titolo = scanner.nextLine();
                        scanner.nextLine();

                        List<ElementoCatalogo> risultati = archivio.cercaPerTitolo(titolo);
                        if (risultati.isEmpty()) {
                            System.out.println("Nessun elemento trovato con il titolo contenente: " + titolo);
                        } else {
                            System.out.println("Elementi trovati:");
                            risultati.forEach(e -> System.out.println("- " + e.getTitolo() + " (ISBN: " + e.getIsbn() + ")"));
                        }
                    } catch (ElementoNonTrovatoException e) {
                        System.out.println("Errore: " + e.getMessage());
                    } catch (IllegalArgumentException e) {
                        System.out.println("Errore: " + e.getMessage());
                    }
                }
                case 0-> System.out.println("Alla prossima!");
                default-> System.out.println("Scelta non valida");

            }

        }
    }
}
