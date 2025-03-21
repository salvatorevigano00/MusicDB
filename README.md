# MusicDB
Questo progetto è stato sviluppato come prova per l'IBM Academy 2025 il 18/03/2025

## Descrizione
"music" è un'applicazione Java progettata per la gestione di un database musicale. L'applicazione consente di archiviare e gestire informazioni dettagliate su artisti, album e tracce musicali.

Le funzionalità principali includono:
* **Gestione degli artisti:** Aggiunta, modifica, eliminazione e ricerca di artisti.
* **Gestione degli album:** Aggiunta, modifica, eliminazione e ricerca di album, con associazione agli artisti.
* **Gestione delle tracce:** Aggiunta, modifica, eliminazione e ricerca di tracce, con associazione ad album e artisti.
* **Interrogazioni complesse:** Ricerca di album per artista, ricerca di tracce per album, ecc.
* **Persistenza dei dati:** Utilizzo di JDBC per l'interazione con un database MySQL, garantendo la persistenza dei dati.
* **Test unitari:** Implementazione di test unitari con JUnit 5 per garantire la qualità del codice e la correttezza delle funzionalità.
* **Documentazione Javadoc:** Documentazione completa del codice sorgente per facilitare la comprensione e la manutenzione.

## Tecnologie utilizzate
* Java 21
* Maven
* MySQL
* JDBC
* JUnit 5

## Installazione
1.  Clona il repository:
    ```bash
    git clone https://github.com/salvatorevigano00/MusicDB.git
    ```

2.  Naviga nella directory del progetto:
    ```bash
    cd music
    ```

3.  Configura la connessione al database MySQL:
    * Crea un database MySQL chiamato "music".
    * Modifica il file `application.properties` nella directory `src/main/resources` con le tue credenziali di database (nel mio caso ho inserito direttamente le credenziali in `MySQLConnection`, ma è considerata una scelta completata sbagliata a livello di sicurezza).
  
4.  Compila il progetto con Maven:
    ```bash
    mvn clean install
    ```
