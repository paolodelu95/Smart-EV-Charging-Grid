
import java.time.LocalDateTime;

public class Veicolo {

    private final String targa;
    private final LocalDateTime oraIngresso;
    private static int ticketNumber;

    public Veicolo(String targa, LocalDateTime oraIngresso, int ticketNumber) {
        this.targa = targa;
        this.oraIngresso = oraIngresso;
        Veicolo.ticketNumber = ticketNumber;
    }

    public String getTarga() {
        return targa;
    }

    public LocalDateTime getOraIngresso() {
        return oraIngresso;
    }

    public static int getTicketNumber() {
        return ticketNumber;
    }

}
