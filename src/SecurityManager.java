import java.util.ArrayList;
import java.util.List;

public class SecurityManager {
    private List<SecurityEvent> journal;

    public SecurityManager (){
        this.journal = new ArrayList<>();
    }

    public void saveEvent(SecurityEvent event){
        journal.add(event);
    }

    public void searchEvent(){
        System.out.println("Voici la liste des évènements : ");
        for(SecurityEvent e : this.journal){
            System.out.println(e);
        }
    }
    // -----------------------------------
    // 1. Compter le nombre d'évènement dans le journal
    // -----------------------------------
    public int getTotalEvent(){
        int countEvent = this.journal.size(); // Méthode intégré dans ArrayList plutôt qu'une boucle for-each
        return countEvent;
    }
    // -----------------------------------
    // 2. Nombre d'échecs de connexion
    // -----------------------------------
    public int getTotalFailureLogin(){
        int countFail = 0;
        for(SecurityEvent e : this.journal){
            if(e instanceof LoginFailure){
            countFail++;
            }
        }
        return countFail;
    }
    // -----------------------------------
    // 3. Nombre d'activités suspectes
    // -----------------------------------
    public int getTotalSuspiciousActivity(){
        int countSuspicious = 0;
        for(SecurityEvent e : this.journal){
            if(e instanceof SuspiciousActivity){
            countSuspicious++;
            }
        }
       return countSuspicious;
    }
    // -----------------------------------
    // 4. Nombre d'évènements critiques
    // -----------------------------------
    public int getTotalCriticalEvent(){
        int countCritical = 0;
        for(SecurityEvent e : this.journal){
            int riskScore = e.getRiskScore();
            if(riskScore >= 7){
                countCritical++;
            }
        }
        return countCritical;
    }
    // -----------------------------------
    // 5. Score minimum et maximum
    // -----------------------------------
    public int[] getMaxMinScore(){
        SecurityEvent firstEvent = this.journal.get(0);
        int scoreMin = firstEvent.getRiskScore();
        int scoreMax = firstEvent.getRiskScore();
        int[] scores = new int[2];
        // On récupère le minimum et le maximum pour lequel il y a des valeurs
        for(SecurityEvent e : this.journal){
            int score = e.getRiskScore();
            if(score < scoreMin){
                scoreMin = score;
            }
            if(score > scoreMax){
                scoreMax = score;
            }
        }
        scores[0] = scoreMin;
        scores[1] = scoreMax;
        return scores;
    }
    // -----------------------------------
    // 6. Répartition des scores
    // -----------------------------------
    public int[] getRepartitionScore(){
        int[] repartition = new int[10];
        for(SecurityEvent e : this.journal){
            int score = e.getRiskScore();
            repartition[score]++;
        }
        return repartition;
    }
    // -----------------------------------
    // 7. Score le plus fréquent
    // -----------------------------------
    public int getScoreMoreFrequence(){
        int[] repartition = getRepartitionScore();
        int scoreMoreFrequence = 0;
        for(int i = 0; i < repartition.length; i++){
            if(repartition[scoreMoreFrequence] < repartition[i]){
                scoreMoreFrequence = i;
            }
        }
        return scoreMoreFrequence;
    }
}