package entities;

import java.time.LocalDate;

public class Prestito {
    private Utente utente;
    private ElementiCatalogo elementoPrestato;
    private LocalDate dataInizioPrestito;
    private LocalDate dataRestituzionePrevista; // 30 giorni dalla data di inizio
    private LocalDate dataRestituzioneEffettiva;
}
