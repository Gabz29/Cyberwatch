public class Main {
    public static void main(String[] args) {
        /*
        // =================================================
        // ======== Partie 1. Affichage des risques ========
        // =================================================
        // Titre du projet
        System.out.println("=== CYBERWATCH ===");
        // Taille de la liste
        System.out.println("Evènements analysés : " + args.length + "\n");
        // Répartition des risques
        System.out.println("Répartitions des risques :");
        int[] values = stringToInteger(args);
        int[] param = paramValues(values);
        int[] nbApparition = new int[param[1]-param[0]+1];
        for(int i = param[0]; i <= param[1]; i++){
            int countValues = 0;
            for(int j = 0; j < values.length; j++){
                if(values[j] == i){ // on compare à i et pas à values[i]
                    countValues++;
                }
            }
            nbApparition[i - param[0]] = countValues; // on décale de param[0] pour revenir àl'indice 0 dans nbApparition
        }
        for(int i = param[0]; i <= param[1]; i++){
            System.out.print(i + " ");
            for(int j = 0; j < nbApparition[i - param[0]]; j++){ // On peut aussi utiliser repeat pour mettre les étoiles
                System.out.print("*");
            }
            System.out.println();
        }
        // Risque min
        System.out.println("Risque minimum : " + param[0]);
        // Risque max
        System.out.println("Risque maximum : " + param[1]);
        // Risque la plus  fréquent
        int valuesFrequence = nbApparition[0];
        int index = 0;
        for(int i = 0; i < nbApparition.length; i++){
            if(valuesFrequence < nbApparition[i]){
                valuesFrequence = nbApparition[i];
                index = i;
            }
        }
        int indexValues = index + param[0];
        System.out.println("Risque le plus fréquent : " + indexValues);
        // Fréquence d'apparition de ce risque
        System.out.println("Fréquence de ce risque : " + nbApparition[index]);
        // Nombre d'évènements critiques
        int countCritical = 0;
        for(int i = 0; i< values.length; i++){
            if(values[i] >= 7){
                countCritical ++;
            }
        }
        System.out.println("Evènement critiques : " + countCritical);
        */
       /*
        // ===================================================
        // ======== PARTIE 2. Appelle à SecurityEvent ========
        // ===================================================
        SecurityEvent[] event = new SecurityEvent[10];
        event[0] = new LoginFailure(3, 101, "192.168.1.1", "2026-11-09");
        event[1] = new LoginSuccess("admin", true, false, 102, "10.0.0.5", "2026-11-09");
        event[2] = new SuspiciousActivity("Téléchargements massif", 4, 60000, 103, "192.168.0.5", "2026-11-09");
        int[] score = new int[3];
        for(int i = 0; i < 3; i++){
            score[i] = event[i].getRiskScore();
            System.out.println("Analyse de l'évènement n°" + i + ", son score est : " + score[i]);
        }
        */

        // ==================================================================
        // ======== PARTIE 3. Gestion des évènements et statistiques ========
        // ==================================================================
        SecurityManager manager = new SecurityManager();

        manager.saveEvent(new LoginFailure(2, 101, "192.168.1.45", "2026-11-11 08:14"));
        manager.saveEvent(new LoginFailure(8, 102, "192.168.1.99", "2026-11-11 11:32"));
        manager.saveEvent(new LoginFailure(15, 103, "10.0.0.5", "2026-11-11 14:05"));

        manager.saveEvent(new SuspiciousActivity("Scan de ports", 3, 4000, 201, "172.16.0.4", "2026-11-11 09:20"));
        manager.saveEvent(new SuspiciousActivity("Téléchargements massif", 4, 50000, 202, "10.0.0.12", "2026-11-11 15:45"));
        manager.saveEvent(new SuspiciousActivity("Modification de mot de passe", 5, 6548, 203, "192.168.1.150", "2026-11-11 16:10"));

        manager.saveEvent(new LoginSuccess("marie.curie", false, false, 301, "192.168.1.20", "2026-11-11 08:05"));

        manager.saveEvent(new LoginSuccess("admin", true, true, 302, "82.124.55.12", "2026-11-11 03:15"));

        manager.saveEvent(new LoginSuccess("paul.martin", false, true, 303, "192.168.1.88", "2026-11-11 10:45"));
        
        CyberWatchWindow myFrame = new CyberWatchWindow(manager);
    }
/*
    // Méthode pour transformer une liste de String en Integer
    public static int[] stringToInteger(String[] args){
        int[] values = new int[args.length];
        for(int i = 0; i < args.length; i++){
            values[i] = Integer.parseInt(args[i]);
        }
        return values;
    }

    // Méthode pour récupérer le min et le max d'une liste
    public static int[] paramValues(int[] values){
        int minValues = values[0];
        int maxValues = values[0];
        int [] param = new int[2];
        // On récupère le minimum et le maximum pour lequel il y a des valeurs
        for(int i = 0; i < values.length; i++){
            if(minValues > values[i]){
                minValues = values[i];
            }
            if(maxValues < values[i]){
                maxValues = values[i];
            }
        }
        // On conserve les paramètres dans une liste
        param[0] = minValues;
        param[1] = maxValues;

        return param;
    }
        */
}