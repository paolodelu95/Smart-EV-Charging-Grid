
import java.time.LocalDateTime;

public class Garage {

    private final Veicolo[] posti;
    private final int numeroPosti;
    private int postiOccupati;
    private final String nome;
    private final double tariffaOraria; //in realtà consideriamo la tariffa al secondo, ma per semplicità la chiamiamo tariffa oraria
    private final double tariffaWeekend;
    private int ticketVenduti = 0;

    public Garage(String nome, int numeroPosti, double tariffaOraria, double tariffaWeekend) {
        this.nome = nome;
        this.numeroPosti = numeroPosti;
        this.postiOccupati = 0;
        this.tariffaOraria = tariffaOraria;
        this.tariffaWeekend = tariffaWeekend;
        posti = new Veicolo[numeroPosti];
    }

    public boolean parcheggia(Veicolo v) {
        if (postiOccupati < numeroPosti) {
            posti[postiOccupati] = v;
            postiOccupati++;
            ticketVenduti++;
            return true;
        }
        System.out.println("Impossibile parcheggiare il veicolo " + v.getTarga() + ". Garage pieno.");
        return false;
    }

    public double rimuovi(int ticket) {
        for (int i = 0; i < postiOccupati; i++) {
            if (Veicolo.getTicketNumber() == ticket) {
                double tariffa = calcolaTariffa(posti[i]);
                for (int j = i; j < postiOccupati - 1; j++) {
                    posti[j] = posti[j + 1];
                }
                posti[postiOccupati - 1] = null; // Libera l'ultimo
                postiOccupati--;
                return tariffa;
            }
        }
        return 0.0; //Veicolo non trovato
    }

    public int getPostiDisponibili() {
        return numeroPosti - postiOccupati;
    }

    public String getNome() {
        return nome;
    }

    public double getTariffaOraria() {
        return tariffaOraria;
    }

    public double getTariffaWeekend() {
        return tariffaWeekend;
    }

    public double calcolaTariffa(Veicolo v) {

        for (int i = 0; i < postiOccupati; i++) {
            if (Veicolo.getTicketNumber() == Veicolo.getTicketNumber()) {
                LocalDateTime oraIngresso = posti[i].getOraIngresso();
                LocalDateTime oraUscita = LocalDateTime.now().plusHours(1); // Placeholder, in un'applicazione reale, l'ora di uscita dovrebbe essere registrata al momento della rimozione del veicolo
                long secondiParcheggio = java.time.Duration.between(oraIngresso, oraUscita).toSeconds() % 3600;
                long minutiParcheggio = java.time.Duration.between(oraIngresso, oraUscita).toMinutes() % 60;
                System.out.println("Secondi parcheggio: " + secondiParcheggio + ", Minuti parcheggio: " + minutiParcheggio);
                if (minutiParcheggio > 0) {
                    secondiParcheggio += 60; // Aggiungi un minuto per ogni minuto parziale
                }
                if (oraIngresso.getDayOfWeek().getValue() >= 6) { //Weekend
                    return secondiParcheggio * tariffaWeekend;
                } else {
                    return secondiParcheggio * tariffaOraria;
                }
            }
        }
        System.out.println("Veicolo " + v.getTarga() + " non trovato nel garage.");
        return 0.0; //Veicolo non trovato
    }

    public String visualizzaPostiOccupati() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < postiOccupati; i++) {
            sb.append(posti[i].getTarga()).append(" ").append(posti[i].getOraIngresso()).append(" ").append(Veicolo.getTicketNumber()).append("\n");
        }
        return sb.toString().trim();
    }

    public String getPosti(int p) {
        return posti[p].getTarga();
    }

    public int getTicketVenduti() {
        return ticketVenduti;
    }

    public int generateTicketNumber() {
        int randomTicketNumber = (int) (Math.random() * 1000000); // Genera un numero di ticket casuale tra 0 e 999999
        if (randomTicketNumber < 100000) { // Assicura che il numero di ticket sia sempre a 6 cifre
            randomTicketNumber += 100000;
        }
        for (int i = 0; i < postiOccupati; i++) {

            if (Veicolo.getTicketNumber() == randomTicketNumber) {
                return generateTicketNumber(); // Se il numero di ticket è già in uso, genera un nuovo numero
            }
        }

        return randomTicketNumber;
    }

    public int getTicketNumber(String targa) {
        for(int i=0; i < postiOccupati; i++) {
            if(posti[i].getTarga().equals(targa)) {
                return Veicolo.getTicketNumber();
            }
        }
        return -1; // Veicolo non trovato   
    }

    public String getTarga(int ticket) {
        for(int i = 0; i < postiOccupati; i++) {
            if(Veicolo.getTicketNumber() == ticket){
                return posti[i].getTarga();
            }
        }
        return null;
    }

}
