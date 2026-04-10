
public class Colonnina {

    private final String id; // Identificativo univoco della colonnina
    private final String tipoConnettore; // Tipo di connettore supportato (es. Type 2, CCS, CHAdeMO)
    private final double potenzaMassima; // Potenza massima erogabile in kW
    private boolean stato; // Indica se la colonnina è attualmente occupata, libera o in manutenzione

    public Colonnina(String id, String tipoConnettore, double potenzaMassima, boolean stato) {
        this.id = id;
        this.tipoConnettore = tipoConnettore;
        this.potenzaMassima = potenzaMassima;
        this.stato = stato;
    }

    public String getId() {
        return id;
    }

    public String getConnettore() {
        return tipoConnettore;
    }

    public double getPotenzaMassima() {
        return potenzaMassima;
    }

    public boolean isDisponibile() {
        return stato; // true se la colonnina è disponibile, false se è occupata o fuori uso
    }

    public void setStato(boolean stato) {
        this.stato = stato;
    }

    public void ricarica(VeicoloElettrico veicolo) {
        if (!isDisponibile()) {
            throw new IllegalStateException("La colonnina non è disponibile per la ricarica");
        }
        if (!veicolo.isInCarica()) {
            throw new IllegalStateException("Il veicolo non è in carica");
        }
        if (veicolo.getPotenzaRicarica() > potenzaMassima) {
            veicolo.setPotenzaRicarica(potenzaMassima); // Potenza di ricarica del veicolo limitata alla potenza massima della colonnina
        }
        if (veicolo.getPercentualeBatteriaAttuale() >= 100) {
            throw new IllegalStateException("La batteria del veicolo è già completamente carica");
        }
        if (veicolo.getConnettore().equals(tipoConnettore)) {
            // Logica per la ricarica del veicolo
            double energiaErogata = Math.min(potenzaMassima, veicolo.getPotenzaRicarica()) * 1; // Energia erogata in kWh (ipotizzando 1 ora di ricarica)
            double nuovaPercentualeBatteria = veicolo.getPercentualeBatteriaAttuale() + (energiaErogata / veicolo.getCapacitaBatteria()) * 100;
            veicolo.setPercentualeBatteriaAttuale(Math.min(nuovaPercentualeBatteria, 100)); // Aggiorna la percentuale della batteria del veicolo, limitandola al 100%
            setStato(false); // La colonnina diventa occupata durante la ricarica
        }
    }
}
