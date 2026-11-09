public class LoginFailure extends SecurityEvent{
    private int nbFailure;
    public LoginFailure(int nbFailure, int id, String ipAddress, String date){
        super(id, ipAddress, date);
        this.nbFailure = nbFailure;
    }

    @Override
    public int getRiskScore(){ // Score de 0 à 9
        System.out.println("-> Exécution du calcul dans LoginFailure");
        int f = this.nbFailure;
        int score = 0;
        if(f > 2){
            score = score + 7;
        }
        if(f > 4){
            score = score + 1;
        }
        else{
            score = score + 1;
        }
        return score;
    }

    @Override
    public String toString() {
        return super.toString() + " | Échecs de connexion : " + this.nbFailure;
    }
}