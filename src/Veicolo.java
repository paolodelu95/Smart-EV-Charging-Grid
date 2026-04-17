
import java.time.LocalDateTime;

public class Veicolo {

    private final String targa;
    private final LocalDateTime oraIngresso;
    private int ticketNumber;

    public Veicolo(String targa, LocalDateTime oraIngresso, int ticketNumber) {
        this.targa = targa;
        this.oraIngresso = oraIngresso;
        this.ticketNumber = ticketNumber;
    }

    public String getTarga() {
        return targa;
    }

    public LocalDateTime getOraIngresso() {
        return oraIngresso;
    }

    public int getTicketNumber() {
        return ticketNumber;
    }

}
