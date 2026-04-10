
public class Main {

    public static void main(String[] args) {
        // Creazione colonnine della stazione di ricarica
        Colonnina c1 = new Colonnina("C1", "Type 2", 22, true); // Colonnina con connettore Type2 e potenza massima 22 Kw
        Colonnina c2 = new Colonnina("C2", "CCS", 50, false); // Colonnina con connettore CCS e potenza massima 50 Kw (non disponibile)
        Colonnina c3 = new Colonnina("C3", "CHAdeMO", 100, true); // Colonnina con connettore CHAdeMO e potenza massima 100 Kw
        Colonnina c4 = new Colonnina("C4", "Type 2", 22, true); // Colonnina con connettore Type2 e potenza massima 22 Kw    }

        // Creazione stazione di ricarica con le colonnine
        Colonnina[] colonnina = {c1, c2, c3, c4};
        StazioneRicarica s1 = new StazioneRicarica("s1", "Stazione Orbassano via Roma", 45.0055, 7.5381, colonnina);

        // Creazione veicoli elettrici
        VeicoloElettrico v1 = new VeicoloElettrico.Builder("TE555LA")
                .conCapacitaBatteria(75)
                .conBatteriaAttuale(6)
                .conPotenzaRicarica(50)
                .conConnettore("CHAdeMO")
                .build();

        VeicoloElettrico v2 = new VeicoloElettrico.Builder("ZO000EE")
                .conCapacitaBatteria(40)
                .conBatteriaAttuale(22)
                .conPotenzaRicarica(18)
                .conConnettore("Type 2")
                .build();
        VeicoloElettrico v3 = new VeicoloElettrico.Builder("JE333PP")
                .conCapacitaBatteria(120)
                .conBatteriaAttuale(55)
                .conPotenzaRicarica(100)
                .conConnettore("CCS")
                .build();

        // Simulazione ricarica dei veicoli
        try {
            s1.assegnaColonnina(v1); // Assegna una colonnina al veicolo v1
            s1.assegnaColonnina(v2); // Assegna una colonnina al veicolo v2
            s1.assegnaColonnina(v3); // Assegna una colonnina al veicolo v3
        } catch (IllegalStateException e) {
            System.out.println("Errore durante l'assegnazione della colonnina: " + e.getMessage());
        }

    }
}
