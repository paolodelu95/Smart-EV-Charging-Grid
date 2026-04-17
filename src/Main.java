
import java.time.LocalDateTime;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        Garage garage = new Garage("Garage Piazza San Carlo", 520, 1.79, 3.50);
        StazioneRicarica.creaColonnine(); // Crea le colonnine della stazione di ricarica
        StazioneRicarica s1 = new StazioneRicarica("s1", "Stazione Torino via Roma", 45.0055, 7.5381, StazioneRicarica.getColonnine()); // Crea la stazione di ricarica con le colonnine create
        boolean acceso = true;
        while (acceso == true) {
            System.out.println("__".repeat(50));
            System.out.println("__".repeat(50));
            System.out.println("  ".repeat(50));
            System.out.println("Benvenuto al " + garage.getNome());
            System.out.println("Tariffa oraria: " + garage.getTariffaOraria() + "€" + " | Tariffa weekend: " + garage.getTariffaWeekend() + "€");
            System.out.println("Il garage ha " + garage.getPostiDisponibili() + " posti disponibili.");
            System.out.println("__".repeat(50));
            System.out.println("__".repeat(50));
            System.out.println("  ".repeat(50));
            System.out.println("Che cosa vuoi fare? \n1: Parcheggiare un veicolo, \n2: Rimuovere un veicolo, \n3: Visualizzare posti disponibili, \n4: Uscire");
            System.out.println("  ".repeat(50));
            int scelta = input.nextInt();
            input.nextLine(); // Consuma il newline rimasto
            switch (scelta) {
                case 1 -> {
                    System.out.println("E' un veicolo elettrico? \n1: Sì, \n2: No");
                    System.out.println("  ".repeat(50));
                    int isElettrico = input.nextInt();
                    input.nextLine(); // Consuma il newline rimasto
                    if (isElettrico == 1) {
                        VeicoloElettrico v1 = new VeicoloElettrico.Builder("AA000AA", LocalDateTime.now(), garage.generateTicketNumber())
                                .conCapacitaBatteria(75)
                                .conBatteriaAttuale(6)
                                .conPotenzaRicarica(50)
                                .conConnettore("ChademO")
                                .build();
                        System.out.println("Il veicolo è elettrico, vuoi ricaricarlo? \n1: Sì \n2: No");
                        System.out.println("  ".repeat(50));
                        int ricarica = input.nextInt();
                        input.nextLine(); // Consuma il newline rimasto
                        if (ricarica == 1) {
                            System.out.println("Seleziona il tipo di connettore del tuo veicolo: \n1: Type 2 \n2: CCS \n3: CHAdeMO");
                            int connettore = input.nextInt();
                            input.nextLine(); // Consuma il newline rimasto
                            switch (connettore) {
                                case 1 ->
                                    v1.setTipoConnettore("Type 2");
                                case 2 ->
                                    v1.setTipoConnettore("CCS");
                                case 3 ->
                                    v1.setTipoConnettore("CHAdeMO");
                                default ->
                                    throw new IllegalStateException("Scelta non valida. Seleziona un'opzione tra 1 e 3.");
                            }

                            System.out.println("  ".repeat(50));
                            System.out.println("Scelta del livello di potenza di ricarica: \n1: 22 kW \n2: 50 kW \n3: 100 kW");
                            int potenza = input.nextInt();
                            input.nextLine(); // Consuma il newline rimasto
                            switch (potenza) {
                                case 1 ->
                                    v1.setPotenzaRicarica(22);
                                case 2 ->
                                    v1.setPotenzaRicarica(50);
                                case 3 ->
                                    v1.setPotenzaRicarica(100);
                                default ->
                                    throw new IllegalStateException("Scelta non valida. Seleziona un'opzione tra 1 e 3.");
                            }
                            if (s1.assegnaColonnina(v1)) {
                                System.out.println("Il ticket del tuo veicolo è: " + Veicolo.getTicketNumber());
                            } else {
                                System.out.println("Non è stato possibile parcheggiare il veicolo " + v1.getTarga());
                            }
                        } else {
                            System.out.println("Procediamo con il parcheggio del veicolo senza ricarica.");
                            System.out.println("  ".repeat(50));
                        }
                    }
                    Veicolo v1 = new Veicolo("AA000AA", LocalDateTime.now(), garage.generateTicketNumber());

                    System.out.println("Inserisci la targa del veicolo da parcheggiare: ES: AA123BB");
                    String targa = input.nextLine();
                    String targaUpper = targa.toUpperCase();
                    if (garage.parcheggia(v1)) {
                        System.out.println("Veicolo " + targaUpper + " parcheggiato con successo.");
                        System.out.println("Il ticket del tuo veicolo è: " + Veicolo.getTicketNumber());
                    } else {
                        System.out.println("Non è stato possibile parcheggiare il veicolo " + targaUpper + ".");
                    }
                }
                case 2 -> {
                    System.out.println("Inserisci il ticket:");
                    int ticket = input.nextInt();
                    input.nextLine(); // Consuma il newline rimasto
                    String targaCorrispondente = garage.getTarga(ticket);
                    double tariffa = garage.rimuovi(ticket);
                    double arrotondato = Math.round(tariffa * 100.0) / 100.0;
                    if (tariffa > 0) {
                        System.out.println("Veicolo " + targaCorrispondente + " con ticket n. " + ticket + " rimosso con successo. Tariffa da pagare: " + arrotondato + "€");
                    } else {
                        System.out.println("Non è stato possibile trovare il veicolo con ticket " + ticket + " per la rimozione.");
                    }
                }
                case 3 ->
                    System.out.println("Posti disponibili: " + garage.getPostiDisponibili());

                case 4 -> {
                    System.out.println("Grazie per aver utilizzato il " + garage.getNome() + ". Arrivederci!");
                    acceso = false;
                }

                default ->
                    System.out.println("Scelta non valida. Per favore, seleziona un'opzione tra 1 e 4.");

            }
        }
    }
}
