
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

        if (veicolo.getPotenzaRicarica() > potenzaMassima) {
            veicolo.setPotenzaRicarica(potenzaMassima); // Potenza di ricarica del veicolo limitata alla potenza massima della colonnina
            System.out.println("La potenza di ricarica del veicolo " + veicolo.getTarga() + " è stata limitata a " + potenzaMassima + " kW per compatibilità con la colonnina " + id);
        }
        if (veicolo.getPercentualeBatteriaAttuale() >= 100) {
            throw new IllegalStateException("La batteria del veicolo è già completamente carica");
        }
            // Logica per la ricarica del veicolo
            System.out.println("Ricarica del veicolo " + veicolo.getTarga() + " in corso alla colonnina " + id);
            double energiaErogata = Math.min(potenzaMassima, veicolo.getPotenzaRicarica()) * 1; // Energia erogata in kWh (ipotizzando 1 ora di ricarica)
            double nuovaPercentualeBatteria = veicolo.getPercentualeBatteriaAttuale() + (energiaErogata / veicolo.getCapacitaBatteria()) * 100;
            veicolo.setPercentualeBatteriaAttuale(Math.min(nuovaPercentualeBatteria, 100)); // Aggiorna la percentuale della batteria del veicolo, limitandola al 100%
            setStato(false); // La colonnina diventa occupata durante la ricarica

    }
}
