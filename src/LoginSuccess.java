public class LoginSuccess extends SecurityEvent{
    private String username;
    private boolean outsideWorkingHours;
    private boolean isNewDevice;
    public LoginSuccess(String username, boolean outsideWorkingHours, boolean isNewDevice, int id, String ipAddress, String date){
        super(id, ipAddress, date);
        this.username = username;
        this.outsideWorkingHours = outsideWorkingHours;
        this.isNewDevice = isNewDevice;
    }

    @Override
    public int getRiskScore(){
        System.out.println("-> Exécution du calcul dans LoginSuccess");
        int score = 0;
        if(this.username.equals("admin")){
            score = score + 7;
        }
        if(this.outsideWorkingHours){// Horloge basé sur 24h
            score = score + 1;
        }
        if(this.isNewDevice){
            score = score + 1;
        }
        return score;
    }
}
