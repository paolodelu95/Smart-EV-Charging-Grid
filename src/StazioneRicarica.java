
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
        if (colonnine == null || colonnine.length == 0) {
            throw new IllegalStateException("La stazione di ricarica non ha colonnine disponibili");
        }
        for (Colonnina colonnina : colonnine) {
            if(colonnina.isDisponibile() && colonnina.getConnettore().equals(veicolo.getConnettore())) {
                colonnina.ricarica(veicolo);
                System.out.println("Veicolo " + veicolo.getTarga() + " assegnato alla colonnina " + colonnina.getId() + " per la ricarica.");
                return; //Esce dal ciclo dopo aver assegnato la colonnina al veicolo
            }
            if (colonnina.isDisponibile() && !colonnina.getConnettore().equals(veicolo.getConnettore())) {
                System.out.println("La colonnina " + colonnina.getId() + " è disponibile ma non compatibile con il veicolo " + veicolo.getTarga());
            }
            if (!colonnina.isDisponibile()) {
                System.out.println("Colonnina " + colonnina.getId() + " non disponibile");
            }
        }
    }

}
