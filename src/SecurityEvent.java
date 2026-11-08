public abstract class SecurityEvent { // Création d'une classe qui regroupe les infos communes de nos autres classes, elle est abstraite
    private int id;
    private String ipAddress;
    private String date;

    public SecurityEvent(int id, String ipAddress, String date){
        this.id = id;
        this.ipAddress = ipAddress;
        this.date = date;
    }

    public int getId(){
        return this.id;
    }

    public String getIpAddress(){
        return this.ipAddress;
    }

    public String getDate(){
        return this.date;
    }

    public abstract int getRiskScore();

    @Override
    public String toString() {
        return "Événement de sécurité [ID: " + this.id + " | IP: " + this.ipAddress + " | Date: " + this.date + "]";
    }
}
