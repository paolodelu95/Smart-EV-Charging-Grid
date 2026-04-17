
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
            System.out.println("Che cosa vuoi fare? \n1: Parcheggiare un veicolo, \n2: Rimuovere un veicolo, \n3: Visualizzare veicoli parcheggiati, \n4: Uscire");
            System.out.println("  ".repeat(50));
            int scelta = input.nextInt();
            input.nextLine(); // Consuma il newline rimasto
            switch (scelta) {
                case 1 -> {
                    System.out.println("E' un veicolo elettrico? \n1: Sì \n2: No");
                    System.out.println("  ".repeat(50));
                    int isElettrico = input.nextInt();
                    input.nextLine(); // Consuma il newline rimasto
                    if (isElettrico == 1) {
                        System.out.println("Il veicolo è elettrico, vuoi ricaricarlo? \n1: Sì \n2: No");
                        System.out.println("  ".repeat(50));
                        int ricarica = input.nextInt();
                        input.nextLine(); // Consuma il newline rimasto
                        if (ricarica == 1) {
                            double capacitaBatteria = 50 + Math.random() * 100; // Capacità tra 50 e 150 kWh
                            double batteriaAttuale = Math.random() * 100; // Percentuale tra 0 e 100
                            double potenzaRicarica = 22 + Math.random() * 78; // Potenza tra 22 e 100 kW
                            String[] connettori = {"Type 2", "CCS", "CHAdeMO"};
                            String connettore = connettori[(int) (Math.random() * connettori.length)];

                        VeicoloElettrico v1 = new VeicoloElettrico.Builder("AA000AA", LocalDateTime.now(), garage.generateTicketNumber())
                                .conCapacitaBatteria(capacitaBatteria)
                                .conBatteriaAttuale(batteriaAttuale)
                                .conPotenzaRicarica(potenzaRicarica)
                                .conConnettore(connettore)
                                .build();

                            if (s1.assegnaColonnina(v1)) {
                                //VeicoloElettrico.iniziaRicarica(v1);
                                System.out.println("Ricarica in corso per il veicolo " + v1.getTarga() + " con capacità batteria di " + String.format("%.2f", v1.getCapacitaBatteria()) + " kWh, batteria attuale al " + String.format("%.2f", v1.getPercentualeBatteriaAttuale()) + "%, potenza di ricarica di " + String.format("%.2f", v1.getPotenzaRicarica()) + " kW e connettore " + v1.getConnettore() + ".");
                                System.out.println("Procediamo con il parcheggio del veicolo dopo la ricarica");
                            } else {
                                System.out.println("Non è stato possibile parcheggiare il veicolo " + v1.getTarga());
                            }
                        } else {
                            System.out.println("Procediamo con il parcheggio del veicolo senza ricarica.");
                            System.out.println("  ".repeat(50));
                        }
                    }
                    System.out.println("Inserisci la targa del veicolo da parcheggiare: ES: AA123BB");
                    String targa = input.nextLine();
                    String targaUpper = targa.toUpperCase();
                    Veicolo v1 = new Veicolo(targaUpper, LocalDateTime.now(), garage.generateTicketNumber());

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
                    System.out.println("Veicoli parcheggiati: \n" + garage.visualizzaPostiOccupati());

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
