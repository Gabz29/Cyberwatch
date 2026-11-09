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
}