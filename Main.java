
public class Main {

    public static void main(String[] args) {
        // Creazione colonnine della stazione di ricarica
        Colonnina c0 = new Colonnina("C0", "Type 2", 22, false); // Colonnina con connettore Type2 e potenza massima 22 Kw
        Colonnina c1 = new Colonnina("C1", "Type 2", 22, true); // Colonnina con connettore Type2 e potenza massima 22 Kw
        Colonnina c2 = new Colonnina("C2", "CCS", 50, true); // Colonnina con connettore CCS e potenza massima 50 Kw (non disponibile)
        Colonnina c3 = new Colonnina("C3", "CHAdeMO", 100, true); // Colonnina con connettore CHAdeMO e potenza massima 100 Kw
        Colonnina c4 = new Colonnina("C4", "Type 2", 22, true); // Colonnina con connettore Type2 e potenza massima 22 Kw    }
        Colonnina c5 = new Colonnina("C5", "CCS", 100, true); // Colonnina con connettore CCS e potenza massima 50 Kw (non disponibile)

        // Creazione stazione di ricarica con le colonnine
        Colonnina[] colonnina = {c0,c1, c2, c3, c4, c5};
        StazioneRicarica s1 = new StazioneRicarica("s1", "Stazione Torino via Roma", 45.0055, 7.5381, colonnina);

        // Creazione veicoli elettrici
        VeicoloElettrico v1 = new VeicoloElettrico.Builder("TE555LA", null, 0)
                .conCapacitaBatteria(75)
                .conBatteriaAttuale(6)
                .conPotenzaRicarica(50)
                .conConnettore("ChademO")
                .build();

        VeicoloElettrico v2 = new VeicoloElettrico.Builder("ZO000EE", null, 0)
                .conCapacitaBatteria(40)
                .conBatteriaAttuale(22)
                .conPotenzaRicarica(18)
                .conConnettore("Type 2")
                .build();
        VeicoloElettrico v3 = new VeicoloElettrico.Builder("JE333PP", null, 0)
                .conCapacitaBatteria(120)
                .conBatteriaAttuale(55)
                .conPotenzaRicarica(100)
                .conConnettore("ccs")
                .build();

        VeicoloElettrico v4 = new VeicoloElettrico.Builder("FI500AT", null, 0)
                .conCapacitaBatteria(60)
                .conBatteriaAttuale(16)
                .conPotenzaRicarica(30)
                .conConnettore("CHAdeMO")
                .build();

        // Simulazione ricarica dei veicoli
        try {
            s1.assegnaColonnina(v1); // Assegna una colonnina al veicolo v1
            s1.assegnaColonnina(v2); // Assegna una colonnina al veicolo v2
            s1.assegnaColonnina(v3); // Assegna una colonnina al veicolo v3
            s1.assegnaColonnina(v4); // Assegna una colonnina al veicolo v4
        } catch (IllegalStateException e) {
            System.out.println("Errore durante l'assegnazione della colonnina: " + e.getMessage());
        }

    }
}
