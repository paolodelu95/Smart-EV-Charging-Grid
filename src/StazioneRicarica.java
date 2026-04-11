
public class StazioneRicarica {

    private final String id; // Identificativo univoco della stazione di ricarica
    private final String nome; // Nome della stazione di ricarica
    private final double latitudine; // Posizione geografica della stazione
    private final double longitudine; // Posizione geografica della stazione
    private final Colonnina[] colonnine; // Array di colonnine presenti nella stazione

    public StazioneRicarica(String id, String nome, double latitudine, double longitudine, Colonnina[] colonnine) {
        this.id = id;
        this.nome = nome;
        this.latitudine = latitudine;
        this.longitudine = longitudine;
        this.colonnine = colonnine;
    }

    public String getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public double getLatitudine() {
        return latitudine;
    }

    public double getLongitudine() {
        return longitudine;
    }

    public Colonnina[] getColonnine() {
        return colonnine;
    }

    public void assegnaColonnina(VeicoloElettrico veicolo) {
        System.out.println("__________________________________________________________________________________________________________" + "\n");
        System.out.println("Assegnazione colonnina per il veicolo " + veicolo.getTarga() + " con connettore " + veicolo.getConnettore() + " e potenza di ricarica " + veicolo.getPotenzaRicarica() + " kW");
        System.out.println("__________________________________________________________________________________________________________" + "\n");

        if (colonnine == null || colonnine.length == 0) { 
            throw new IllegalStateException("La stazione di ricarica non ha colonnine disponibili");
        }
        for (Colonnina colonnina : colonnine) {
            if(colonnina.isDisponibile() && colonnina.getConnettore().equalsIgnoreCase(veicolo.getConnettore())) { // Se la colonnina è disponibile e compatibile con il veicolo, assegnala al veicolo
                if(veicolo.getPotenzaRicarica() > colonnina.getPotenzaMassima()) { // Se la potenza di ricarica del veicolo è superiore alla potenza massima della colonnina, cerca una colonnina con potenza massima compatibile
                    for (Colonnina c : colonnine) {
                        if (c.isDisponibile() && c.getConnettore().equalsIgnoreCase(veicolo.getConnettore()) && c.getPotenzaMassima() >= veicolo.getPotenzaRicarica()) {
                            colonnina = c; // Assegna una colonnina con potenza massima compatibile al veicolo
                            System.out.println("La colonnina " + colonnina.getId() + " è stata assegnata al veicolo " + veicolo.getTarga() + " per la ricarica con potenza compatibile.");
                            break;
                        }
                    }
                }
                System.out.println("Veicolo " + veicolo.getTarga() + " assegnato alla colonnina " + colonnina.getId() + " per la ricarica.");
                colonnina.ricarica(veicolo); // Avvia la ricarica del veicolo alla colonnina
                return; //Esce dal ciclo dopo aver assegnato la colonnina al veicolo
            }
            if (colonnina.isDisponibile() && !colonnina.getConnettore().equalsIgnoreCase(veicolo.getConnettore())) { // Se la colonnina è disponibile ma non compatibile con il veicolo, stampa un messaggio di incompatibilità
                System.out.println("La colonnina " + colonnina.getId() + " è disponibile ma non compatibile con il veicolo " + veicolo.getTarga());
            }
            if (!colonnina.isDisponibile()) {   // Se la colonnina non è disponibile, stampa un messaggio di indisponibilità
                System.out.println("Colonnina " + colonnina.getId() + " non disponibile");
            }
        }
            throw new IllegalStateException("Nessuna colonnina disponibile compatibile con il veicolo " + veicolo.getTarga() + " riprovare più tardi, ci scusiamo per il disagio.");
        
    }

}
