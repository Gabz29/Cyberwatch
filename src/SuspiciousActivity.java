public class SuspiciousActivity extends SecurityEvent{
    private String activityType;
    private int severityLevel;
    private int dataVolumeMo;
    public SuspiciousActivity (String activityType, int severityLevel, int dataVolumeMo, int id, String ipAddress, String date){
        super(id, ipAddress, date);
        this.activityType = activityType;
        this.severityLevel = severityLevel;
        this.dataVolumeMo = dataVolumeMo;
    }

    @Override 
    public int getRiskScore(){
        System.out.println("-> Exécution du calcul dans SuspiciousActivity");
        int score = 0;
        if(activityType.equals("Scan de ports") || activityType.equals("Téléchargements massif") || activityType.equals("Modification de mot de passe")){
            score = score + 7;
        }
        if(severityLevel > 3){
            score = score + 1;
        }
        if(dataVolumeMo > 50000){
            score = score + 1;
        }
        return score;
    }
}
