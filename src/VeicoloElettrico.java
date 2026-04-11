
import java.time.LocalDateTime;
public class VeicoloElettrico extends Veicolo {

    private double capacitaBatteria; // Capacità totale della batteria in kWh
    private double batteriaAttuale; // Percentuale attuale della batteria (0-100)
    private boolean inCarica; // Indica se il veicolo è attualmente in carica
    private double potenzaRicarica; // Potenza di ricarica accettata in kW 
    private String connettore; // Tipo di connettore supportato (es. Type 2, CCS, CHAdeMO)

    private VeicoloElettrico(Builder builder) {
        super(builder.targa, LocalDateTime.now(), builder.ticketNumber);
        this.capacitaBatteria = builder.capacitaBatteria;
        this.batteriaAttuale = builder.batteriaAttuale;
        this.inCarica = builder.inCarica;
        this.potenzaRicarica = builder.potenzaRicarica;
        this.connettore = builder.connettore;
    }

    public double getCapacitaBatteria() {
        return capacitaBatteria;
    }

    public double getPercentualeBatteriaAttuale() {
        return batteriaAttuale;
    }
    public boolean isInCarica() {
        return inCarica;
    }
    public double getPotenzaRicarica() {
        return potenzaRicarica;
    }
    public void setPotenzaRicarica(double potenzaRicarica) {
        this.potenzaRicarica = potenzaRicarica;
    }

    public String getConnettore() {
        return connettore;
    }

    public void setTipoConnettore(String connettore) {
        this.connettore = connettore;
    }

    public void setPercentualeBatteriaAttuale(double batteriaAttuale) {
        if (batteriaAttuale < 0 || batteriaAttuale > 100) {
            throw new IllegalArgumentException("La percentuale della batteria deve essere compresa tra 0 e 100");
        }
        this.batteriaAttuale = batteriaAttuale;
    }

    public static class Builder {

        private String targa;
        private int ticketNumber;
        private double capacitaBatteria;
        private double batteriaAttuale;
        private boolean inCarica;
        private double potenzaRicarica;
        private String connettore;

        public Builder(String targa, LocalDateTime localDateTime, int ticketNumber) {
            this.targa = targa;
            this.ticketNumber = ticketNumber;
        }


        public Builder ticketNumber(int ticket) {
            this.ticketNumber = ticket;            
            return this;
        }

        public Builder conCapacitaBatteria(double capacitaBatteria) {
            this.capacitaBatteria = capacitaBatteria;
            return this;
        }

        public Builder conBatteriaAttuale(double batteriaAttuale) {
            this.batteriaAttuale = batteriaAttuale;
            return this;
        }

        public Builder conPotenzaRicarica(double potenzaRicarica) {
            this.potenzaRicarica = potenzaRicarica;
            return this;
        }
        public Builder conConnettore(String connettore) {
            this.connettore = connettore;            
            return this;
        }

        public VeicoloElettrico build() {
            if (targa == null || targa.isEmpty()) {
                throw new IllegalArgumentException("La targa è obbligatoria");
            }
            if (capacitaBatteria <= 0) {
                throw new IllegalArgumentException("La capacità della batteria deve essere maggiore di zero");
            }
            if (batteriaAttuale < 0 || batteriaAttuale > 100) {
                throw new IllegalArgumentException("La percentuale della batteria deve essere compresa tra 0 e 100");
            }
            if (potenzaRicarica <= 0) {
                throw new IllegalArgumentException("La potenza di ricarica deve essere maggiore di zero");
            }
            return new VeicoloElettrico(this);
        }
    }

}
